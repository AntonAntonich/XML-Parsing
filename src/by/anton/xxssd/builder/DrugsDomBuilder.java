package by.anton.xxssd.builder;

import by.anton.xxssd.entity.Drug;
import by.anton.xxssd.entity.HematotropicDrug;
import by.anton.xxssd.entity.NeuroTropicDrug;
import by.anton.xxssd.exeption.DrugParserException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static by.anton.xxssd.handler.DrugsTags.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DrugsDomBuilder {
    private Set<Drug> drugs;
    private DocumentBuilder documentBuilder;

    public DrugsDomBuilder() {
        drugs = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            //log
        }
    }

    public Set<Drug> getDrugs() {
        return drugs;
    }

    public void buildSetDrugs(String fileName) {
        Document document = null;

        try {
            document = documentBuilder.parse(fileName);
            Element root = document.getDocumentElement();

            NodeList drugsList = root.getElementsByTagName(NEUROTROPIC_DRUG.getValue());
            addDrugToDrugList(drugsList);
            drugsList = root.getElementsByTagName(HEMATOTROPIC_DRUG.getValue());
            addDrugToDrugList(drugsList);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DrugParserException drugParserException) {
            drugParserException.printStackTrace();
        }

        System.out.println(drugs);
    }

    private Drug buildDrug(Element drugElement) throws DrugParserException {
        Drug drug;
        String tagName = drugElement.getTagName();
        if ("neurotropic-drug".equals(tagName)) {
            drug = new NeuroTropicDrug();
            drugAllParameterSetter(drugElement, drug);
            NeuroTropicDrug neuroTropicDrug = (NeuroTropicDrug) drug;
            List<String> nsTypes = getElementsTextList(drugElement, NERVOUS_SYSTEM_TYPE.getValue());
            for (String nsType : nsTypes) {
                neuroTropicDrug.addNervousSystemType(nsType);
            }
            return neuroTropicDrug;
        } else if ("hematotropic-drug".equals(tagName)) {
            drug = new HematotropicDrug();
            drugAllParameterSetter(drugElement, drug);
            HematotropicDrug hematotropicDrug = (HematotropicDrug) drug;
            String subGroup = getElementTextContent(drugElement, HEMATOTROPIC_SUBGROUP.getValue());
            hematotropicDrug.setSubGroup(subGroup);
            return hematotropicDrug;
        } else {
            throw new DrugParserException();
        }
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String text = node.getTextContent();
        return text;
    }

    private static List<String> getElementsTextList(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        List<String> unitDoses = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            String dosage = node.getTextContent();
            unitDoses.add(dosage);
        }
        return unitDoses;
    }

    private void drugAllParameterSetter(Element drugElement, Drug drug) {
        String innName = drugElement.getAttribute(INTERNATIONAL_NONPROPRIETARY_NAME.getValue());
        String pharmGroup = drugElement.getAttribute(PHARMACEUTICAL_GROUP.getValue());
        String pharmCompany = getElementTextContent(drugElement, PHARMACEUTICAL_COMPANY.getValue());
        String brandName = getElementTextContent(drugElement, BRAND_NAME.getValue());
        List<String> unitDoses = getElementsTextList(drugElement, DOSAGE_TYPE.getValue());

        drug.setInternationalNonproprietaryName(innName);
        drug.setPharmaceuticalGroup(pharmGroup);
        drug.setPharmaceuticalCompany(pharmCompany);
        drug.setBrandName(brandName);
        for (String dosage : unitDoses) {
            drug.addUnitDoses(dosage);
        }
    }

    private void addDrugToDrugList(NodeList drugsList) throws DrugParserException {
        for (int i = 0; i < drugsList.getLength(); i++) {
            Element drugElement = (Element) drugsList.item(i);
            Drug drug = buildDrug(drugElement);
            drugs.add(drug);
        }
    }
}
