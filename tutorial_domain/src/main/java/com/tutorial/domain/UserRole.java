package com.tutorial.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Jimmy. 2018/4/4  10:20
 * 用户-角色关系表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table
@Entity(name="user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 9039825446773388024L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long roleId;
}
