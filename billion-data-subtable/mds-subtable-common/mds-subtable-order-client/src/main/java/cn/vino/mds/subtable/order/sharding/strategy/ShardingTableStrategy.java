package cn.vino.mds.subtable.order.sharding.strategy;

/**
 * @author vino
 */
public interface ShardingTableStrategy {

    /**
     * 获取分表结果
     *
     * @param externalId 外部id
     * @param tableCount 表数量
     * @return 分表结果
     */
    public int getTable(String externalId, int tableCount);
}