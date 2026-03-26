package inklingmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import inklingmod.Tags;

import static inklingmod.inklingMod.makeID;

public class aggressiveMovementPower extends BasePower {
    public static final String POWER_ID = makeID("aggressiveMovement");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public aggressiveMovementPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }
    public void onUseCard(AbstractCard card, UseCardAction action) {
        flash();
        if (card.hasTag(Tags.NEEDS_INK) || card.hasTag(Tags.GAINS_INK)) {
            addToBot((AbstractGameAction) new SFXAction("ATTACK_HEAVY"));
            if (Settings.FAST_MODE) {
                addToBot((AbstractGameAction) new VFXAction((AbstractGameEffect) new CleaveEffect()));
            } else {
                addToBot((AbstractGameAction) new VFXAction(this.owner, (AbstractGameEffect) new CleaveEffect(), 0.2F));
            }
            addToBot((AbstractGameAction) new DamageAllEnemiesAction(this.owner,
                    DamageInfo.createDamageMatrix(2, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true));

        }
    }
    public void updateDescription() {
        this.description = "Does 2 damage whenever you use or gain ink";
    }
}
