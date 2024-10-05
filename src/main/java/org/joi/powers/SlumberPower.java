package org.joi.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.joi.patches.CardTagEnum;

public class SlumberPower extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = "SpireJoi:SlumberPower";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    private static final String IMG_PATH = "joi/img/icons/slumber.png";

    public SlumberPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.img = ImageMaster.loadImage(IMG_PATH);
        this.type = PowerType.DEBUFF;
        this.amount = -1;
        // 先结算易伤和睡意
        this.priority = 15;
        this.updateDescription();
    }

    // 减少伤害
    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL && this.owner != null && !this.owner.hasPower("SpireJoi:LetterOfApologyPower")) {
            return damage * 0.75F;
        }
        return damage;
    }

    // 每回合是取消
    @Override
    public void atEndOfRound() {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "SpireJoi:SlumberPower"));
    }

    // 计算被攻击伤害
    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
        if (damageType == DamageInfo.DamageType.NORMAL && this.owner != null && !this.owner.hasPower("SpireJoi:LetterOfApologyPower")) {
            return damage * 2;
        }
        return damage;
    }

    // 计算被攻击伤害
    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType, AbstractCard card) {
        if (card != null && card.hasTag(CardTagEnum.SCARE) && damageType == DamageInfo.DamageType.NORMAL) {
            // 惊醒造成三倍伤害
            return damage * 3;
        } else {
            return this.atDamageReceive(damage, damageType);
        }
    }

    // 被攻击时
    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        return damageAmount;
    }
    
    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0];
    }
}