package org.joi.cards.attack;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static org.joi.character.JoiCharacter.PlayerColorEnum.JOI_YELLOW;

public class Strike extends CustomCard {
    public static final String ID = "SpireJoi:Strike";
    private static final String NAME = "打击";
    private static final String IMG_PATH = "joi/img/cards/strike.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = "造成 !D! 点伤害。";
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = JOI_YELLOW;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Strike() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 6;
        // 基础打击牌tag(潘多拉魔盒等卡牌使用)
        this.tags.add(CardTags.STRIKE);
        // 打击牌tag
        this.tags.add(CardTags.STARTER_STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(
                new DamageAction(
                        m,
                        new DamageInfo(
                                p,
                                damage,
                                DamageInfo.DamageType.NORMAL
                        ),
                        AbstractGameAction.AttackEffect.POISON
                )
        );
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            // 将该卡牌的伤害提高3点。
            this.upgradeDamage(3);
        }
    }
}
