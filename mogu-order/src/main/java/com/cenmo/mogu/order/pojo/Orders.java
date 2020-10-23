package com.cenmo.mogu.order.pojo;

import com.cenmo.mogu.pojo.Order;
import com.cenmo.mogu.pojo.OrderItem;
import com.cenmo.mogu.pojo.OrderShipping;
import lombok.Data;

import java.util.List;

/**
 * @author Cenmo
 * @Date 2020-10-23 2020/10/23
 */
@Data
public class Orders {

    private Order order;
    private List<OrderItem> orderItems;
    private OrderShipping orderShipping;
}
