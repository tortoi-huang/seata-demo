# seata-demo
分布式事务Seata测试项目  
在本例中，我们从client发起事务，先调用库存服务`svr-stock` 扣减库存，然后 调用`svr-account` 扣减金额， 其中 `svr-account`服务会调用积分服务`svr-score`增加积分。