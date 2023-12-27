package DetailedIntents.patches;

import DetailedIntents.DetailedIntents;
import DetailedIntents.intents.Details;
import DetailedIntents.util.TexLoader;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.monsters.city.*;

import java.util.ArrayList;

import static DetailedIntents.DetailedIntents.*;

// Patches basegame monsters to have detailed intents
public class CityPatch {

    @SpirePatch(
            clz = Byrd.class,
            method = "getMove"
    )
    public static class addDetailedIntentsByrd {
        @SpirePostfixPatch()
        public static void addDetails(Byrd instance, int num) {
            ByrdStuff(instance);
        }
    }

    @SpirePatch(
            clz = Byrd.class,
            method = "changeState"
    )
    public static class addDetailedIntentsByrdChangeState {
        @SpirePostfixPatch()
        public static void addDetails(Byrd instance, String state) {
            ByrdStuff(instance);
        }
    }

    @SpirePatch(
            clz = Byrd.class,
            method = "takeTurn"
    )
    public static class addDetailedIntentsByrdTakeTurn {
        @SpirePostfixPatch()
        public static void addDetails(Byrd instance) {
            ByrdStuff(instance);
        }
    }

    public static void ByrdStuff(Byrd instance) {
        ArrayList<Details> details = new ArrayList<>();
        EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
        String textureString = makeImagePath("Flight.png");
        Texture texture = TexLoader.getTexture(textureString);
        int amount;
        if (AbstractDungeon.ascensionLevel >= 17) {
            amount = 4;
        } else {
            amount = 3;
        }
        switch (move.nextMove) {
            case 2:
                Details flightDetail = new Details(instance, amount, texture);
                details.add(flightDetail);
                break;
            case 6:
                Details powerDetail = new Details(instance, 1, STRENGTH_TEXTURE);
                details.add(powerDetail);
                break;
        }
        DetailedIntents.intents.put(instance, details);
    }

    @SpirePatch(
            clz = Chosen.class,
            method = "getMove"
    )
    public static class addDetailedIntentsChosen {
        @SpirePostfixPatch()
        public static void addDetails(Chosen instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            String textureString = makeImagePath("Hex.png");
            Texture texture = TexLoader.getTexture(textureString);
            switch (move.nextMove) {
                case 2:
                    Details weakDetail = new Details(instance, 3, WEAK_TEXTURE);
                    details.add(weakDetail);
                    Details strengthDetail = new Details(instance, 3, STRENGTH_TEXTURE);
                    details.add(strengthDetail);
                    break;
                case 3:
                    Details vulnerableDetail = new Details(instance, 2, VULNERABLE_TEXTURE);
                    details.add(vulnerableDetail);
                    break;
                case 4:
                    Details hexDetail = new Details(instance, 1, texture);
                    details.add(hexDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = ShelledParasite.class,
            method = "getMove"
    )
    public static class addDetailedIntentsShelledParasite {
        @SpirePostfixPatch()
        public static void addDetails(ShelledParasite instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            switch (move.nextMove) {
                case 1:
                    Details frailDetail = new Details(instance, 2, FRAIL_TEXTURE);
                    details.add(frailDetail);
                    break;
                case 3:
                    Details lifeSteal = new Details(instance, Details.LIFESTEAL);
                    details.add(lifeSteal);
                    break;

            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = SphericGuardian.class,
            method = "getMove"
    )
    public static class addDetailedIntentsSphericGuardian {
        @SpirePostfixPatch()
        public static void addDetails(SphericGuardian instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            int amount;
            if (AbstractDungeon.ascensionLevel >= 17) {
                amount = 35;
            } else {
                amount = 25;
            }
            switch (move.nextMove) {
                case 4:
                    Details frailDetail = new Details(instance, 5, FRAIL_TEXTURE);
                    details.add(frailDetail);
                    break;
                case 2:
                    Details blockDetail = new Details(instance, amount, BLOCK_TEXTURE);
                    details.add(blockDetail);
                    break;
                case 3:
                    Details attackBlockDetail = new Details(instance, 15, BLOCK_TEXTURE);
                    details.add(attackBlockDetail);
                    break;

            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = Centurion.class,
            method = "getMove"
    )
    public static class addDetailedIntentsCenturion {
        @SpirePostfixPatch()
        public static void addDetails(Centurion instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            int amount;
            if (AbstractDungeon.ascensionLevel >= 17) {
                amount = 20;
            } else {
                amount = 15;
            }
            switch (move.nextMove) {
                case 2:
                    Details blockDetail = new Details(instance, amount, BLOCK_TEXTURE);
                    details.add(blockDetail);
                    break;

            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = Healer.class,
            method = "getMove"
    )
    public static class addDetailedIntentsHealer {
        @SpirePostfixPatch()
        public static void addDetails(Healer instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            String textureString = makeImagePath("Heal.png");
            Texture texture = TexLoader.getTexture(textureString);
            int amount;
            int strAmt;
            if (AbstractDungeon.ascensionLevel >= 17) {
                amount = 20;
                strAmt = 4;
            } else if (AbstractDungeon.ascensionLevel >= 2) {
                strAmt = 3;
                amount = 16;
            } else {
                strAmt = 2;
                amount = 16;
            }
            switch (move.nextMove) {
                case 1:
                    Details frailDetail = new Details(instance, 2, FRAIL_TEXTURE);
                    details.add(frailDetail);
                    break;
                case 2:
                    Details healDetail = new Details(instance, amount, texture, Details.TargetType.ALL_ENEMIES);
                    details.add(healDetail);
                    break;
                case 3:
                    Details buffDetail = new Details(instance, strAmt, STRENGTH_TEXTURE, Details.TargetType.ALL_ENEMIES);
                    details.add(buffDetail);
                    break;

            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = SnakePlant.class,
            method = "getMove"
    )
    public static class addDetailedIntentsSnakePlant {
        @SpirePostfixPatch()
        public static void addDetails(SnakePlant instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            switch (move.nextMove) {
                case 2:
                    Details frailDetail = new Details(instance, 2, FRAIL_TEXTURE);
                    details.add(frailDetail);
                    Details weakDetail = new Details(instance, 2, WEAK_TEXTURE);
                    details.add(weakDetail);
                    break;

            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = Snecko.class,
            method = "getMove"
    )
    public static class addDetailedIntentsSnecko {
        @SpirePostfixPatch()
        public static void addDetails(Snecko instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            String textureString = makeImagePath("Confusion.png");
            Texture texture = TexLoader.getTexture(textureString);
            switch (move.nextMove) {
                case 1:
                    Details confusionDetail = new Details(instance, 1, texture);
                    details.add(confusionDetail);
                    break;
                case 3:
                    if (AbstractDungeon.ascensionLevel >= 17) {
                        Details weakDetail = new Details(instance, 2, WEAK_TEXTURE);
                        details.add(weakDetail);
                    }
                    Details vulnerableDetail = new Details(instance, 2, VULNERABLE_TEXTURE);
                    details.add(vulnerableDetail);
                    break;

            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = GremlinLeader.class,
            method = "getMove"
    )
    public static class addDetailedIntentsGremlinLeader {
        @SpirePostfixPatch()
        public static void addDetails(GremlinLeader instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            int amount;
            int strAmt;
            if (AbstractDungeon.ascensionLevel >= 18) {
                strAmt = 5;
                amount = 10;
            } else if (AbstractDungeon.ascensionLevel >= 3) {
                strAmt = 4;
                amount = 6;
            } else {
                strAmt = 3;
                amount = 6;
            }
            switch (move.nextMove) {
                case 3:
                    Details strDetail = new Details(instance, strAmt, STRENGTH_TEXTURE, Details.TargetType.ALL_ENEMIES);
                    details.add(strDetail);
                    Details blockDetail = new Details(instance, amount, BLOCK_TEXTURE, Details.TargetType.ALL_MINIONS);
                    details.add(blockDetail);
                    break;

            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = Taskmaster.class,
            method = "getMove"
    )
    public static class addDetailedIntentsTaskmaster {
        @SpirePostfixPatch()
        public static void addDetails(Taskmaster instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            int amount;
            if (AbstractDungeon.ascensionLevel >= 18) {
                amount = 3;
            } else if (AbstractDungeon.ascensionLevel >= 3) {
                amount = 2;
            } else {
                amount = 1;
            }
            switch (move.nextMove) {
                case 2:
                    Details statusDetail = new Details(instance, amount, WOUND_TEXTURE, Details.TargetType.DISCARD_PILE);
                    details.add(statusDetail);
                    if (AbstractDungeon.ascensionLevel >= 18) {
                        Details strDetail = new Details(instance, 1, STRENGTH_TEXTURE);
                        details.add(strDetail);
                    }
                    break;

            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = BronzeAutomaton.class,
            method = "getMove"
    )
    public static class addDetailedIntentsBronzeAutomaton {
        @SpirePostfixPatch()
        public static void addDetails(BronzeAutomaton instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            int amount;
            int strAmt;
            if (AbstractDungeon.ascensionLevel >= 9) {
                amount = 12;
                strAmt = 4;
            } else if (AbstractDungeon.ascensionLevel >= 4) {
                amount = 9;
                strAmt = 4;
            } else {
                amount = 9;
                strAmt = 3;
            }
            switch (move.nextMove) {
                case 5:
                    Details blockDetail = new Details(instance, amount, BLOCK_TEXTURE);
                    details.add(blockDetail);
                    Details strDetail = new Details(instance, strAmt, STRENGTH_TEXTURE);
                    details.add(strDetail);
                    break;

            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = BronzeOrb.class,
            method = "getMove"
    )
    public static class addDetailedIntentsBronzeOrb {
        @SpirePostfixPatch()
        public static void addDetails(BronzeOrb instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            switch (move.nextMove) {
                case 2:
                    Details blockDetail = new Details(instance, 12, BLOCK_TEXTURE);
                    details.add(blockDetail);
                    break;
                case 3:
                    Details stealDetail = new Details(instance, Details.STEALS);
                    details.add(stealDetail);
                    break;

            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = Champ.class,
            method = "getMove"
    )
    public static class addDetailedIntentsChamp {
        @SpirePostfixPatch()
        public static void addDetails(Champ instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            String textureString = makeImagePath("Metal.png");
            Texture texture = TexLoader.getTexture(textureString);
            int blockAmt;
            int metalAmt;
            int strAmt;
            int enrageStrAmt;
            if (AbstractDungeon.ascensionLevel >= 19) {
                blockAmt = 20;
                strAmt = 4;
                metalAmt = 7;
                enrageStrAmt = 12;
            } else if (AbstractDungeon.ascensionLevel >= 9) {
                blockAmt = 18;
                strAmt = 3;
                metalAmt = 6;
                enrageStrAmt = 9;
            } else if (AbstractDungeon.ascensionLevel >= 4) {
                blockAmt = 15;
                strAmt = 3;
                metalAmt = 5;
                enrageStrAmt = 9;
            } else {
                blockAmt = 15;
                strAmt = 2;
                metalAmt = 5;
                enrageStrAmt = 6;
            }
            switch (move.nextMove) {
                case 2:
                    Details blockDetail = new Details(instance, blockAmt, BLOCK_TEXTURE);
                    details.add(blockDetail);
                    Details metalDetail = new Details(instance, metalAmt, texture);
                    details.add(metalDetail);
                    break;
                case 4:
                    Details frailDetail = new Details(instance, 2, FRAIL_TEXTURE);
                    details.add(frailDetail);
                    Details vulnerableDetail = new Details(instance, 2, VULNERABLE_TEXTURE);
                    details.add(vulnerableDetail);
                    break;
                case 5:
                    Details strDetail = new Details(instance, strAmt, STRENGTH_TEXTURE);
                    details.add(strDetail);
                    break;
                case 6:
                    Details weakDetail = new Details(instance, 2, WEAK_TEXTURE);
                    details.add(weakDetail);
                    Details vulernableDetailTaunt = new Details(instance, 2, VULNERABLE_TEXTURE);
                    details.add(vulernableDetailTaunt);
                    break;
                case 7:
                    Details cleanseDetail = new Details(instance, Details.CLEANSE);
                    details.add(cleanseDetail);
                    Details enrageDetail = new Details(instance, enrageStrAmt, STRENGTH_TEXTURE);
                    details.add(enrageDetail);
                    break;

            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = TheCollector.class,
            method = "getMove"
    )
    public static class addDetailedIntentsTheCollector {
        @SpirePostfixPatch()
        public static void addDetails(TheCollector instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            int debuffAmt;
            int strAmt;
            int blockAmt;
            if (AbstractDungeon.ascensionLevel >= 19) {
                debuffAmt = 5;
                strAmt = 5;
                blockAmt = 23;
            } else if (AbstractDungeon.ascensionLevel >= 9) {
                debuffAmt = 3;
                strAmt = 4;
                blockAmt = 18;
            } else if (AbstractDungeon.ascensionLevel >= 4) {
                debuffAmt = 3;
                strAmt = 4;
                blockAmt = 15;
            } else {
                debuffAmt = 3;
                strAmt = 3;
                blockAmt = 15;
            }
            switch (move.nextMove) {
                case 3:
                    Details blockDetail = new Details(instance, blockAmt, BLOCK_TEXTURE);
                    details.add(blockDetail);
                    Details strengthDetail = new Details(instance, strAmt, STRENGTH_TEXTURE, Details.TargetType.ALL_ENEMIES);
                    details.add(strengthDetail);
                    break;
                case 4:
                    Details weakDetail = new Details(instance, debuffAmt, WEAK_TEXTURE);
                    details.add(weakDetail);
                    Details vulnerableDetail = new Details(instance, debuffAmt, VULNERABLE_TEXTURE);
                    details.add(vulnerableDetail);
                    Details frailDetail = new Details(instance, debuffAmt, FRAIL_TEXTURE);
                    details.add(frailDetail);
                    break;

            }
            DetailedIntents.intents.put(instance, details);
        }
    }

}