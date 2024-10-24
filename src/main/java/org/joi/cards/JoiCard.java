package org.joi.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.joi.patches.CardTagEnum;

public abstract class JoiCard extends CustomCard {
    public JoiCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }
    public JoiCard(String id, String name, RegionName img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }
    protected int handleUkeleleDamage(int damage) {
        if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic("SpireJoi:Ukelele") && this.hasTag(CardTagEnum.LIVE)) {
            return damage + 3;
        }
        return damage;
    }
}
