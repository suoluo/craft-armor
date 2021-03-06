package io.craft.armor;

import io.craft.armor.spi.ArmorFilterChain;
import io.craft.atom.util.thread.NamedThreadFactory;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author mindwind
 * @version 1.0, Dec 25, 2014
 */
@ToString
public class DefaultArmorAttribute implements ArmorAttribute {
	
	
	@Getter @Setter private volatile boolean            degrade        ;
	@Getter @Setter private volatile boolean            async          ;
	@Getter @Setter private volatile long               timeoutInMillis;
	@Getter @Setter private volatile int                threads        ;
	@Getter @Setter private volatile ThreadPoolExecutor executorService;
	@Getter @Setter private volatile ArmorFilterChain   filterChain    ;
	
	
	public DefaultArmorAttribute() {
		timeoutInMillis = DEFAULT_TIMEOUT * 1000;
		threads         = DEFAULT_THREADS;
		executorService = new ThreadPoolExecutor(1, threads, 60, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new NamedThreadFactory("craft-armor"));
		executorService.allowCoreThreadTimeOut(true);
	}
	
	
	// ~ -------------------------------------------------------------------------------------------------------------


	@Override
	public void setThreadSize(int threads) {
		this.threads = threads;
		executorService.setMaximumPoolSize(threads);
		executorService.setCorePoolSize(threads);
	}

	@Override
	public int getThreadSize() {
		return executorService.getMaximumPoolSize();
	}

	@Override
	public void degrade() {
		this.degrade = true;
	}

	@Override
	public void upgrade() {
		this.degrade = false;
	}

	@Override
	public boolean isDegraded() {
		return degrade;
	}

}
