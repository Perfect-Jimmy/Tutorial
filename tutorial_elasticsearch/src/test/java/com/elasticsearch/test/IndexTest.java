package com.elasticsearch.test;

import com.elasticsearch.ElasticsearchStart;
import io.searchbox.client.JestClient;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import org.elasticsearch.common.settings.Settings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Created by Jimmy. 2018/1/29  14:13
 * https://github.com/searchbox-io/Jest/tree/master/jest
 * https://github.com/searchbox-io/Jest/tree/master/jest/src/test/java/io/searchbox/core
 * http://blog.csdn.net/u010466329/article/details/75020956
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchStart.class)
public class IndexTest {

    @Autowired
    private JestClient jestClient;

    /**
     * 创建索引
     */
    @Test
    public void createIndex() throws IOException {
        CreateIndex.Builder index = new CreateIndex.Builder("news");
        jestClient.execute(index.build());
    }

    /**
     * 创建索引settings
     */
    @Test
    public void createIndexWithSettings() throws IOException {
        Settings.Builder settingsBuilder = Settings.builder();
        settingsBuilder.put("number_of_shards",3);
        settingsBuilder.put("number_of_replicas",1);

        jestClient.execute(new CreateIndex.Builder("articles").settings(settingsBuilder.build()).build());
    }

    /**
     * 删除索引
     * @throws IOException
     */
    @Test
    public void deleteIndex() throws IOException {
       // DeleteIndex index = new DeleteIndex.Builder("articles1").build();
       // jestClient.execute(index);
        DeleteIndex.Builder index = new DeleteIndex.Builder("tutorial1");
        jestClient.execute(index.build());
    }

}
