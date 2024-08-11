package org.joi.infos;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class SleepyDamageInfo extends DamageInfo {
    public SleepyDamageInfo(AbstractCreature damageSource, int base, DamageType type) {
        super(damageSource, base, type);
    }
    public SleepyDamageInfo(AbstractCreature owner, int base) {
        super(owner, base);
    }
}
