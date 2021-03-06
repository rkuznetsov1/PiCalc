package com.apps.elliotgrin.chudnovsky

import org.junit.Test

import org.junit.Assert.assertEquals

class ChudnovskyAlgorithmThreadedTest {

    private val alg = ChudnovskyAlgorithm()

    @Test
    fun testSingleThreaded() {
        val precision = 200L
        println("single-threaded Pi with precision of " + precision + ": " + alg.calculatePi(precision, false) + "\n")
    }

    @Test
    fun testMultiThreaded() {
        val precision = 200L
        val numberOfThreads = 4
        println(
            "multi-threaded (" + numberOfThreads + " threads) Pi with precision of " + precision + ": " + alg.calculatePi(
                precision,
                false,
                numberOfThreads
            ) + "\n"
        )
    }

    @Test
    fun testCompareSingleToMultiThreaded() {
        var precision: Long = 1
        while (precision <= Math.pow(2.0, 15.0)) {

            var startTime = System.nanoTime()
            val singleThreadedPi = alg.calculatePi(precision, false)
            var singleThreadedDuration = System.nanoTime() - startTime
            if (singleThreadedDuration > 0) {
                singleThreadedDuration /= 1000000
            }
            println("single-threaded Pi with precision of $precision: $singleThreadedPi")
            println("execution time: $singleThreadedDuration ms\n")

            for (numberOfThreads in 1..8) {

                startTime = System.nanoTime()
                val multiThreadedPi = alg.calculatePi(precision, false, numberOfThreads)
                var multiThreadedDuration = System.nanoTime() - startTime
                if (multiThreadedDuration > 0) {
                    multiThreadedDuration /= 1000000
                }
                println("multi-threaded ($numberOfThreads threads) Pi with precision of $precision: $multiThreadedPi")
                println("execution time: $multiThreadedDuration ms")
                if (singleThreadedDuration > 0 && multiThreadedDuration > 0) {
                    println(
                        "% speed increase with $numberOfThreads threads, from single threaded: " + String.format(
                            "%.2f",
                            100.0 * (1.0 - multiThreadedDuration.toDouble() / singleThreadedDuration)
                        ) + "%\n"
                    )
                }

                assertEquals(singleThreadedPi, multiThreadedPi)
            }
            precision *= 2L
        }
    }

}

