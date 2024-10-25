package org.joi.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import org.joi.patches.CardTagEnum;

public class Ukulele extends CustomRelic {
    public static final String ID = "SpireJoi:Ukulele";
    private static final String IMG_PATH = "joi/img/relics/ukulele.png";
    private static final String IMG_OTL_PATH = "joi/img/relics/outline/ukulele.png";
    private static final RelicTier RELIC_TIER = RelicTier.UNCOMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public Ukulele() {
        super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(IMG_OTL_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public float atDamageModify(float damage, AbstractCard c) {
        if (c.hasTag(CardTagEnum.LIVE)) {
            return damage + 3.0F;
        }
        return damage;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}