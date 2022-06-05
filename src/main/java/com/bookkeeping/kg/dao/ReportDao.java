package com.bookkeeping.kg.dao;

import com.bookkeeping.kg.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public List<SalaryDetailInfoDto> getDetailSalaryReport(String dateFrom, String dateTo) {

        List<SalaryDetailInfoDto> arrayList = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        String sql;
        try{
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();

            sql = String.format("select\n" +
                    "    p.id \"product id\",\n" +
                    "    (select product_name from tbl_product_name where id = p.id_product_name),\n" +
                    "    (select product_type from tbl_product_type where id = p.id_product_type),\n" +
                    "       e.id  \"id_workers\",\n" +
                    "    e.name \"name_workers\",\n" +
                    "    p.count_products / (select count(products_id) from tbl_product_employees where products_id = p.id group by products_id) \"product\",\n" +
                    "    p.count_brak / (select count(products_id) from tbl_product_employees where products_id = p.id group by products_id) \"brak_workers\",\n" +
                    "    (p.count_products / (select count(products_id) from tbl_product_employees where products_id = p.id group by products_id) ) * (select product_price from tbl_product_name where id = p.id_product_name) \"made_product_currency\",\n" +
                    "    (p.count_brak*(select brak_price from tbl_product_name where id = p.id_product_name)) /  (select count(products_id) from tbl_product_employees where products_id = p.id group by products_id) \"made_brak_currency\",\n" +
                    "    (p.count_products*(select product_price from tbl_product_name where id = p.id_product_name)) /  (select count(products_id) from tbl_product_employees where products_id = p.id group by products_id) -\n" +
                    "    (p.count_brak*(select brak_price from tbl_product_name where id = p.id_product_name)) /  (select count(products_id) from tbl_product_employees where products_id = p.id group by products_id) \"made_workers_currency\",\n" +
                    "    p.create_date\n"+
                    "from tbl_product_employees a, tbl_employee e,tbl_product p\n" +
                    "\n" +
                    "where a.employees_id=e.id and a.products_id = p.id\n" +
                    "  and\n" +
                    "    p.create_date BETWEEN '%s' AND '%s' group by p.id, p.id_product_name , p.id_product_type, e.id order by 1 desc;\n", dateFrom, dateTo);
            ResultSet resultSet = stmt.executeQuery(sql);

            if(resultSet.next()){
                do {
                    SalaryDetailInfoDto model = new SalaryDetailInfoDto();
                    model.setProductId(resultSet.getString(1));
                    model.setProductName(resultSet.getString(2));
                    model.setProductType(resultSet.getString(3));
                    model.setWorkerId(resultSet.getString(4));
                    model.setWorkerName(resultSet.getString(5));
                    model.setProduct(resultSet.getDouble(6));
                    model.setWorkerBrak(resultSet.getDouble(7));
                    model.setProductMadeCurrency(resultSet.getDouble(8));
                    model.setBrakMadeCurrency(resultSet.getDouble(9));
                    model.setWorkerMadeCurrency(resultSet.getDouble(10));
                    model.setCreateDate(resultSet.getDate(11));
                    arrayList.add(model);
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
        return arrayList;
    }

    public List<SalaryInfoDto> getReportSalary(String dateFrom, String dateTo) {

        List<SalaryInfoDto> arrayList = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        String sql;
        try{
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();

            sql = String.format("SELECT * FROM getsalaryinfo('%s','%s');", dateFrom, dateTo);
            ResultSet resultSet = stmt.executeQuery(sql);

            if(resultSet.next()){
                do {
                    SalaryInfoDto model = new SalaryInfoDto();
                    model.setWorkerId(resultSet.getString(1));
                    model.setWorkerName(resultSet.getString(2));
                    model.setProduct(resultSet.getDouble(3));
                    model.setWorkerBrak(resultSet.getDouble(4));
                    model.setProductMadeCurrency(resultSet.getDouble(5));
                    model.setBrakMadeCurrency(resultSet.getDouble(6));
                    model.setWorkerMadeCurrency(resultSet.getDouble(7));
                    arrayList.add(model);
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
        return arrayList;
    }

    public SalaryDto salaryDto(String dateFrom, String dateTo){

        List<SalaryInfoDto> salaryInfoDtoList = getReportSalary(dateFrom,dateTo);
        List<SalaryDetailInfoDto> salaryDetailInfoDtoList = getDetailSalaryReport(dateFrom,dateTo);

        SalaryDto salaryDto = new SalaryDto();
        salaryDto.setSalaryInfoDtoList(salaryInfoDtoList);
        salaryDto.setSalaryDetailInfoDtoList(salaryDetailInfoDtoList);

        return salaryDto;
    }

}
