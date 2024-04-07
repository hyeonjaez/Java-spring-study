package starcraft.unit.terran;


import starcraft.unit.Unit;

public class Tank extends Unit {
    private static final int EXTRA_ATTACK_POWER = 7;
    private static final int EXTRA_DEFENSE_POWER = 15;

    public Tank() {
        setAbility(EXTRA_ATTACK_POWER, EXTRA_DEFENSE_POWER);
        setItem();
        setCanFly();
    }
}
