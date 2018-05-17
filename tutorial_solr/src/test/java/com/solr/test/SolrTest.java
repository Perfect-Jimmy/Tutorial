package com.solr.test;

import com.solr.SolrStart;
import com.solr.domain.ProgramSeries;
import com.solr.service.ProgramSeriesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by Jimmy. 2018/5/17  15:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SolrStart.class)
public class SolrTest {
    @Autowired
    private ProgramSeriesService programSeriesService;

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
