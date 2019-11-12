package com.wangkang.config;

import com.wangkang.util.ConfigUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 17:32 2019/1/21
 * @Modified By:
 */
@Component
public class MyCacheManager implements CommandLineRunner {
    @Autowired
    CacheManager cacheManager;

    @Override
    public void run(String... strings) throws Exception {

        //读取配置文件，如果值为true的话，设置事务cache
        if (cacheManager instanceof AbstractTransactionSupportingCacheManager) {
            Properties properties = ConfigUtil.getProperties("application.properties");
            String transaction = properties.getProperty("ehcache_transaction_aware");
            if ("true".equals(transaction)) {
                ((AbstractTransactionSupportingCacheManager) cacheManager).setTransactionAware(true);
            }
        }
    }
}
