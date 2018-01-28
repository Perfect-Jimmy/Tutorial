### bool
可取值:
1. must:文档必须匹配must选项下的检索条件,相当于逻辑运算AND.
2. should:文档可以匹配也可以不匹配should选项下的检索条件,相当于逻辑运算OR.
3. must_not:与must相反.
4. filter:和must一样,匹配filter选项下的检索条件.但是filter不评分,只过滤.

* 检索releaseDate=2008-10-9或者grade=8.8,并且releaseDate不等于2010-8-12
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/movie/_search?pretty' -d '
{
  "query": {
      "constant_score":{
         "filter":{
            "bool":{
               "should":[
                  {"term":{"releaseDate.keyword":"2008-10-9"}},
                  {"term":{"grade":8.8}}
               ],
               "must_not":{"term":{"releaseDate.keyword":"2010-8-12"}}}
            }
         }
      }
  }
}
'
```

* 检索name=战狼2,或者grade=8.8并且releaseDate等于2010-8-12
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/movie/_search?pretty' -d '
{
  "query": {
     "constant_score":{
        "filter":{
           "bool":{
              "should":[
                 {"term":{"name.keyword":"战狼2"}},
                 {"bool":
                   {
                     "must":[
                        {"term":{"grade":8.8}},
                        {"term":{"releaseDate.keyword":"2010-8-12"}}
                     ]
                   }
                 }
              ]
           }
        }
     }
  }
}
'
```
