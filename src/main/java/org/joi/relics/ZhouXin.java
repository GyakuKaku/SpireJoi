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
    // 遗物ID
    public static final String ID = "SpireJoi:ZhouXin";
    // 图片路径
    private static final String IMG_PATH = "joi/img/relics/ZhouXin.png";
    private static final String IMG_PATH_C = "joi/img/relics/ZhouXinCry.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;
    private static final RelicStrings Relic_STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);

    public ZhouXin() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
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
            this.invalidZhouXin();
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
    private void invalidZhouXin() {
        this.img = ImageMaster.loadImage(IMG_PATH_C);
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
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