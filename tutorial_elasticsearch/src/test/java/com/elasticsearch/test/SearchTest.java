package com.elasticsearch.test;

import com.elasticsearch.ElasticsearchStart;
import com.elasticsearch.domain.Movie;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

/**
 * Created by Jimmy. 2018/1/29  16:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchStart.class)
public class SearchTest {
    @Autowired
    private JestClient jestClient;

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

}
