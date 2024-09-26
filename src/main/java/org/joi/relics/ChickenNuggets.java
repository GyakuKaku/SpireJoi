package org.joi.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.curses.Injury;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

public class ChickenNuggets extends CustomRelic {
    public static final String ID = "SpireJoi:ChickenNuggets";
    private static final String IMG_PATH = "joi/img/relics/chicken_nuggets.png";
    private static final String IMG_OTL_PATH = "joi/img/relics/outline/chicken_nuggets.png";
    private static final RelicTier RELIC_TIER = RelicTier.BOSS;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public ChickenNuggets() {
        super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(IMG_OTL_PATH), RELIC_TIER, LANDING_SOUND);
    }

    @Override
    public void onEquip() {
        Injury injury = new Injury();
        UnlockTracker.markCardAsSeen(injury.cardID);
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(injury, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
        AbstractDungeon.player.energy.energyMaster++;
    }

    @Override
    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster--;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}