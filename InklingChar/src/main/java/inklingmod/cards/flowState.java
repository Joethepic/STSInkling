package inklingmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import inklingmod.Tags;
import inklingmod.character.InklingCharacter;
import inklingmod.util.CardStats;
import inklingmod.util.GashActionFlow;

public class flowState extends BaseCard {
    public static final String ID = makeID(flowState.class.getSimpleName());
    private static final CardStats info = new CardStats(
    InklingCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
    CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
    CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
    CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use
    0 //Cost
    );

    public flowState() {
        super(ID, info);
        setDamage(3, 2);//Pass the required information to the BaseCard constructor.
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        tags.add(Tags.FLOW_STATE);
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        addToBot(new GashActionFlow(this, this.magicNumber));
    }
}
