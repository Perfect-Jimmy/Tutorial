package com.elasticsearch.test;

import com.elasticsearch.ElasticsearchStart;
import com.elasticsearch.domain.Movie;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Jimmy. 2018/1/29  16:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchStart.class)
public class SearchTest {
    public static final String INDEX ="tutorial";
    public static final String TYPE  ="movie";

    @Autowired
    private JestClient jestClient;

    /**
     * Search
     * @throws IOException
     */
    @Test
    public void searchTest1() throws IOException {
        String query = "{\"query\": {\n" +
                "       \"ids\":{\n" +
                "          \"type\":[\"tweet\"],\n" +
                "          \"values\":[1,3,5]\n" +
                "       }\n" +
                "  }}";

        Search search = new Search.Builder(query)
                // multiple index or types can be added.
                .addIndex("twitter")
                .addType("tweet")
                .build();

        SearchResult result = jestClient.execute(search);

        System.out.println(result.getHits(Movie.class).get(0).source);
    }


    /**
     * SearchSourceBuilder
     * @throws IOException
     */
    @Test
    public void searchTest2() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("style", "动作"));

        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex("twitter")
                .addType("tweet")
                .build();

        SearchResult result = jestClient.execute(search);
        System.out.println(result.getTotal());
        List<SearchResult.Hit<Movie, Void>> hits = result.getHits(Movie.class);
        System.out.println(hits.size());
    }

    /**
     * term 单值完全匹配查询,分页
     * @throws IOException
     */
    @Test
    public void termTest() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders
                .termQuery("name.keyword", "赌神1");
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        String query = searchSourceBuilder.toString();
        System.out.println(query);
        Search search = new Search.Builder(query).addIndex(INDEX).addType(TYPE).build();
        //
        SearchResult result = jestClient.execute(search);
        //
        List<SearchResult.Hit<Movie, Void>> hits = result.getHits(Movie.class);
        System.out.println(hits.size());
    }

    /**
     * terms 多值完全匹配查询
     * @throws IOException
     */
    @Test
    public void termsTest() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders
                .termsQuery("name.keyword", "赌神1","精武英雄");
        searchSourceBuilder.query(queryBuilder);
        String query = searchSourceBuilder.toString();
        System.out.println(query);
        Search search = new Search.Builder(query).addIndex(INDEX).addType(TYPE).build();
        //
        SearchResult result = jestClient.execute(search);
        //
        List<SearchResult.Hit<Movie, Void>> hits = result.getHits(Movie.class);
        for(SearchResult.Hit<Movie, Void> hit : hits){
            System.out.println(hit.source);
        }
    }

    /**
     * wildcard 通配符查询
     * @throws IOException
     */
    @Test
    public void wildcardTest() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders
                .wildcardQuery("name.keyword", "*西游*");
        searchSourceBuilder.query(queryBuilder);
        String query = searchSourceBuilder.toString();
        System.out.println(query);
        Search search = new Search.Builder(query).addIndex(INDEX).addType(TYPE).build();
        //
        SearchResult result = jestClient.execute(search);
        //
        List<SearchResult.Hit<Movie, Void>> hits = result.getHits(Movie.class);
        for(SearchResult.Hit<Movie, Void> hit : hits){
            System.out.println(hit.source);
        }
    }

    /**
     * prefix 查询
     * @throws IOException
     */
    @Test
    public void prefixTest() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders
                .prefixQuery("name.keyword", "大话");
        searchSourceBuilder.query(queryBuilder);
        String query = searchSourceBuilder.toString();
        System.out.println(query);
        Search search = new Search.Builder(query).addIndex(INDEX).addType(TYPE).build();
        //
        SearchResult result = jestClient.execute(search);
        //
        List<SearchResult.Hit<Movie, Void>> hits = result.getHits(Movie.class);
        for(SearchResult.Hit<Movie, Void> hit : hits){
            System.out.println(hit.source);
        }
    }

    /**
     * range 查询
     * @throws IOException
     */
    @Test
    public void rangeTest() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders
                .rangeQuery("grade")
                .gt("7.0")
                .lt("8.8")
                .includeLower(true)
                .includeUpper(true);
        searchSourceBuilder.query(queryBuilder);
        String query = searchSourceBuilder.toString();
        System.out.println(query);
        Search search = new Search.Builder(query).addIndex(INDEX).addType(TYPE).build();
        //
        SearchResult result = jestClient.execute(search);
        //
        List<SearchResult.Hit<Movie, Void>> hits = result.getHits(Movie.class);
        for(SearchResult.Hit<Movie, Void> hit : hits){
            System.out.println(hit.source);
        }
    }

    /**
     * fuzzy 查询
     * @throws IOException
     */
    @Test
    public void fuzzyTest() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders
                .fuzzyQuery("name.keyword","Titanci");
        searchSourceBuilder.query(queryBuilder);
        String query = searchSourceBuilder.toString();
        System.out.println(query);
        Search search = new Search.Builder(query).addIndex(INDEX).addType(TYPE).build();
        //
        SearchResult result = jestClient.execute(search);
        //
        List<SearchResult.Hit<Movie, Void>> hits = result.getHits(Movie.class);
        for(SearchResult.Hit<Movie, Void> hit : hits){
            System.out.println(hit.source);
        }
    }

    /**
     * bool 查询
     * @throws IOException
     */
    @Test
    public void boolTest() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termsQuery("name.keyword", new String[]{"赌神2","初恋这件小事"}))
                .must(QueryBuilders.rangeQuery("grade").gte("8.0").lte("10.0"));
        searchSourceBuilder.query(queryBuilder);
        String query = searchSourceBuilder.toString();
        System.out.println(query);
        Search search = new Search.Builder(query).addIndex(INDEX).addType(TYPE).build();
        //
        SearchResult result = jestClient.execute(search);
        //
        List<SearchResult.Hit<Movie, Void>> hits = result.getHits(Movie.class);
        for(SearchResult.Hit<Movie, Void> hit : hits){
            System.out.println(hit.source);
        }
    }

    /**
     * stringQuery 查询
     * @throws IOException
     */
    @Test
    public void stringQueryTest() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.queryStringQuery("").escape(true);
        //
        searchSourceBuilder.query(queryBuilder);
        String query = searchSourceBuilder.toString();
        System.out.println(query);
        Search search = new Search.Builder(query).addIndex(INDEX).addType(TYPE).build();
        //
        SearchResult result = jestClient.execute(search);
        //
        List<SearchResult.Hit<Movie, Void>> hits = result.getHits(Movie.class);
        for(SearchResult.Hit<Movie, Void> hit : hits){
            System.out.println(hit.source);
        }
    }

    /**
     * matchAll 查询 默认返回10条
     * @throws IOException
     */
    @Test
    public void matchAllTest() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        //
        searchSourceBuilder.query(queryBuilder);
        String query = searchSourceBuilder.toString();
        System.out.println(query);
        Search search = new Search.Builder(query).addIndex(INDEX).build();
        //
        SearchResult result = jestClient.execute(search);
        //
        List<SearchResult.Hit<Movie, Void>> hits = result.getHits(Movie.class);
        System.out.println(result.getTotal());
        for(SearchResult.Hit<Movie, Void> hit : hits){
            System.out.println(hit.source);
        }
    }

    /**
     * highLight
     * @throws IOException
     */
    @Test
    public void highLightTest() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("name", "西游");
        //
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name");//高亮name
        highlightBuilder.preTags("<em>").postTags("</em>");//高亮标签
        highlightBuilder.fragmentSize(500);//高亮内容长度
        searchSourceBuilder.highlighter(highlightBuilder);
        //
        searchSourceBuilder.query(queryBuilder);
        String query = searchSourceBuilder.toString();
        System.out.println(query);
        Search search = new Search.Builder(query).addIndex(INDEX).build();
        //
        SearchResult result = jestClient.execute(search);
        //
        List<SearchResult.Hit<Movie, Void>> hits = result.getHits(Movie.class);
        System.out.println(result.getTotal());
        for(SearchResult.Hit<Movie, Void> hit : hits){
            Movie movie = hit.source;
            System.out.println(movie);
            //获取高亮后的内容
            Map<String, List<String>> highlight = hit.highlight;
            List<String> names = highlight.get("name");//高亮后的name
            System.out.println("names=="+names);
            if(names!=null){
                System.out.println("highLight name:"+names.get(0));
                movie.setName(names.get(0));
            }
        }
    }

    /**
     * multiMatch
     * @throws IOException
     */
    @Test
    public void multiMatchTest() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("刘德华","leadRole","desc");
        //
        HighlightBuilder highlightLeadRole = new HighlightBuilder();
        highlightLeadRole.field("leadRole");//高亮leadRole
        highlightLeadRole.preTags("<em>").postTags("</em>");//高亮标签
        highlightLeadRole.fragmentSize(500);//高亮内容长度
        searchSourceBuilder.highlighter(highlightLeadRole);
        //
       /* HighlightBuilder highlightDesc = new HighlightBuilder();
        highlightDesc.field("desc");//高亮name
        highlightDesc.preTags("<em>").postTags("</em>");//高亮标签
        highlightDesc.fragmentSize(500);//高亮内容长度
        searchSourceBuilder.highlighter(highlightDesc);*/
        //

        searchSourceBuilder.query(queryBuilder);
        String query = searchSourceBuilder.toString();
        System.out.println(query);
        Search search = new Search.Builder(query).addIndex(INDEX).build();
        //
        SearchResult result = jestClient.execute(search);
        //
        List<SearchResult.Hit<Movie, Void>> hits = result.getHits(Movie.class);
        System.out.println(result.getTotal());
        for(SearchResult.Hit<Movie, Void> hit : hits){
            Movie movie = hit.source;
            System.out.println(movie);
            //获取高亮后的内容
            Map<String, List<String>> highlight = hit.highlight;
            if(highlight != null){
                System.out.println("map:"+highlight);
                if(highlight.get("leadRole") != null){//高亮后的leadRoles
                    List<String> leadRoles = highlight.get("leadRole");
                    System.out.println("leadRoles:"+leadRoles);
                    System.out.println("highLight leadRole:"+leadRoles.get(0));
                }

                if(highlight.get("desc") != null){//高亮后的desc
                    List<String> descs = highlight.get("desc");
                    System.out.println("descs:"+descs);
                    System.out.println("highLight desc:"+descs.get(0));
                }
            }
        }
    }
}
