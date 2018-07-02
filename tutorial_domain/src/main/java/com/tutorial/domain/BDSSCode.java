package com.tutorial.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 百度频道code码与ysten频道code码对照表
 */
@Data
@Entity
@Table(name = "bd_ss_code")
public class BDSSCode implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "bd_code")
    private String bdCode;

    @Column(name = "ss_code")
    private String ssCode;

    @Column(name = "channel_name")
    private String channelName;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

}
