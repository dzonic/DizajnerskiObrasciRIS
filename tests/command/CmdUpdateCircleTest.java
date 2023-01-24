package command;

import command.CmdUpdateCircle;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.awt.Color;
import org.mockito.MockitoAnnotations;
import shapes.Circle;
import static org.junit.Assert.assertEquals;
import shapes.Point;

import static org.mockito.Mockito.*;

class CmdUpdateCircleTest {
    private Circle oldState;
    private Circle newState;
    private Circle originalState;
    private CmdUpdateCircle cmdModifyCircle;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        oldState = new Circle(new Point(1, 1), 2,  Color.BLACK, Color.WHITE);
        newState = new Circle(new Point(1, 2), 1, Color.WHITE, Color.BLACK);
        originalState = new Circle(new Point(1, 1), 2,  Color.BLACK, Color.WHITE);
        cmdModifyCircle = new CmdUpdateCircle(oldState, newState);
    }

    @Test
    public void testExecute() {
        cmdModifyCircle.execute();
        assertEquals(newState, oldState);
    }

    @Test
    public void testUnexecute() {
        cmdModifyCircle.unexecute();
        assertEquals(originalState, oldState);
    }
}
