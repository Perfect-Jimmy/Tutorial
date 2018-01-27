### ids query
检索指定id的文档.参数type可选,可接受组数,未指定时会检索索引中所有type.

* 检索id为1,3,5的数据
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/movie/_search?pretty' -d '
{
  "query": {
       "ids":{
          "type":["movie"],
          "values":[1,3,5]
       }
  }
}
'
```