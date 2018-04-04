package com.tutorial.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Jimmy. 2018/4/4  10:19
 * 角色表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table
@Entity(name="role")
public class Role implements Serializable, GrantedAuthority {

    private static final long serialVersionUID = 5685290281330279379L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
