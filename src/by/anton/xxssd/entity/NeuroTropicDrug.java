package by.anton.xxssd.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Neurotropic-Drug", propOrder = {
        "nervousSystemTypes"})
public class NeuroTropicDrug extends Drug{
    private List<NervousSystemTypes> nervousSystemTypes;

    public void addNervousSystemType(String typeNerSys) {
        if(nervousSystemTypes == null) {
            nervousSystemTypes = new ArrayList<>();
        }
            nervousSystemTypes.add(NervousSystemTypes.valueOf(typeNerSys.toUpperCase()));

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NeuroTropicDrug{");
        sb.append("\nnervousSystemTypes=").append(nervousSystemTypes);
        sb.append(", \ninternationalNonproprietaryName='").append(internationalNonproprietaryName).append('\'');
        sb.append(", \npharmaceuticalGroup='").append(pharmaceuticalGroup).append('\'');
        sb.append(", \npharmaceuticalCompany='").append(pharmaceuticalCompany).append('\'');
        sb.append(", \nbrandName='").append(brandName).append('\'');
        sb.append(", \nunitDosesList=").append(unitDosesList);
        sb.append("\n}");
        sb.append("\n");
        return sb.toString();
    }
}
