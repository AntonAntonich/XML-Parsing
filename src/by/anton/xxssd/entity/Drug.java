package by.anton.xxssd.entity;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static by.anton.xxssd.util.FillUnitDosesList.fillUnitDosesList;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Drug", propOrder = {"pharmaceuticalCompany",
        "brandName",
        "unitDoses"})
public abstract class Drug {
    @XmlAttribute(name = "international-nonproprietary-name", required = true)
    protected String internationalNonproprietaryName;
    @XmlAttribute(name = "pharmaceutical-group", required = true)
    protected String pharmaceuticalGroup;
    @XmlElement(name = "pharmaceutical-company-name", required = true)
    protected String pharmaceuticalCompany;
    @XmlElement(name = "brand-name", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String brandName;
    @XmlElement(name = "unit-doses", required = true)
    protected List<String> unitDoses;
    protected List<UnitDoses> unitDosesList;

    public Drug() {}

    protected Drug(String internationalNonproprietaryName,
                String pharmaceuticalGroup,
                String pharmaceuticalCompany,
                String brandName,
                List<UnitDoses> unitDoses) {
        this.internationalNonproprietaryName = internationalNonproprietaryName;
        this.pharmaceuticalGroup = pharmaceuticalGroup;
        this.pharmaceuticalCompany = pharmaceuticalCompany;
        this.brandName = brandName;
        this.unitDosesList = unitDoses;
        fillUnitDosesList(unitDosesList, this.unitDoses);
    }

    public void setInternationalNonproprietaryName(String internationalNonproprietaryName) {
        this.internationalNonproprietaryName = internationalNonproprietaryName;
    }

    public void setPharmaceuticalGroup(String pharmaceuticalGroup) {
        this.pharmaceuticalGroup = pharmaceuticalGroup;
    }

    public void setPharmaceuticalCompany(String pharmaceuticalCompany) {
        this.pharmaceuticalCompany = pharmaceuticalCompany;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void addUnitDoses(String unitDosage) {
        if(unitDosesList == null) {
            unitDosesList = new ArrayList<>();
            }
        unitDosesList.add(UnitDoses.valueOf(unitDosage.toUpperCase()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drug drug = (Drug) o;
        return brandName.equals(drug.brandName);
    }

}
