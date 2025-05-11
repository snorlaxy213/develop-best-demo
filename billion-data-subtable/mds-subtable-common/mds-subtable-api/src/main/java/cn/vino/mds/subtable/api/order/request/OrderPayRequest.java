package cn.vino.mds.subtable.api.order.request;

import cn.vino.mds.subtable.api.order.constant.TradeOrderEvent;
import cn.vino.mds.subtable.api.pay.constant.PayChannel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author vino
 */
@Getter
@Setter
public class OrderPayRequest extends BaseOrderUpdateRequest {

    /**
     * 支付方式
     */
    private PayChannel payChannel;

    /**
     * 支付流水号
     */
    private String payStreamId;

    /**
     * 支付金额
     */
    private BigDecimal amount;

    @Override
    public TradeOrderEvent getOrderEvent() {
        return TradeOrderEvent.PAY;
    }
}

