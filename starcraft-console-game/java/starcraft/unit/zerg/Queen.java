package starcraft.unit.zerg;


import starcraft.unit.Unit;

public class Queen extends Unit {
    private static final int EXTRA_ATTACK_POWER = 15;
    private static final int EXTRA_DEFENSE_POWER = 25;

    public Queen() {
        setAbility(EXTRA_ATTACK_POWER, EXTRA_DEFENSE_POWER);
        setItem();
        setCanFly();
    }

    @Override
    public boolean canFly() {
        return true;
    }
}
