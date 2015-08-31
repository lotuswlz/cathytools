package com.thoughtworks.cathywu.tools.random.generator;

import com.thoughtworks.cathywu.tools.random.exception.InvalidGenericTypeException;
import com.thoughtworks.cathywu.tools.random.exception.InvalidRateException;
import com.thoughtworks.cathywu.tools.random.model.CustomizedRandomSeeds;

/**
 * @author lzwu
 * @since 9/1/15
 */
public class CustomizedRandomGeneratorFactory {
    public static <T> CustomizedRandomGenerator<T> createInstance(Class<T> cls, CustomizedRandomSeeds<T> candidateItems) throws InvalidGenericTypeException, InvalidRateException {
        if (Number.class.isAssignableFrom(cls) || String.class.isAssignableFrom(cls)) {
            return new BasicTypeRandomGenerator(candidateItems);
        } else if (Cloneable.class.isAssignableFrom(cls)) {
            return new CloneableTypeRandomGenerator(candidateItems);
        }
        throw new InvalidGenericTypeException("Type should extends basic data type or be cloneable.");
    }
}
