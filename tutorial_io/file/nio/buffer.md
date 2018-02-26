#### Buffer 

#### 读
* flip():从写模式切换到读模式;将position设回0,并将limit设置成之前position的值
* get():读取数据
* rewind():position设回0,所以可以重读Buffer中的所有数据;limit保持不变,仍然表示能从Buffer中读取多少个元素(byte、char等)
* mark():可以标记Buffer中的一个特定position
* reset():恢复到Buffer.mark()标记时的position
* clear():清空整个缓冲区,position将被设回0,limit被设置成capacity的值
* compact():只会清除已经读过的数据;任何未读的数据都被移到缓冲区的起始处,新写入的数据将放到缓冲区未读数据的后面;将position设到最后一个未读元素正后面,limit被设置成capacity的值

#### 写
* put(127)


