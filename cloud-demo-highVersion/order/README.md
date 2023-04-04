# 订单模块

# 简介
* jdk8
* spring-cloud.version:  Hoxton.SR12
* spring-cloud-alibaba.version: 2.2.9.RELEASE
* springboot.version: 2.3.12.RELEASE
* 其他依赖版本看pom具体细节

# 说明
核心业务入口: OrderController  createOrder方法
核心业务: OrderService
依赖业务: 
* FeignProductService 查询商品 扣减库存
  ProductFeignErrorService 降级回调

* FeignUserService 扣减账户余额
  UserFeignErrorService 降级回调
* 业务切面拦截: TestAspect

如果业务系统使用的FeignClient开启了sentinel或hixtrix等服务降级，没有使用业务切面拦截则会造成seata事务不会回滚

解决方案就是加上业务切面拦截: TestAspect,并且在降级方法中进行改造,比如说正常业务返回1 进入了降级方法说明业务调用
链路发生了错误,降级方法返回0,在createOrder方法中拿到返回结果如果是0,则抛异常让事务回滚,同时中断后续链路的方法调用,
如果是1，则继续后续的调用。针对网络超时这种错误也是比较好的解决方法,超时就走降级方法，在主业务中对结果判断，不满足就
中断后续链路
