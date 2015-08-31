package com.thoughtworks.cathywu.tools.random;

import com.thoughtworks.cathywu.tools.random.exception.InvalidWeightException;
import com.thoughtworks.cathywu.tools.random.generator.CustomizedRandomGenerator;
import com.thoughtworks.cathywu.tools.random.model.CustomizedRandomSeeds;
import org.junit.Test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

/**
 * @author lzwu
 * @since 8/31/15
 */
//@RunWith(Parameterized.class)
public class IntegrationTest {

    @Test
    public void should_numbers_work() throws Exception {
        final Integer[] itemArray = new Integer[]{1, 2, 3, 8, 10};
        final Double[] rateArray = new Double[]{0.3, 0.1, 0.2, 0.1, 0.3};

        CustomizedRandomSeeds<Integer> seeds = new CustomizedRandomSeeds<Integer>();
        for (int i = 0; i < itemArray.length; i++) {
            seeds.addCandidateItem(itemArray[i], rateArray[i]);
        }

        CustomizedRandomGenerator<Integer> generator = CustomizedRandomGenerator.getInstance(Integer.class, seeds);

        final int TOTAL_NUMBER = 1000000;
        Map<Integer, Integer> counter = generateItems(generator, TOTAL_NUMBER);

        for (int i = 0; i < itemArray.length; i++) {
            assertEquals(rateArray[i], calcRate(itemArray[i], counter));
        }
    }

    @Test
    public void should_string_work() throws Exception {
        CustomizedRandomSeeds<String> seeds = new CustomizedRandomSeeds<String>();
        seeds.addCandidateItem("abc", 0.5);
        seeds.addCandidateItem("def", 0.3);
        seeds.addCandidateItem("ghi", 0.2);

        CustomizedRandomGenerator<String> generator = CustomizedRandomGenerator.getInstance(String.class, seeds);
        final int TOTAL_NUMBER = 10000000;
        Map<String, Integer> counter = generateItems(generator, TOTAL_NUMBER);

        assertEquals(0.5, calcRate("abc", counter));
        assertEquals(0.3, calcRate("def", counter));
        assertEquals(0.2, calcRate("ghi", counter));
    }

    @Test
    public void should_obj_work() throws Exception {
        CustomizedRandomSeeds<TestObj> seeds = new CustomizedRandomSeeds<TestObj>();
        seeds.addCandidateItem(new TestObj("Cathy", 11), 0.5);
        seeds.addCandidateItem(new TestObj("Cathy1", 14), 0.1);
        seeds.addCandidateItem(new TestObj("Cathy2", 13), 0.2);
        seeds.addCandidateItem(new TestObj("Cathy3", 12), 0.2);
        CustomizedRandomGenerator<TestObj> generator = CustomizedRandomGenerator.getInstance(TestObj.class, seeds);
        final int TOTAL_NUMBER = 1000000;

        Map<TestObj, Integer> objCounter = new HashMap<TestObj, Integer>();
        Map<String, Integer> textCounter = new HashMap<String, Integer>();

        for (int i = 0; i < TOTAL_NUMBER; i++) {
            TestObj testObj = generator.generate();
            addToMap(objCounter, testObj);
            addToMap(textCounter, testObj.getName());
        }

        assertEquals(TOTAL_NUMBER, objCounter.size());
        assertEquals(seeds.getCandidateItems().size(), textCounter.size());
    }

    private <T extends Serializable> Map<T, Integer> generateItems(CustomizedRandomGenerator<T> generator, int size) throws InvalidWeightException {
        int times = 0;
        Map<T, Integer> counter = new HashMap<T, Integer>();
        while (times++ < size) {
            T item = generator.generate();
            addToMap(counter, item);
        }
        return counter;
    }

    private <T> void addToMap(Map<T, Integer> map, T key) {
        int cnt = 0;
        if (map.containsKey(key)) {
            cnt = map.get(key);
        }
        map.put(key, ++cnt);
    }

    private <T> double calcRate(T item, Map<T, Integer> counter) {
        int totalCount = 0;
        for (T itm : counter.keySet()) {
            totalCount += counter.get(itm);
        }

        BigDecimal count = new BigDecimal(counter.get(item));
        BigDecimal total = new BigDecimal(totalCount);

        return count.divide(total, 1, RoundingMode.HALF_DOWN).doubleValue();
    }
}
