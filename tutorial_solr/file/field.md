### field
1. name 字段名
2. type 字段类型
3. indexed 是否进行索引
4. stored 是否进行保存，如不保存，可以进行搜索，但不能显示该字段的内容
5. required 是否是必须字段，如若是，该字段必须有值，否则索引报错
6. multiValued 是否允许多值
7. docValues
8. sortMissingLast
```
<field name="id" type="string" indexed="true" stored="true" required="true" multiValued="false" />
```


### dynamicFields
动态字段表示,如果字段的定义没有在配置中找到,就在动态字段类型中进行查找
```
<dynamicField name="*_txt" type="text_general"    indexed="true"  stored="true" multiValued="true"/> 
```

### copyField
复制源字段到目标字段,maxchars 限制复制的最大长度
```
<copyField source="body" dest="teaser" maxChars="300"/>  
```

### uniqueKey
相当于数据库中得主键,如建索引时遇到重复的,则会覆盖掉以前的记录
```
<uniqueKey>id</uniqueKey>
```

### defaultSearchField
如果搜索参数中没有指定具体的field,那么这是默认的域
```
<defaultSearchField>text</defaultSearchField>  
```


### solrQueryParser
配置搜索参数短语间的逻辑,可以是"AND | OR"
```
<solrQueryParser defaultOperator="OR" /> 
```
