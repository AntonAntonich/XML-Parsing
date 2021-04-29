package by.anton.xxssd.entity;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Hematotropic-Drug", propOrder = {
        "subGroup"
})

public class HematotropicDrug extends Drug{
    @XmlElement(name = "hematotropic-subgroup", required = true)
    @XmlSchemaType(name = "string")
    private HematotropicSubGroups subGroup;

    public void setSubGroup(String subGroup) {
        this.subGroup = HematotropicSubGroups.valueOf(subGroup.toUpperCase());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HematotropicDrug{");
        sb.append("subGroup=").append(subGroup);
        sb.append(", internationalNonproprietaryName='").append(internationalNonproprietaryName).append('\'');
        sb.append(", pharmaceuticalGroup='").append(pharmaceuticalGroup).append('\'');
        sb.append(", pharmaceuticalCompany='").append(pharmaceuticalCompany).append('\'');
        sb.append(", brandName='").append(brandName).append('\'');
        sb.append(", unitDoses=").append(unitDoses);
        sb.append(", unitDosesList=").append(unitDosesList);
        sb.append('}');
        return sb.toString();
    }
}
