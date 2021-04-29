package by.anton.xxssd.builder;

import by.anton.xxssd.entity.Drug;
import by.anton.xxssd.entity.HematotropicDrug;
import by.anton.xxssd.entity.NeuroTropicDrug;
import by.anton.xxssd.exeption.DrugParserException;
import by.anton.xxssd.handler.DrugsTags;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DrugsStAXBuilder {
    private Set<Drug> drugs;
    private XMLInputFactory inputFactory;

    public DrugsStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
        drugs = new HashSet<>();
    }

    public Set<Drug> getDrugs() {
        return drugs;
    }

    public void buildSetDrugs(String fileName) throws DrugParserException {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;

        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasText()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name == DrugsTags.NEUROTROPIC_DRUG.getValue()) {
                        NeuroTropicDrug neuro = buildNeuroTropicDrug(reader);
                        drugs.add(neuro);
                    } else if (name == DrugsTags.HEMATOTROPIC_DRUG.getValue()) {
                        Drug hemo = buildHemoTropicDrug(reader);
                        drugs.add(hemo);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new DrugParserException();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                // log + filename
            }
        }
    }

    private NeuroTropicDrug buildNeuroTropicDrug(XMLStreamReader reader) throws XMLStreamException {
        NeuroTropicDrug neuroTropicDrug = new NeuroTropicDrug();
        fillAttributes(neuroTropicDrug, reader);
        String name;
        while (reader.hasText()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    innerSwitch(name, neuroTropicDrug, reader);
                    addNervousSystemType(name, neuroTropicDrug, reader);
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (name == DrugsTags.NEUROTROPIC_DRUG.getValue()) {
                        return neuroTropicDrug;
                    }
            }
        }
        //log + name
        throw new XMLStreamException();
    }

    private HematotropicDrug buildHemoTropicDrug(XMLStreamReader reader) throws XMLStreamException {
        HematotropicDrug hematotropicDrug = new HematotropicDrug();
        fillAttributes(hematotropicDrug, reader);
        String name;
        while (reader.hasText()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    innerSwitch(name, hematotropicDrug, reader);
                    if(name == DrugsTags.HEMATOTROPIC_SUBGROUP.getValue()) {
                        hematotropicDrug.setSubGroup(getXMLText(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (name == DrugsTags.HEMATOTROPIC_DRUG.getValue()) {
                        return hematotropicDrug;
                    }
            }
        }
        //log + name
        throw new XMLStreamException();
    }

    private void addNervousSystemType(String name, NeuroTropicDrug neuroTropicDrug, XMLStreamReader reader) throws XMLStreamException {
        while (name == DrugsTags.NERVOUS_SYSTEM_TYPE.getValue()) {
            neuroTropicDrug.addNervousSystemType(getXMLText(reader));
        }
    }

    private void innerSwitch(String name, Drug drug, XMLStreamReader reader) throws XMLStreamException {
        switch (name) {
            case DrugsTags.PHARMACEUTICAL_COMPANY.getValue():
                drug.setPharmaceuticalGroup(getXMLText(reader));
                break;
            case DrugsTags.BRAND_NAME.getValue():
                drug.setBrandName(getXMLText(reader));
                break;
            case DrugsTags.DOSAGE_TYPE.getValue():
                drug.addUnitDoses(getXMLText(reader));
                break;
        }
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if(reader.hasText()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    private void fillAttributes ( Drug drug, XMLStreamReader reader) {
        drug.setInternationalNonproprietaryName(reader.getAttributeValue(null,
                DrugsTags.INTERNATIONAL_NONPROPRIETARY_NAME.getValue()));
        drug.setPharmaceuticalGroup(reader.getAttributeValue(null,
                DrugsTags.PHARMACEUTICAL_GROUP.getValue()));
    }


}
