package starcraft.unit.protoss;


import starcraft.unit.Unit;

public class Dragoon extends Unit {
    private static final int EXTRA_ATTACK_POWER = 3;
    private static final int EXTRA_DEFENSE_POWER = 15;

    public Dragoon() {
        setAbility(EXTRA_ATTACK_POWER, EXTRA_DEFENSE_POWER);
        setItem();
        setCanFly();
    }

    @Override
    public boolean hasItem() {
        return true;
    }
}
