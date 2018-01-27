### Prefix Query 前缀匹配  性能低
检索某个字段中以给到前缀开始的文档.

* 检索name中以"大话"为前缀的数据
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/movie/_search?pretty' -d '
{
  "query": {
       "prefix":{
          "name.keyword":"大话"
       }
  }
}
'
```

* 检索name中以"Ti"为前缀的数据
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/movie/_search?pretty' -d '
{
  "query": {
       "prefix":{
          "name.keyword":"Ti"
       }
  }
}
'
```
结果:有数据

* 检索name中以"ti"为前缀的数据
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/movie/_search?pretty' -d '
{
  "query": {
       "prefix":{
          "name.keyword":"ti"
       }
  }
}
'
```
结果:无数据

* 检索name中以"Kate"为前缀的数据
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/movie/_search?pretty' -d '
{
  "query": {
       "prefix":{
          "leadRole.keyword":"Kate"
       }
  }
}
'
结果:有数据


