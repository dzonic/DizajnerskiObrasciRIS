package shapes;

import static org.junit.Assert.*;
import org.junit.Test;

import java.awt.*;

public class DonutTest {
    @Test
    public void testDefaultConstructor() {
        Donut d = new Donut();
        assertEquals(0, d.getInnerRadius());
    }

    @Test
    public void testConstructorWithParams() {
        Point center = new Point(0, 0);
        Donut d = new Donut(center, 5, 2);
        assertEquals(center, d.getCenter());
        assertEquals(5, d.getRadius());
        assertEquals(2, d.getInnerRadius());
    }

    @Test
    public void testCompareTo() {
        Donut d1 = new Donut(new Point(0, 0), 5, 2);
        Donut d2 = new Donut(new Point(0, 0), 4, 1);
        assertTrue(d1.compareTo(d2) > 0);
    }

    @Test
    public void testContains() {
        Donut d = new Donut(new Point(0, 0), 5, 2);
        assertTrue(d.contains(2, 2));
        assertFalse(d.contains(0, 0));
    }

    @Test
    public void testInnerColor() {
        Donut d = new Donut();
        d.setInnerColor(Color.RED);
        assertEquals(Color.RED, d.getInnerColor());
    }

    @Test
    public void testEdgeColor() {
        Donut d = new Donut();
        d.setEdgeColor(Color.RED);
        assertEquals(Color.RED, d.getEdgeColor());
    }

    @Test
    public void testSelectedProperty() {
        Donut d = new Donut();
        d.setSelected(true);
        assertTrue(d.isSelected());
    }

}
