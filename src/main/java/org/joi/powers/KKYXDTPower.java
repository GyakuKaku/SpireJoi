package org.joi.powers;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.joi.actions.ApplySleepyAction;
import org.joi.effects.ShowKKYXDTTimeEffect;

public class KKYXDTPower extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = "SpireJoi:KKYXDTPower";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    private static final String IMG_PATH_1 = "joi/img/icons/kkyxdt_1.png";
    private static final String IMG_PATH_2 = "joi/img/icons/kkyxdt_2.png";
    private static final String IMG_PATH_3 = "joi/img/icons/kkyxdt_3.png";
    private static int KKYXDTIdOffset = 0;
    private int count = 0;

    public KKYXDTPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID + KKYXDTIdOffset;
        KKYXDTIdOffset++;
        this.owner = owner;
        this.img = ImageMaster.loadImage(IMG_PATH_1);
        this.type = PowerType.BUFF;
        this.amount = -1;
        this.count = 1;
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                this.addToBot(new ApplySleepyAction(mo, this.owner, new SleepyPower(mo, 1), 1));
            }
        }
        this.updateDescription();
        this.showKKYXDTTime(IMG_PATH_1);
    }

    private void showKKYXDTTime(String img) {
        this.addToBot(new VFXAction(new ShowKKYXDTTimeEffect(img)));
    }

    @Override
    public void atStartOfTurn() {
        if (this.count == 1) {
            this.img = ImageMaster.loadImage(IMG_PATH_2);
            this.showKKYXDTTime(IMG_PATH_2);
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    this.addToBot(new ApplySleepyAction(mo, this.owner, new SleepyPower(mo, 1), 1));
                }
            }
            this.count = 2;
            this.updateDescription();
        } else if (this.count == 2) {
            this.img = ImageMaster.loadImage(IMG_PATH_3);
            this.showKKYXDTTime(IMG_PATH_3);
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    this.addToBot(new RemoveSpecificPowerAction(mo, mo, SleepyPower.POWER_ID));
                    this.addToBot(new ApplyPowerAction(mo, mo, new SlumberPower(this.owner)));
                }
            }
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
            this.updateDescription();
        }
    }
    
    @Override
    public void updateDescription() {
        this.description = (this.count == 2 ? powerStrings.DESCRIPTIONS[1] : powerStrings.DESCRIPTIONS[0]);
    }
}