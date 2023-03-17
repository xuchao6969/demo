package com.xc.optimisticlock.service;

import com.xc.optimisticlock.entity.NumberSource;
import com.xc.optimisticlock.entity.Order;
import com.xc.optimisticlock.mapper.NumberSourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NumberSourceService {
    @Autowired
    private NumberSourceMapper mapper;

    /**
     * 挂号 扣减号源 增加订单
     * @param did 医生id
     * @param uid 用户id
     * @return SUCCESS 1 FAILURE 0
     */
    @Transactional
    public int register(int did, int uid) {

        // 获取号源信息
        NumberSource ns = mapper.getNumberSource(did);
        // 号源库存大于0 可以挂号
        if (ns.getStock() > 0) {
            // 乐观锁 扣减库存
            int update = mapper.decreaseNumberSourceStockByIdAndVersion(did, ns.getVersion());
            // 更新失败，说明其他线程已经修改过数据，本次扣减库存失败，可以重试一定次数或者返回
            if (update == 0) {
                return 0;
            }
            // 库存扣减成功，生成订单
            Order order = new Order();
            order.setUid(uid);
            order.setDid(did);
            int result = mapper.insertOrder(order);
            return result;
        }
        // 失败返回
        return 0;
    }
}
