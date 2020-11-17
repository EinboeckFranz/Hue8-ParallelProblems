package net.htlgrieskirchen.pos3.pcp;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;


public class Consumer implements Runnable {
    private final String name;
    private final Storage storage;
    private final int sleepTime;
    
    private final List<Integer> received;
    private boolean running;
    
    public Consumer(String name, Storage storage, int sleepTime) {
        this.name = name;
        this.storage = storage;
        this.sleepTime = sleepTime;
        received = new ArrayList<>();
    }
 
    @Override
    public void run() {
        while(!(storage.isProductionComplete()&&storage.getStoredCounter()==storage.getFetchedCounter())){
            Integer get = storage.get();
            if(get!=null)
                received.add(get);
            try {
                sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Integer> getReceived() {
        return this.received;
    }
}

