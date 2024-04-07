package starcraft.unit.zerg;


import starcraft.unit.Unit;

public class Hydralisk extends Unit {
    private static final int EXTRA_ATTACK_POWER = 3;
    private static final int EXTRA_DEFENSE_POWER = 7;

    public Hydralisk() {
        setAbility(EXTRA_ATTACK_POWER, EXTRA_DEFENSE_POWER);
        setItem();
        setCanFly();
    }

    @Override
    public boolean hasItem() {
        return true;
    }
}
