package com.thoughtworks.cathywu.tools.random.handler;

import com.thoughtworks.cathywu.tools.random.util.CalculateUtils;

import java.util.Arrays;
import java.util.Random;

/**
 * @author lzwu
 * @since 9/1/15
 */
public class ItemSelector {

    private double[] rates;
    private int[] sourceIndexArray;

    public ItemSelector(double...rates) {
        this.rates = rates;
        this.sourceIndexArray = buildIndexArray();
    }

    public int index() {
        Random random = new Random(System.currentTimeMillis());
        int index = random.nextInt(sourceIndexArray.length);
        return this.sourceIndexArray[index];
    }

    private int[] buildIndexArray() {
        int[] numberCountArray = getIndexNumbers();
        int sum = CalculateUtils.sum(numberCountArray);
        int[] filledIndexArray = new int[sum];
        int index = 0;
        for (int i = 0; i < numberCountArray.length; i++) {
            Arrays.fill(filledIndexArray, index, index + numberCountArray[i], i);
            index += numberCountArray[i];
        }
        return filledIndexArray;
    }

    private int[] getIndexNumbers() {
        int[] rateArray = CalculateUtils.enlargeToInteger(rates);
        int gcd = CalculateUtils.calcGcd(rateArray);
        return CalculateUtils.calcQuotient(rateArray, gcd);
    }
}
