### fuzzy query 模糊检索 性能低
模糊检索通过计算词项与文档的编辑距离检索数据.

* 搜索name="Titanic"但写错成了"Titanci"
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/movie/_search?pretty' -d '
{
  "query": {
       "fuzzy":{
          "name.keyword":"Titanci"
       }
  }
}
'
```

