package com.apps.elliotgrin.chudnovsky;

import org.apfloat.Apfloat;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChudnovskyAlgorithmThreadedTest {

    private ChudnovskyAlgorithm alg = new ChudnovskyAlgorithm();
    
    @Test
    public void testSingleThreaded() {
        long precision = 200l;
        System.out.println("single-threaded Pi with precision of " + precision + ": " + alg.calculatePi(precision, true) + "\n");
    }

    @Test
    public void testMultiThreaded() {
        long precision = 200l;
        int numberOfThreads = 4;
        System.out.println("multi-threaded (" + numberOfThreads + " threads) Pi with precision of " + precision + ": " + alg.calculatePi(precision, numberOfThreads, true) + "\n");
    }

    @Test
    public void testCompareSingleToMultiThreaded() {
        for (long precision = 1; precision <= Math.pow(2, 15); precision *= 2l) {

            long startTime = System.nanoTime();
            Apfloat singleThreadedPi = alg.calculatePi(precision, true);
            long singleThreadedDuration = (System.nanoTime() - startTime);
            if (singleThreadedDuration > 0) {
                singleThreadedDuration /= 1000000;
            }
            System.out.println("single-threaded Pi with precision of " + precision + ": " + singleThreadedPi);
            System.out.println("execution time: " + singleThreadedDuration + " ms\n");

            for (int numberOfThreads = 1; numberOfThreads <= 8; numberOfThreads++) {

                startTime = System.nanoTime();
                Apfloat multiThreadedPi = alg.calculatePi(precision, numberOfThreads, true);
                long multiThreadedDuration = (System.nanoTime() - startTime);
                if (multiThreadedDuration > 0) {
                    multiThreadedDuration /= 1000000;
                }
                System.out.println("multi-threaded (" + numberOfThreads + " threads) Pi with precision of " + precision + ": " + multiThreadedPi);
                System.out.println("execution time: " + multiThreadedDuration + " ms");
                if(singleThreadedDuration > 0 && multiThreadedDuration > 0) {
                    System.out.println("% speed increase with " + numberOfThreads + " threads, from single threaded: " + String.format("%.2f", 100.0*(1.0 - ((double)multiThreadedDuration/singleThreadedDuration))) + "%\n");
                }

                assertEquals(singleThreadedPi, multiThreadedPi);
            }
        }
    }

}

