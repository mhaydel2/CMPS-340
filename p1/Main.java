package com.company;
import java.math.BigInteger;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println("Result Verification:");
        System.out.println("Big Integer: 3^101 = " + bigInt(3, 101));
        System.out.println("Iterative:   3^101 = " + Iterative(3, 101));
        System.out.println("Recursive:   3^101 = " + Recursive(3, 101));
        dataCollection();
    }

    public static void dataCollection() {
        Random rand = new Random();
        long before, after, lowBI = 0, lowIT = 0, lowRE = 0, medBI = 0, medIT = 0, medRE = 0, highBI = 0, highIT = 0, highRE = 0;
        for (int i = 0; i < 2000; i++) {
            int base = rand.nextInt(298) + 3;
            int low = rand.nextInt(101) + 450;
            before = System.nanoTime()/1000;
            bigInt(base, low);
            after = System.nanoTime()/1000;
            lowBI += after - before;

            before = System.nanoTime()/1000;
            Iterative(base, low);
            after = System.nanoTime()/1000;
            lowIT += after - before;

            before = System.nanoTime()/1000;
            Recursive(base, low);
            after = System.nanoTime()/1000;
            lowRE += after - before;
        }
        System.out.println("\nLow Input Size:");
        Print(lowBI, lowIT, lowRE);

        for (int i = 0; i < 2000; i++) {
            int base = rand.nextInt(298) + 3;
            int med = rand.nextInt(401) + 1800;
            before = System.nanoTime();
            bigInt(base, med);
            after = System.nanoTime();
            medBI += after - before;

            before = System.nanoTime();
            Iterative(base, med);
            after = System.nanoTime();
            medIT += after - before;

            before = System.nanoTime();
            Recursive(base, med);
            after = System.nanoTime();
            medRE += after - before;
        }
        System.out.println("\nMedium Input Size:");
        Print(medBI, medIT, medRE);

        for (int i = 0; i < 2000; i++) {
            int base = rand.nextInt(298) + 3;
            int high = rand.nextInt(4001) + 8000;
            before = System.nanoTime();
            bigInt(base, high);
            after = System.nanoTime();
            highBI += after - before;

            before = System.nanoTime();
            Iterative(base, high);
            after = System.nanoTime();
            highIT += after - before;

            before = System.nanoTime();
            Recursive(base, high);
            after = System.nanoTime();
            highRE += after - before;
        }
        System.out.println("\nHigh Input Size:");
        Print(highBI, highIT, highRE);
    }

    public static BigInteger bigInt(int base, int exp) {
        return BigInteger.valueOf(base).pow(exp);
    }

    public static BigInteger Iterative(int base, int exp) {
        BigInteger count = new BigInteger("1");
        for (int i = 0; i < exp; i++) {
            count = count.multiply(BigInteger.valueOf(base));
        }
        return count;
    }

    public static BigInteger Recursive(int base, int exp) {
        BigInteger temp;
        if (exp == 0)
            return BigInteger.valueOf(1);
        temp = Recursive(base, exp / 2);
        if (exp % 2 == 0)
            return temp.multiply(temp);
        else
            return (temp.multiply(temp)).multiply(BigInteger.valueOf(base));
    }

    public static void Print(long BI, long IT, long RE){
        float BIT = (float) (BI/2000.0);
        float ITT = (float) (IT/2000.0);
        float RET = (float) (RE/2000.0);
        System.out.print("Timer [BigInteger]: ");
        System.out.printf("%.2f", BIT);
        System.out.println(" µs");
        System.out.print("Timer [Iterative]: ");
        System.out.printf("%.2f", ITT);
        System.out.println(" µs");
        System.out.print("Timer [Recursive]: ");
        System.out.printf("%.2f", RET);
        System.out.println(" µs");
    }
}
