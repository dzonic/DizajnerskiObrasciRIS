package shapes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CircleTest {

    @Test
    public void testCircleConstructor() {
        Point center = new Point(1, 2);
        int radius = 5;
        Circle c = new Circle(center, radius);
        assertEquals(center, c.getCenter());
        assertEquals(radius, c.getRadius());
    }

    @Test
    public void testMoveBy() {
        Point center = new Point(1, 2);
        int radius = 5;
        Circle c = new Circle(center, radius);
        c.moveBy(3, 4);
        assertEquals(new Point(4, 6), c.getCenter());
    }

    @Test
    public void testCompareTo() {
        Circle c1 = new Circle(new Point(1, 2), 5);
        Circle c2 = new Circle(new Point(1, 2), 8);
        assertEquals(-3, c1.compareTo(c2));
    }

    @Test
    public void testContains() {
        Circle c = new Circle(new Point(1, 2), 5);
        assertTrue(c.contains(3, 4));
        assertFalse(c.contains(8, 9));
    }

    @Test
    public void testEquals() {
        Circle c1 = new Circle(new Point(1, 2), 5);
        Circle c2 = new Circle(new Point(1, 2), 5);
        Circle c3 = new Circle(new Point(1, 2), 8);
        assertTrue(c1.equals(c2));
        assertFalse(c1.equals(c3));
    }

    @Test
    public void testArea() {
        Circle c = new Circle(new Point(1, 2), 5);
        assertEquals(25 * Math.PI, c.area());
    }

}
