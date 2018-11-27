package com.apps.elliotgrin.chudnovsky;

import org.apfloat.Apfloat;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChudnovskyAlgorithmTest {

    private ChudnovskyAlgorithm alg = new ChudnovskyAlgorithm();

    @Test
    public void testPrecision1() {
        Apfloat pi1 = new Apfloat("3.1");
        assertEquals(pi1, alg.calculatePi(1l, true));
    }

    @Test
    public void testPrecision2() {
        Apfloat pi2 = new Apfloat("3.14");
        assertEquals(pi2, alg.calculatePi(2l, true));
    }

    @Test
    public void testPrecision10() {
        Apfloat pi10 = new Apfloat("3.1415926535");
        assertEquals(pi10, alg.calculatePi(10l, true));
    }

    @Test
    public void testDoublePrecision() {
        assertEquals(new Apfloat(Math.PI), alg.calculatePi(15l, true));
    }

    @Test
    public void testPrecision1000() {
        Apfloat pi1000 = new Apfloat("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679821480865132823066470938446095505822317253594081284811174502841027019385211055596446229489549303819644288109756659334461284756482337867831652712019091456485669234603486104543266482133936072602491412737245870066063155881748815209209628292540917153643678925903600113305305488204665213841469519415116094330572703657595919530921861173819326117931051185480744623799627495673518857527248912279381830119491298336733624406566430860213949463952247371907021798609437027705392171762931767523846748184676694051320005681271452635608277857713427577896091736371787214684409012249534301465495853710507922796892589235420199561121290219608640344181598136297747713099605187072113499999983729780499510597317328160963185950244594553469083026425223082533446850352619311881710100031378387528865875332083814206171776691473035982534904287554687311595628638823537875937519577818577805321712268066130019278766111959092164201989");
        assertEquals(pi1000, alg.calculatePi(1000l, true));
    }

    @Test
    public void testPrecision10000() {
        System.out.println("PI: " + alg.calculatePi(10000l, true));
    }

}
