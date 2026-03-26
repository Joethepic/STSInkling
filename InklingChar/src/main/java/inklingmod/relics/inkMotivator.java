package inklingmod.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import inklingmod.Tags;
import inklingmod.character.InklingCharacter;
import inklingmod.powers.inkPower;

import static inklingmod.inklingMod.makeID;
import static inklingmod.inklingMod.modID;

public class inkMotivator extends BaseRelic {
    private static final String NAME = "inkMotivator"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.BOSS; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    public inkMotivator() {
        super(ID, NAME, InklingCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        {
            if (card.hasTag(Tags.NEEDS_INK)) {
                this.counter++;
                if (this.counter == 3) {
                    addToBot((AbstractGameAction) new GainEnergyAction(1));
                }
            }
        }
    }
    public void atTurnStart() {
        this.counter = 0;
    }
    @Override
    public String getUpdatedDescription()
    {
        return "Gives 1 [E] when using 3 or more cards in one turn that use #yinklingchar:Ink.";
    }
}