package command;

import command.CmdUpdateLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import shapes.Line;
import shapes.Point;
import java.awt.*;
import static org.junit.Assert.assertEquals;


class CmdUpdateLineTest {
    private Line oldState;
    private Line newState;
    private Line originalState;
    private CmdUpdateLine cmdModifyLine;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        oldState = new Line(new Point(1, 1), new Point(3, 2),  Color.BLACK);
        newState = new Line(new Point(1, 2), new Point(4, 1), Color.WHITE);
        originalState = new Line(new Point(1, 1), new Point(3, 2),Color.BLACK);
        cmdModifyLine = new CmdUpdateLine(oldState, newState);
    }

    @Test
    public void testExecute() {
        cmdModifyLine.execute();
        assertEquals(newState, oldState);
    }

    @Test
    public void testUnexecute() {
        cmdModifyLine.unexecute();
        assertEquals(originalState, oldState);
    }
}