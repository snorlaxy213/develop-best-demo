package cn.vino.mds.subtable.order.domain.entity;


import cn.vino.mds.subtable.api.goods.constant.GoodsType;
import cn.vino.mds.subtable.api.order.constant.TradeOrderState;
import cn.vino.mds.subtable.api.order.request.OrderConfirmRequest;
import cn.vino.mds.subtable.api.order.request.OrderCreateRequest;
import cn.vino.mds.subtable.api.order.request.OrderFinishRequest;
import cn.vino.mds.subtable.api.order.request.OrderPayRequest;
import cn.vino.mds.subtable.api.pay.constant.PayChannel;
import com.alibaba.fastjson2.JSON;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * @author vino
 */
public class TradeOrderTest {

    @Test
    public void createOrder() {
        OrderCreateRequest orderCreateRequest = orderCreateRequest();
        TradeOrder tradeOrder = TradeOrder.createOrder(orderCreateRequest);

        System.out.println(JSON.toJSONString(tradeOrder));
        Assert.assertEquals(orderCreateRequest.getItemCount(), tradeOrder.getItemCount());
        Assert.assertEquals(orderCreateRequest.getItemPrice().compareTo(tradeOrder.getItemPrice()), 0);
        Assert.assertEquals(orderCreateRequest.getBuyerId(), tradeOrder.getBuyerId());
        Assert.assertEquals(orderCreateRequest.getBuyerType(), tradeOrder.getBuyerType());
        Assert.assertEquals(orderCreateRequest.getSellerId(), tradeOrder.getSellerId());
        Assert.assertEquals(orderCreateRequest.getSellerType(), tradeOrder.getSellerType());
        Assert.assertEquals(orderCreateRequest.getGoodsId(), tradeOrder.getGoodsId());
        Assert.assertEquals(orderCreateRequest.getGoodsName(), tradeOrder.getGoodsName());
        Assert.assertEquals(orderCreateRequest.getGoodsType(), tradeOrder.getGoodsType());
        Assert.assertEquals(orderCreateRequest.getOrderAmount(), tradeOrder.getOrderAmount());
        Assert.assertEquals(orderCreateRequest.getSnapshotVersion(), tradeOrder.getSnapshotVersion());
        Assert.assertEquals(orderCreateRequest.getGoodsPicUrl(), tradeOrder.getGoodsPicUrl());
        Assert.assertNotNull(tradeOrder.getOrderId());
        Assert.assertNull(tradeOrder.getOrderFinishedTime());
        Assert.assertNull(tradeOrder.getOrderConfirmedTime());
        Assert.assertNull(tradeOrder.getPayChannel());
        Assert.assertNull(tradeOrder.getPayStreamId());
        Assert.assertNull(tradeOrder.getPaySucceedTime());
        Assert.assertEquals(tradeOrder.getOrderState(), TradeOrderState.CREATE);
    }

    @Test
    public void confirm() {
        OrderCreateRequest orderCreateRequest = orderCreateRequest();
        TradeOrder tradeOrder = TradeOrder.createOrder(orderCreateRequest);

        OrderConfirmRequest orderConfirmRequest = new OrderConfirmRequest();
        orderConfirmRequest.setIdentifier(UUID.randomUUID().toString());
        orderConfirmRequest.setOperateTime(new Date());
        tradeOrder.confirm(orderConfirmRequest);

        Assert.assertEquals(tradeOrder.getOrderState(), TradeOrderState.CONFIRM);
        Assert.assertNotNull(tradeOrder.getOrderConfirmedTime());
    }
    

    @Test
    public void finish() {
        OrderCreateRequest orderCreateRequest = orderCreateRequest();
        TradeOrder tradeOrder = TradeOrder.createOrder(orderCreateRequest);

        Assert.assertNull(tradeOrder.getOrderConfirmedTime());
        Assert.assertEquals(tradeOrder.getOrderState(), TradeOrderState.CREATE);

        OrderConfirmRequest orderConfirmRequest = new OrderConfirmRequest();
        orderConfirmRequest.setIdentifier(UUID.randomUUID().toString());
        orderConfirmRequest.setOperateTime(new Date());
        tradeOrder.confirm(orderConfirmRequest);

        OrderPayRequest orderPayRequest = new OrderPayRequest();
        orderPayRequest.setOperateTime(new Date());
        orderPayRequest.setPayChannel(PayChannel.ALIPAY);
        orderPayRequest.setPayStreamId("dsadasdsa");
        orderPayRequest.setAmount(new BigDecimal("2312.32"));

        tradeOrder.paySuccess(orderPayRequest);

        OrderFinishRequest orderFinishRequest = new OrderFinishRequest();
        orderFinishRequest.setOperateTime(new Date());

        tradeOrder.finish(orderFinishRequest);

        Assert.assertEquals(tradeOrder.getOrderFinishedTime().compareTo(orderFinishRequest.getOperateTime()), 0);
        Assert.assertEquals(tradeOrder.getOrderState(), TradeOrderState.FINISH);
    }

    private OrderCreateRequest orderCreateRequest() {
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest();
        orderCreateRequest.setBuyerId("2312321");
        orderCreateRequest.setSellerId("67546456");
        orderCreateRequest.setGoodsId("909090");
        orderCreateRequest.setGoodsName("测试商品名称");
        orderCreateRequest.setGoodsType(GoodsType.BLIND_BOX);
        orderCreateRequest.setOrderAmount(new BigDecimal("20233.33"));
        orderCreateRequest.setItemCount(2);
        orderCreateRequest.setItemPrice(new BigDecimal("21312"));
        orderCreateRequest.setSnapshotVersion(123);
        orderCreateRequest.setOrderId(UUID.randomUUID().toString());
        orderCreateRequest.setGoodsPicUrl("http://www.nft.com/pic");
        return orderCreateRequest;
    }
}