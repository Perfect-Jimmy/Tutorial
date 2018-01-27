### wildcard query 通配符检索  性能低
支持单字通配符和多字通配符
* ?:匹配一个任意字符
* *:匹配零或多个字符

* 查询name中包含"西游"的数据
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/movie/_search?pretty' -d '
{
  "query": {
       "wildcard":{
          "name.keyword":"*西游*"
       }
  }
}
'
```
