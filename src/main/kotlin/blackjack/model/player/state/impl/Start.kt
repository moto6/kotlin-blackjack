package blackjack.model.player.state.impl

import blackjack.model.card.Card
import blackjack.model.card.Hands
import blackjack.model.player.state.State

class Start(
    private val hands: Hands,
) : State {
    constructor(cards: List<Card> = emptyList()) : this(Hands(cards))

    init {
        require(hands.count() <= 2) { "카드 수량이 부족합니다" }
    }

    override fun draw(card: Card): State {
        val hands = hands + card
        if (hands.totalScore().isBlackJackScore()) {
            return BlackJack()
        }
        if (hands.hasTwo()) {
            return Hit(hands)
        }
        TODO()
    }

    override fun stay(): State {
        throw IllegalArgumentException()
    }
}
