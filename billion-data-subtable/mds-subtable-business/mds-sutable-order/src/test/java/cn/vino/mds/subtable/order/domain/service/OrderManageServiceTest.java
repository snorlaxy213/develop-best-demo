package cn.vino.mds.subtable.order.domain.service;

import cn.hutool.core.util.IdUtil;
import cn.vino.mds.subtable.api.common.constant.BusinessCode;
import cn.vino.mds.subtable.api.order.request.OrderCreateRequest;
import cn.vino.mds.subtable.order.domain.OrderBaseTest;
import cn.vino.mds.subtable.order.domain.entity.TradeOrder;
import cn.vino.mds.subtable.order.infrastructure.mapper.OrderMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author vino
 */
public class OrderManageServiceTest extends OrderBaseTest {
    @Autowired
    OrderManageService orderService;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderReadService orderReadService;

    @Test
    public void create() {
        OrderCreateRequest orderCreateRequest = orderCreateRequest();

        String orderId = orderService.create(orderCreateRequest).getOrderId();
        Assert.assertNotNull(orderId);
    }

    @Test
    public void createAndQuery() {
        OrderCreateRequest orderCreateRequest = orderCreateRequest();

        String orderId = orderService.create(orderCreateRequest).getOrderId();
        System.out.println(orderId);

        QueryWrapper<TradeOrder> queryWrapper = new QueryWrapper();
        queryWrapper.eq("order_id", orderId);
        TradeOrder tradeOrder = orderService.getOne(queryWrapper);
        Assert.assertNotNull(tradeOrder);

        queryWrapper = new QueryWrapper();
        queryWrapper.eq("buyer_id", orderCreateRequest.getBuyerId());
        tradeOrder = orderService.getOne(queryWrapper);
        Assert.assertNotNull(tradeOrder);

        queryWrapper = new QueryWrapper();
        queryWrapper.eq("seller_id", orderCreateRequest.getSellerId());
        tradeOrder = orderService.getOne(queryWrapper);
        Assert.assertNotNull(tradeOrder);
    }
    
    @Test
    public void testUid() {
        System.out.println(IdUtil.getSnowflake(BusinessCode.TRADE_ORDER.code()).nextId());
    }

}