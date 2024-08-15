package org.joi.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.joi.SpireJoi;
import org.joi.patches.CardTagEnum;

public class LiveRewardPower extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = "SpireJoi:LiveRewardPower";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    private static final String IMG_PATH = "joi/img/icons/live_reward.png";
    // 已获得金币
    public int earnedGolds;

    public LiveRewardPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.img = ImageMaster.loadImage(IMG_PATH);
        this.type = PowerType.BUFF;
        this.amount = amount;
        this.earnedGolds = 0;
        this.updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        SpireJoi.logger.info("LiveRewardPower:owner" + this.owner.isPlayer);
        if (this.owner != null &&
                this.owner.isPlayer &&
                card != null &&
                card.hasTag(CardTagEnum.LIVE) &&
                this.amount * 20 > this.earnedGolds
        ) {
            SpireJoi.logger.info("LiveRewardPower:计算金币");
            int gold = Math.min(this.amount, this.amount * 20 - this.earnedGolds);
            SpireJoi.logger.info("LiveRewardPower:计算金币" + gold);

            this.owner.gainGold(gold);
            this.earnedGolds = this.earnedGolds + gold;
            this.updateDescription();
            flash();
        }
    }
    
    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1] + (this.amount * 20 - this.earnedGolds) + powerStrings.DESCRIPTIONS[2];
    }
}