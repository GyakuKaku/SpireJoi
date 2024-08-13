package org.joi.actions;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import org.joi.powers.SleepyPower;

public class ApplySleepyAction extends ApplyPowerAction {
    public ApplySleepyAction(AbstractCreature target, AbstractCreature source, SleepyPower powerToApply, int stackAmount) {
        super(target, source, powerToApply, stackAmount);
    }

    @Override
    public void update() {
        if (this.target != null) {
            // 已经进入困倦状态不会再叠在睡意
            if (this.target.hasPower("SpireJoi:SlumberPower")) {
                this.isDone = true;
                return;
            }
        }
        super.update();
    }
}
