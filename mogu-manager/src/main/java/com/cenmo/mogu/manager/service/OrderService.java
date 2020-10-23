package com.cenmo.mogu.manager.service;

import com.cenmo.mogu.common.vo.EUDataGridResult;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
public interface OrderService extends IService<Order> {

    EUDataGridResult getOrderList(int page, int rows);

    MoguResult deleteOrders(long[] orderIds);
}
