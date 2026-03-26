package inklingmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import inklingmod.Tags;
import inklingmod.character.InklingCharacter;
import inklingmod.powers.inkPower;
import inklingmod.util.CardStats;
import inklingmod.Messages;

import static basemod.DevConsole.log;

public class unload extends BaseCard {
    public static final String ID = makeID(unload.class.getSimpleName());
    private static final CardStats info = new CardStats(
            InklingCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    public unload()
    {
        super(ID, info);
        setDamage(3, 1);
        tags.add(Tags.NEEDS_INK);
        exhaust = true;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower po = p.getPower(inkPower.POWER_ID);
        int amount = 0;
        if (po != null) {
            amount = po.amount;
        }
        magicNumber = amount;
        for (int i = 0; i < this.magicNumber; i++)
            addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new ReducePowerAction(p, p, inkPower.POWER_ID, amount));
    }
}

