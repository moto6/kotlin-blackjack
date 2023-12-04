package blackjack.model.player.playable.impl

import blackjack.model.blackjack.BlackJackStatus
import blackjack.model.blackjack.judgment.impl.BlackJackJudgment
import blackjack.model.card.Hands
import blackjack.model.card.pack.Pack
import blackjack.model.player.BlackjackScore
import blackjack.model.player.PlayableReaction
import blackjack.model.player.Players
import blackjack.model.player.playable.Playable
import blackjack.model.player.playblestrategy.PlayingStrategy
import blackjack.model.result.DealerResult
import blackjack.model.result.PlayableResult

class Dealer(
    val hands: Hands = Hands(),
) : Playable {

    override fun score(): BlackjackScore {
        return hands.totalScore()
    }

    override fun dealing(pack: Pack) {
        this.hands.add(pack.pickCard())
        this.hands.add(pack.pickCard())
    }

    fun countOfCards(): Int {
        return hands.count()
    }

    override fun playing(playingStrategy: PlayingStrategy, pack: Pack): PlayableReaction {
        if (playingStrategy.isHit()) {
            hands.add(pack.pickCard())
            return PlayableReaction.HIT
        }
        return PlayableReaction.STAND
    }

    override fun result(playable: Playable): PlayableResult {
        return BlackJackJudgment.sentence(this, playable)
    }

    override fun status(): BlackJackStatus {
        if (this.score().isBurst()) {
            return BlackJackStatus.STOP
        }
        return BlackJackStatus.ALIVE
    }

    override fun isBurst(): Boolean {
        return this.score().isBurst()
    }

    fun dealerResult(players: Players): DealerResult {
        val results = players.values.map { player -> this.result(player) }
        return DealerResult(
            winningCount = results.count { it == PlayableResult.WIN },
            drawingCount = results.count { it == PlayableResult.DRAW },
            losingCount = results.count { it == PlayableResult.LOSE },
        )
    }
}