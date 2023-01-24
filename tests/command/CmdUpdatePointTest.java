package command;

import command.CmdUpdatePoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import shapes.Point;
import static org.junit.Assert.*;
import java.awt.*;


class CmdUpdatePointTest {
    private Point oldState;
    private Point newState;
    private Point originalState;
    private CmdUpdatePoint cmdModifyPoint;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        oldState = new Point(1, 3, Color.BLACK);
        newState = new Point(2, 4, Color.WHITE);
        originalState = new Point(1, 3, Color.BLACK);
        cmdModifyPoint = new CmdUpdatePoint(oldState, newState);
    }

    @Test
    public void testExecute() {
        cmdModifyPoint.execute();
        assertEquals(newState, oldState);
    }

    @Test
    public void testUnexecute() {
        cmdModifyPoint.unexecute();
        assertEquals(originalState, oldState);
    }
}