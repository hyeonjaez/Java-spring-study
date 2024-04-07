package starcraft.unit.protoss;


import starcraft.unit.Unit;

public class Zealot extends Unit {
    private static final int EXTRA_ATTACK_POWER = 5;
    private static final int EXTRA_DEFENSE_POWER = 20;

    public Zealot() {
        setAbility(EXTRA_ATTACK_POWER, EXTRA_DEFENSE_POWER);
        setItem();
        setCanFly();
    }
}
