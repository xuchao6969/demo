# seata-server

优先启动nacos注册中心 !!!
如果使用的seata服务端是官网下载的编译好的二进制压缩包比如说:seata-server-1.6.1 启动，则这个module可以不用启动

# 简介
* jdk8
* spring-cloud.version:  Hoxton.SR12
* spring-cloud-alibaba.version: 2.2.9.RELEASE
* springboot.version: 2.3.12.RELEASE
* seata.version: 1.5.2
* 其他依赖版本看pom具体细节

# 说明
注意主启动类 引入的是jar包中的SpringApplication.run(io.seata.server.ServerApplication.class, args);
不是自己定义的Class !!!
