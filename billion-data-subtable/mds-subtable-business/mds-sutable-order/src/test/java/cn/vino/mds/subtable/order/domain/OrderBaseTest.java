package cn.vino.mds.subtable.order.domain;

import cn.hutool.core.util.RandomUtil;
import cn.vino.mds.subtable.api.common.constant.BusinessCode;
import cn.vino.mds.subtable.api.goods.constant.GoodsType;
import cn.vino.mds.subtable.api.order.request.OrderCreateRequest;
import cn.vino.mds.subtable.order.MdsSubtableOrderApplication;
import cn.vino.mds.subtable.order.sharding.id.DistributeID;
import cn.vino.mds.subtable.order.sharding.id.WorkerIdHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author vino
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MdsSubtableOrderApplication.class})
@ActiveProfiles("test")
public class OrderBaseTest {
    
    @MockBean
    private RedissonClient redissonClient;

    @MockBean
    private WorkerIdHolder workerIdHolder;

    @Test
    public void test(){

    }

    protected OrderCreateRequest orderCreateRequest() {
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest();
        String buyerId = String.valueOf(UUID.randomUUID().hashCode());
        orderCreateRequest.setBuyerId(buyerId);
        orderCreateRequest.setSellerId(UUID.randomUUID().toString().substring(0, 10));
        orderCreateRequest.setGoodsId(RandomUtil.randomNumbers(5));
        orderCreateRequest.setGoodsName(UUID.randomUUID().toString());
        orderCreateRequest.setGoodsType(GoodsType.BLIND_BOX);
        orderCreateRequest.setOrderAmount(new BigDecimal("20233.33"));
        orderCreateRequest.setIdentifier(UUID.randomUUID().toString());
        orderCreateRequest.setItemPrice(new BigDecimal("3212"));
        orderCreateRequest.setItemCount(1);
        orderCreateRequest.setOrderId(DistributeID.generateWithSnowflake(BusinessCode.TRADE_ORDER, WorkerIdHolder.WORKER_ID, buyerId));
        return orderCreateRequest;
    }
}
