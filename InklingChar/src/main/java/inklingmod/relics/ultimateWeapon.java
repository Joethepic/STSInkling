package inklingmod.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import inklingmod.Tags;
import inklingmod.character.InklingCharacter;
import inklingmod.powers.inkPower;

import java.util.ArrayList;

import static inklingmod.inklingMod.makeID;

public class ultimateWeapon extends BaseRelic {
    private static final String NAME = "ultimateWeapon"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.UNCOMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    public ultimateWeapon() {
        super(ID, NAME, RARITY, SOUND);
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (this.counter == 2) {
            if (card.type == AbstractCard.CardType.ATTACK) {
                flash();
                addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                ArrayList<AbstractCard> groupCopy = new ArrayList<>();
                for (AbstractCard abstractCard : AbstractDungeon.player.hand.group) {
                    if (abstractCard.cost > 0 && abstractCard.costForTurn > 0 && !abstractCard.freeToPlayOnce) {
                        groupCopy.add(abstractCard);
                        continue;
                    }
                }
                for (CardQueueItem i : AbstractDungeon.actionManager.cardQueue) {
                    if (i.card != null) {
                        groupCopy.remove(i.card);
                    }
                }
                AbstractCard c = null;
                if (!groupCopy.isEmpty()) {
                    for (AbstractCard cc : groupCopy) {
                        c = groupCopy.get(AbstractDungeon.cardRandomRng.random(0, groupCopy.size() - 1));
                    }
                } else {
                }
                if (c != null) {
                    c.setCostForTurn(0);
                } else {
                }
            }
            this.counter = -1;
        }
        this.counter += 1;
    }
    @Override
    public void atBattleStart() {
        this.counter = 0;
    }
    @Override
    public String getUpdatedDescription()
    {
        return "Every 2 attacks that are played one random card costs 0 [E] for this turn.";
    }
}