package view;

import model.DrawingModel;
import org.junit.Test;
import shapes.Rectangle;
import shapes.Point;

import static org.junit.Assert.*;

import java.awt.*;

public class DrawingViewTest {

    @Test
    public void testSetModel() {
        DrawingModel model = new DrawingModel();
        DrawingView view = new DrawingView();
        view.setModel(model);
        assertEquals(model, view.model);
    }

    @Test
    public void testPaint() {
        DrawingModel model = new DrawingModel();
        DrawingView view = new DrawingView();
        Point Point = new Point(1,1);
        model.addShape(new Rectangle(Point, 10, 20, Color.BLACK, Color.BLACK));
        view.setModel(model);
    }
}
