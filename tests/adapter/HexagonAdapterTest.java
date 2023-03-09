package adapter;

import hexagon.Hexagon;
import org.junit.jupiter.api.Test;
import shapes.Point;


import java.awt.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.*;
class HexagonAdapterTest {
    private Graphics graphics;
    private HexagonAdapter hexagonAdapter;
    @Before
    public void setUp() {
        graphics = mock(Graphics.class);
        hexagonAdapter = new HexagonAdapter(new Hexagon(1, 2, 3), Color.BLACK, Color.WHITE);
        hexagonAdapter = new HexagonAdapter(new Hexagon(1, 2, 3));
    }
    @Test
    public void testContainsTrueExcepted() {
        hexagonAdapter = new HexagonAdapter(new Hexagon(1, 2, 3));
        assertTrue(hexagonAdapter.contains(1, 1));
    }
    @Test
    public void testContainsFalseExcepted() {
        hexagonAdapter = new HexagonAdapter(new Hexagon(1, 2, 3));
        assertFalse(hexagonAdapter.contains(21, 61));
    }
    @Test
    public void testEqualsNotSameType() {
        hexagonAdapter = new HexagonAdapter(new Hexagon(1, 2, 3));
        assertFalse(hexagonAdapter.equals(new Point(1, 2)));
    }
    @Test
    public void testEqualsFalseExpectedRadius() {
        hexagonAdapter = new HexagonAdapter(new Hexagon(1, 2, 3));
        assertFalse(hexagonAdapter.equals(new HexagonAdapter(new Hexagon(1, 2, 4))));
    }
    @Test
    public void testEqualsFalseExpectedXCoordinate() {
        hexagonAdapter = new HexagonAdapter(new Hexagon(1, 2, 3));
        assertFalse(hexagonAdapter.equals(new HexagonAdapter(new Hexagon(2, 2, 3))));
    }
    @Test
    public void testEqualsFalseExpectedYCoordinate() {
        hexagonAdapter = new HexagonAdapter(new Hexagon(1, 2, 3));
        assertFalse(hexagonAdapter.equals(new HexagonAdapter(new Hexagon(1, 1, 3))));
    }
    @Test
    public void testEqualsTrueExpected() {
        hexagonAdapter = new HexagonAdapter(new Hexagon(1, 2, 3));
        assertTrue(hexagonAdapter.equals(new HexagonAdapter(new Hexagon(1, 2, 3))));
    }
}