### buckets 桶
满足特定条件的文档的集合.随着聚合被执行,每份文档中的值会被计算来决定它们是否匹配了桶的条件,如果匹配成功,那么该文档会被置入该桶中,
同时聚合会继续执行.比如日期2014-10-28属于十月份这个桶.

* terms 
根据zone.country.keyword查询各country有多少文档
```
curl -H "Content-Type:application/json" -X GET "localhost:9200/es/blog/_search?pretty" -d'
{
  "size":0,
  "aggs":{
     "country_terms_aggs":{
         "terms":{
             "field":"zone.country.keyword",
             "order" : { "_key" : "asc" }
         }
     }
  }
}
'
```
结果:
```
{
  "took" : 4,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 14,
    "max_score" : 0.0,
    "hits" : [ ]
  },
  "aggregations" : {
    "country_terms_aggs" : {
      "doc_count_error_upper_bound" : 0,
      "sum_other_doc_count" : 0,
      "buckets" : [
        {
          "key" : "中国",
          "doc_count" : 12
        },
        {
          "key" : "美国",
          "doc_count" : 2
        }
      ]
    }
  }
}
```
**注:size只获取聚合结果而不要执行聚合的原始数据**