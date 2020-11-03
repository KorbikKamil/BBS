package com.company;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;


import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BBSTest {

    static BigInteger p;
    static BigInteger q;
    static BBS bbs;
    static BitSet bitSet;

    @BeforeAll
    static void beforeAllInit() {
        p = new BigInteger("3000000019");
        q = new BigInteger("4000000007");
        bbs = new BBS(20000, p, q);
        bitSet = bbs.generateBitset();
    }

    @Test
    void shouldGenerateRandomNumberInRange() {
        BigInteger minLimit = new BigInteger("500000000");
        BigInteger maxLimit = new BigInteger("1000000000");
        BigInteger actual = bbs.generateBigIntRandomNumber(500000000, 1000000000);
        assertEquals(1, maxLimit.compareTo(actual));
        assertEquals(-1, minLimit.compareTo(actual));
    }

    @Test
    void shouldGenerateGCDNumber() {
        BigInteger actual = bbs.generateGCDNumber(500000000, 1000000000);
        assertEquals(BigInteger.ONE, actual.gcd(bbs.N));
    }

    @Test
    void monoBitTest() {
        int actual = bitSet.cardinality();
        assertTrue(actual > 9725);
        assertTrue(actual < 10275);
    }

    @Test
    void runsTest() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bitSet.length(); i++) {
            s.append(bitSet.get(i) ? 1 : 0);
        }

        Pattern single0 = Pattern.compile("(?<=1)0(?=1)");
        Pattern single1 = Pattern.compile("(?<=0)1(?=0)");
        Pattern two0 = Pattern.compile("(?<=1)00(?=1)");
        Pattern two1 = Pattern.compile("(?<=0)11(?=0)");
        Pattern three0 = Pattern.compile("(?<=1)000(?=1)");
        Pattern three1 = Pattern.compile("(?<=0)111(?=0)");
        Pattern four0 = Pattern.compile("(?<=1)0000(?=1)");
        Pattern four1 = Pattern.compile("(?<=0)1111(?=0)");
        Pattern five0 = Pattern.compile("(?<=1)00000(?=1)");
        Pattern five1 = Pattern.compile("(?<=0)11111(?=0)");
        Pattern sixAndMore0 = Pattern.compile("(?<=1)0000000*(?=1)");
        Pattern sixAndMore1 = Pattern.compile("(?<=0)1111111*(?=0)");

        Matcher countSingle0Matcher = single0.matcher(s);
        Matcher countSingle1Matcher = single1.matcher(s);
        Matcher countTwo0Matcher = two0.matcher(s);
        Matcher countTwo1Matcher = two1.matcher(s);
        Matcher countThree0Matcher = three0.matcher(s);
        Matcher countThree1Matcher = three1.matcher(s);
        Matcher countFour0Matcher = four0.matcher(s);
        Matcher countFour1Matcher = four1.matcher(s);
        Matcher countFive0Matcher = five0.matcher(s);
        Matcher countFive1Matcher = five1.matcher(s);
        Matcher countSixAndMore0Matcher = sixAndMore0.matcher(s);
        Matcher countSixAndMore1Matcher = sixAndMore1.matcher(s);

        long actualSingle0 = countSingle0Matcher.results().count();
        long actualSingle1 = countSingle1Matcher.results().count();
        long actualTwo0 = countTwo0Matcher.results().count();
        long actualTwo1 = countTwo1Matcher.results().count();
        long actualThree0 = countThree0Matcher.results().count();
        long actualThree1 = countThree1Matcher.results().count();
        long actualFour0 = countFour0Matcher.results().count();
        long actualFour1 = countFour1Matcher.results().count();
        long actualFive0 = countFive0Matcher.results().count();
        long actualFive1 = countFive1Matcher.results().count();
        long actualSixAndMore0 = countSixAndMore0Matcher.results().count();
        long actualSixAndMore1 = countSixAndMore1Matcher.results().count();

        assertTrue(actualSingle0 >= 2315 && actualSingle0 <= 2685, "Single 0 problem");
        assertTrue(actualSingle1 >= 2315 && actualSingle1 <= 2685, "Single 1 problem");
        assertTrue(actualTwo0 >= 1114 && actualTwo0 <= 1386, "Two 0 problem");
        assertTrue(actualTwo1 >= 1114 && actualTwo1 <= 1386, "Two 1 problem");
        assertTrue(actualThree0 >= 527 && actualThree0 <= 723, "Three 0 problem");
        assertTrue(actualThree1 >= 527 && actualThree1 <= 723, "Three 1 problem");
        assertTrue(actualFour0 >= 240 && actualFour0 <= 384, "Four 0 problem");
        assertTrue(actualFour1 >= 240 && actualFour1 <= 384, "Four 1 problem");
        assertTrue(actualFive0 >= 103 && actualFive0 <= 209, "Five 0 problem");
        assertTrue(actualFive1 >= 103 && actualFive1 <= 209, "Five 1 problem");
        assertTrue(actualSixAndMore0 >= 103 && actualSixAndMore0 <= 209, "SixAndMore 0 problem");
        assertTrue(actualSixAndMore1 >= 103 && actualSixAndMore1 <= 209, "SixAndMore 1 problem");
    }

    @Test
    void longRunsTest() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bitSet.length(); i++) {
            s.append(bitSet.get(i) ? 1 : 0);
        }
        Pattern twentySixOrMore0 = Pattern.compile("(?<=1)0{26,}(?=1)");
        Pattern twentySixOrMore1 = Pattern.compile("(?<=0)1{26,}(?=0)");
        Matcher countTWSOM0 = twentySixOrMore0.matcher(s);
        Matcher countTWSOM1 = twentySixOrMore1.matcher(s);
        assertEquals(0, countTWSOM0.results().count());
        assertEquals(0, countTWSOM1.results().count());
    }

    @Test
    void pokerTest() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bitSet.length(); i++) {
            s.append(bitSet.get(i) ? 1 : 0);
        }
        String bits = s.toString();
        String[] fourBitsArray = bits.split("(?<=\\G....)");
        int[] countsVariations = new int[16];
        Arrays.fill(countsVariations, 0);
        for (String value : fourBitsArray) {
            countsVariations[Integer.parseInt(value, 2)]++;
        }
        float sumForPattern = 0;
        for (int count : countsVariations) {
            sumForPattern += count * count;
        }
        float x = (16 * sumForPattern) / 5000 - 5000;
        assertTrue(x > 2.16 && x < 46.17);
    }
}