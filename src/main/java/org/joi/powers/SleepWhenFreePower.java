package org.joi.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SleepWhenFreePower extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = "SpireJoi:SleepWhenFreePower";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    private static final String IMG_PATH = "joi/img/icons/facade.png";
    private boolean gainEnergyNext = false;
    private boolean firstTurn = false;

    public SleepWhenFreePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.img = ImageMaster.loadImage(IMG_PATH);
        this.type = PowerType.BUFF;
        this.amount = amount;
        this.gainEnergyNext = false;
        this.firstTurn = false;
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        if (this.gainEnergyNext && !this.firstTurn) {
            flash();
            this.addToBot(new GainEnergyAction(this.amount));
        }
        this.firstTurn = false;
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
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }
}