package starcraft.player;

import starcraft.exception.CanNotAttack;
import starcraft.tribe.Tribe;
import starcraft.unit.Unit;

public abstract class Player {

    private Tribe tribe;

    protected void setTribe(Tribe tribe) {
        this.tribe = tribe;
    }

    public void printMyUnit() {
        int count = 0;
        for (Unit unit : tribe.getUnitList()) {
            System.out.println(count + ". " + unit.toString());
            count++;
        }
    }

    public void deleteDieUnit() {
        this.getTribe().getUnitList().removeIf(unit -> unit.getDefensePower() <= 0);
    }

    public void attack(Unit targetUnit, int friendlyNumber) throws CanNotAttack {
        this.tribe.getUnitList().get(friendlyNumber).attackUnit(targetUnit);
    }

    public Tribe getTribe() {
        return tribe;
    }

    public String getName() {
        String fullName = this.getClass().getName();
        int lastIndex = fullName.lastIndexOf(".");
        return fullName.substring(lastIndex + 1);
    }


}
