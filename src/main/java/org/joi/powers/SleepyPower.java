package org.joi.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.joi.SpireJoi;
import org.joi.infos.ScareDamageInfo;

public class SleepyPower extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = "SpireJoi:SleepyPower";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    private static final String IMG_PATH = "joi/img/icons/sleepy.png";

    public SleepyPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.img = ImageMaster.loadImage(IMG_PATH);
        this.type = PowerType.DEBUFF;
        this.amount = amount;
        // 先结算易伤
        this.priority = 10;
        this.updateDescription();
    }

    // 每回合减少一层
    @Override
    public void atEndOfRound() {
        if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower("SpireJoi:LiveRecordPower") && this.owner != null && !this.owner.isPlayer) {
            return;
        }
        if (this.amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        } else {
            addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;

        this.amount += stackAmount;
        if (this.amount >= 5) {
            // 叠到五层取消并进入困倦状态
            SpireJoi.logger.info("睡意叠加到五层");
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new SlumberPower(this.owner)));
        } else {
            SpireJoi.logger.info("睡意叠加:" + this.amount);
        }
    }

    // 计算被攻击伤害
    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
        if (damageType == DamageInfo.DamageType.NORMAL) {
            // 增加伤害
            damage = damage + this.amount;
        }
        return damage;
    }

    // 计算被攻击伤害
    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType, AbstractCard card) {
        return this.atDamageReceive(damage, damageType);
    }

    // 被攻击时
    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info instanceof ScareDamageInfo) {
            SpireJoi.logger.info("受到惊醒伤害");
            // 结束睡意状态
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        } else {
            SpireJoi.logger.info("受到普通伤害");
            // 不做特殊处理
        }
        return damageAmount;
    }
    
    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }
}