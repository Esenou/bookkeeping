package com.bookkeeping.kg.dao;

import com.bookkeeping.kg.model.ReportsDto;
import com.bookkeeping.kg.model.SalaryDto;
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

    public List<ReportsDto> getReportsByBetweenDate(String dateFrom, String dateTo) {
        List<ReportsDto> arrayList = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        String sql;
        try{
            connection = this.dataSource.getConnection();
            stmt = connection.createStatement();
            //

            sql = String.format("select\n" +
                    "       (select product_name from tbl_product_name where id = id_product_name),\n" +
                    "       (select product_type from tbl_product_type where id = id_product_type),\n" +
                    "       SUM(p.count_products) \"product\",\n" +
                    "       SUM(p.count_brak) \"brak workers\",\n" +
                    "       SUM(p.count_stanok) \"brak stanok\",\n" +
                    "       SUM(p.count_saya) \"brak say\",\n" +
                    "       (SUM(p.count_products)+SUM(p.count_brak)+SUM(p.count_stanok)+SUM(p.count_saya)) \"ALL\"\n" +
                    "from tbl_product p where p.create_date BETWEEN '%s' AND '%s' " +
                    "group by id_product_name , id_product_type\n" +
                    "union all\n" +
                    "select '',\n" +
                    "    'ИТОГО:',\n" +
                    "    SUM(p.count_products) \"product\",\n" +
                    "    SUM(p.count_brak) \"brak workers\",\n" +
                    "    SUM(p.count_stanok) \"brak stanok\",\n" +
                    "    SUM(p.count_saya) \"brak say\",\n" +
                    "    (SUM(p.count_products)+SUM(p.count_brak)+SUM(p.count_stanok)+SUM(p.count_saya)) \"ALL\"\n" +
                    "from tbl_product p where p.create_date BETWEEN '%s' AND '%s' ", dateFrom, dateTo,dateFrom, dateTo);
            ResultSet resultSet = stmt.executeQuery(sql);

            if(resultSet.next()){
                do {
                    ReportsDto model = new ReportsDto ();
                    model.setProductName(resultSet.getString(1));
                    model.setProductType(resultSet.getString(2));
                    model.setProduct(resultSet.getDouble(3));
                    model.setBrakWorkers(resultSet.getDouble(4));
                    model.setBrakStanok(resultSet.getDouble(5));
                    model.setBrakSay(resultSet.getDouble(6));
                    model.setAll(resultSet.getDouble(7));
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

    public List<SalaryDto> getSalaryByBetweenDate(String dateFrom, String dateTo) {

        List<SalaryDto> arrayList = new ArrayList<>();
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
                    "    p.count_products*(select product_price from tbl_product_name where id = p.id_product_name) \"made_product_currency\",\n" +
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
                    SalaryDto model = new SalaryDto ();
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
}
