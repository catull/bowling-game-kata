package kata.bowling;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BowlingGameTest5 extends BowlingGameTestBase {
    @Test
    public void testPerfectGame() throws Exception {
        rollMany(12, 10);
        assertEquals(300, g.score());
    }
}
