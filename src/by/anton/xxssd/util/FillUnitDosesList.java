package by.anton.xxssd.util;

import by.anton.xxssd.entity.UnitDoses;

import java.util.List;

public class FillUnitDosesList {
    public static void fillUnitDosesList(List<UnitDoses> unitDosesList,
                                         List<String> unitDoses) {
        for (String unitDose : unitDoses) {
            UnitDoses unitDoses1 = UnitDoses.valueOf(unitDose.toUpperCase());
            unitDosesList.add(unitDoses1);
        }
    }
}
