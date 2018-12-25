package com.yipinketang.app.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    public enum OrderType {
        CONSUMER, BUSINESS
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private Integer number;
    private OrderType orderType;
    private String orderName;
    private String creator;
    private Date createDate = new Date();
    private Date modifyDate = new Date();

}
