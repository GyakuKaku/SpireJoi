package org.joi.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;
import org.joi.SpireJoi;

public class Cat extends CustomRelic {
    // 遗物ID
    public static final String ID = "SpireJoi:Cat";
    // 图片路径
    private static final String IMG_PATH = "joi/img/relics/cat.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.COMMON;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public Cat() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    @Override
    public void atTurnStart() {
        Random random = AbstractDungeon.cardRandomRng;
        // 0.3概率造成伤害
        int attackCheck = MathUtils.random(10);
        SpireJoi.logger.info("attackCheck:" + attackCheck);
        if (attackCheck > 2) {
            return;
        }
        // 0.2概率打玩家
        int attackPlayerCheck = MathUtils.random(10);
        SpireJoi.logger.info("attackPlayerCheck:" + attackPlayerCheck);
        if (attackPlayerCheck > 1) {
            // 攻击敌人
            AbstractMonster target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            if (target != null) {
                this.addToBot(new RelicAboveCreatureAction(target, this));
                this.addToBot(new DamageAction(target, new DamageInfo(AbstractDungeon.player, 5, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            }
        } else {
            // 攻击玩家
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, 5, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}