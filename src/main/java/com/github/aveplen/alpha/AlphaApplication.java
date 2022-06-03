package com.github.aveplen.alpha;

import com.github.aveplen.alpha.cron.OxrCacheEvicter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableAsync
@EnableCaching
@EnableScheduling
@RequiredArgsConstructor
public class AlphaApplication {

	private final OxrCacheEvicter oxrCacheEvicter;

	public static void main(String[] args) {
		SpringApplication.run(AlphaApplication.class, args);
	}
}
