package org.joi.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;
import org.joi.contents.ColorContents;

public class MUAPowerAttackAction extends AbstractGameAction {
    private String powerToRemove;
    private AbstractPower powerInstance;
    private static final float DURATION = 0.1F;

    public MUAPowerAttackAction(AbstractCreature target, AbstractCreature source, String powerToRemove) {
        this.setValues(target, source, this.amount);
        this.actionType = ActionType.DEBUFF;
        this.duration = 0.1F;
        this.powerToRemove = powerToRemove;
    }
    public void update() {
        if (this.duration == 0.1F) {
            if (this.target.isDeadOrEscaped()) {
                this.isDone = true;
                return;
            }
            AbstractPower removeMe = null;
            if (this.powerToRemove != null) {
                removeMe = this.target.getPower(this.powerToRemove);
            } else if (this.powerInstance != null && this.target.powers.contains(this.powerInstance)) {
                removeMe = this.powerInstance;
            }
            if (removeMe != null) {
                AbstractDungeon.effectList.add(new TextAboveCreatureEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, "MUA", ColorContents.JOI_COLOR));
                removeMe.onRemove();
                this.target.powers.remove(removeMe);
                AbstractDungeon.onModifyPower();

                for (AbstractOrb o : AbstractDungeon.player.orbs) {
                    o.updateDescription();
                }
            } else {
                this.duration = 0.0F;
            }
        }

        this.tickDuration();
    }
}