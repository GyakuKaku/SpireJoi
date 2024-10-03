package org.joi.cards.attack;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.joi.effects.ZhouXinGoEffect;
import org.joi.relics.ZhouXin;

import static org.joi.patches.PlayerColorEnum.JOI_YELLOW;


public class ZhouXinGo extends CustomCard {
    public static final String ID = "SpireJoi:ZhouXinGo";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "joi/img/cards/zhouxin_go.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = JOI_YELLOW;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public ZhouXinGo() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 25;
        this.isMultiDamage = true;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractRelic zhouxin = p.relics.stream().filter(relic -> "SpireJoi:ZhouXin".equals(relic.relicId)).findFirst().orElse(null);
        if (zhouxin != null && zhouxin instanceof ZhouXin) {
            ((ZhouXin) zhouxin).invalidZhouXin(true);
        }
        this.addToBot(new VFXAction(new ZhouXinGoEffect(AbstractDungeon.player.hb.cX + AbstractDungeon.player.hb.width / 2.0F, AbstractDungeon.player.hb.cY), 0.0F));
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        AbstractRelic zhouxin = p.relics.stream().filter(relic -> "SpireJoi:ZhouXin".equals(relic.relicId)).findFirst().orElse(null);
        return zhouxin != null && !zhouxin.usedUp;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(6);
            initializeDescription();
        }
    }
}
