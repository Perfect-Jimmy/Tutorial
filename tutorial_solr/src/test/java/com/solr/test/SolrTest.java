package com.solr.test;

import com.solr.SolrStart;
import com.solr.domain.ProgramSeries;
import com.solr.service.ProgramSeriesService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Jimmy. 2018/5/17  15:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SolrStart.class)
public class SolrTest {
    @Autowired
    private ProgramSeriesService programSeriesService;

    @Autowired
    private SolrClient solrClient;

    @Test
    public void query() throws IOException, SolrServerException {
        System.out.println(solrClient.getClass().toString());
        SolrQuery query = new SolrQuery();// 查询
        query.setQuery("*:*");
        QueryResponse response = solrClient.query("programSeries",query);
        SolrDocumentList solrDocumentList = response.getResults();
        System.out.println(solrDocumentList.size());
        for(SolrDocument sd : solrDocumentList){
            System.out.println("solr获取值 id：" + sd.getFieldValue("id"));
            System.out.println("solr获取值 name：" + sd.getFieldValue("name"));
        }
    }



    @Test
    public void save(){
        ProgramSeries programSeries = new ProgramSeries();
        programSeries.setId(1L);
        programSeries.setName("测试名字");
        programSeries.setPublishDate(new Date());
        programSeriesService.save(programSeries);
    }



    @Test
    public void delete(){
        programSeriesService.delete(1L);
    }
}
