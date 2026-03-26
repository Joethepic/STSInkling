package inklingmod.potions;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import inklingmod.character.InklingCharacter;
import inklingmod.inklingMod;
import inklingmod.powers.inkPower;

import static inklingmod.character.InklingCharacter.Meta.INKLING;
import static inklingmod.inklingMod.makeID;

public class inkPotion extends BasePotion {
    public static final String ID = makeID(inkPotion.class.getSimpleName());

    private static final Color LIQUID_COLOR = CardHelper.getColor(255, 201, 54);
    private static final Color HYBRID_COLOR = CardHelper.getColor(255, 0, 255);
    private static final Color SPOTS_COLOR = null;

    public inkPotion() {
        super(ID, 3, AbstractPotion.PotionRarity.COMMON, AbstractPotion.PotionSize.MOON, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
        this.isThrown = false;
        playerClass = INKLING;
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new inkPower(AbstractDungeon.player, potency), potency));
        }
    }

    @Override
    public String getDescription() {
        return "Gives " + potency +  " Ink.";
    }
}
