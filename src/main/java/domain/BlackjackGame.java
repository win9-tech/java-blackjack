package domain;

import java.util.List;


public class BlackjackGame {

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(Players players) {
        this.cardDeck = new CardDeck();
        this.dealer = new Dealer();
        this.players = players;
    }

    public void initDeal() {
        for (int i = 0; i < 2; i++) {
            players.receiveCardAll(cardDeck::draw);
            dealer.receiveCard(cardDeck.draw());
        }
    }

    public void deal(Participant participant) {
        participant.receiveCard(cardDeck.draw());
    }

    public void processDealerTurn(Runnable onHit) {
        while (dealer.isHit()) {
            deal(dealer);
            onHit.run();
        }
    }

    public void determineResult() {
        HandResult dealerHand = new HandResult(dealer);

        for (Player player : players.getPlayers()) {
            HandResult playerHand = new HandResult(player);
            GameResult userResult = GameRule.judge(playerHand, dealerHand);
            player.setGameResult(userResult);
            dealer.setRounds(userResult.reverse());
        }
    }

    public List<Player> getBlackjackPlayers() {
        return this.players.getBlackjackPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
