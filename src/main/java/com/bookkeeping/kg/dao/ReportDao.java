package com.bookkeeping.kg.dao;

import com.bookkeeping.kg.model.*;
import com.bookkeeping.kg.model.salary.EmpInfo;
import com.bookkeeping.kg.model.salary.ProductInfo;
import com.bookkeeping.kg.model.salary.ReportSalaryInfo;
import com.bookkeeping.kg.model.salary.SalaryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReportDao {
    private final DataSource dataSource;

    @Autowired
    public ReportDao(ApplicationContext applicationContext, DataSource dataSource) {
        this.dataSource = dataSource;
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        if (autowireCapableBeanFactory.containsBean("jdbcDataSource")) {
            dataSource = (DataSource) autowireCapableBeanFactory.getBean("jdbcDataSource");
        } else {
            dataSource = (DataSource) autowireCapableBeanFactory.getBean("dataSource");
        }
    }

    public ReportsDto getReportsByBetweenDate(String dateFrom, String dateTo) {
        List<ProductDto> productList = new ArrayList<>();
        List<WorkerDto> workerList = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        String sql;
        ResultSet resultSet;
        try{
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();
            sql = String.format("select   (select product_name from tbl_product_name where id = id_product_name),\n" +
                    "                 (select product_type from tbl_product_type where id = id_product_type),\n" +
                    "                  SUM(p.packaging) \"packaging\",\n" +
                    "                  SUM(p.count_products) \"product\",\n" +
                    "                  SUM(p.in_bags) \"in bags\",\n" +
                    "                  SUM(p.count_brak) \"brak workers\",\n" +
                    "                  SUM(p.count_stanok) \"brak stanok\",\n" +
                    "                  SUM(p.count_saya) \"brak say\"\n" +
                    "        from tbl_product p where p.create_date_product BETWEEN '%s' AND '%s'\n" +
                    "            group by id_product_name , id_product_type\n" +
                    "            union all\n" +
                    "        select '',\n" +
                    "               'ИТОГО:',\n" +
                    "                SUM(p.packaging) \"packaging\",\n" +
                    "                SUM(p.count_products) \"product\",\n" +
                    "                SUM(p.in_bags) \"in bags\",\n" +
                    "                SUM(p.count_brak) \"brak workers\",\n" +
                    "                SUM(p.count_stanok) \"brak stanok\",\n" +
                    "                SUM(p.count_saya) \"brak say\"\n" +
                    "      from tbl_product p where p.create_date_product BETWEEN '%s' AND '%s' ", dateFrom, dateTo,dateFrom, dateTo);
            resultSet = stmt.executeQuery(sql);

            if(resultSet.next()){
                do {
                    ProductDto model = new ProductDto ();
                    model.setProductName(resultSet.getString(1));
                    model.setProductType(resultSet.getString(2));
                    model.setPackaging(resultSet.getDouble(3));
                    model.setProduct(resultSet.getDouble(4));
                    model.setInBags(resultSet.getDouble(5));
                    model.setBrakWorkers(resultSet.getDouble(6));
                    model.setBrakStanok(resultSet.getDouble(7));
                    model.setBrakSay(resultSet.getDouble(8));
                    productList.add(model);
                } while (resultSet.next());
            } else {
                System.out.println("result set is empty");
            }
            resultSet.close();

            sql = String.format("select emp.surname, emp.name from tbl_product prod inner join tbl_product_employees prod_emp\n" +
                    "    on prod.id = prod_emp.products_id\n" +
                    "    inner join tbl_employee  emp\n" +
                    "        on emp.id = prod_emp.employees_id\n" +
                    "where prod.create_date_product BETWEEN '%s' AND '%s'\n" +
                    "group by emp.name, emp.surname", dateFrom, dateTo);


            resultSet = stmt.executeQuery(sql);
            if(resultSet.next()){
                do {
                    WorkerDto model = new WorkerDto ();
                    model.setSurname(resultSet.getString(1));
                    model.setName(resultSet.getString(2));
                    workerList.add(model);
                } while (resultSet.next());
            } else {
                System.out.println("result set is empty");
            }
            resultSet.close();

        } catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    connection.close();
            } catch (SQLException se) {
                System.out.println(se);
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        if(productList.size()==1){
            productList.remove(0);
        }
        ReportsDto reportsDto = new ReportsDto();
        reportsDto.setProductDtoList(productList);
        reportsDto.setWorkerDtoList(workerList);

        return reportsDto;
    }

    public List<ProductInfo> getDetailSalaryReport(String dateFrom, String dateTo) {

        List<ProductInfo> productInfoList = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        String sql;
        try{
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();

            sql = String.format("select p.id,\n" +
                    "                           (select product_name from tbl_product_name where id = id_product_name),\n" +
                    "                           (select product_type from tbl_product_type where id = id_product_type),\n" +
                    "                           SUM(p.packaging) \"packaging\",\n" +
                    "                           SUM(p.count_products) \"product\",\n" +
                    "                           SUM(p.in_bags) \"in bags\",\n" +
                    "                           SUM(p.count_brak) \"brak workers\",\n" +
                    "                           SUM(p.count_stanok) \"brak stanok\",\n" +
                    "                           SUM(p.count_saya) \"brak say\",\n" +
                    "                           SUM(p.count_products) * (select product_price from tbl_product_name  prod where prod.id = p.id_product_name) \"made_product_currency\",\n" +
                    "                           SUM(p.count_brak) * (select prod.brak_price from tbl_product_name  prod where prod.id = p.id_product_name) \"made_brak_currency\",\n" +
                    "                           SUM(p.count_products) * (select product_price from tbl_product_name  prod where prod.id = p.id_product_name) - SUM(p.count_brak) * (select prod.brak_price from tbl_product_name  prod where prod.id = p.id_product_name) \"made_workers_currency\",\n" +
                    "                           p.create_date_product\n" +
                    "                       from tbl_product p where p.create_date_product BETWEEN '%s' AND '%s'\n" +
                    "                       group by id_product_name , id_product_type, p.id\n" +
                    "                       ORDER BY p.id;", dateFrom, dateTo);


            ResultSet resultSet = stmt.executeQuery(sql);
            if(resultSet.next()){
                do {
                    ProductInfo model = new ProductInfo ();
                    model.setId(resultSet.getLong(1));
                    model.setProductName(resultSet.getString(2));
                    model.setProductType(resultSet.getString(3));
                    model.setPackaging(resultSet.getFloat(4));
                    model.setProduct(resultSet.getFloat(5));
                    model.setInBags(resultSet.getFloat(6));
                    model.setBrakWorkers(resultSet.getFloat(7));
                    model.setBrakStanok(resultSet.getFloat(8));
                    model.setBrakSay(resultSet.getFloat(9));
                    model.setMadeProductCurrency(resultSet.getFloat(10));
                    model.setMadeBrakCurrency(resultSet.getFloat(11));
                    model.setMadeWorkersCurrency(resultSet.getFloat(12));
                    model.setDate(resultSet.getDate(13));

                    productInfoList.add(model);

                } while (resultSet.next());
            } else {
                System.out.println("result set is empty");
            }
            resultSet.close();
        } catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    connection.close();
            } catch (SQLException se) {
                System.out.println(se);
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return productInfoList;
    }

    public ReportSalaryInfo getReportSalary(String dateFrom, String dateTo) {

        List<EmpInfo> empInfoList = new ArrayList<>();
        List<ProductInfo> productInfoList = new ArrayList<>();
        List<SalaryInfo> salaryInfoList = new ArrayList<>();

        Connection connection = null;
        Statement stmt = null;
        String sql;
        try{
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();

            /*sql = String.format("SELECT * FROM  getsalary('%s','%s');", dateFrom, dateTo);


            ResultSet resultSet = resultSet = stmt.executeQuery(sql);
            if(resultSet.next()){
                do {
                    SalaryInfo model = new SalaryInfo ();
                    model.setSalary(resultSet.getFloat(1));
                    model.setDate(resultSet.getDate(2));
                    salaryInfoList.add(model);
                } while (resultSet.next());
            } else {
                System.out.println("result set is empty");
            }
            resultSet.close();*/

            sql = String.format("SELECT * FROM  getsalary('%s','%s');", dateFrom, dateTo);
            ResultSet resultSet = stmt.executeQuery(sql);

            if(resultSet.next()){
                do {
                    EmpInfo model = new EmpInfo();
                    model.setSurname(resultSet.getString(1));
                    model.setName(resultSet.getString(2));
                    model.setSalary(resultSet.getFloat(3));
                    model.setCreateDate(resultSet.getDate(4));
                    empInfoList.add(model);
                } while (resultSet.next());
            } else {
                System.out.println("result set is empty");
            }
            resultSet.close();

            sql = String.format("select p.id,\n" +
                            "                           (select product_name from tbl_product_name where id = id_product_name),\n" +
                            "                           (select product_type from tbl_product_type where id = id_product_type),\n" +
                            "                           SUM(p.packaging) \"packaging\",\n" +
                            "                           SUM(p.count_products) \"product\",\n" +
                            "                           SUM(p.in_bags) \"in bags\",\n" +
                            "                           SUM(p.count_brak) \"brak workers\",\n" +
                            "                           SUM(p.count_stanok) \"brak stanok\",\n" +
                            "                           SUM(p.count_saya) \"brak say\",\n" +
                            "                           SUM(p.count_products) * (select product_price from tbl_product_name  prod where prod.id = p.id_product_name) \"made_product_currency\",\n" +
                            "                           SUM(p.count_brak) * (select prod.brak_price from tbl_product_name  prod where prod.id = p.id_product_name) \"made_brak_currency\",\n" +
                            "                           SUM(p.count_products) * (select product_price from tbl_product_name  prod where prod.id = p.id_product_name) - SUM(p.count_brak) * (select prod.brak_price from tbl_product_name  prod where prod.id = p.id_product_name) \"made_workers_currency\",\n" +
                            "                           p.create_date_product\n" +
                            "                       from tbl_product p where p.create_date_product BETWEEN '%s' AND '%s'\n" +
                            "                       group by id_product_name , id_product_type, p.id\n" +
                            "                       ORDER BY p.id;", dateFrom, dateTo);


            resultSet = stmt.executeQuery(sql);
            if(resultSet.next()){
                do {
                    ProductInfo model = new ProductInfo ();
                    model.setId(resultSet.getLong(1));
                    model.setProductName(resultSet.getString(2));
                    model.setProductType(resultSet.getString(3));
                    model.setPackaging(resultSet.getFloat(4));
                    model.setProduct(resultSet.getFloat(5));
                    model.setInBags(resultSet.getFloat(6));
                    model.setBrakWorkers(resultSet.getFloat(7));
                    model.setBrakStanok(resultSet.getFloat(8));
                    model.setBrakSay(resultSet.getFloat(9));
                    model.setMadeProductCurrency(resultSet.getFloat(10));
                    model.setMadeBrakCurrency(resultSet.getFloat(11));
                    model.setMadeWorkersCurrency(resultSet.getFloat(12));
                    model.setDate(resultSet.getDate(13));

                    productInfoList.add(model);

                } while (resultSet.next());
            } else {
                System.out.println("result set is empty");
            }
            resultSet.close();




        } catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    connection.close();
            } catch (SQLException se) {
                System.out.println(se);
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        ReportSalaryInfo reportSalaryInfo = new ReportSalaryInfo();
        reportSalaryInfo.setEmpInfoList(empInfoList);
        reportSalaryInfo.setProductInfoList(productInfoList);
        //reportSalaryInfo.setSalaryInfo(salaryInfo);
        return reportSalaryInfo;
    }

}
