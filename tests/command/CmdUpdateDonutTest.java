package command;

import command.CmdUpdateDonut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import shapes.Donut;
import shapes.Point;
import static org.junit.Assert.assertEquals;
import java.awt.Color;

import static org.mockito.Mockito.*;

class CmdUpdateDonutTest {
    private Donut oldState;
    private Donut newState;
    private Donut originalState;
    private CmdUpdateDonut cmdModifyDonut;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        oldState = new Donut(new Point(1, 1), 3, 2, Color.BLACK, Color.WHITE);
        newState = new Donut(new Point(1, 2), 4, 1,  Color.WHITE, Color.BLACK);
        originalState = new Donut(new Point(1, 1), 3, 2,  Color.BLACK, Color.WHITE);
        cmdModifyDonut = new CmdUpdateDonut(oldState, newState);
    }
    @Test
    public void testExecute() {
        cmdModifyDonut.execute();
        assertEquals(newState, oldState);
    }
    @Test
    public void testUnexecute() {
        cmdModifyDonut.unexecute();
        assertEquals(originalState, oldState);
    }
}