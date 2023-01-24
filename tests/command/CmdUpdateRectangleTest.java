package command;

import command.CmdUpdateRectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.mockito.MockitoAnnotations;
import shapes.Point;
import shapes.Rectangle;
import java.awt.*;


class CmdUpdateRectangleTest {

    private Rectangle oldState;
    private Rectangle newState;
    private Rectangle originalState;
    private CmdUpdateRectangle cmdModifyRectangle;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        oldState = new Rectangle(new Point(1, 3), 6, 7, Color.BLACK, Color.WHITE);
        newState = new Rectangle(new Point(2, 4), 4, 9, Color.WHITE, Color.BLACK);
        originalState = new Rectangle(new Point(1, 3), 6, 7,  Color.BLACK, Color.WHITE);
        cmdModifyRectangle = new CmdUpdateRectangle(oldState, newState);
    }
    @Test
    public void testExecute() {
        cmdModifyRectangle.execute();
        assertEquals(newState, oldState);
    }

    @Test
    public void testUnexecute() {
        cmdModifyRectangle.unexecute();
        assertEquals(originalState, oldState);
    }
}
