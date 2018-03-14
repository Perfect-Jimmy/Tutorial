#### Buffer 
缓冲区本质上是一块可以写入数据,然后可以从中读取数据的内存.这块内存被包装成NIO Buffer对象,并提供了一组方法用来方便的访问该块内存.

#### 读
* flip():从写模式切换到读模式;将position设回0,并将limit设置成之前position的值
* get():读取数据
* rewind():position设回0,所以可以重读Buffer中的所有数据;limit保持不变,仍然表示能从Buffer中读取多少个元素(byte、char等)
* mark():可以标记Buffer中的一个特定position
* reset():恢复到Buffer.mark()标记时的position
* clear():清空整个缓冲区,position将被设回0,limit被设置成capacity的值
* compact():只会清除已经读过的数据;任何未读的数据都被移到缓冲区的起始处,新写入的数据将放到缓冲区未读数据的后面;将position设到最后一个未读元素正后面,limit被设置成capacity的值

#### 写
* Buffer的put(127).
* 从Channel写到Buffer.Channel的read()方法


#### 流程
当向buffer写入数据时,Buffer会记录下写了多少数据.一旦要读取数据,需要先通过flip()方法将Buffer从写模式切换到读模式,
在读模式下可以读取之前写入到buffer的所有数据.  
一旦读完了所有的数据,就需要清空缓冲区,让它可以再次被写入.  
有两种方式能清空缓冲区:调用clear()或compact()方法. 


#### Buffer 分配
想获得一个Buffer对象首先要进行分配,每一个Buffer类都有一个allocate方法.

#### equals()与compareTo()方法
* equals()方法:满足如下条件时表示两个Buffer相等
1. 有相同的类型(byte、char、int等)
2. Buffer中剩余的byte、char等的个数相等
3. Buffer中所有剩余的byte、char等都相同
*equals只是比较Buffer的一部分,不是每一个在它里面的元素都比较.实际上它只比较Buffer中的剩余元素*

* compareTo()方法:比较两个Buffer的剩余元素(byte、char等),如果满足下列条件,则认为一个Buffer"小于"另一个Buffer
1. 第一个不相等的元素小于另一个Buffer中对应的元素
2. 所有元素都相等,但第一个Buffer比另一个先耗尽(第一个Buffer的元素个数比另一个少)
*剩余元素是从position到limit之间的元素*
