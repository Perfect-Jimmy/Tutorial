### selector
FileChannel是可读可写的Channel,它必须阻塞,不能用在非阻塞模式中.
新的Socket Channel能在非阻塞模式下运行并且是可选择的(以前的Socket程序是阻塞的,服务器必须始终等待客户端的连接).

#### 主要方法
|方法                    | 说明  
| :-:                   | :-: 
open()          | 打开一个选择器    
select()        | 选择一组键,其相应的通道已为I/O操作准备就绪
selectedKeys()  | 返回此选择器的已选择键集,4个常量:(1)OP_ACCEPT:用于套接字接受操作的操作集位 (2)OP_CONNECT:用于套接字连接操作的操作集位 
                  (3)OP_READ:用于读取操作的操作集位 (4)OP_WRITE:用于写入操作的操作集位

