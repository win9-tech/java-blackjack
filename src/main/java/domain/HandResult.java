package domain;

public class HandResult {
    private final int score;
    private final boolean isBlackjack;
    private final boolean isBurst;

    public HandResult(Participant participant) {
        this.score = participant.calculateScore();
        this.isBlackjack = participant.isBlackjack();
        this.isBurst = participant.isBurst(score);
    }

    public int getScore() {
        return score;
    }

    public boolean isBlackjack() {
        return isBlackjack;
    }

    public boolean isBurst() {
        return isBurst;
    }
}
