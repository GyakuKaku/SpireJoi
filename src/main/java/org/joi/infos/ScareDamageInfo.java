package org.joi.infos;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

/**
 *  新增伤害类型DamageType时，影响效果判断
 */
public class ScareDamageInfo extends DamageInfo {
    public ScareDamageInfo(AbstractCreature damageSource, int base, DamageType type) {
        super(damageSource, base, type);
    }
    public ScareDamageInfo(AbstractCreature owner, int base) {
        super(owner, base);
    }
}
