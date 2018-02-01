### bool
#### 可取值:
1. must:文档必须匹配must选项下的检索条件,相当于逻辑运算AND.
2. should:文档可以匹配也可以不匹配should选项下的检索条件,相当于逻辑运算OR.
3. must_not:与must相反.
4. filter:和must一样,匹配filter选项下的检索条件.但是filter不评分,只过滤.

* 检索releaseDate=2017-01-30或者title含有Tutorial,并且releaseDate不等于2017-10-16
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "_source":["id","title","releaseDate"],
  "query": {
      "constant_score":{
         "filter":{
            "bool":{
               "should":[
                  {"term":{"releaseDate":"2017-01-30"}},
                  {"match":{"title":"Tutorial"}}
               ],
               "must_not":{"term":{"releaseDate":"2017-10-16"}}}
            }
         }
      }
  }
}
'
```
结果:
```
"hits" : {
    "total" : 3,
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "8",
        "_score" : 1.0,
        "_source" : {
          "releaseDate" : "2017-01-30",
          "id" : 8,
          "title" : "Springboot教程"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "9",
        "_score" : 1.0,
        "_source" : {
          "releaseDate" : "2017-01-30",
          "id" : 9,
          "title" : "爪哇学习教程"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "7",
        "_score" : 1.0,
        "_source" : {
          "releaseDate" : "2017-01-30",
          "id" : 7,
          "title" : "Elasticsearch Tutorial"
        }
      }
    ]
  }
```

* 检索title=白云流水,或者weekClick=15并且releaseDate等于2017-01-30
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": {
     "constant_score":{
        "filter":{
           "bool":{
              "should":[
                 {"term":{"title.keyword":"白云流水"}},
                 {"bool":
                   {
                     "must":[
                        {"term":{"weekClick":15}},
                        {"term":{"releaseDate":"2017-01-30"}}
                     ]
                   }
                 }
              ]
           }
        }
     }
  },
  "_source":["id","title","weekClick","releaseDate"]
}
'
```
结果:
```
"hits" : {
    "total" : 2,
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "6",
        "_score" : 1.0,
        "_source" : {
          "weekClick" : 25,
          "releaseDate" : "2017-01-28",
          "id" : 6,
          "title" : "白云流水"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "7",
        "_score" : 1.0,
        "_source" : {
          "weekClick" : 15,
          "releaseDate" : "2017-01-30",
          "id" : 7,
          "title" : "Elasticsearch Tutorial"
        }
      }
    ]
  }
```
