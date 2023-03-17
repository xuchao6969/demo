# Optimistic Lock Concurrency Control Demo

# 简介
* base on springboot 2.7.5
* jdk8
* 基于乐观锁实现的一个解决预约挂号,号源超卖的demo

# sql脚本

CREATE TABLE `number_source` (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '医生id',
`name` varchar(50) DEFAULT NULL COMMENT '医生姓名',
`stock` int(11) DEFAULT '0' COMMENT '号源数量',
`version` int(11) DEFAULT '0' COMMENT '并发版本控制',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '号源表';

INSERT INTO `number_source` VALUES (1, '张三', 10, 0);
INSERT INTO `number_source` VALUES (2, '李四', 10, 0);


CREATE TABLE `order` (
`id` int(11) AUTO_INCREMENT,
`uid` int(11) COMMENT '用户id',
`did` int(11) COMMENT '医生id',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '订单表';

## 其他
主要代码位于src/test/java/com/xc/optimisticlock

测试代码位于src/test/java/com/xc/optimisticlock/OptimisticLockApplicationTests.java