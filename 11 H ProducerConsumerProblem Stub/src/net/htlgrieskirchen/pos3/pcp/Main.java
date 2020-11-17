package net.htlgrieskirchen.pos3.pcp;

public class Main {
    public static void main(String[] args) {
        try {
            Executor runner = new Executor();
            runner.runExample();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}