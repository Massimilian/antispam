package ru.progwards.java2.lessons.gc;

import org.junit.Assert;
import org.junit.Test;
import ru.progwards.java2.lessons.gc.heap.Heap;
import ru.progwards.java2.lessons.gc.heap.InvalidPointerException;
import ru.progwards.java2.lessons.gc.heap.OutOfMemoryException;

import static org.junit.Assert.assertTrue;

public class HeapTest {
    private Heap heap = new Heap(256);
    private Heap h = new Heap(1932735283);

    @Test
    public void wneTryToAddNewValuesIntoBytesThenDoIt() throws OutOfMemoryException {
        Assert.assertEquals(heap.malloc(8), 0);
        Assert.assertEquals(heap.malloc(7), 8);
        Assert.assertEquals(heap.malloc(2), 15);
    }

    @Test
    public void whenTryToFragmentizeHeapThenDoIt() throws InvalidPointerException, OutOfMemoryException {
        heap.malloc(3);
        heap.malloc(5);
        heap.malloc(2);
        heap.free(0);
        heap.free(8);
    }

    @Test(expected = InvalidPointerException.class)
    public void whenTryToFragmentizeHeapWithFaultOutOfHeapThenReseaveFault() throws InvalidPointerException, OutOfMemoryException {
        heap.malloc(1);
        heap.free(100);
    }

    @Test(expected = InvalidPointerException.class)
    public void whenTryToFragmentozeHeapWithFaultIntoTheHeapThemReseaveFault() throws InvalidPointerException, OutOfMemoryException {
        heap.malloc(2);
        heap.free(1);
    }

    @Test
    public void whenTryToCompressHeapThenDoIt() throws InvalidPointerException, OutOfMemoryException {
        heap.malloc(3);
        heap.malloc(5);
        heap.malloc(3);
        heap.malloc(4);
        heap.malloc(3);
        heap.malloc(5);
        heap.malloc(2);
        heap.free(3);
        heap.free(11);
        heap.free(18);
        heap.compact();
        assertTrue(heap.status().contains("11") && heap.status().contains("245") && heap.status().contains("true"));
    }

    @Test
    public void whenTryToCompressEasyWayThenDoIt() throws InvalidPointerException, OutOfMemoryException {
        heap.malloc(3);
        heap.malloc(5);
        heap.malloc(3);
        heap.malloc(4);
        heap.malloc(3);
        heap.malloc(5);
        heap.malloc(2);
        heap.free(3);
        heap.free(11);
        heap.free(18);
        heap.compactEasy();
        assertTrue(heap.status().contains("11") && heap.status().contains("245") && heap.status().contains("true"));
    }

    @Test
    public void whenTryToCompressExtremelyBigFileThenDoIt() throws OutOfMemoryException, InvalidPointerException {
        heap.malloc(3);
        heap.malloc(10);
        heap.free(0);
        heap.compact();
        assertTrue(heap.status().contains("10") && heap.status().contains("246"));
    }

    @Test
    public void whenTryToCompressManyBigAndSmallValuesThenDoIt() throws OutOfMemoryException, InvalidPointerException {
        for (int i = 0; i < 20; i++) {
            heap.malloc(10);
        }
        heap.malloc(56);
        heap.free(170);
        heap.free(20);
        heap.malloc(20);
        heap.compact();
        System.out.println(heap.status());
        assertTrue(heap.status().contains("0") && heap.status().contains("256"));
    }
}
