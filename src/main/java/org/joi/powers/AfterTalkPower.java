package org.joi.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.joi.patches.CardTagEnum;

public class AfterTalkPower extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = "SpireJoi:AfterTalkPower";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;

    public AfterTalkPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.loadRegion("draw");
        this.type = PowerType.BUFF;
        this.amount = amount;
        this.updateDescription();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(CardTagEnum.LIVE)) {
            this.addToBot(new DrawCardAction(AbstractDungeon.player, this.amount));
        }
    }
    
    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }
}