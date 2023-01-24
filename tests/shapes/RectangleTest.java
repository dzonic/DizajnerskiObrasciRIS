package shapes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import shapes.Point;
import shapes.Rectangle;

public class RectangleTest {

    @Test
    public void testRectangleConstructor() {
        Point upperLeft = new Point(1, 2);
        int height = 5;
        int width = 6;
        Rectangle r = new Rectangle(upperLeft, height, width);
        assertEquals(upperLeft, r.getUpperLeftPoint());
        assertEquals(height, r.getHeight());
        assertEquals(width, r.getWidth());
    }

    @Test
    public void testMoveBy() {
        Point upperLeft = new Point(1, 2);
        int height = 5;
        int width = 6;
        Rectangle r = new Rectangle(upperLeft, height, width);
        r.moveBy(3, 4);
        assertEquals(new Point(4, 6), r.getUpperLeftPoint());
    }

    @Test
    public void testCompareTo() {
        Rectangle r1 = new Rectangle(new Point(1, 2), 5, 6);
        Rectangle r2 = new Rectangle(new Point(1, 2), 8, 9);
        assertEquals(-42, r1.compareTo(r2));
    }

    @Test
    public void testContains() {
        Rectangle r = new Rectangle(new Point(1, 2), 5, 6);
        assertTrue(r.contains(3, 4));
        assertFalse(r.contains(8, 9));
    }

    @Test
    public void testEquals() {
        Rectangle r1 = new Rectangle(new Point(1, 2), 5, 6);
        Rectangle r2 = new Rectangle(new Point(1, 2), 5, 6);
        Rectangle r3 = new Rectangle(new Point(1, 2), 8, 9);
        assertTrue(r1.equals(r2));
        assertFalse(r1.equals(r3));
    }

    @Test
    public void testArea() {
        Rectangle r = new Rectangle(new Point(1, 2), 5, 6);
        assertEquals(30, r.area());
    }

}
