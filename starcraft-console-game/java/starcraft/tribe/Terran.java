package starcraft.tribe;

import java.util.ArrayList;
import starcraft.unit.terran.BattleCruzer;
import starcraft.unit.terran.Goliath;
import starcraft.unit.terran.Marine;
import starcraft.unit.terran.Tank;
import starcraft.unit.terran.Valkyrie;
import starcraft.unit.terran.Wraith;

public class Terran extends Tribe {

    private static final String[] UNIT = {"marine", "tank", "goliath", "wraith", "valkyrie", "battleCruzer"};
    private static final int PRODUCTION_NUMBER = 5;

    public Terran() {
        unitList = new ArrayList<>();
        addUnit(PRODUCTION_NUMBER, UNIT);
    }

    public Marine marine() {
        return new Marine();
    }

    public Tank tank() {
        return new Tank();
    }

    public Valkyrie valkyrie() {
        return new Valkyrie();
    }

    public Goliath goliath() {
        return new Goliath();
    }

    public Wraith wraith() {
        return new Wraith();
    }

    public BattleCruzer battleCruzer() {
        return new BattleCruzer();
    }
}
