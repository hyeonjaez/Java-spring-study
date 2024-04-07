package starcraft.unit.zerg;


import starcraft.unit.Unit;

public class Zergling extends Unit {

    private static final int EXTRA_ATTACK_POWER = 2;
    private static final int EXTRA_DEFENSE_POWER = 2;

    public Zergling() {
        setAbility(EXTRA_ATTACK_POWER, EXTRA_DEFENSE_POWER);
        setItem();
        setCanFly();
    }
}
