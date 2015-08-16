package kata.bowling;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BowlingGameTest2 extends BowlingGameTestBase {
    @Test
    public void testGutterGame() throws Exception {
        rollMany(20, 0);
        assertEquals(0, g.score());
    }

    @Test
    public void testAllOnes() throws Exception {
        rollMany(20, 1);
        assertEquals(20, g.score());
    }
}
