package org.joi.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

public class ChickenNuggets extends CustomRelic {
    // 遗物ID
    public static final String ID = "SpireJoi:ChickenNuggets";
    // 图片路径
    private static final String IMG_PATH = "joi/img/relics/chicken_nuggets.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.BOSS;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public ChickenNuggets() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    @Override
    public void onEquip() {
        Wound wound = new Wound();
        UnlockTracker.markCardAsSeen(wound.cardID);
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(wound, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
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