package kata.bowling;

import org.junit.Before;

public class BowlingGameTestBase {
    protected Game g;

    @Before
    public void setUp() throws Exception {
        g = new Game();
    }

    protected void rollMany(final int n, final int pins) {
        for (int i = 0; i < n; i++) {
            g.roll(pins);
        }
    }

    protected void rollStrike() {
        g.roll(10);
    }

    protected void rollSpare() {
        g.roll(5);
        g.roll(5);
    }
}
