package inklingmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import static inklingmod.inklingMod.makeID;

public class inkRecoveryPower extends BasePower {
    public static final String POWER_ID = makeID("subRecoveryPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public inkRecoveryPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }
    public void atStartOfTurnPostDraw()
    {
        flash();
        addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new inkPower(this.owner, this.amount), this.amount));
    }
    public void updateDescription() {
        this.description = "Gives 1 ink at the start of each turn";
    }
}
