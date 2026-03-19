package domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameRule {

    BOTH_BLACKJACK((player, dealer) -> player.isBlackjack() && dealer.isBlackjack(), GameResult.DRAW),
    PLAYER_BLACKJACK((player, dealer) -> player.isBlackjack(), GameResult.WIN),
    DEALER_BLACKJACK((player, dealer) -> dealer.isBlackjack(), GameResult.LOSE),
    PLAYER_BURST((player, dealer) -> player.isBurst(), GameResult.LOSE),
    DEALER_BURST((player, dealer) -> dealer.isBurst(), GameResult.WIN),
    PLAYER_HIGHER((player, dealer) -> player.getScore() > dealer.getScore(), GameResult.WIN),
    DEALER_HIGHER((player, dealer) -> player.getScore() < dealer.getScore(), GameResult.LOSE),
    TIE((player, dealer) -> true, GameResult.DRAW);

    private final BiPredicate<HandResult, HandResult> condition;
    private final GameResult gameResult;

    GameRule(BiPredicate<HandResult, HandResult> condition, GameResult result) {
        this.condition = condition;
        this.gameResult = result;
    }

    public static GameResult judge(HandResult dealer, HandResult player) {
        return Arrays.stream(values())
                .filter(rule -> rule.condition.test(dealer, player))
                .findFirst()
                .map(rule -> rule.gameResult)
                .orElse(GameResult.DRAW);
    }
}
