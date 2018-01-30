### book
> 数据结构设计

|Field       | Type    | Description
| :-: | :-: | :-: | 
id          | Long    | id
name_en     | keyword | 书名(英文)
name_cn     | keyword | 书名(中文)
author      | Text    | 作者
style       | Keyword | 书籍类型:IT 科技 爱情 小说
zone        | keyword | 中国香港 大陆 欧美
grade       | Float   | 评分
desc        | Text    | 简介
releaseDate | Date    | 发布日期

> 测试数据
```
curl -H "Content-Type:application/json" -X PUT 'localhost:9200/elasticsearch/book/1?pretty' -d ' 
{
  "id": "1",
  "name_en": "Thinking in Java",
  "name_cn": "Java编程思想",
  "author" : ["Bruce Eckel"],
  "style": "IT Java",
  "zone": {
      "country": "美国",
      "city": ""
  },
  "grade": 9.6,
  "desc": "这是一本关于讲述java编程技术的书籍",
  "releaseDate": "2007-6-1"
}'


curl -H "Content-Type:application/json" -X PUT 'localhost:9200/elasticsearch/book/2?pretty' -d ' 
{
  "id": "2",
  "name_en": "Spring In Action",
  "name_cn": "Spring实战",
  "author" : ["Craig Walls"],
  "style": "IT Java Spring",
  "zone": {
      "country": "美国",
      "city": ""
  },
  "grade": 9.0,
  "desc": "介绍了使用Spring框架进行开发必须掌握的核心概念",
  "releaseDate": "2013-6-1"
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/elasticsearch/book/3?pretty' -d ' 
{
  "id": "3",
  "name_en": "JavaScript",
  "name_cn": "JavaScript权威指南",
  "author" : ["David Flanagan"],
  "style": "IT JS",
  "zone": {
      "country": "美国",
      "city": ""
  },
  "grade": 8.8,
  "desc": "学习JavaScript的书籍",
  "releaseDate": "2007-8-1"
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/elasticsearch/book/4?pretty' -d ' 
{
  "id": "4",
  "name_en": "",
  "name_cn": "神雕侠侣",
  "author" : ["金庸"],
  "style": "小说 武侠",
  "zone": {
      "country": "中国",
      "city": "香港"
  },
  "grade": 8.7,
  "desc": "是"射雕三部曲"系列第二部",
  "releaseDate": "2014-6-7"
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/elasticsearch/book/5?pretty' -d ' 
{
  "id": "5",
  "name_en": "",
  "name_cn": "朝花夕拾",
  "author" : ["鲁迅"],
  "style": "散文",
  "zone": {
      "country": "中国",
      "city": ""
  },
  "grade": 8.7,
  "desc": "这些篇章,文笔深沉隽永,是中国现代散文中的经典作品",
  "releaseDate": "1997-6-7"
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/elasticsearch/book/6?pretty' -d ' 
{
  "id": "6",
  "name_en": "",
  "name_cn": "朝花夕拾",
  "author" : ["鲁迅"],
  "style": "散文",
  "zone": {
      "country": "中国",
      "city": ""
  },
  "grade": 8.7,
  "desc": "这些篇章,文笔深沉隽永,是中国现代散文中的经典作品",
  "releaseDate": "1997-6-7"
}'
```