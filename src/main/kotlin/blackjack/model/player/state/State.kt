package blackjack.model.player.state

import blackjack.model.card.Card

interface State {
    fun draw(card: Card): State
    fun stay(): State
}
