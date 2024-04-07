package starcraft.unit.protoss;


import starcraft.unit.Unit;

public class Carrier extends Unit {
    private static final int EXTRA_ATTACK_POWER = 25;
    private static final int EXTRA_DEFENSE_POWER = 40;

    public Carrier() {
        setAbility(EXTRA_ATTACK_POWER, EXTRA_DEFENSE_POWER);
        setItem();
        setCanFly();
    }

    @Override
    public boolean canFly() {
        return true;
    }
}
