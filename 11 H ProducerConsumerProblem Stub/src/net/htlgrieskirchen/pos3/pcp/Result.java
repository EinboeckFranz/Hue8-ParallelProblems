/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.pcp;

import java.util.List;

/**
 *
 * @author rsickinger
 */
public class Result {
    private List<Integer> received1;
    private List<Integer> received2;
    private List<Integer> producerSent;
    private int storedCounter;
    private int fetchedCounter;
    private int underflowCounter;
    private int overflowCounter;

    public List<Integer> getReceived1() {
        return received1;
    }

    public void setReceived1(List<Integer> received1) {
        this.received1 = received1;
    }

    public List<Integer> getReceived2() {
        return received2;
    }

    public void setReceived2(List<Integer> received2) {
        this.received2 = received2;
    }

    public List<Integer> getProducerSent() {
        return producerSent;
    }

    public void setProducerSent(List<Integer> producerSent) {
        this.producerSent = producerSent;
    }

    public int getStoredCounter() {
        return storedCounter;
    }

    public void setStoredCounter(int storedCounter) {
        this.storedCounter = storedCounter;
    }

    public int getFetchedCounter() {
        return fetchedCounter;
    }

    public void setFetchedCounter(int fetchedCounter) {
        this.fetchedCounter = fetchedCounter;
    }

    public int getUnderflowCounter() {
        return underflowCounter;
    }

    public void setUnderflowCounter(int underflowCounter) {
        this.underflowCounter = underflowCounter;
    }

    public int getOverflowCounter() {
        return overflowCounter;
    }

    public void setOverflowCounter(int overflowCounter) {
        this.overflowCounter = overflowCounter;
    }


    
    
}
