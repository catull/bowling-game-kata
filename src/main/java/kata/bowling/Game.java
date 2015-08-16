package kata.bowling;

public class Game {
    private final Frame[] frames = new Frame[10];
    private int currentFrameIndex = 0;
    private boolean gameEnded = false;

    /**
     * This method is called each time a player rolls a ball.
     *
     * @param pins The number of pins knocked down
     */
    public void roll(final int pins) {
        if (gameEnded) {
            throw new RuntimeException("Game has ended");
        }

        Frame currentFrame = frames[currentFrameIndex];

        if (currentFrame == null) {
            // First roll, new frame
            frames[currentFrameIndex] = isTenthFrame() ? new TenthFrame() : new Frame();
            currentFrame = frames[currentFrameIndex];
            currentFrame.setFirstRoll(pins);

            if (currentFrame.isStrike()) {
                if (!isTenthFrame()) {
                    currentFrameIndex++;
                }
            }

            return;
        }

        // Second or third roll
        if (isTenthFrame()) {
            if (currentFrame.isSecondRolled()) {
                ((TenthFrame) currentFrame).setThirdRoll(pins);
                gameEnded = true;
            } else {
                currentFrame.setSecondRoll(pins);
                if (!currentFrame.isSpare() && !currentFrame.isStrike()) {
                    gameEnded = true;
                }
            }
        } else {
            currentFrame.setSecondRoll(pins);
            currentFrameIndex++;
        }
    }

    protected boolean isTenthFrame() {
        return currentFrameIndex == 9;
    }

    /**
     * This method is called at the very end of the game and returns the total
     * score.
     *
     * @return The total score of the game.
     */
    public int score() {
        int total = 0;

        for (int i = 0; i < frames.length; i++) {
            Frame f = frames[i];
            total += f.getScore();

            if (! (f instanceof TenthFrame)) {
                if (f.isStrike()) {
                    Frame nextFrame = frames[i + 1];

                    if (nextFrame.isStrike()) {
                        if (nextFrame instanceof TenthFrame) {
                            total += nextFrame.getFirstRoll() + nextFrame.getSecondRoll();
                        } else {
                            total += nextFrame.getFirstRoll() + frames[i + 2].getFirstRoll();
                        }
                    } else {
                        total += nextFrame.getScore();
                    }
                } else if (f.isSpare()) {
                    total += frames[i + 1].getFirstRoll();
                }
            }

        }

        return total;
    }

    protected Frame getCurrentFrame() {
        return this.frames[this.currentFrameIndex];
    }
}
