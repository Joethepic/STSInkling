package inklingmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import inklingmod.Messages;
import inklingmod.character.InklingCharacter;
import inklingmod.powers.inkPower;
import inklingmod.util.CardStats;
import inklingmod.util.specialFunctions;

public class splatRoller extends BaseCard {
    public static final String ID = makeID(splatRoller.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 25, 5, , , 2, 1
    private static final CardStats info = new CardStats(
            InklingCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.ALL_ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    public int DAMAGE = 10;
    public int UPG_DAMAGE = 3;
    public splatRoller() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        AbstractPower po = p.getPower(inkPower.POWER_ID);
        int amount = 0;
        if (po != null) {
            amount = po.amount;
        }
        if (amount >= 1) { // where magicNumber is 5 when unupgraded and 3 when upgraded
            return super.canUse(p, m);
        }
        this.cantUseMessage = Messages.NOT_ENOUGH_INK; // ideally this would be loaded from localization files
        return false;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        m = specialFunctions.getFrontmostEnemy();
        if (m != null) {
            specialFunctions.dmg(m, damage);
        }
    }
}