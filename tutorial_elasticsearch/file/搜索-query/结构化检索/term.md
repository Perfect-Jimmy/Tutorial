### Term Query 单个精确值搜索

> 基于dynamic mapping

* 搜索name="赌神1"的电影
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/movie/_search?pretty' -d '
{
  "query": {
       "term":{
           "name.keyword":"赌神1"
       }
  }
}
'
```
#### dynamic mapping把name定义了两种类型:text和keyword.text用于全文检索,keyword用于精准检索.


