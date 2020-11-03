package com.company;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.Random;

public class BBS {
    //Długość generowanego ciągu
    private int keyLenght;
    private BigInteger p;
    private BigInteger q;
    protected BigInteger N;

    public BBS(int keyLenght, BigInteger p, BigInteger q) {
        this.keyLenght = keyLenght;
        this.p = p;
        this.q = q;
        this.N = p.multiply(q);
    }

    public BitSet generateBitset(){
        BitSet bitSet = new BitSet(keyLenght);

        BigInteger x = generateGCDNumber(500000000, 1000000000);
        BigInteger x0 = x.multiply(x).mod(N);
        if (x0.mod(BigInteger.TWO).equals(BigInteger.ONE)) {
            bitSet.set(0);
        }
        BigInteger xi = x0;
        for (int i = 1; i < keyLenght; i++) {
            xi = xi.multiply(xi).mod(N);
            if (xi.mod(BigInteger.TWO).equals(BigInteger.ONE)) {
                bitSet.set(i);
            }
        }
        return bitSet;
    }

    protected BigInteger generateBigIntRandomNumber(int minimalLimit, int maximalLimit){
        BigInteger maxLimit = BigInteger.valueOf(maximalLimit);
        BigInteger minLimit = BigInteger.valueOf(minimalLimit);
        BigInteger range = maxLimit.subtract(minLimit);
        Random randNum = new Random();
        int len = maxLimit.bitLength();
        BigInteger res = new BigInteger(len, randNum);
        if (res.compareTo(minLimit) < 0)
            res = res.add(minLimit);
        if (res.compareTo(range) >= 0)
            res = res.mod(range).add(minLimit);
        return res;
    }

    protected BigInteger generateGCDNumber(int minimalLimit, int maximalLimit){
        BigInteger gcdNum;
        do {
            gcdNum = generateBigIntRandomNumber(minimalLimit,maximalLimit);
        } while(!(gcdNum.gcd(N).equals(BigInteger.ONE)));
        return gcdNum;
    }
}
