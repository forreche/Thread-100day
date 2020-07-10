多线程读写锁分离设计模式 
主要知识点：
1.waitset
2.wait
3.notifyAll
4.solution
5.ReaderWriterLock
6.BooleanLock
7.synchronized


booleanLock好处
同一份数据 如果多个线程同一时间读写时
只能有一个线程在读 只有这个线程读完 其他才能读
1.read other thread will enter to the waitset
2.read write锁分离


concern conflict 
1.read read 并行化
2.read write 不允许
3.write write 不允许
