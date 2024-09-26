package org.joi.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class ResetEnergyAction extends AbstractGameAction {
    public ResetEnergyAction() {
        this.actionType = ActionType.ENERGY;
        this.duration = Settings.ACTION_DUR_FAST;
    }
    public void update() {
        int amount = AbstractDungeon.player.energy.energyMaster - EnergyPanel.totalCount;
        if (amount > 0) {
            this.addToTop(new GainEnergyAction(amount));
        }
        this.isDone = true;
    }
}
