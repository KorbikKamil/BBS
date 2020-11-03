package com.company;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.Random;

public class Main {
    //Długość generowanego ciągu
    public final static int keyLenght = 20000;

    public static void main(String[] args) {
        BigInteger p = new BigInteger("3000000019");
        BigInteger q = new BigInteger("3000000019");
        BBS bbs = new BBS(20000, p,q);
        BitSet bitSet = bbs.generateBitset();

        //Wyświetlenie
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bitSet.length(); i++) {
            s.append(bitSet.get(i) ? 1 : 0);
        }
        System.out.println("Ciąg: " + s);

        //Test pojedynczych bitów
        System.out.println("Test pojedynczych bitów: Ilość ustawionych bitów 9725 < n(1) < 10275: " + bitSet.cardinality());
    }
}
