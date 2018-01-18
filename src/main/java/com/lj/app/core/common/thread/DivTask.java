package com.lj.app.core.common.thread;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 除任务
 */
public class DivTask implements Runnable {

  private int a;
  private int b;

  public DivTask(int a, int b) {
    this.a = a;
    this.b = b;
  }

  @Override
  public void run() {
    double re = a / b;
    System.out.println("re=" + re);
  }

  /**
   * 
   * @param args 参数
   * @throws Exception 异常
   */
  public static void main(String[] args) throws Exception {

    ThreadPoolExecutor pools = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS,
        new SynchronousQueue<Runnable>());

    for (int i = 0; i < 5; i++) {
      pools.execute(new DivTask(100, i));
    }
  }
}
