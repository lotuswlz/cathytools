package com.thoughtworks.cathywu.tools.random.generator;

import com.thoughtworks.cathywu.tools.random.exception.InvalidRateException;
import com.thoughtworks.cathywu.tools.random.model.CustomizedRandomSeeds;

import java.io.Serializable;

/**
 * @author lzwu
 * @since 9/1/15
 */
public class BasicTypeRandomGenerator<T extends Serializable> extends CustomizedRandomGenerator<Serializable> {

    protected BasicTypeRandomGenerator(CustomizedRandomSeeds<Serializable> candidateItems) throws InvalidRateException {
        super(candidateItems);
    }

    @Override
    protected T generateItem() {
        int index = this.itemSelector.index();
        return (T) this.candidateItems.getItem(index);
    }
}
