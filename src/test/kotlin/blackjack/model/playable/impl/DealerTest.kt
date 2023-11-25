package blackjack.model.playable.impl

import blackjack.model.card.CardFixture
import blackjack.model.pack.impl.ShuffledPack
import blackjack.model.playblestrategy.impl.DealerStrategy
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({

    "딜러는 처음에 받은 2장의 카드 점수 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 한다" {
        val dealer = Dealer(
            CardFixture.makeCards(CardFixture.king, CardFixture.six)
        )
        dealer.playing(DealerStrategy(16), ShuffledPack)

        dealer.cards.cards shouldContain CardFixture.king
        dealer.cards.cards shouldContain CardFixture.six
        dealer.countOfCards() shouldBe 3
    }

    "딜러는 카드의 점수 합계가 17점 이상이면 추가로 받을 수 없다" {
        val dealer = Dealer(
            CardFixture.makeCards(CardFixture.king, CardFixture.seven)
        )
        dealer.playing(DealerStrategy(17), ShuffledPack)

        dealer.cards.cards shouldContain CardFixture.king
        dealer.cards.cards shouldContain CardFixture.seven
        dealer.countOfCards() shouldBe 2
    }
})
