package com.elasticsearch.test;

import com.elasticsearch.ElasticsearchStart;
import io.searchbox.client.JestClient;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Created by Jimmy. 2018/1/29  14:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchStart.class)
public class MappingTest {
    @Autowired
    private JestClient jestClient;

    /**
     * 创建映射
     */
    public void createMapping() throws IOException {

    }

}
