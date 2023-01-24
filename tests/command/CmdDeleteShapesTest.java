package command;

import command.CmdDeleteShapes;
import model.DrawingModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import shapes.Shape;

import java.util.List;

import static org.mockito.Mockito.*;

class CmdDeleteShapesTest {
    @Mock
    DrawingModel model;
    @Mock
    List<Shape> shapesForDelete;
    @InjectMocks
    CmdDeleteShapes cmdDeleteShapes;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testExecute() {
        cmdDeleteShapes.execute();
    }

    @Test
    void testUnexecute() {
        cmdDeleteShapes.unexecute();
    }
}