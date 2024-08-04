package org.joi.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class PlayerColorEnum {
    @SpireEnum
    public static AbstractPlayer.PlayerClass JOI_CHARACTER;
    @SpireEnum
    public static AbstractCard.CardColor JOI_YELLOW;

    public PlayerColorEnum() {
    }
}