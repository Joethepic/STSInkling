package inklingmod.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
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

public class motivatedFlowState extends BaseRelic {
    private static final String NAME = "motivatedFlowState"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.RARE; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    public motivatedFlowState() {
        super(ID, NAME, InklingCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
            if (card.hasTag(Tags.FLOW_STATE)) {
                addToBot(new DrawCardAction(1));
            }
    }
    @Override
    public String getUpdatedDescription()
    {
        return "Gives 1 Card whenever a flow state card is played.";
    }
}