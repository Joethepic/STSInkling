package inklingmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
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

public class burstBomb extends BaseCard {
    public static final String ID = makeID(burstBomb.class.getSimpleName());
    private static final CardStats info = new CardStats(
            InklingCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    private static final int DAMAGE = 10;
    private static final int UPG_DMG = 5;

    public burstBomb() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DMG);//Pass the required information to the BaseCard constructor.
        setMagic(2);
        tags.add(Tags.NEEDS_INK);
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        AbstractPower po = p.getPower(inkPower.POWER_ID);
        int amount = 0;
        if (po != null) {
            amount = po.amount;
        }
        if (amount >= magicNumber) { // where magicNumber is 5 when unupgraded and 3 when upgraded
            return super.canUse(p, m);
        }
        this.cantUseMessage = Messages.NOT_ENOUGH_INK; // ideally this would be loaded from localization files
        return false;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ReducePowerAction(p, p, inkPower.POWER_ID, magicNumber));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }
}

