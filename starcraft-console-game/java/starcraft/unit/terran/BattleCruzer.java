package starcraft.unit.terran;


import starcraft.unit.Unit;

public class BattleCruzer extends Unit {
    private static final int EXTRA_ATTACK_POWER = 20;
    private static final int EXTRA_DEFENSE_POWER = 30;

    public BattleCruzer() {
        setAbility(EXTRA_ATTACK_POWER, EXTRA_DEFENSE_POWER);
        setItem();
        setCanFly();
    }

    @Override
    public boolean canFly() {
        return true;
    }
}
