package com.bookkeeping.kg.entity;

import com.bookkeeping.kg.model.ReportsDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_product")

@SqlResultSetMapping(
        name = "ReportResult",
        classes = @ConstructorResult(
                targetClass = ReportsDto.class,
                columns = {
                        @ColumnResult(name = "product_name",type = String.class),
                        @ColumnResult(name = "product_type",type = String.class),
                        @ColumnResult(name = "product",type = Double.class),
                        @ColumnResult(name = "brak workers",type = Double.class),
                        @ColumnResult(name = "brak stanok",type = Double.class),
                        @ColumnResult(name = "brak say",type = Double.class),
                        @ColumnResult(name = "ALL",type = Double.class)
                }))

@NamedNativeQuery(name = "getReport",
        query = "select\n" +
                "       (select product_name from tbl_product_name where id = id_product_name),\n" +
                "       (select product_type from tbl_product_type where id = id_product_type),\n" +
                "       SUM(count_products) \"product\",\n" +
                "       SUM(count_brak) \"brak workers\",\n" +
                "       SUM(count_stanok) \"brak stanok\",\n" +
                "       SUM(count_saya) \"brak say\",\n" +
                "       (SUM(count_products)+SUM(count_brak)+SUM(count_stanok)+SUM(count_saya)) \"ALL\"\n" +
                "from tbl_product\n" +
                "group by id_product_name , id_product_type\n" +
                "union all\n" +
                "select\n" +
                "    '',\n" +
                "    'ИТОГО:',\n" +
                "    SUM(count_products) \"product\",\n" +
                "    SUM(count_brak) \"brak workers\",\n" +
                "    SUM(count_stanok) \"brak stanok\",\n" +
                "    SUM(count_saya) \"brak say\",\n" +
                "    (SUM(count_products)+SUM(count_brak)+SUM(count_stanok)+SUM(count_saya)) \"ALL\"\n" +
                "from tbl_product;", resultSetMapping = "ReportResult")


public class Product extends BaseEntity {

    @Column(name = "count_products")
    private Double countProducts;

    @Column(name = "count_stanok")
    private Double countStanok;

    @Column(name = "count_brak")
    private Double countBrak;

    @Column(name = "count_saya")
    private Double countSaya;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_product_name")
    private ProductName productName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_product_type")
    private ProductType productType;

}
