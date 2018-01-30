### 常用API

> 索引列表 curl 'localhost:9200/_cat/indices?v'

> 创建索引 curl -X PUT 'localhost:9200/tutorial?pretty'

> 删除索引 curl -X DELETE 'localhost:9200/tutorial?pretty'

> 查看映射 curl -X GET 'localhost:9200/tutorial/_mapping/movie?pretty'

> ID查询  curl -X GET 'localhost:9200/tutorial/employee/1?pretty'

> 查看分词
```
curl  -H "Content-Type:application/json" -X GET "localhost:9200/_analyze?pretty" -d'
{
  "analyzer": "standard",
  "text":     "Set the shape to semi-transparent by calling set_trans(5)"
}
'
```
