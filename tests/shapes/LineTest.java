package shapes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import shapes.Point;

public class LineTest {
    @Test
    public void testLineConstructor() {
        Point startPoint = new Point(1, 2);
        Point endPoint = new Point(3, 4);
        Line line = new Line(startPoint, endPoint);
        assertEquals(startPoint, line.getStartPoint());
        assertEquals(endPoint, line.getEndPoint());
    }
    @Test
    public void testMoveBy() {
        Point startPoint = new Point(1, 2);
        Point endPoint = new Point(3, 4);
        Line line = new Line(startPoint, endPoint);
        line.moveBy(3, 4);
        assertEquals(new Point(4, 6), line.getStartPoint());
        assertEquals(new Point(6, 8), line.getEndPoint());
    }
    @Test
    public void testCompareTo() {
        Line line1 = new Line(new Point(1, 2), new Point(3, 4));
        Line line2 = new Line(new Point(1, 2), new Point(5, 6));
        assertEquals(-2, line1.compareTo(line2));
    }
    @Test
    public void testContains() {
        Line line = new Line(new Point(1, 2), new Point(3, 4));
        assertTrue(line.contains(2, 3));
        assertFalse(line.contains(0, 0));
    }
    @Test
    public void testEquals() {
        Line line1 = new Line(new Point(1, 2), new Point(3, 4));
        Line line2 = new Line(new Point(1, 2), new Point(3, 4));
        Line line3 = new Line(new Point(2, 3), new Point(4, 5));
        assertTrue(line1.equals(line2));
        assertFalse(line1.equals(line3));
    }
    @Test
    public void testLength() {
        Line line = new Line(new Point(1, 2), new Point(3, 4));
        assertEquals(2.8284271247461903, line.length(), 0.0001);
    }
    @Test
    public void testMiddleOfLine() {
        Line line = new Line(new Point(1, 2), new Point(3, 4));
        assertEquals(new Point(2, 3), line.middleOfLine());
    }
}

