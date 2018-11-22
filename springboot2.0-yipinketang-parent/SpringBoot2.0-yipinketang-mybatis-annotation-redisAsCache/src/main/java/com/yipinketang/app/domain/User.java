package com.yipinketang.app.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.yipinketang.app.jsonView.UserView;

import java.io.Serializable;
import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)//值为null的字段不返回
public class User implements Serializable {
    private static final long serialVersionUID = -12123123123123123L;
    public enum OrderType {
        CONSUMER, BUSINESS
    }

    private Long id;
    private Integer number;
    private OrderType orderType;
    private String orderName;
    private String creator;
    private Date createDate = new Date();
    private Date modifyDate = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonView(UserView.class)//控制返回字段
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

}
