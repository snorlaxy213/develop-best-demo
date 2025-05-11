package cn.vino.mds.subtable.api.order.request;

import cn.vino.mds.subtable.api.order.constant.TradeOrderEvent;
import lombok.Getter;
import lombok.Setter;

/**
 * @author vino
 */
@Getter
@Setter
public class OrderFinishRequest extends BaseOrderUpdateRequest {

    @Override
    public TradeOrderEvent getOrderEvent() {
        return TradeOrderEvent.FINISH;
    }
}

