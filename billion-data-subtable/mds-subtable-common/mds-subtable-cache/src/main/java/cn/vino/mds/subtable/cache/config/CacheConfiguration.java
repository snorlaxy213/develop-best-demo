package cn.vino.mds.subtable.cache.config;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.context.annotation.Configuration;

/**
 * 缓存配置
 *
 * @author vino
 */
@Configuration
@EnableMethodCache(basePackages = "cn.vino.mds.subtable")
public class CacheConfiguration {
}
