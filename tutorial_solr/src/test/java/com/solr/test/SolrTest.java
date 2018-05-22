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

    //nameStr string不分词
    @Test
    public void query() throws IOException, SolrServerException {
        System.out.println(solrClient.getClass().toString());
        SolrQuery query = new SolrQuery();// 查询
        // query.setQuery("id:1");
        // query.setQuery("*:*");
        //   query.setQuery("name:*新闻联播*");
        query.setParam("q","nameStr:新闻联播*");
        QueryResponse response = solrClient.query("programSeries",query);
        SolrDocumentList solrDocumentList = response.getResults();
        System.out.println(solrDocumentList.size());
        for(SolrDocument sd : solrDocumentList){
            System.out.println("solr获取值 id：" + sd.getFieldValue("id"));
            System.out.println("solr获取值 name：" + sd.getFieldValue("name"));
        }
    }

    //name 中文分词
    @Test
    public void queryIK() throws IOException, SolrServerException {
        SolrQuery query = new SolrQuery();// 查询
        query.setParam("q","name:江西新闻联播");
        query.setParam("defType","edismax");
        query.setParam("mm","10");
        System.out.println(query.toQueryString());
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
        programSeries.setName("江西新闻联播");
        programSeries.setNameStr("江西新闻联播");
        programSeries.setPublishDate(new Date());
        programSeriesService.save(programSeries);

        ProgramSeries programSeries1 = new ProgramSeries();
        programSeries1.setId(2L);
        programSeries1.setName("安徽新闻联播");
        programSeries1.setNameStr("安徽新闻联播");
        programSeries1.setPublishDate(new Date());
        programSeriesService.save(programSeries1);

        ProgramSeries programSeries2 = new ProgramSeries();
        programSeries2.setId(3L);
        programSeries2.setName("新闻联播");
        programSeries2.setNameStr("新闻联播");
        programSeries2.setPublishDate(new Date());
        programSeriesService.save(programSeries2);

        ProgramSeries programSeries3 = new ProgramSeries();
        programSeries3.setId(4L);
        programSeries3.setName("新闻联播看点");
        programSeries3.setNameStr("新闻联播看点");
        programSeries3.setPublishDate(new Date());
        programSeriesService.save(programSeries3);


        ProgramSeries programSeries4 = new ProgramSeries();
        programSeries4.setId(5L);
        programSeries4.setName("新闻看点");
        programSeries4.setNameStr("新闻看点");
        programSeries4.setPublishDate(new Date());
        programSeriesService.save(programSeries4);


        ProgramSeries programSeries5 = new ProgramSeries();
        programSeries5.setId(6L);
        programSeries5.setName("今日看点");
        programSeries5.setNameStr("今日看点");
        programSeries5.setPublishDate(new Date());
        programSeriesService.save(programSeries5);
    }



    @Test
    public void delete(){
        for(Long i=1L;i<=4L;i++){
            programSeriesService.delete(i);
        }
    }
}
