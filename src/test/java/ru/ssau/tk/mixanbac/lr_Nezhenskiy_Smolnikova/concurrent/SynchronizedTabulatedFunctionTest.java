package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.concurrent;

import org.testng.annotations.Test;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.ArrayTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.ZeroFunction;

import static org.testng.Assert.*;

public class SynchronizedTabulatedFunctionTest { public static final double DELTA = 0.0001;
    private final double[] xValues = {1.1, 1.2, 1.3, 1.4, 1.5};
    private final double[] yValues = {2.1, 2.2, 2.3, 2.4, 2.5};
    private final Object mutex = new Object();

    private SynchronizedTabulatedFunction getSynchronizedList() {
        return new SynchronizedTabulatedFunction(new LinkedListTabulatedFunction(xValues, yValues), mutex);
    }

    private SynchronizedTabulatedFunction getSynchronizedArray() {
        return new SynchronizedTabulatedFunction(new ArrayTabulatedFunction(xValues, yValues), mutex);
    }

    @Test
    public void testGetCount() {
        assertEquals(getSynchronizedList().getCount(), 5);
        assertEquals(getSynchronizedArray().getCount(), 5);
    }

    @Test
    public void testGetX() {
        assertEquals(getSynchronizedList().getX(0), 1.1);
        assertEquals(getSynchronizedList().getX(1), 1.2);
        assertEquals(getSynchronizedList().getX(2), 1.3);
        assertEquals(getSynchronizedList().getX(3), 1.4);
        assertEquals(getSynchronizedList().getX(4), 1.5);
        assertEquals(getSynchronizedArray().getX(0), 1.1);
        assertEquals(getSynchronizedArray().getX(1), 1.2);
        assertEquals(getSynchronizedArray().getX(2), 1.3);
        assertEquals(getSynchronizedArray().getX(3), 1.4);
        assertEquals(getSynchronizedArray().getX(4), 1.5);

    }

    @Test
    public void testGetY() {
        assertEquals(getSynchronizedList().getY(0), 2.1);
        assertEquals(getSynchronizedList().getY(1), 2.2);
        assertEquals(getSynchronizedList().getY(2), 2.3);
        assertEquals(getSynchronizedList().getY(3), 2.4);
        assertEquals(getSynchronizedList().getY(4), 2.5);
        assertEquals(getSynchronizedArray().getY(0), 2.1);
        assertEquals(getSynchronizedArray().getY(1), 2.2);
        assertEquals(getSynchronizedArray().getY(2), 2.3);
        assertEquals(getSynchronizedArray().getY(3), 2.4);
        assertEquals(getSynchronizedArray().getY(4), 2.5);
    }

    @Test
    public void testSetY() {
        ZeroFunction source = new ZeroFunction();
        SynchronizedTabulatedFunction checkFunction = new SynchronizedTabulatedFunction(new LinkedListTabulatedFunction(source, 0, 20, 15), mutex);
        checkFunction.setY(0, 5);
        assertEquals(checkFunction.getY(0), 5);
        checkFunction.setY(1, 3);
        assertEquals(checkFunction.getY(1), 3);
        checkFunction.setY(2, 7);
        assertEquals(checkFunction.getY(2), 7);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(getSynchronizedArray().indexOfX(1.2), 1);
        assertEquals(getSynchronizedArray().indexOfX(1.5), 4);
        assertEquals(getSynchronizedArray().indexOfX(1.1), 0);
        assertEquals(getSynchronizedList().indexOfX(1.2), 1);
        assertEquals(getSynchronizedList().indexOfX(1.3), 2);
        assertEquals(getSynchronizedList().indexOfX(1.4), 3);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(getSynchronizedArray().indexOfY(2.4), 3);
        assertEquals(getSynchronizedArray().indexOfY(2.2), 1);
        assertEquals(getSynchronizedArray().indexOfY(2.1), 0);
        assertEquals(getSynchronizedList().indexOfY(2.1), 0);
        assertEquals(getSynchronizedList().indexOfY(2.3), 2);
        assertEquals(getSynchronizedList().indexOfY(2.5), 4);
    }

    @Test
    public void testLeftBound() {
        assertEquals(getSynchronizedList().leftBound(), 1.1);
        assertEquals(getSynchronizedArray().leftBound(), 1.1);
    }

    @Test
    public void testRightBound() {
        assertEquals(getSynchronizedArray().rightBound(), 1.5);
        assertEquals(getSynchronizedList().rightBound(), 1.5);
    }

    @Test
    public void testIterator() {
    }

    @Test
    public void testApply() {
        assertEquals(getSynchronizedList().apply(5), 6);
        assertEquals(getSynchronizedList().apply(7), 8);
        assertEquals(getSynchronizedList().apply(9), 10);
        assertEquals(getSynchronizedArray().apply(6), 7);
        assertEquals(getSynchronizedArray().apply(8), 9);
        assertEquals(getSynchronizedArray().apply(10), 11);
    }
}