package cn.vino.mds.subtable.order.domain.listener.event;

import cn.vino.mds.subtable.order.domain.entity.TradeOrder;
import org.springframework.context.ApplicationEvent;

/**
 * @author vino
 */
public class OrderCreateEvent extends ApplicationEvent {

    public OrderCreateEvent(TradeOrder tradeOrder) {
        super(tradeOrder);
    }
}
