package starcraft.unit.zerg;


import starcraft.unit.Unit;

public class Guardian extends Unit {

    private static final int EXTRA_ATTACK_POWER = 4;
    private static final int EXTRA_DEFENSE_POWER = 12;

    public Guardian() {
        setAbility(EXTRA_ATTACK_POWER, EXTRA_DEFENSE_POWER);
        setItem();
        setCanFly();
    }

    @Override
    public boolean canFly() {
        return true;
    }
}
