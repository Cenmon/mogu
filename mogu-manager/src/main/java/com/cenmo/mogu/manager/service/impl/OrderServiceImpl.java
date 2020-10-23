package com.cenmo.mogu.manager.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cenmo.mogu.common.vo.EUDataGridResult;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.Order;
import com.cenmo.mogu.mapper.OrderMapper;
import com.cenmo.mogu.manager.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public EUDataGridResult getOrderList(int page, int rows) {
        //自定义mapper.xml，获取所需的orderList
        Page<Order> orderPage = new Page<>(page,rows);
        IPage<Order> iPage = baseMapper.selectPage(orderPage, null);

        EUDataGridResult result = new EUDataGridResult();
        result.setTotal(iPage.getTotal());
        result.setRows(iPage.getRecords());
        return result;
    }

    @Override
    public MoguResult deleteOrders(long[] orderIds) {
        for (long orderId : orderIds) {
//            orderListMapper.deleteOrderById(orderId);
        }
        return MoguResult.ok();
    }
}
