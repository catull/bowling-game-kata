package kata.bowling;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BowlingGameTest4 extends BowlingGameTestBase {
    @Test
    public void testOneStrike() throws Exception {
        rollStrike();
        g.roll(3);
        g.roll(4);
        rollMany(16, 0);
        assertEquals(24, g.score());
    }
}
