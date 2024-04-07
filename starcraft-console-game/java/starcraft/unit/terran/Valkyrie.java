package starcraft.unit.terran;


import starcraft.unit.Unit;

public class Valkyrie extends Unit {
    private static final int EXTRA_ATTACK_POWER = 4;
    private static final int EXTRA_DEFENSE_POWER = 12;

    public Valkyrie() {
        setAbility(EXTRA_ATTACK_POWER, EXTRA_DEFENSE_POWER);
        setItem();
        setCanFly();
    }

    @Override
    public boolean canFly() {
        return true;
    }
}
