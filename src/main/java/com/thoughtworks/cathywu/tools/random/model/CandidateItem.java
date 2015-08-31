package com.thoughtworks.cathywu.tools.random.model;

/**
 * @author lzwu
 * @since 8/31/15
 */
public class CandidateItem <T> {

    private T originItem;
    private double rate;

    public CandidateItem(T originItem, double rate) {
        this.originItem = originItem;
        this.rate = rate;
    }

    public T getOriginItem() {
        return originItem;
    }

    public double getRate() {
        return rate;
    }
}
