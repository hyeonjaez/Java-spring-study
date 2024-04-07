package starcraft.tribe;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;
import starcraft.unit.Unit;

public abstract class Tribe {
    protected List<Unit> unitList;

    protected void addUnit(int unitNumber, String[] unit) {
        for (int i = 0; i < unitNumber; i++) {
            unitList.add(randomProductionUnit(unit));
        }
    }

    private Unit randomProductionUnit(String[] unit) {
        Random random = new Random();
        try {
            String selectUnit = unit[random.nextInt(unit.length)];

            return (Unit) this.getClass().getMethod(selectUnit).invoke(this);


        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTribeName() {

        String className = this.getClass().getName();
        int lastIndex = className.lastIndexOf(".");

        return className.substring(lastIndex + 1);
    }

    public List<Unit> getUnitList() {
        return unitList;
    }
}
