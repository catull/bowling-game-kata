package kata.bowling;

/**
 * If the last roll is a strike or a spare, there can be a third roll.
 */
public class TenthFrame extends Frame {
    private int thirdRoll = 0;

    public void setThirdRoll(final int pins) {
        if (this.rolls > 2) {
            throw new RuntimeException("Third roll is already made");
        }

        this.thirdRoll = pins;
        this.rolls = 3;
    }

    public boolean isThirdRolled() {
        return this.rolls == 3;
    }

    @Override
    public int getScore() {
        return super.getScore() + this.thirdRoll;
    }

    @Override
    public String toString() {
        return super.toString() + "\nthird: " + this.thirdRoll;
    }
}
