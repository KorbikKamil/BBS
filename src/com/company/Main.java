package com.company;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.Random;

public class Main {
    //Długość generowanego ciągu
    public final static int keyLenght = 20000;

    public static void main(String[] args) {
        //1. Wyznacz wartość iloczynu N dwóch dużych liczb pierwszych, takich że:
        //p ≡ 3 mod 4
        //q ≡ 3 mod 4

        BigInteger p = new BigInteger("3000000019");
        BigInteger q = new BigInteger("4000000007");
        BigInteger N = p.multiply(q);

        System.out.println("N: " + N.toString());

        //2. Wybierz w sposób losowy taką liczbę x taką, że	x i	N są względnie pierwsze.

        BigInteger maxLimit = new BigInteger("1000000000");
        BigInteger minLimit = new BigInteger("500000000");
        BigInteger bigInteger = maxLimit.subtract(minLimit);
        Random randNum = new Random();
        int len = maxLimit.bitLength();
        BigInteger x;
        do {
            x = new BigInteger(len, randNum);
            if (x.compareTo(minLimit) < 0)
                x = x.add(minLimit);
            if (x.compareTo(bigInteger) >= 0)
                x = x.mod(bigInteger).add(minLimit);
        } while (!(x.gcd(N).equals(BigInteger.ONE)));

        System.out.println("x: " + x.toString());

        //3. Wyznacz wartość pierwotną generatora:
        //x0 = x^2 mod N

        BitSet bitSet = new BitSet(keyLenght);

        BigInteger x0 = x.multiply(x).mod(N);
        System.out.println("x0: " + x0.toString());
        //Parzysta 0 Nieparzysta 1
        if (x0.mod(BigInteger.TWO).equals(BigInteger.ONE)) {
            bitSet.set(0);
        }

        //4. Powtarzaj w pętli
        //xi+1 = xi^2 mod N
        BigInteger xi = x0;
        for (int i = 1; i < keyLenght; i++) {
            xi = xi.multiply(xi).mod(N);
            if (xi.mod(BigInteger.TWO).equals(BigInteger.ONE)) {
                bitSet.set(i);
            }
        }

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
