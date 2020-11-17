/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.pcp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author rsickinger
 */
public class ExecutorTest {

    @Test
    public void testRun1_1() throws Exception {
        Executor runner = new Executor();

        Result result = runner.run(500, 500, 100, 20);

        testBoundaries(result, 20);
    }

    @Test
    public void testRun1_2() throws Exception {
        Executor runner = new Executor();

        Result result = runner.run(500, 500, 100, 20);

        testProducerSent(result, 20);
    }

    @Test
    public void testRun1_3() throws Exception {
        Executor runner = new Executor();

        Result result = runner.run(500, 500, 100, 20);

        testProducerSentSize(result);
    }

    @Test
    public void testRun1_4() throws Exception {
        Executor runner = new Executor();

        Result result = runner.run(500, 500, 100, 20);

        testListEquality(result, 20);
    }

    @Test
    public void testRun2_1() throws Exception {
        Executor runner = new Executor();

        Result result = runner.run(10, 10, 50, 100);

        assertEquals(result.getStoredCounter(), 100);
        assertEquals(result.getFetchedCounter(), 100);
        assertTrue(result.getUnderflowCounter() > 0);
    }

    @Test
    public void testRun2_2() throws Exception {
        Executor runner = new Executor();

        Result result = runner.run(10, 10, 50, 100);

        testProducerSent(result, 100);
    }

    @Test
    public void testRun2_3() throws Exception {
        Executor runner = new Executor();

        Result result = runner.run(10, 10, 50, 100);

        testProducerSentSize(result);
    }

    @Test
    public void testRun2_4() throws Exception {
        Executor runner = new Executor();

        Result result = runner.run(10, 10, 50, 100);

        testListEquality(result, 100);
    }

    @Test
    public void testRun3() throws Exception {
        Executor runner = new Executor();

        Result result = runner.run(1, 1, 1, 2);

        testListEquality(result, 2);
    }

    @Test
    public void testRun4() throws Exception {
        Executor runner = new Executor();

        Result result = runner.run(0, 0, 0, 100);

        testListEquality(result, 100);
        assertTrue(result.getUnderflowCounter() > 0);
    }

    @Test
    public void testRun5() throws Exception {
        Executor runner = new Executor();

        Result result = runner.run(5, 5, 0, 100);

        testListEquality(result, 100);
        assertTrue(result.getOverflowCounter() > 0);
    }

    private void testProducerSent(Result result, int n) {
        List<Integer> ret = IntStream.rangeClosed(0, n - 1).boxed().collect(Collectors.toList());

        assertEquals(result.getProducerSent().size(), n);

        for (int i = 0; i < n; i++) {
            assertEquals(result.getProducerSent().get(i), ret.get(i));
        }
    }

    private void testBoundaries(Result result, int n) {
        assertEquals(result.getStoredCounter(), n);
        assertEquals(result.getFetchedCounter(), n);
        assertTrue(result.getUnderflowCounter() > 0);
        assertTrue(result.getOverflowCounter() > 0);
    }

    private void testProducerSentSize(Result result) {
        List<Integer> cmp = new ArrayList<>();

        cmp.addAll(result.getReceived1());
        cmp.addAll(result.getReceived2());

        assertEquals(result.getProducerSent().size(), cmp.size());
    }

    private void testListEquality(Result result, int n) {
        List<Integer> ret = IntStream.rangeClosed(0, n - 1).boxed().collect(Collectors.toList());

        List<Integer> cmp = new ArrayList<>();

        cmp.addAll(result.getReceived1());
        cmp.addAll(result.getReceived2());

        assertTrue(cmp.containsAll(ret));
        assertTrue(ret.containsAll(cmp));
    }
}
