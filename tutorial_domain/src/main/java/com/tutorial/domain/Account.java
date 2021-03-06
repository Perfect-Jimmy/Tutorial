package com.tutorial.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=5,max=10,message = "名字长度5-10位")
    private String userName;

    //@Size(min=6,max=10)
    //@CustomValidator(values = "1,2,3")
    private String password;

    private String email;

    private String token;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
