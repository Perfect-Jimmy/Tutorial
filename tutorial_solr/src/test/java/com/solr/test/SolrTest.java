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
import org.apache.solr.common.util.NamedList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public void queryProgramSeries() throws IOException, SolrServerException {
        System.out.println(solrClient.getClass().toString());
        SolrQuery query = new SolrQuery();// 查询
        query.setParam("q","programPinyin:yxgz");
       /* query.setParam("defType","edismax");
        query.setParam("mm","10");*/


        query.setHighlight(true); // 开启高亮
        query.addHighlightField("programPinyin"); // 高亮字段
        query.setHighlightSimplePre("<font color='red'>"); // 高亮单词的前缀
        query.setHighlightSimplePost("</font>"); // 高亮单词的后缀
       /* query.setHighlightFragsize(1);
        query.setHighlightFragsize(15);
        query.setHighlightRequireFieldMatch(true);*/
        QueryResponse response = solrClient.query("programSeries",query);
        SolrDocumentList solrDocumentList = response.getResults();
        System.out.println(solrDocumentList.size());

        NamedList<Object> highlightResult = response.getResponse();
        NamedList highlighting = (NamedList) highlightResult.get("highlighting");
        for (int i = 0; i <highlighting.size() ; i++) {
            System.out.println(highlighting.getName(i)+"："+highlighting.getVal(i));
        }


    }




    //nameStr string不分词
    @Test
    public void query() throws IOException, SolrServerException {
        System.out.println(solrClient.getClass().toString());
        SolrQuery query = new SolrQuery();// 查询
        // query.setQuery("id:1");
        // query.setQuery("*:*");
           query.setQuery("name:\"新闻联播\" OR name:*新闻联播*");
      //  query.setParam("q","nameStr:*新闻联播*");
      /*  query.setParam("defType","edismax");
        query.setParam("mm","10");*/
       // query.setQuery("*:*");
      //  query.addFilterQuery("nameStr:" + "*"+"新闻联播"+"*");
        System.out.println(query.toQueryString());
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
        query.setHighlight(true); // 开启高亮
     //   query.addHighlightField("title"); // 高亮字段
        query.addHighlightField("name"); // 高亮字段
        query.setHighlightSimplePre("<font color='red'>"); // 高亮单词的前缀
        query.setHighlightSimplePost("</font>"); // 高亮单词的后缀
        /**
         * hl.snippets
         * hl.snippets参数是返回高亮摘要的段数，因为我们的文本一般都比较长，含有搜索关键字的地方有多处，如果hl.snippets的值大于1的话，
         * 会返回多个摘要信息，即文本中含有关键字的几段话，默认值为1，返回含关键字最多的一段描述。solr会对多个段进行排序。
         * hl.fragsize
         * hl.fragsize参数是摘要信息的长度。默认值是100，这个长度是出现关键字的位置向前移6个字符，再往后100个字符，取这一段文本。
         */
        //query.setHighlightFragsize(100);
        System.out.println(query.toQueryString());
        QueryResponse response = solrClient.query("programSeries",query);

        List<ProgramSeries> programSeriesList = response.getBeans(ProgramSeries.class);
        Map<String, Map<String, List<String>>> highlightresult = response.getHighlighting();

       /* System.out.println("highlightresult--"+highlightresult);
        Map<String,List<String >> m = highlightresult.get(1);
        System.out.println("m:"+m);*/
       /* for (Map.Entry<Integer, String> entry : map.entrySet()) {
                       //Map.entry<Integer,String> 映射项（键-值对）  有几个方法：用上面的名字entry
                       //entry.getKey() ;entry.getValue(); entry.setValue();
                        //map.entrySet()  返回此映射中包含的映射关系的 Set视图。
                        System.out.println("key= " + entry.getKey() + " and value= "
                                        + entry.getValue());
        }*/
        for(ProgramSeries ps:programSeriesList){
            System.out.println(ps.getId()+"---"+ps.getName());
            if(highlightresult.get(ps.getId()) != null){
                Map<String,List<String >> high = highlightresult.get(ps.getId());
                System.out.println("highName:"+high.get("name").get(0));
            }
        }


       /* SolrDocumentList solrDocumentList = response.getResults();
        System.out.println(solrDocumentList.size());
        for(SolrDocument sd : solrDocumentList){
            System.out.println("solr获取值 id：" + sd.getFieldValue("id"));
            System.out.println("solr获取值 name：" + sd.getFieldValue("name"));
            if(highlightresult.get("1") != null){

            }
        }*/
    }



    @Test
    public void save(){
        ProgramSeries programSeries = new ProgramSeries();
        programSeries.setId(String.valueOf(1));
        programSeries.setName("江西新闻联播");
        programSeries.setNameStr("江西新闻联播");
        programSeries.setPublishDate(new Date());
        programSeriesService.save(programSeries);

        ProgramSeries programSeries1 = new ProgramSeries();
        programSeries1.setId(String.valueOf(2));
        programSeries1.setName("安徽新闻联播");
        programSeries1.setNameStr("安徽新闻联播");
        programSeries1.setPublishDate(new Date());
        programSeriesService.save(programSeries1);

        ProgramSeries programSeries2 = new ProgramSeries();
        programSeries2.setId(String.valueOf(3));
        programSeries2.setName("新闻联播");
        programSeries2.setNameStr("新闻联播");
        programSeries2.setPublishDate(new Date());
        programSeriesService.save(programSeries2);

        ProgramSeries programSeries3 = new ProgramSeries();
        programSeries3.setId(String.valueOf(4));
        programSeries3.setName("新闻联播看点");
        programSeries3.setNameStr("新闻联播看点");
        programSeries3.setPublishDate(new Date());
        programSeriesService.save(programSeries3);


        ProgramSeries programSeries4 = new ProgramSeries();
        programSeries4.setId(String.valueOf(5));
        programSeries4.setName("新闻看点");
        programSeries4.setNameStr("新闻看点");
        programSeries4.setPublishDate(new Date());
        programSeriesService.save(programSeries4);


        ProgramSeries programSeries5 = new ProgramSeries();
        programSeries5.setId(String.valueOf(6));
        programSeries5.setName("今日看点");
        programSeries5.setNameStr("今日看点");
        programSeries5.setPublishDate(new Date());

        ProgramSeries programSeries6 = new ProgramSeries();
        programSeries6.setId(String.valueOf(7));
        programSeries6.setName("新闻联播 明日看点");
        programSeries6.setNameStr("新闻联播 明日看点");
        programSeries6.setPublishDate(new Date());


        ProgramSeries programSeries7 = new ProgramSeries();
        programSeries7.setId(String.valueOf(8));
        programSeries7.setName("新闻联播 今日说法");
        programSeries7.setNameStr("新闻联播 今日说法");
        programSeries7.setPublishDate(new Date());
        programSeriesService.save(programSeries5);
    }



    @Test
    public void delete(){
        for(Long i=1L;i<=6L;i++){
            programSeriesService.delete(i);
        }
    }
}
