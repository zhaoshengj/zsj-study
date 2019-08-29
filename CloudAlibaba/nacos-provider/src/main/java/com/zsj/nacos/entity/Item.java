package com.zsj.nacos.entity;

import lombok.Data;
import java.math.BigDecimal;

/**
 * @author zsj
 * @date 2019-06-20  19:35
 */
@Data
//@Document( indexName = "zsj",type = "item",shards = 1,replicas = 0)
public class Item {

    //@Field(type = FieldType.Text)
    private String id;

    //@Field(type = FieldType.Text,index = true,searchAnalyzer = "ik_smart")
    private String name;

    //@Field(type = FieldType.Integer)
    private int age;

    //@Field(type = FieldType.Integer)
    private BigDecimal money;

    public Item(String id, String name, int age, BigDecimal money) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.money = money;
    }
}
