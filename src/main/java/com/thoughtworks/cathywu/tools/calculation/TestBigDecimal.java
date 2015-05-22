package com.thoughtworks.cathywu.tools.calculation;

import java.math.BigDecimal;

/**
 * Created by lzwu on 3/25/15.
 */
public class TestBigDecimal {
    public static long EXPONENT = -4;


    public static BigDecimal calcCharging(long ccMoneyUnitValueDigits, long exponent) {
        BigDecimal amount = new BigDecimal(ccMoneyUnitValueDigits);
        BigDecimal factor = new BigDecimal(10).pow((int) Math.abs(exponent));
        if (exponent < 0) {
            amount = amount.divide(factor, (int) Math.abs(exponent), BigDecimal.ROUND_HALF_UP);
        } else {
            amount = amount.multiply(factor).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return amount;
    }

    public static void main(String[] args) {
        System.out.println(calcCharging(500, 4));
    }
}
