package DetailedIntents.patches;

import DetailedIntents.DetailedIntents;
import DetailedIntents.intents.Details;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.monsters.beyond.Maw;

import java.util.ArrayList;

import static DetailedIntents.DetailedIntents.*;

// Patches basegame monsters to have detailed intents
public class BaseGamePatch {

    @SpirePatch(
            clz = Maw.class,
            method = "getMove"
    )
    public static class addDetailedIntents {
        @SpirePostfixPatch()
        public static void addDetails(Maw instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            switch (move.nextMove) {
                case 2:
                    Details weakDetail = new Details(instance, 3, WEAK_TEXTURE, Details.YOU);
                    Details frailDetail = new Details(instance, 3, FRAIL_TEXTURE, Details.YOU);
                    details.add(weakDetail);
                    details.add(frailDetail);
                    break;
                case 4:
                    Details strengthDetail = new Details(instance, 3, STRENGTH_TEXTURE, Details.SELF);
                    details.add(strengthDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

}