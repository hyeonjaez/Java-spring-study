package starcraft.unit.zerg;


import starcraft.unit.Unit;

public class Mutalisk extends Unit {
    private static final int EXTRA_ATTACK_POWER = 2;
    private static final int EXTRA_DEFENSE_POWER = 8;

    public Mutalisk() {
        setAbility(EXTRA_ATTACK_POWER, EXTRA_DEFENSE_POWER);
        setItem();
        setCanFly();
    }

    @Override
    public boolean canFly() {
        return true;
    }
}
