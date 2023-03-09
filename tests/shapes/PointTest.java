package shapes;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class PointTest {
    @Test
    public void testPointDefaultConstructor() {
        Point point = new Point();
        assertNotNull(point);
    }
    @Test
    public void testPointConstructor() {
        Point point = new Point(1, 2);
        assertEquals(1, point.getxCoordinate());
        assertEquals(2, point.getyCoordinate());
    }
    @Test
    public void testPointConstructorWithEdgeColor() {
        Point point = new Point(1, 2, Color.BLACK);
        assertEquals(Color.BLACK, point.getEdgeColor());
    }
    @Test
    public void testPointConstructorWithAnotherPoint() {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(point1, Color.BLACK);
        assertEquals(point1.getxCoordinate(), point2.getxCoordinate());
        assertEquals(point1.getyCoordinate(), point2.getyCoordinate());
    }
    @Test
    public void testPointConstructorWithSelected() {
        Point point = new Point(1, 2, true);
        assertTrue(point.isSelected());
    }
    @Test
    public void testMoveBy() {
        Point point = new Point(1, 2);
        point.moveBy(1, 1);
        assertEquals(2, point.getxCoordinate());
        assertEquals(3, point.getyCoordinate());
    }
    @Test
    public void testCompareTo() {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(3, 4);
        assertTrue(point1.compareTo(point2) < 0);
    }
    @Test
    public void testContains() {
        Point point = new Point(1, 2);
        assertTrue(point.contains(1, 2));
        assertTrue(point.contains(2, 3));
    }
    @Test
    public void testEquals() {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(1, 2);
        assertTrue(point1.equals(point2));
    }
    @Test
    public void testDistance() {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(3, 4);
        assertEquals(2.8284271247461903, point1.distance(point2.getxCoordinate(), point2.getyCoordinate()), 0.01);
    }
    @Test
    public void testToString() {
        Point point = new Point(1, 2, Color.BLACK);
        assertEquals("Point(1|2)|EdgeColor(-16777216)", point.toString());
    }
    @Test
    public void testParse() {
        Point point = Point.parse("Point(1|2)|EdgeColor(-16777216)");
        assertEquals(1, point.getxCoordinate());
        assertEquals(2, point.getyCoordinate());
        assertEquals(Color.BLACK, point.getEdgeColor());
    }
}
