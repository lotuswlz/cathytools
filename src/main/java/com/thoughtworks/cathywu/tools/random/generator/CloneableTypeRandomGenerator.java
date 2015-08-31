package com.thoughtworks.cathywu.tools.random.generator;

import com.thoughtworks.cathywu.tools.random.exception.InvalidWeightException;
import com.thoughtworks.cathywu.tools.random.model.CustomizedRandomSeeds;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author lzwu
 * @since 9/1/15
 */
public class CloneableTypeRandomGenerator<T extends Cloneable> extends CustomizedRandomGenerator<Cloneable> {

    protected CloneableTypeRandomGenerator(CustomizedRandomSeeds<Cloneable> candidateItems) throws InvalidWeightException {
        super(candidateItems);
    }

    @Override
    protected T generateItem() {
        int index = this.itemSelector.index();
        Cloneable item = this.candidateItems.getItem(index);
        try {
            return cloneObj((T) item);
        } catch (Exception e) {
            return null;
        }
    }

    private <T extends Cloneable> T cloneObj(T originObj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = originObj.getClass().getMethod("clone");
        T clonedObj = (T) method.invoke(originObj);
        return clonedObj;
    }
}
