package kata.bowling;

public class Frame {
    private int firstRoll = 0;
    private int secondRoll = 0;
    protected int rolls = 0;

    public int getFirstRoll() {
        return firstRoll;
    }

    public void setFirstRoll(final int pins) {
        if (this.rolls > 0) {
            throw new RuntimeException("First roll is already made");
        }

        this.firstRoll = pins;
        this.rolls = 1;
    }

    public int getSecondRoll() {
        return secondRoll;
    }

    public void setSecondRoll(final int pins) {
        if (this.rolls > 1) {
            throw new RuntimeException("Second roll is already made");
        }

        this.secondRoll = pins;
        this.rolls = 2;
    }

    public boolean isStrike() {
        return this.firstRoll == 10;
    }

    public boolean isSpare() {
        return !isStrike() && (this.firstRoll + this.secondRoll == 10);
    }

    public int getScore() {
        return this.firstRoll + this.secondRoll;
    }

    public boolean isFirstRolled() {
        return this.rolls == 1;
    }

    public boolean isSecondRolled() {
        return this.rolls == 2;
    }

    @Override
    public String toString() {
        return "first: " + this.firstRoll + "\nsecond:" + this.secondRoll;
    }
}
