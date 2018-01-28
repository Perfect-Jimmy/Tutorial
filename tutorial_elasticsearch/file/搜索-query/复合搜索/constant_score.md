### constant_score query
constant_score:查询以非评分模式来执行term查询并以1作为统一评分
* 检索grade=7.8的电影
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/movie/_search?pretty' -d '
{
  "query": {
      "constant_score":{
          "filter":{
               "term":{
                   "grade":7.8
               }
          }
      }
  }
}
'
```
结果:max_score和_score都是1.0
```
{
  "took" : 2,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 2,
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "tutorial",
        "_type" : "movie",
        "_id" : "9",
        "_score" : 1.0,
        "_source" : {
          "id" : "9",
          "name" : "羞羞的铁拳",
          "leadRole" : [
            "沈腾",
            "马丽"
          ],
          "style" : "喜剧",
          "zone" : {
            "country" : "中国",
            "city" : "大陆"
          },
          "grade" : 7.8,
          "desc" : "靠打假拳混日子的艾迪生（艾伦 饰），本来和正义感十足的体育记者马小（马丽 饰）是一对冤家，没想到因为一场意外的电击，男女身体互换",
          "releaseDate" : "2017-10-1"
        }
      },
      {
        "_index" : "tutorial",
        "_type" : "movie",
        "_id" : "10",
        "_score" : 1.0,
        "_source" : {
          "id" : "10",
          "name" : "战狼2",
          "leadRole" : [
            "吴京",
            "弗兰克·格里罗"
          ],
          "style" : "军事",
          "zone" : {
            "country" : "中国",
            "city" : "大陆"
          },
          "grade" : 7.8,
          "desc" : "故事发生在非洲附近的大海上，主人公冷锋（吴京 饰）遭遇人生滑铁卢，被“开除军籍”，本想漂泊一生的他，正当他打算这么做的时候，一场突如其来的意外打破了他的计划",
          "releaseDate" : "2017-10-1"
        }
      }
    ]
  }
}
```

* 检索grade=7.8的电影,指定`boost参数`
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/movie/_search?pretty' -d '
{
  "query": {
    "constant_score":{
        "filter":{
             "term":{
                 "grade":7.8
             }
        },
        "boost":1.2
    }
  }
}
'
```
结果:max_score和_score都是1.2
```
{
  "took" : 2,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 2,
    "max_score" : 1.2,
    "hits" : [
      {
        "_index" : "tutorial",
        "_type" : "movie",
        "_id" : "9",
        "_score" : 1.2,
        "_source" : {
          "id" : "9",
          "name" : "羞羞的铁拳",
          "leadRole" : [
            "沈腾",
            "马丽"
          ],
          "style" : "喜剧",
          "zone" : {
            "country" : "中国",
            "city" : "大陆"
          },
          "grade" : 7.8,
          "desc" : "靠打假拳混日子的艾迪生（艾伦 饰），本来和正义感十足的体育记者马小（马丽 饰）是一对冤家，没想到因为一场意外的电击，男女身体互换",
          "releaseDate" : "2017-10-1"
        }
      },
      {
        "_index" : "tutorial",
        "_type" : "movie",
        "_id" : "10",
        "_score" : 1.2,
        "_source" : {
          "id" : "10",
          "name" : "战狼2",
          "leadRole" : [
            "吴京",
            "弗兰克·格里罗"
          ],
          "style" : "军事",
          "zone" : {
            "country" : "中国",
            "city" : "大陆"
          },
          "grade" : 7.8,
          "desc" : "故事发生在非洲附近的大海上，主人公冷锋（吴京 饰）遭遇人生滑铁卢，被“开除军籍”，本想漂泊一生的他，正当他打算这么做的时候，一场突如其来的意外打破了他的计划",
          "releaseDate" : "2017-10-1"
        }
      }
    ]
  }
}
```
