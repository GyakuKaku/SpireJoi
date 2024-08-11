package org.joi.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import org.joi.SpireJoi;

@Deprecated
public class SleepAttackAction extends AbstractGameAction {
    private DamageInfo info;

    public SleepAttackAction(AbstractCreature target, DamageInfo info) {
        this.info = info;
        this.setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = AttackEffect.BLUNT_HEAVY;
        this.duration = 0.1F;
    }

    public SleepAttackAction(AbstractCreature target, DamageInfo info, AbstractGameAction.AttackEffect effect) {
        this(target, info);
        this.attackEffect = effect;
    }

    @Override
    public void update() {
        if (this.duration == 0.1F && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
            this.target.damage(this.info);
            SpireJoi.logger.info("SpireJoi: 增加睡意值");
        }
        this.tickDuration();
    }
}
