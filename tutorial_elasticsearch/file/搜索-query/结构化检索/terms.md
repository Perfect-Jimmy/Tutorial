### Term Query 多个精确值搜索

* 查询name="赌神1"或者name="战狼2"的电影
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/movie/_search?pretty' -d '
{
  "query": {
       "terms":{
           "name.keyword":["赌神1","战狼2"]
       }
  }
}
'
```