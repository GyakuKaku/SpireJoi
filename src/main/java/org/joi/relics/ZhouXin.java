package org.joi.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ZhouXin extends CustomRelic {
    // 遗物ID
    public static final String ID = "SpireJoi:ZhouXin";
    // 图片路径
    private static final String IMG_PATH = "joi/img/relics/ZhouXin.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

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
        ) {
            this.flash();
            damageAmount = 0;

            // 特殊无效处理
            this.usedUp = true;
            this.description = MSG[2];
            this.tips.clear();
            this.tips.add(new PowerTip(this.name, this.description));
            this.initializeTips();
            this.counter = -2;
        }
        return damageAmount;
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}