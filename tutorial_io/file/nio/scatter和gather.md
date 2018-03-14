### scatter 分散  
从Channel中读取,在读操作时将读取的数据写入多个buffer中  
Channel将从Channel中读取的数据"分散(scatter)"到多个Buffer中


### gather 聚集   
写入Channel,在写操作时将多个buffer的数据写入同一个Channel中  
Channel将多个Buffer中的数据"聚集(gather)"后发送到Channel