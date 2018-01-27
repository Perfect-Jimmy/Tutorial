### Prefix Query 前缀匹配
检索某个字段中以给到前缀开始的文档.

* 检索name中以"大话"为前缀的数据
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/movie/_search?pretty' -d '
{
  "query": {
       "prefix":{
          "name":"大话"
       }
  }
}
'
```
结果:没有数据