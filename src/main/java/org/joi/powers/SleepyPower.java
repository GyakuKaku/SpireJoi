package org.joi.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.joi.SpireJoi;
import org.joi.infos.SleepyDamageInfo;

public class SleepyPower extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = "SpireJoi:SleepyPower";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;

    private static final String IMG_PATH = "joi/img/icons/facade.png";

    public SleepyPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.img = ImageMaster.loadImage(IMG_PATH);
        this.type = PowerType.DEBUFF;
        this.amount = amount;
        this.updateDescription();
    }

    // 减少伤害
    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            if (!this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Crane")) {
                return damage * 0.6F;
            }
            return damage * 0.75F;
        }
        return damage;
    }

    // 每回合减少
    @Override
    public void atEndOfRound() {
        if (this.amount == 0) {
            addToBot((AbstractGameAction) new RemoveSpecificPowerAction(this.owner, this.owner, "Weakened"));
        } else {
            addToBot((AbstractGameAction) new ReducePowerAction(this.owner, this.owner, "Weakened", 1));
        }
    }

    // 被攻击时
    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info instanceof SleepyDamageInfo) {
            SpireJoi.logger.info("受到催眠伤害");
        } else {
            SpireJoi.logger.info("受到普通伤害");
        }
        return damageAmount;
    }
    
    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0];
    }
}