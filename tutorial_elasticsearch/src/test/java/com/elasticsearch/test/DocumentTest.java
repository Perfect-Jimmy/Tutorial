package com.elasticsearch.test;

import com.elasticsearch.ElasticsearchStart;
import com.elasticsearch.domain.Movie;
import com.elasticsearch.domain.Zone;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import io.searchbox.params.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Jimmy. 2018/1/29  15:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchStart.class)
public class DocumentTest {
    @Autowired
    private JestClient jestClient;

    /**
     * 索引文档
     */
    @Test
    public void indexDocument() throws IOException {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setName("test");
        movie.setLeadRole(new String[]{"刘德华"});
        movie.setStyle("动作");
        movie.setGrade(7.8F);
        Zone zone = new Zone();
        zone.setCountry("中国");
        zone.setCity("香港");
        movie.setZone(zone);
        movie.setDesc("这是测试");
        movie.setReleaseDate("2018-09-07");
        Index index = new Index.Builder(movie).index("twitter").type("tweet").build();
        //
        jestClient.execute(index);
    }


    /**
     * 索引文档-设置参数
     */
    @Test
    public void indexDocumentWithParameters() throws IOException {
        Movie movie = new Movie();
        movie.setId(100L);
        movie.setName("test");
        movie.setLeadRole(new String[]{"刘德华"});
        movie.setStyle("动作");
        movie.setGrade(7.8F);
        Zone zone = new Zone();
        zone.setCountry("中国");
        zone.setCity("香港");
        movie.setZone(zone);
        movie.setDesc("这是测试");
        movie.setReleaseDate("2018-09-07");
        Index index = new Index.Builder(movie).index("twitter").type("tweet")
                .setParameter(Parameters.REFRESH, true)
                .build();
        //
        jestClient.execute(index);
    }


    /**
     * 批量索引文档-操作同一个索引
     */
    @Test
    public void indexBulkDocument() throws IOException {
        Movie movie1 = new Movie();
        movie1.setId(1L);
        movie1.setName("test");
        movie1.setLeadRole(new String[]{"刘德华"});
        movie1.setStyle("动作");
        movie1.setGrade(7.8F);
        Zone zone = new Zone();
        zone.setCountry("中国");
        zone.setCity("香港");
        movie1.setZone(zone);
        movie1.setDesc("这是测试");
        movie1.setReleaseDate("2018-09-07");

        Movie movie2 = new Movie();
        movie2.setId(2L);
        movie2.setName("test");
        movie2.setLeadRole(new String[]{"刘德华"});
        movie2.setStyle("动作");
        movie2.setGrade(7.8F);
        movie2.setZone(zone);
        movie2.setDesc("这是测试");
        movie2.setReleaseDate("2018-09-07");

        Bulk bulk = new Bulk.Builder()
                .defaultIndex("facebook")
                .defaultType("tweet")
                .addAction(Arrays.asList(
                        new Index.Builder(movie1).build(),
                        new Index.Builder(movie2).build()))
                .build();
        //
        jestClient.execute(bulk);
    }

    /**
     * 批量索引文档和删除文档-操作不同的索引
     * @throws IOException
     */
    @Test
    public void bulkDocument() throws IOException {
        Movie movie1 = new Movie();
        movie1.setId(4L);
        movie1.setName("test");
        movie1.setLeadRole(new String[]{"刘德华","黎明"});
        movie1.setStyle("动作");
        movie1.setGrade(7.8F);
        Zone zone = new Zone();
        zone.setCountry("中国");
        zone.setCity("香港");
        movie1.setZone(zone);
        movie1.setDesc("这是测试");
        movie1.setReleaseDate("2018-09-07");

        Bulk bulk = new Bulk.Builder()
                .defaultIndex("facebook")
                .defaultType("tweet")
                .addAction(new Index.Builder(movie1).build())
              //.addAction(new Index.Builder(article2).build())
              // 可以是相同的索引也可以是不同的索引
                .addAction(new Delete.Builder("1").index("twitter").type("tweet").build())
                .build();
        //
        jestClient.execute(bulk);
    }

    /**
     * 检索文档
     * @throws IOException
     */
    @Test
    public void getDocument() throws IOException {
        Get get = new Get.Builder("twitter", "100").type("tweet").build();
        JestResult jestResult = jestClient.execute(get);
        System.out.println(jestResult.getSourceAsString());
    }

    /**
     * 检索文档-POJO
     * @throws IOException
     */
    @Test
    public void searchDocument() throws IOException {
        Get get = new Get.Builder("twitter", "100").type("tweet").build();
        JestResult jestResult = jestClient.execute(get);
        Movie movie = jestResult.getSourceAsObject(Movie.class);
        System.out.println(movie);
    }

    /**
     * 更新文档
     * @throws IOException
     */
    @Test
    public void modifyDocument() throws IOException {
       /* Get get = new Get.Builder("twitter", "1").type("tweet").build();
        JestResult jestResult = jestClient.execute(get);
        Movie movie = jestResult.getSourceAsObject(Movie.class);
        System.out.println(movie);
        //更新
        movie.setStyle("爱情");
        System.out.println(movie);
        Update update = new Update.Builder(movie).index("twitter").type("tweet").id("1").build();
        jestClient.execute(update);*/
        String script = "{" +
                "    \"doc\" : {" +
                "        \"style\" : \""+"爱情"+"\"," +
             /*   "        \"content\" : \""+article.getContent()+"\"," +
                "        \"author\" : \""+article.getAuthor()+"\"," +
                "        \"source\" : \""+article.getSource()+"\"," +
                "        \"url\" : \""+article.getUrl()+"\"," +
                "        \"pubdate\" : \""+new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(article.getPubdate())+"\"" +*/
                "    }" +
                "}";
        jestClient.execute(new Update.Builder(script).index("twitter").type("tweet").id("1").build());
    }

    /**
     * 删除文档
     * @throws IOException
     */
    @Test
    public void deleteDocument() throws IOException {
        Delete delete = new Delete.Builder("1").index("twitter").type("tweet").build();
        jestClient.execute(delete);
      /*  jestClient.execute(new Delete.Builder("100")
                .index("twitter")
                .type("tweet")
                .build());*/
    }
}
