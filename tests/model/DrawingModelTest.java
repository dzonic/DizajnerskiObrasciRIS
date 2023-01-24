package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shapes.Point;
import shapes.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DrawingModelTest {

    private DrawingModel drawingModelUnderTest;

    @BeforeEach
    void setUp() {
        drawingModelUnderTest = new DrawingModel();
    }

    @Test
    void testAddShape() {
        final Shape shape = null;
        drawingModelUnderTest.addShape(shape);
    }

    @Test
    void testRemove() {
        final Shape s = null;
        drawingModelUnderTest.remove(s);
    }

    @Test
    void testGetIndex() {
        final Shape shape = null;
        final int result = drawingModelUnderTest.getIndex(shape);
        assertEquals(-1, result);
    }

    @Test
    void testAddShapeAtIndex() {
        final Shape shape = null;
        drawingModelUnderTest.addShapeAtIndex(shape, 0);
    }

    @Test
    void testRemoveSelected() {
        drawingModelUnderTest.removeSelected();
    }

    @Test
    void testDeselect() {
        drawingModelUnderTest.deselect();
    }

    @Test
    void testAddAll() {
        final ArrayList<Shape> shapes = new ArrayList<>(List.of());
        drawingModelUnderTest.addAll(shapes);
    }

    @Test
    void testSelect() {
        final Point point = new Point(0, 0, new Color(0, 0, 0, 0));
        drawingModelUnderTest.select(point);

    }

    @Test
    void testGetSelectedShapes() {
        final ArrayList<Shape> result = drawingModelUnderTest.getSelectedShapes();
    }

    @Test
    void testGetSelected() {
        final int result = drawingModelUnderTest.getSelected();
        assertEquals(-1, result);
    }

    @Test
    void testIsEmpty() {
        final boolean result = drawingModelUnderTest.isEmpty();
        assertTrue(result);
    }

    @Test
    void testGetShapes() {
        final ArrayList<Shape> result = drawingModelUnderTest.getShapes();
    }
}
