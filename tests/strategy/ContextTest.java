package strategy;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.io.File;

public class ContextTest {

    @Test
    public void testSaveFile() {
        File file = new File("test.txt");
        Strategy strategy = new TestStrategy();
        Context context = new Context(strategy);
        context.saveFile(file);
        assertTrue(((TestStrategy) strategy).saved);
    }

    @Test
    public void testOpenFile() {
        File file = new File("test.txt");
        Strategy strategy = new TestStrategy();
        Context context = new Context(strategy);
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
