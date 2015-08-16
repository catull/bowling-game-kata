package kata.bowling;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BowlingGameTest1 extends BowlingGameTestBase {
    @Test
    public void testGutterGame() throws Exception {
        for (int i = 0; i < 20; i++) {
            g.roll(0);
        }

        assertEquals(0, g.score());
    }
}
