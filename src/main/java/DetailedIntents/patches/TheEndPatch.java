package DetailedIntents.patches;

import DetailedIntents.DetailedIntents;
import DetailedIntents.intents.Details;
import DetailedIntents.util.TexLoader;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.monsters.beyond.Deca;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;
import com.megacrit.cardcrawl.monsters.ending.SpireShield;
import com.megacrit.cardcrawl.monsters.ending.SpireSpear;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.BeatOfDeathPower;
import com.megacrit.cardcrawl.powers.PainfulStabsPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.ArrayList;

import static DetailedIntents.DetailedIntents.*;

public class TheEndPatch {

    @SpirePatch(
            clz = SpireShield.class,
            method = "getMove"
    )
    public static class addDetailedIntentsSpireShield {
        @SpirePostfixPatch()
        public static void addDetails(SpireShield instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            switch (move.nextMove) {
                case 1:
                    Details detail = new Details(instance, -1, STRENGTH_TEXTURE);
                    details.add(detail);
                    break;
                case 2:
                    Details blockDetail = new Details(instance, 30, BLOCK_TEXTURE, Details.TargetType.ALL_ENEMIES);
                    details.add(blockDetail);
                    break;
                case 3:
                    int blockAmount = instance.damage.get(1).output;
                    if (AbstractDungeon.ascensionLevel >= 18) {
                        blockAmount = 99;
                    }
                    Details attackBlockDetail = new Details(instance, blockAmount, BLOCK_TEXTURE);
                    details.add(attackBlockDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = SpireSpear.class,
            method = "getMove"
    )
    public static class addDetailedIntentsSpireSpear {
        @SpirePostfixPatch()
        public static void addDetails(SpireSpear instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            switch (move.nextMove) {
                case 1:
                    Details detail;
                    if (AbstractDungeon.ascensionLevel >= 18) {
                        detail = new Details(instance, 2, BURN_TEXTURE, Details.TargetType.DRAW_PILE);
                    } else {
                        detail = new Details(instance, 2, BURN_TEXTURE, Details.TargetType.DISCARD_PILE);
                    }
                    details.add(detail);
                    break;
                case 2:
                    Details strengthDetail = new Details(instance, 2, STRENGTH_TEXTURE, Details.TargetType.ALL_ENEMIES);
                    details.add(strengthDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = CorruptHeart.class,
            method = "getMove"
    )
    public static class addDetailedIntentsCorruptHeart {
        @SpirePostfixPatch()
        public static void addDetails(CorruptHeart instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            String artifactString = makeImagePath("Artifact.png");
            Texture artifactTexture = TexLoader.getTexture(artifactString);
            String beatString = makeImagePath("Beat.png");
            Texture beatTexture = TexLoader.getTexture(beatString);
            String painfulString = makeImagePath("Painful.png");
            Texture painfulTexture = TexLoader.getTexture(painfulString);
            switch (move.nextMove) {
                case 3:
                    Details vulnerableDetail = new Details(instance, 2, VULNERABLE_TEXTURE);
                    details.add(vulnerableDetail);
                    Details weakDetail = new Details(instance, 2, WEAK_TEXTURE);
                    details.add(weakDetail);
                    Details frailDetail = new Details(instance, 2, FRAIL_TEXTURE);
                    details.add(frailDetail);
                    Details dazedDetail = new Details(instance, 1, DAZED_TEXTURE, Details.TargetType.DRAW_PILE);
                    details.add(dazedDetail);
                    Details slimedDetail = new Details(instance, 1, SLIMED_TEXTURE, Details.TargetType.DRAW_PILE);
                    details.add(slimedDetail);
                    Details woundDetail = new Details(instance, 1, WOUND_TEXTURE, Details.TargetType.DRAW_PILE);
                    details.add(woundDetail);
                    Details burnDetail = new Details(instance, 1, BURN_TEXTURE, Details.TargetType.DRAW_PILE);
                    details.add(burnDetail);
                    Details voidDetail = new Details(instance, 1, VOID_TEXTURE, Details.TargetType.DRAW_PILE);
                    details.add(voidDetail);
                    break;
                case 4:
                    Details removeNegStrDetail = new Details(instance, Details.REMOVE_NEG_STR);
                    details.add(removeNegStrDetail);
                    int strAmt = 2;
                    int buffCount = ReflectionHacks.getPrivate(instance, CorruptHeart.class, "buffCount");
                    switch (buffCount) {
                        case 0:
                            Details artifactDetail = new Details(instance, 2, artifactTexture);
                            details.add(artifactDetail);
                            break;
                        case 1:
                            Details beatDetail = new Details(instance, 1, beatTexture);
                            details.add(beatDetail);
                            break;
                        case 2:
                            Details painfulDetail = new Details(instance, 1, painfulTexture);
                            details.add(painfulDetail);
                            break;
                        case 3:
                            strAmt += 10;
                            break;
                        default:
                            strAmt += 50;
                    }
                    Details strengthDetail = new Details(instance, strAmt, STRENGTH_TEXTURE);
                    details.add(strengthDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }
}
