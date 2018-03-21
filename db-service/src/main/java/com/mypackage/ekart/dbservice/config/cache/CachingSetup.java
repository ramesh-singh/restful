package com.mypackage.ekart.dbservice.config.cache;

import java.util.concurrent.TimeUnit;

import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.TouchedExpiryPolicy;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;

public class CachingSetup implements JCacheManagerCustomizer {

	@Override
	public void customize(CacheManager cacheManager) {
		 cacheManager.createCache("customer", new MutableConfiguration<>()  
			        .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new javax.cache.expiry.Duration(TimeUnit.SECONDS, 10)))
			        .setStoreByValue(false)
			        .setStatisticsEnabled(true));
		
	}

}
