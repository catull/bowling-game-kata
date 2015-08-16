package kata.bowling;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BowlingGameTest3 extends BowlingGameTestBase {
    @Test
    public void testOneSpare() throws Exception {
        rollSpare();
        g.roll(3);
        rollMany(17, 0);
        assertEquals(16, g.score());
    }
}
