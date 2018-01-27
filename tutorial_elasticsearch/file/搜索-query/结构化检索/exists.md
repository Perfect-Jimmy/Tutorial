### Exists Query
检索字段中至少有一个非空值的文档.

> Elasticsearch中以下情况当做null处理
1. null
2. []
3. [null]

* 以下情况会检索到搜索数据,因为name字段都有值
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/movie/_search?pretty' -d '
{
  "query": {
       "exists":{
          "field":"name"
       }
  }
}
'
```


* 以下情况检索不到数据,因为没有totalPrice字段
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/movie/_search?pretty' -d '
{
  "query": {
       "exists":{
          "field":"totalPrice"
       }
  }
}
'
```