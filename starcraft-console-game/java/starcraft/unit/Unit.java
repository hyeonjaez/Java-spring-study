package starcraft.unit;


import starcraft.exception.CanNotAttack;

public abstract class Unit implements Item, Flyable {

    private int attackPower;
    private int defensePower;
    private boolean canFly;
    private boolean hasItem;

    public final void attackUnit(Unit targetUnit) throws CanNotAttack {
        if (!this.canFly) {
            if (targetUnit.canFly && !this.hasItem) {
                throw new CanNotAttack("이 유닛은 대상 유닛을 공격 못합니다.");

            }
        }
        attack(targetUnit);
    }


    private void attack(Unit targetUnit) {
        targetUnit.defensePower -= this.attackPower;
        if (targetUnit.defensePower <= 0) {
            System.out.println("파괴되었다");
        } else {
            System.out.println("공격했다");
        }
    }

    protected void setAbility(int extraAttackPower, int extraDefensePower) {
        this.attackPower += extraAttackPower;
        this.defensePower += extraDefensePower;
    }

    protected void setCanFly() {
        this.canFly = canFly();
    }

    protected void setItem() {
        this.hasItem = hasItem();
    }

    public int getDefensePower() {
        return defensePower;
    }

    public String getUnitName() {
        String fullName = this.getClass().getName();
        int lastIndex = fullName.lastIndexOf(".");
        return fullName.substring(lastIndex + 1);
    }

    @Override
    public boolean canFly() {
        return false;
    }

    @Override
    public boolean hasItem() {
        return false;
    }

    @Override
    public final String toString() {
        return getUnitName() + "(현재 방어력: " + this.defensePower + ")";
    }
}
