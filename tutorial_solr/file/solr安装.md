### solr安装 7.x版本
5.x之后solr内部集成了jetty,不需放在tomcat启动

#### 环境要求
1. jdk 8


#### windows环境







#### linux环境
* tomcat 启动

1. 下载:solr-7.3.0.tgz
2. 上传,解压:tar -zxvf solr-7.3.0.tgz
3. 复制并重命名solr-7.3.0目录里的server/solr-webapp/webapp文件夹到tomcat的webapps目录为solr7  
```
cp -r server/solr-webapp/webapp  /usr/local/java/apache-tomcat-8.5.29/webapps/solr7
```
4. 将server/lib/ext/下的所有jar复制到 /usr/local/java/apache-tomcat-8.5.29/webapps/solr7/WEB-INF/lib/下
```
＃ cd server/lib/ext/
#  ll
#  cp  *  /usr/local/java/apache-tomcat-8.5.29/webapps/solr7/WEB-INF/lib/
```
5. 将server/lib/metrics* 开头的5个jar复制到 /usr/local/java/apache-tomcat-8.5.29/webapps/solr7/WEB-INF/lib/下
6. 把server/resources/目录下的log4j.properties,复制到的solr7的WEB-INF/,要创建一个classes的目录
7. 创建solr home:复制并重命名solr-7.3.0/servier/solr为/usr/local/solr_home
8. 修改tomcat里solr7/WEB-INF/web.xml文件,找到solr/home的env-entry,打开注释,修改env-entry-value值为新建的solr_home,
   并将如下注释掉.
```
 <!-- 
 <security-constraint>
    <web-resource-collection>
      <web-resource-name>Disable TRACE</web-resource-name>
      <url-pattern>/</url-pattern>
      <http-method>TRACE</http-method>
    </web-resource-collection>
    <auth-constraint/>
  </security-constraint>
  <security-constraint>
    <web-resource-collection>
      <web-resource-name>Enable everything but TRACE</web-resource-name>
      <url-pattern>/</url-pattern>
      <http-method-omission>TRACE</http-method-omission>
    </web-resource-collection>
  </security-constraint> -->
```
9. 启动tomcat,访问:http://ip:port/solr/index.html



* jetty 启动
1. 上传:
2. 启动:
3. 访问:默认端口8983