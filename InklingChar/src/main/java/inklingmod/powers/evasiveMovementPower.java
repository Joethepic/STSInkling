package inklingmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import inklingmod.Tags;

import static inklingmod.inklingMod.makeID;

public class evasiveMovementPower extends BasePower {
    public static final String POWER_ID = makeID("evasiveMovement");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public evasiveMovementPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }
    public void onUseCard(AbstractCard card, UseCardAction action) {
         if (card.hasTag(Tags.GAINS_INK) || card.hasTag(Tags.NEEDS_INK))
        {
            if (Settings.FAST_MODE) {
                addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, this.amount * 2, true));
            } else {
                addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, this.amount * 2));
            }
        }
        flash();
    }
    public void updateDescription() {
        this.description = "Gives 2 block whenever you use or gain ink";
    }
}
