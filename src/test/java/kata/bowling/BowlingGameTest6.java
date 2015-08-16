package kata.bowling;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BowlingGameTest6 extends BowlingGameTestBase {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void testRepeatedFirstRoll() {
        g.roll(0);
        Frame f = g.getCurrentFrame();
        expectedException.expect(RuntimeException.class);
        f.setFirstRoll(-9);
    }

    @Test
    public void testRepeatedSecondRoll() {
        g.roll(0);
        g.roll(0);
        Frame f = g.getCurrentFrame();
        expectedException.expect(RuntimeException.class);
        f.setSecondRoll(-9);
    }

    @Test
    public void testGameOver() {
        rollMany(20, 0);
        expectedException.expect(RuntimeException.class);
        rollMany(1, 0);
    }

    @Test
    public void testGameOver2() {
        rollMany(12, 10);
        expectedException.expect(RuntimeException.class);
        rollMany(1, 0);
    }
}
