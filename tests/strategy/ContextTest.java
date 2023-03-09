package strategy;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.io.File;

public class ContextTest {
    File file = new File("test.txt");
    Strategy strategy = new TestStrategy();
    Context context = new Context(strategy);
    @Test
    public void testSaveFile() {
        context.saveFile(file);
        assertTrue(((TestStrategy) strategy).saved);
    }
    @Test
    public void testOpenFile() {
        context.openFile(file);
        assertTrue(((TestStrategy) strategy).opened);
    }
    class TestStrategy implements Strategy {
        public boolean saved = false;
        public boolean opened = false;
        public void saveFile(File file) {
            saved = true;
        }
        public void openFile(File file) {
            opened = true;
        }
    }
}
