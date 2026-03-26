package inklingmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GainPowerEffect;
import inklingmod.Tags;
import inklingmod.character.InklingCharacter;
import inklingmod.powers.inkPower;
import inklingmod.util.CardStats;
import inklingmod.util.EasyXCostAction;

import static basemod.DevConsole.log;
import static inklingmod.util.specialFunctions.atb;

public class gather extends BaseCard {
    public static final String ID = makeID(gather.class.getSimpleName());
    private static final CardStats info = new CardStats(
            InklingCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            -1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    public gather()
    {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        baseMagicNumber = magicNumber = 0;
        exhaust = true;
        tags.add(Tags.GAINS_INK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        atb(new EasyXCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect + params[0]; i++) {
                addToBot(new ApplyPowerAction(p, p, new inkPower(p, 1)));
                addToTop(new GainEnergyAction(1));
            }
            return true;
        }, magicNumber));
        addToBot(new GainEnergyAction(1));
    }
    @Override
    public void upgrade() {
        upgradeMagicNumber(1);
    }
}

