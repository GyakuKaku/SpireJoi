package org.joi.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MUAPower extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = "SpireJoi:MUAPower";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    private static final String IMG_PATH = "joi/img/icons/mua.png";
    private static int MUAIdOffset = 0;
    private boolean ready;
    private int damage = 0;

    public MUAPower(AbstractCreature owner, int newDamage) {
        this.name = NAME;
        this.ID = POWER_ID + MUAIdOffset;
        MUAIdOffset++;
        this.owner = owner;
        this.img = ImageMaster.loadImage(IMG_PATH);
        this.type = PowerType.BUFF;
        this.damage = newDamage;
        this.amount = -1;
        this.ready = false;
        this.updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            if (this.ready) {
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
                // 造成伤害
                this.addToBot(new DamageAllEnemiesAction(this.owner, DamageInfo.createDamageMatrix(this.damage, false), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            } else {
                this.ready = true;
                this.updateDescription();
            }
        }
    }
    
    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }
}