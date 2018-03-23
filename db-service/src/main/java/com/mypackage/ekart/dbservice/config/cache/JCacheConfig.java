package com.mypackage.ekart.dbservice.config.cache;

import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;

public class JCacheConfig {
	
	public JCacheManagerCustomizer cacheCustomizer(){
		return new JCacheManagerCustomizer() {
			
			@Override
			public void customize(CacheManager cacheManager) {
				MutableConfiguration<Object, Object> config= new MutableConfiguration<Object, Object>();
				config.setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(Duration.TEN_MINUTES));
				config.setStatisticsEnabled(true);
				cacheManager.createCache("customer", config);
				cacheManager.createCache("product", config);
				cacheManager.createCache("order", config);
				
			}
		};
	}

}
