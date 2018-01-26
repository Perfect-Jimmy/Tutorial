### Term Query 单个精确值搜索

> 基于dynamic mapping

* keyword 搜索name="赌神1"的电影,需指定"name.keyword":"赌神1",不使用分词              
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

* text 搜索
1. "name":"赌神1" 检索不到数据
2. "name":"赌"    检索到所有name包含"赌"的数据 
 
原因:默认分析器把"赌神1"分词如下
```
{
  "tokens" : [
    {
      "token" : "赌",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "<IDEOGRAPHIC>",
      "position" : 0
    },
    {
      "token" : "神",
      "start_offset" : 1,
      "end_offset" : 2,
      "type" : "<IDEOGRAPHIC>",
      "position" : 1
    },
    {
      "token" : "1",
      "start_offset" : 2,
      "end_offset" : 3,
      "type" : "<NUM>",
      "position" : 2
    }
  ]
}
``` 

      
> 使用analyze API 测试分析器
```
curl  -H "Content-Type:application/json" -XGET "localhost:9200/_analyze?pretty" -d'
{
  "text": "赌神1"
}
'
```
