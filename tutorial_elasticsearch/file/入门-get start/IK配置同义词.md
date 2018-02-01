### IK配置同义词
https://www.jianshu.com/p/3e63f6739631
https://www.cnblogs.com/kaynet/p/6185859.html
https://www.cnblogs.com/NextNight/p/6837407.html

在配置同义词规则时有Solr synonyms和WordNet synonyms，一般我们使用的都是Solr synonyms。在配置时又存在映射和对等两种方式，区别如下：

// 精确映射同义词，【阿迪】、【阿迪达斯】和【adidasi】的token将会转换为【Adidas】存入倒排索引中
阿迪, 阿迪达斯, adidasi => Adidas

// 对等同义词
// 当expand为true时，当出现以下任何一个token，三个token都会存入倒排索引中
// 当expand为false时，当出现以下任何一个token，第一个token也就是【Nike】会存入倒排索引中
Nike, 耐克, naike

