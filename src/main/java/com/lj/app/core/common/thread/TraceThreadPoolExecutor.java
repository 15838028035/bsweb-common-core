package com.lj.app.core.common.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 自定义线程池
 *
 */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor {

  private static Log logger = LogFactory.getLog(TraceThreadPoolExecutor.class);
  
  public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
      BlockingQueue<Runnable> workQueue) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
  }

  @Override
  public void execute(Runnable task) {
    super.execute(wrap(task, clientTrace(), Thread.currentThread().getName()));
  }

  @Override
  public Future<?> submit(Runnable task) {
    return super.submit(wrap(task, clientTrace(), Thread.currentThread().getName()));
  }

  private Exception clientTrace() {
    return new Exception("Client stack trace");
  }

  private Runnable wrap(final Runnable task, final Exception clientStack, final String clientTreadName) {
    return new Runnable() {
      @Override
      public void run() {
        try {
          task.run();
        } catch (Exception e) {
          logger.error(e);
          // throw e;
        }
      }
    };
  }
}
