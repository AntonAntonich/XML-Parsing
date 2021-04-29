package by.anton.xxssd.handler;

import by.anton.xxssd.entity.Drug;
import by.anton.xxssd.entity.HematotropicDrug;
import by.anton.xxssd.entity.NeuroTropicDrug;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import static by.anton.xxssd.handler.DrugsTags.*;
import java.util.*;

public class DrugsSaxHandler extends DefaultHandler {
    private Set<Drug> drugs;
    private Drug current;
    private DrugsTags currentEnum;
    private EnumSet<DrugsTags> withText;

    public DrugsSaxHandler() {
        drugs = new HashSet<>();
        withText = EnumSet.range(DrugsTags.INTERNATIONAL_NONPROPRIETARY_NAME, DrugsTags.HEMATOTROPIC_SUBGROUP);
    }

    public Set<Drug> getDrugs() {
        return drugs;
    }



    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (NEUROTROPIC_DRUG.getValue().equals(localName)) {
            current = new NeuroTropicDrug();
            if (INTERNATIONAL_NONPROPRIETARY_NAME.getValue().equals(attributes.getLocalName(0))) {
                current.setInternationalNonproprietaryName(attributes.getValue(0));
                current.setPharmaceuticalGroup(attributes.getValue(1));
            } else {
                current.setInternationalNonproprietaryName(attributes.getValue(1));
                current.setPharmaceuticalGroup(attributes.getValue(0));
            }
        } else {
            DrugsTags temp = DrugsTags.valueOf(localName.toUpperCase().replace("-", "_"));
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }

        if (HEMATOTROPIC_DRUG.getValue().equals(localName)) {
            current = new HematotropicDrug();
            if (INTERNATIONAL_NONPROPRIETARY_NAME.getValue().equals(attributes.getLocalName(0))) {
                current.setInternationalNonproprietaryName(attributes.getValue(0));
                current.setPharmaceuticalGroup(attributes.getValue(1));
            } else {
                current.setInternationalNonproprietaryName(attributes.getValue(1));
                current.setPharmaceuticalGroup(attributes.getValue(0));
            }
        } else {
            DrugsTags temp = DrugsTags.valueOf(localName.toUpperCase().replace("-", "_"));
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (NEUROTROPIC_DRUG.getValue().equals(localName)) {
            drugs.add(current);
        } else if (HEMATOTROPIC_DRUG.getValue().equals(localName)) {
            drugs.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String info = new String(ch, start, length);
        if (currentEnum != null) {
            switch (currentEnum) {
                case PHARMACEUTICAL_COMPANY:
                    current.setPharmaceuticalCompany(info);
                    break;
                case BRAND_NAME:
                    current.setBrandName(info);
                    break;
                case DOSAGE_TYPE:
                    current.addUnitDoses(info);
                    break;
                case NERVOUS_SYSTEM_TYPE:
                    NeuroTropicDrug temp1 = (NeuroTropicDrug) current;
                    temp1.addNervousSystemType(info);
                    break;
                case HEMATOTROPIC_SUBGROUP:
                    HematotropicDrug temp = (HematotropicDrug) current;
                    temp.setSubGroup(info);
                    break;
                default:
                    throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(), currentEnum.name());
            }
            currentEnum = null;
        }
    }
}
