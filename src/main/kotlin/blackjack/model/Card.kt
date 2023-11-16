package blackjack.model

class Card private constructor(
    val suit: Suit,
    val cardRank: CardRank,
) {
    companion object {
        private val CARDS: Map<Pair< Suit, CardRank>, Card> by lazy {
            Suit.values().flatMap { suit ->
                CardRank.values().map { rank ->
                    (suit to rank) to Card(suit, rank)
                }
            }.toMap()
        }

        fun of(suit: Suit, cardRank: CardRank): Card {
            return requireNotNull(this.CARDS[suit to cardRank]) { "입력된 suit=[$suit] , cardRank=[$cardRank] 는 찾을수 없습니다" }
        }
    }
}
