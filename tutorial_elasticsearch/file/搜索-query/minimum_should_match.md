### minimum_should_match
控制搜索结果的精准度:指定检索关键字中必须至少匹配其中的多少个关键字才能作为结果返回.

* 检索包含周润发的电影
```
curl -H "Content-Type:application/json" -XGET 'localhost:9200/tutorial/movie/_search?pretty' -d '
{
   "query": { 
       "match": { 
           "leadRole.keyword":{
               "query":"周润发"
           }
       }
   }
}
'
```
结果:出现2部

* 检索包含周润发和刘德华的电影
```
curl -H "Content-Type:application/json" -XGET 'localhost:9200/tutorial/movie/_search?pretty' -d '
 {
    "query": { 
          "match": { 
              "leadRole":{
                  "query":"周润发 刘德华",
                  "minimum_should_match": "75%"
              }
          }
      }
 }
 '
```
