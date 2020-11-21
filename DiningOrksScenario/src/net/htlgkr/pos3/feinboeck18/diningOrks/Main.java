package net.htlgkr.pos3.feinboeck18.diningOrks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private int orkCount = 5;
    private List<Dagger> daggers = new ArrayList<>(orkCount);
    private List<Ork> orks = new ArrayList<>(orkCount);

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    void run() {
        for (int i = 0; i < orkCount; i++)
            daggers.add(new Dagger(i+1));

        for (int i = 0; i < orkCount; i++) {
            int nextDagger = ((i+1) == orkCount) ? 0 : i+1;
            orks.add(new Ork(i+1, daggers.get(i), daggers.get(nextDagger)));
        }

        orks.forEach(ork -> new Thread(ork).start());
    }
}
record Ork(int orKNr, Dagger leftDagger, Dagger rightDagger) implements Runnable {

    @Override
    public void run() {
        boolean hasDagger;
        while (true) {
            //Drinking
            drinkingOrFeasting(1);
            //Picking Up Left Dagger
            do {
                synchronized(this) {
                    hasDagger = leftDagger.takeDagger(this.orKNr);
                }
            } while(!hasDagger);
            System.out.println("Ork " + orKNr + " took Dagger " + this.leftDagger.getDaggerNr());
            //Picking Up Right Dagger
            do {
                synchronized (this) {
                    hasDagger = rightDagger.takeDagger(this.orKNr);
                }
            } while (!hasDagger);
            System.out.println("Ork " + orKNr + " took Dagger " + this.rightDagger.getDaggerNr());
            //Eating
            drinkingOrFeasting(2);
            //Putting Down Both Daggers
            System.out.println("Ork " + orKNr + " puts down both daggers");
            this.leftDagger.putDaggerDown();
            this.rightDagger.putDaggerDown();
        }
    }

    //Parameter Option -> 1 = drinking - 2 = feasting
    private void drinkingOrFeasting(int option) {
        Random random = new Random();
        System.out.println("Ork " + orKNr + " is " + ((option == 1) ? "drinking" : "feasting"));
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Dagger {
    private int daggerNr;
    private boolean onDesk;
    private int lastOrkNr;

    public Dagger(int daggerNr) {
        this.daggerNr = daggerNr;
        onDesk = true;
        lastOrkNr = -1;
    }

    public boolean takeDagger(int orkNr) {
        if(lastOrkNr == orkNr)
            return false;
        if(onDesk) {
            lastOrkNr = orkNr;
            onDesk = false;
            return true;
        }
        return false;
    }

    public void putDaggerDown() {
        onDesk = true;
    }

    public int getDaggerNr() {
        return this.daggerNr;
    }
}