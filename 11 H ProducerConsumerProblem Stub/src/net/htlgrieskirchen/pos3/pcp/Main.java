/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.pcp;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Executor runner = new Executor();
            
            runner.runExample();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
