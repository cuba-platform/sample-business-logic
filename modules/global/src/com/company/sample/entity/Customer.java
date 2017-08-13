package com.company.sample.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@Table(name = "SAMPLE_CUSTOMER")
@Entity(name = "sample$Customer")
public class Customer extends StandardEntity {
    private static final long serialVersionUID = -942735474105405408L;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "DISCOUNT")
    protected BigDecimal discount;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }


}