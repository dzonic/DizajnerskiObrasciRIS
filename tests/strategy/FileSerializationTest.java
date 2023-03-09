package strategy;

import static org.junit.Assert.*;
import java.io.File;
import java.util.ArrayList;
import org.junit.Test;
import model.DrawingModel;
import shapes.Point;
import shapes.Shape;

public class FileSerializationTest {
    @Test
    public void testPointShape() {
        DrawingModel model = new DrawingModel();
        Point point = new Point(1, 2);
        model.addShape(point);
        FileSerialization fileSerialization = new FileSerialization(model);
        File file = new File("testPointShape.draw");
        fileSerialization.saveFile(file);
        fileSerialization.openFile(file);
        ArrayList<Shape> shapes = model.getShapes();
        assertEquals(1, shapes.size());
        assertTrue(shapes.get(0) instanceof Point);
    }
}
