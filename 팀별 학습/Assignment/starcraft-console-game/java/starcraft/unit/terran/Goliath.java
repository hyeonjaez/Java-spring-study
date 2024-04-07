package starcraft.unit.terran;


import starcraft.unit.Unit;

public class Goliath extends Unit {
    private static final int EXTRA_ATTACK_POWER = 5;
    private static final int EXTRA_DEFENSE_POWER = 15;

    public Goliath() {
        setAbility(EXTRA_ATTACK_POWER, EXTRA_DEFENSE_POWER);
        setCanFly();
        setItem();
    }

    @Override
    public boolean hasItem() {
        return true;
    }


}
