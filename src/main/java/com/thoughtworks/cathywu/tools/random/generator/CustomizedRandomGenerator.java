package com.thoughtworks.cathywu.tools.random.generator;

import com.thoughtworks.cathywu.tools.random.exception.InvalidGenericTypeException;
import com.thoughtworks.cathywu.tools.random.exception.InvalidRateException;
import com.thoughtworks.cathywu.tools.random.handler.ItemSelector;
import com.thoughtworks.cathywu.tools.random.model.CustomizedRandomSeeds;

/**
 * @author lzwu
 * @since 8/31/15
 */
public abstract class CustomizedRandomGenerator<T> {

    protected CustomizedRandomSeeds<T> candidateItems;
    protected ItemSelector itemSelector;

    protected CustomizedRandomGenerator(CustomizedRandomSeeds<T> candidateItems) throws InvalidRateException {
        this.candidateItems = candidateItems;
        this.candidateItems.validate();
        this.itemSelector = new ItemSelector(this.candidateItems.getRates());
    }

    public static <T> CustomizedRandomGenerator<T> getInstance(Class<T> cls, CustomizedRandomSeeds<T> candidateItems) throws InvalidGenericTypeException, InvalidRateException {
        return CustomizedRandomGeneratorFactory.createInstance(cls, candidateItems);
    }

    public T generate() {
        return generateItem();
    }

    protected abstract T generateItem();
}
