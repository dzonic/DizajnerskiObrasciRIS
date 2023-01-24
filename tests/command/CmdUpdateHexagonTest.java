package command;

import adapter.HexagonAdapter;
import command.CmdUpdateHexagon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import hexagon.Hexagon;
import java.awt.*;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

class CmdUpdateHexagonTest {
    private HexagonAdapter oldState;
    private HexagonAdapter newState;
    private HexagonAdapter originalState;
    private CmdUpdateHexagon cmdUpdateHexagon;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        oldState = new HexagonAdapter(new Hexagon(3, 2, 3),Color.WHITE, Color.BLACK);
        newState = new HexagonAdapter(new Hexagon(4, 1, 5), Color.BLACK, Color.WHITE);
        originalState = new HexagonAdapter(new Hexagon(3, 2, 3), Color.WHITE, Color.BLACK);
        cmdUpdateHexagon = new CmdUpdateHexagon(oldState, newState);
    }

    @Test
    public void testExecute() {
        cmdUpdateHexagon.execute();
        assertEquals(newState, oldState);
    }

    @Test
    public void testUnexecute() {
        cmdUpdateHexagon.unexecute();
        assertEquals(originalState, oldState);
    }
}