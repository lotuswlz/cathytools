package com.thoughtworks.cathywu.tools.random.model;

import com.thoughtworks.cathywu.tools.random.exception.InvalidRateException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzwu
 * @since 9/1/15
 */
public class CustomizedRandomSeeds<T> {
    private List<CandidateItem<T>> candidateItems;

    public CustomizedRandomSeeds() {
        this.candidateItems = new ArrayList<CandidateItem<T>>();
    }

    public void addCandidateItem(T value, double rate) {
        this.candidateItems.add(new CandidateItem<T>(value, rate));
    }

    public List<CandidateItem<T>> getCandidateItems() {
        return candidateItems;
    }

    public void validate() throws InvalidRateException {
        double totalWeight = 0;
        for (CandidateItem candidateItem : candidateItems) {
            totalWeight += candidateItem.getRate();
        }
        if (new BigDecimal(totalWeight).setScale(3, BigDecimal.ROUND_HALF_UP).compareTo(BigDecimal.ONE) != 0) {
            throw new InvalidRateException("Total weight more than one");
        }
    }

    public double[] getRates() {
        double[] rates = new double[this.candidateItems.size()];
        int index = 0;
        for (CandidateItem<T> item : this.candidateItems) {
            rates[index++] = item.getRate();
        }
        return rates;
    }

    public T getItem(int index) {
        return this.candidateItems.get(index).getOriginItem();
    }
}
