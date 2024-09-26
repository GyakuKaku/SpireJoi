package org.joi.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.joi.actions.ApplySleepyAction;

public class SleepWhenFreePower extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = "SpireJoi:SleepWhenFreePower";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    private static final String IMG_PATH = "joi/img/icons/sleep_when_free.png";
    private boolean gainEnergyNext = false;

    public SleepWhenFreePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.img = ImageMaster.loadImage(IMG_PATH);
        this.type = PowerType.BUFF;
        this.amount = amount;
        this.gainEnergyNext = true;
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        if (this.gainEnergyNext) {
            this.flash();
            this.addToBot(new GainEnergyAction(this.amount * 2));
            if (this.owner != null && this.owner.isPlayer) {
                this.addToBot(new ApplySleepyAction(this.owner, this.owner, new SleepyPower(this.owner, this.amount),this.amount));
            }
        }
        this.gainEnergyNext = true;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.gainEnergyNext = false;
        }
    }

    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + (this.amount * 2) + powerStrings.DESCRIPTIONS[1];
    }
}