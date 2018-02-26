### fileLock 文件锁
当一个线程将文件锁定之后,其他线程无法操作此文件.文件的锁操作是使用FileLock类来进行完成的,此类对象需要依赖FileChannel进行实例化.

#### 文件锁方式
* 共享锁:允许多个线程进行文件读取
* 独占锁:只允许一个线程进行文件的读写操作

#### 主要方法
|方法                    | 说明  
| :-:                   | :-: 
lock()                  | 获取对此通道的文件的独占锁定  
lock(long position, long size, boolean shared)    | 获取此通道的文件给定区域上的锁定
tryLock() throws IOException                   | 试图获取对此通道的文件的独占锁定
tryLock(long position, long size, boolean shared) throws IOException | 试图获取对此通道的文件给定区域的锁定

#### lock()和tryLock()的区别
1. lock()阻塞的方法,锁定范围可以随着文件的增大而增加.无参lock()默认为独占锁;有参lock(0L, Long.MAX_VALUE, true)为共享锁
2. tryLock()非阻塞,当未获得锁时,返回null.无参tryLock()默认为独占锁;有参tryLock(0L, Long.MAX_VALUE, true)为共享锁