/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.pcp;

import java.util.concurrent.ArrayBlockingQueue;

public class Storage { 
    private final ArrayBlockingQueue<Integer> queue;
    
    private int fetchedCounter;
    private int storedCounter;
    private int underflowCounter;
    private int overflowCounter;
    private boolean productionComplete;
    
    public Storage() {
        // implement this
    }
    
    public synchronized boolean put(Integer data) throws InterruptedException {
        // implement this
        return false;
    }
 
    public synchronized Integer get() {
        // implement this
        return null;
    }

    public boolean isProductionComplete() {
        // implement this
        return false;
    }

    public void setProductionComplete() {
        // implement this
    }

    public int getFetchedCounter() {
        // implement this
        return -1;
    }

    public int getStoredCounter() {
        // implement this
        return -1;
    }

    public int getUnderflowCounter() {
        // implement this
        return -1;
    }

    public int getOverflowCounter() {
        // implement this
        return -1;
    }
}
