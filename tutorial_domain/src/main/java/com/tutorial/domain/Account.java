package com.tutorial.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Jimmy. 2018/3/26  15:11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table
@Entity(name="account")
public class Account implements Serializable {
    private static final long serialVersionUID = -5055385164319127586L;

    @Id
    @GeneratedValue
    private Long id;

    @Size(min=6,max=10,message = "名字长度6-10位")
    private String userName;

    //@Size(min=6,max=10)
    private String password;

    private String email;
}
