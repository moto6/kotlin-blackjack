package blackjack.model

import blackjack.model.pack.Pack

class Players(
    val values: Set<Player>,
) {
    constructor(vararg players: Player) : this(players.toSet())

    fun deal(pack: Pack) {
        values.forEach { it.deal(pack) }
    }

    fun isGameOver(): Boolean {
        return values.any { Referee.isGameOver(it) }
    }

    fun count(): Int {
        return values.size
    }

    fun scoreBattle(dealerScore: Int): Map<Player, PlayerResult> {
        return values.associateWith { Referee.playerResult(it, dealerScore) }
    }

    fun walkover(): Map<Player, PlayerResult> {
        return values.associateWith { PlayerResult.WIN }
    }

    init {
        require(values.map { it.name }.distinct().size == values.size) { "Player 들의 이름은 중복이 허용되지 않습니다" }
    }
}