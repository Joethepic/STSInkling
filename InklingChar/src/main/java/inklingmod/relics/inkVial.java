package inklingmod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import inklingmod.character.InklingCharacter;
import inklingmod.powers.inkPower;

import static inklingmod.inklingMod.makeID;

public class inkVial extends BaseRelic {
    private static final String NAME = "inkVial"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.
    public inkVial()
    {
        super(ID, NAME, InklingCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }
    @Override
    public void atBattleStart()
    {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new inkPower(AbstractDungeon.player, 1)));
    }
    @Override
    public String getUpdatedDescription()
    {
        return "Gain 1 #yinklingchar:Ink at the start of combat";
    }
}
