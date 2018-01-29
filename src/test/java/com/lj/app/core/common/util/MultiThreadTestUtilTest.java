package com.lj.app.core.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class MultiThreadTestUtilTest {

  private static Log logger = LogFactory.getLog(MultiThreadTestUtilTest.class);
  
	private AtomicInteger executedCount = new AtomicInteger();
	int expectedCount = 2000;
	
	@Test
	public void testExecute() throws InterruptedException {
		CountDownLatch doneSignel = MultiThreadTestUtil.execute(expectedCount, new Runnable() {
			public void run() {
				executedCount.getAndIncrement();
			}
		});
		
		doneSignel.await();
		
		assertEquals(expectedCount,executedCount.intValue());
	}
	
	@Test
	public void testExecuteFail() throws InterruptedException {
		CountDownLatch doneSignel = MultiThreadTestUtil.execute(expectedCount, new Runnable() {
			public void run() {
				executedCount.getAndIncrement();
			}
		});
		
		System.out.println(executedCount);
		assertTrue(executedCount.intValue() < expectedCount);
	}
	
	@Test
	public void testexecuteAndWaitForDone() throws InterruptedException {
		
		long costTime = MultiThreadTestUtil.executeAndWait(expectedCount, new Runnable() {
			public void run() {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					logger.error(e);
				}
			}
		});
		
		System.out.println("costTime:"+costTime);
		assertTrue(costTime > 0);
	}
	
	@Test
	public void testMultiThreadPermenece() throws InterruptedException {
		Map map = new TreeMap();
		int steps = 100;
		for(int i = 1; i < 3000; i = i + steps) {
			steps = steps + (int)(steps * 0.2);
			long costTime = execute(i);
			System.out.println("threadCount:"+ i +" costTime:"+costTime+" nextStep:"+steps);
			map.put(costTime,i);
		}
		System.out.println(map);
	}

	long MAX_COUNT = 10000;
	
	private long execute(int threadCount) throws InterruptedException {
		final AtomicLong count = new AtomicLong(0);
		long costTime = MultiThreadTestUtil.executeAndWait(threadCount, new Runnable() {
			int selfCount = 0;
			public void run() {
				while(true) {
					if(count.incrementAndGet() > MAX_COUNT) {
						return;
					}
					for(int i = 0; i < 500; i++) {
					}
				}
			}
		});
		return costTime;
}

}
