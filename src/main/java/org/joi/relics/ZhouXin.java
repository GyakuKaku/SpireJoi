package org.joi.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class ZhouXin extends CustomRelic {
    public static final String ID = "SpireJoi:ZhouXin";
    private static final String IMG_PATH = "joi/img/relics/ZhouXin.png";
    private static final String IMG_OTL_PATH = "joi/img/relics/outline/ZhouXin.png";
    private static final String IMG_PATH_C = "joi/img/relics/ZhouXinCry.png";
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;
    private static final RelicStrings Relic_STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);

    public ZhouXin() {
        super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(IMG_OTL_PATH), RELIC_TIER, LANDING_SOUND);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null &&
                info.type != DamageInfo.DamageType.HP_LOSS &&
                info.type != DamageInfo.DamageType.THORNS &&
                damageAmount >= AbstractDungeon.player.currentHealth &&
                damageAmount > 0
                && !this.usedUp
        ) {
            this.flash();
            damageAmount = 0;
            this.invalidZhouXin(true);
        }
        return damageAmount;
    }

    @Override
    public void justEnteredRoom(AbstractRoom room) {
        this.resetZhouXin();
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    // 特殊无效处理
    public void invalidZhouXin(boolean showImg) {
        this.img = ImageMaster.loadImage(IMG_PATH_C);
        if (showImg) {
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }
        this.usedUp = true;
        this.description = Relic_STRINGS.DESCRIPTIONS[2];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    // 重置轴芯
    private void resetZhouXin() {
        this.usedUp = false;
        this.img = ImageMaster.loadImage(IMG_PATH);
        this.description = this.getUpdatedDescription();
    }
}