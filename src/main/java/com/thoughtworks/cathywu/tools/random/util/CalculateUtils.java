package com.thoughtworks.cathywu.tools.random.util;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author lzwu
 * @since 9/1/15
 */
public final class CalculateUtils {

    public static int[] enlargeToInteger(double... rates) {
        double[] changedRates = Arrays.copyOf(rates, rates.length);
        int bit = 1;
        while (!isAllInteger(changedRates)) {
            for (int i = 0; i < changedRates.length; i++) {
                changedRates[i] *= Math.pow(10, bit);
            }
            bit++;
        }
        return arrayDoubleToInt(changedRates);
    }

    public static int calcGcd(int... rates) {
        BigInteger gcd = new BigInteger(String.valueOf(rates[0]));
        for (int i = 1; i < rates.length; i++) {
            gcd = gcd.gcd(new BigInteger(String.valueOf(rates[i])));
        }
        return gcd.intValue();
    }

    public static boolean isAllInteger(double... rates) {
        for (double rate : rates) {
            if (rate - (int) rate != 0) {
                return false;
            }
        }
        return true;
    }

    public static int[] calcQuotient(int[] rates, int gcd) {
        int[] quotients = Arrays.copyOf(rates, rates.length);
        for (int i = 0; i < quotients.length; i++) {
            quotients[i] = quotients[i] / gcd;
        }
        return quotients;
    }

    public static int sum(int...numbers) {
        int total = 0;
        for (int number : numbers) {
            total += number;
        }
        return total;
    }

    private static int[] arrayDoubleToInt(double[] changedRates) {
        int[] intValues = new int[changedRates.length];
        for (int i = 0; i < changedRates.length; i++) {
            intValues[i] = (int) changedRates[i];
        }
        return intValues;
    }
}
