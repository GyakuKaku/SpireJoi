package org.joi.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.helpers.CardLibrary;

public class PlayerLibraryEnum {
    // ***将CardColor和LibraryType的变量名改为你的角色的颜色名称，确保不会与其他mod冲突***
    // ***并且名称需要一致！***

    @SpireEnum
    public static CardLibrary.LibraryType JOI_YELLOW;

    public PlayerLibraryEnum() {
    }
}