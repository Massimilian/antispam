package ru.progwards.java2.lessons.basetypes.doubleHashTable;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoubleHashTableTest {
    DoubleHashTable dht = new DoubleHashTable();

    @Test
    public void WhenTryToAddNewValueIntHashThenDoIt() {
        DoubleHashTableObject dhto = new DoubleHashTableObject("This is number 1622.");
        Assert.assertEquals(dhto.getHash() == 1622, true);
    }

    @Test
    public void whenTryToAddNewValueThenDoItAndCheckSize() {
        String s = dht.add(new DoubleHashTableObject("Number one"));
        String s1 = dht.add(new DoubleHashTableObject("Number one"));
        String s2 = dht.add(new DoubleHashTableObject("Number one"));
        String s3 = dht.add(new DoubleHashTableObject("Number one"));
        String s4 = dht.add(new DoubleHashTableObject("Number one"));
        String s5 = dht.add(new DoubleHashTableObject("Number one"));
        String s6 = dht.add(new DoubleHashTableObject("Number one"));
        String s7 = dht.add(new DoubleHashTableObject("Number one"));
        String s8 = dht.add(new DoubleHashTableObject("Number one"));
        String s9 = dht.add(new DoubleHashTableObject("Number one"));
        Assert.assertEquals(dht.getSize() == 101, true);
        String s10 = dht.add(new DoubleHashTableObject("Number one"));
        Assert.assertEquals(dht.getSize() == 205, true);
    }

    @Test
    public void WhenTryToGetByKeyThenDoIt() {
        DoubleHashTableObject<Double> d = new DoubleHashTableObject(Math.random() * 9000 + 1000);
        String s = dht.add(d);
        Matcher matcher = Pattern.compile("\\d{4}").matcher(s);
        matcher.find();
        String result = matcher.group();
        double res = (double) dht.get(Integer.valueOf(result));
        Assert.assertEquals(res == d.getValue(), true);
    }

    @Test
    public void WhenTryToDeleteThenDoIt() {
        dht.add(new DoubleHashTableObject(5));
        String first = dht.remove(5);
        String second = dht.remove(5);
        Assert.assertEquals("Value with key №5 removed form position №5.", first);
        Assert.assertEquals("Cannot do this operation: the value was not found.", second);
    }

    @Test
    public void whenTryToChangeKeyThenDoIt() {
        DoubleHashTableObject<Double> d = new DoubleHashTableObject(Math.random() * 9000 + 1000);
        double value = d.getValue();
        int key = d.getKey();
        dht.add(d);
        dht.change(key, 0);
        Assert.assertEquals(d.getValue() == value, true);
    }

    @Test
    public void WhenTryToUseIteratorThenDoIt() {
        dht.add(new DoubleHashTableObject("Number one"));
        dht.add(new DoubleHashTableObject("Number two"));
        dht.add(new DoubleHashTableObject("Number three"));
        Iterator<DoubleHashTable<String>> it = dht.getIterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(", ");
        }
        String result = sb.substring(0, 36);
        Assert.assertEquals(result, "Number one, Number three, Number two");
    }
}
