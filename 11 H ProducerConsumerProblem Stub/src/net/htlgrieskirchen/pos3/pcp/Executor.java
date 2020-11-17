/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.pcp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author rsickinger
 */
public class Executor {

    public Result runExample() throws Exception {
        return this.run(500, 500, 100, 20);
    }

    public Result run(int t1, int t2, int t3, int t4) throws Exception {
        Storage s = new Storage();

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        Consumer c1 = new Consumer("C1", s, t1);
        Consumer c2 = new Consumer("C2", s, t2);

        threadPool.execute(c1);
        threadPool.execute(c2);

        Producer p = new Producer("P", s, t3, t4);
       
        Future producerStatus1 = threadPool.submit(p);
        producerStatus1.get();

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Customer 1 received     : " + c1.getReceived());
        System.out.println("Customer 2 received     : " + c2.getReceived());
        System.out.println("Producer sent           : " + p.getSent());

        System.out.println("Store stored counter    : " + s.getStoredCounter());
        System.out.println("Store fetched counter   : " + s.getFetchedCounter());

        System.out.println("Store underflow counter : " + s.getUnderflowCounter());
        System.out.println("Store overflow counter  : " + s.getOverflowCounter());

        Result result = new Result();
        result.setReceived1(c1.getReceived());
        result.setReceived2(c2.getReceived());
        result.setProducerSent(p.getSent());
        result.setStoredCounter(s.getStoredCounter());
        result.setFetchedCounter(s.getFetchedCounter());
        result.setUnderflowCounter(s.getUnderflowCounter());
        result.setOverflowCounter(s.getOverflowCounter());

        return result;
    }
}
