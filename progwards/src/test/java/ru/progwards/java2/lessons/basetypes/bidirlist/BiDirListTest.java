package ru.progwards.java2.lessons.basetypes.bidirlist;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class BiDirListTest {
    private BiDirList<String> bdl = new BiDirList();

    @Test
    public void WhenAddNewValueInTheEndThenDoIt() {
        bdl.addLast("First");
        Assert.assertEquals(bdl.size(), 1);
    }

    @Test
    public void WhenAddNewValueInTheHeadThenDoIt() {
        bdl.addLast("First");
        bdl.addFirst("Zero");
        Assert.assertEquals(bdl.size(), 2);
    }

    @Test
    public void WhenTryToDeleteItemsThenDoIt() {
        bdl.addLast("First");
        bdl.addFirst("Zero");
        bdl.addLast("Second");
        bdl.addFirst("Minus First");
        bdl.addLast("Third");
        bdl.remove("First");
        bdl.remove("Third");
        bdl.remove("Minus First");
        Assert.assertEquals(bdl.size(), 2);
        bdl.remove("third");
        Assert.assertEquals(bdl.size(), 2);
        bdl.remove("Zero");
        Assert.assertEquals(bdl.size(), 1);
        bdl.remove("Second");
        Assert.assertEquals(bdl.size(), 0);
    }

    @Test
    public void WhenTryToFindValueByNumberThenDoIt() {
        bdl.addLast("Second");
        bdl.addFirst("First");
        bdl.addLast("Third");
        bdl.addFirst("Zero");
        bdl.addLast("Third");
        String s = bdl.at(0);
        Assert.assertEquals(bdl.at(0).equals("Zero"), true);
        Assert.assertEquals(bdl.at(3), bdl.at(4));
    }

    @Test
    public void WhenTryToGetBiDirListFromArrayThenDoIt() {
        String[] array = {"Zero", "First", "Second"};
        BiDirList<String> fromArr = BiDirList.from(array);
        Assert.assertEquals(fromArr.size(), 3);
        Assert.assertEquals(fromArr.at(1), "First");
    }

    @Test
    public void WhenTryToGetBiDirListFromValuesThenDoIt() {
        Assert.assertEquals(BiDirList.of(1, 2, 3, 4, 5).at(3) == 4, true);
    }

    @Test
    public void WhenTryToGetIteratorThenDoIt() {
        bdl.addFirst("Zero");
        bdl.addLast("First");
        bdl.addLast("Second");
        bdl.addLast("Third");
        Iterator<String> iterator = bdl.getIterator();
        String test = null;
        while (iterator.hasNext()) {
            test = iterator.next();
        }
        Assert.assertEquals(test.equals("Third"), true);
    }
}
