package com.solr.domain;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by Jimmy. 2018/5/17  15:40
 */
@Data
@SolrDocument(solrCoreName = "programSeries")
public class ProgramSeries implements Serializable {
    private static final long serialVersionUID = 6972281365024142388L;

    @Id
    @Field
    private String id;

    @Field
    private String name;

    @Field
    private String nameStr;

  //  @Field
    private Date publishDate;

    @Override
    public String toString() {
        return "ProgramSeries{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nameStr='" + nameStr + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }
}
