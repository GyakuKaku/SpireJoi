package org.joi.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.joi.actions.ApplySleepyAction;
import org.joi.powers.SleepyPower;

public class Schedule extends CustomRelic {
    public static final String ID = "SpireJoi:Schedule";
    private static final String IMG_PATH = "joi/img/relics/schedule.png";
    private static final String IMG_OTL_PATH = "joi/img/relics/outline/schedule.png";
    private static final RelicTier RELIC_TIER = RelicTier.UNCOMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public Schedule() {
        super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(IMG_OTL_PATH), RELIC_TIER, LANDING_SOUND);
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void atTurnStart() {
        this.counter = this.counter > 6 ? 1 : (this.counter + 1);
        switch (this.counter) {
            case 1:
            case 2:
            case 4:
            case 6:
                // 给予全体敌人睡意
                for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    if (!mo.isDeadOrEscaped()) {
                        this.addToBot(new ApplySleepyAction(mo, AbstractDungeon.player, new SleepyPower(mo, 1), 1));
                    }
                }
                break;
            default: break;
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}