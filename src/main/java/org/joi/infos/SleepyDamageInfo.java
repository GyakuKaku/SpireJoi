package org.joi.infos;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

/**
 * 防止新增伤害类型DamageType时，影响原版效果判断
 */
public class SleepyDamageInfo extends DamageInfo {
    public SleepyDamageInfo(AbstractCreature damageSource, int base, DamageType type) {
        super(damageSource, base, type);
    }
    public SleepyDamageInfo(AbstractCreature owner, int base) {
        super(owner, base);
    }
}
