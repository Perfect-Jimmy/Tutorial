### match
> 它知道如何处理全文字段(Full-text, analyzed)和精确值字段(Exact-value,not_analyzed)

match查询处理:对用户输入的内容进行高级处理,比如对其进行分词处理,会分析输入内容的类型,如果是日期类型或数字类型,就会采用精确匹配,如果是一个文本内容,则会对其进行分析成词条(terms),然后采用比较低级的term查询进行处理,同时 Match Query提供了一些特性来更好的帮助我们优化搜索结果,比如fuzziness.

> Single word query 单词查询之英文
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/movie/_search?pretty' -d '
{
  "query": { 
        "match": { "name": "Titanic" } 
      }
}
'
```     
处理流程:       
1. 检查字段类型:name字段是一个全文字符串字段(analyzed),意味着查询字符串也需要被分析
2. 解析查询字符串:查询字符串"Titanic"会被传入到标准解析器中,得到单一词条"Titanic",因为只有一个词条,match查询会使用一个term低级查询来执行查询
3. 找到匹配的文档:term查询会在倒排索引中查询"Titanic",然后获取到含有该词条的文档列表并返回
4. 对每份文档打分：term查询会为每份匹配的文档计算其相关度分值_score

*注:相关度分值通过综合考虑词条频度(Term Frequency)("Titanic"在匹配的每份文档的name字段中出现的频繁程度),
倒排频度(Inverted Document Frequency)("Titanic"在整个索引中的所有文档的name字段中的出现程度),
以及每个字段的长度(较短的字段会被认为相关度更高)来得到*

> Single word query 单词查询之中文
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/movie/_search?pretty' -d '
{
  "query": { 
        "match": { "name": "战狼2" } 
      }
}
'
``` 
处理流程:       
1. 查询字符串会被分词成"战","狼","2".因此战狼2,赌神2和大话西游2都会被检索出来.

> Fuzziness 模糊查询

fuzziness指定编辑距离,取值0,1,2
```
curl -H "Content-Type:application/json" -XGET 'localhost:9200/tutorial/book/_search?pretty' -d '
{
  "query": { 
        "match": { 
              "name": {
                    "query":"Titanci",
                    "fuzziness":1
              }
        }
  }
}
'
```
*使用场景:当用户输入一个短语或单词,可能输错了其中一个字母或汉字,此时系统应该发现这种情况并返回用户期望的结果*

> Operator 控制并存性
1. 查询name中包含"Elasticsearch"并且"Guide"的数据
```
curl -H "Content-Type:application/json" -XGET 'localhost:9200/tutorial/book/_search?pretty' -d '
 {
   "query": { 
         "match": { 
               "title": {
                     "query":"Elasticsearch Guide",
                     "operator": "and"
               }
         }
   }
 }
 '
```
*注:查询内容"Elasticsearch Guide"被分析后成为两个词条("Elasticsearch","Guide")*  

2. 查询title中包含"Elasticsearch"或者"Guide"的数据
```
curl -H "Content-Type:application/json" -XGET 'localhost:9200/tutorial/book/_search?pretty' -d '
 {
   "query": { 
         "match": { 
               "title": {
                     "query":"Elasticsearch Guide",
                     "operator": "or"
               }
         }
   }
 }
 '
```

> Zero terms query 零term查询

> Cutoff frequency 高低频截断
