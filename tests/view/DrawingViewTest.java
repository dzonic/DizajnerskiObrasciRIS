package view;

import model.DrawingModel;
import org.junit.Test;
import shapes.Rectangle;
import shapes.Point;

import static org.junit.Assert.*;

import java.awt.*;

public class DrawingViewTest {
    DrawingModel model = new DrawingModel();
    DrawingView view = new DrawingView();
    @Test
    public void testSetModel() {
        view.setModel(model);
        assertEquals(model, view.model);
    }
    @Test
    public void testPaint() {
        Point Point = new Point(1,1);
        model.addShape(new Rectangle(Point, 10, 20, Color.BLACK, Color.BLACK));
        view.setModel(model);
    }
}
