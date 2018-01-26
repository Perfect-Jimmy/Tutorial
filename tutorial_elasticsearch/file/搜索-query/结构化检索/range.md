### Range Query

检索匹配在某一范围内的数值、日期、字符串文档.**range查询只能用于一个字段,不能作用于多个字段**

1 gt:大于
2 gte:大于等于
3 lt:小于
4 lte:小于等于

* 检索grade在8.5——9.0之间的电影
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/movie/_search?pretty' -d '
{
  "query": {
       "range":{
           "grade":{
               "gt":8.5,
               "lt":9.0
           }
       }
  }
}
'
```

* 检索releaseDate在1990-1-1——1997-12-31之间的电影
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/movie/_search?pretty' -d '
{
  "query": {
       "range":{
           "releaseDate":{
               "gte":"1990-1-1",
               "lte":"1997-12-31",
               "format":"yyyy-MM-dd"
           }
       }
  }
}
'
```
