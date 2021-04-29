package by.anton.xxssd.builder;

import by.anton.xxssd.entity.Drug;
import by.anton.xxssd.handler.DrugsSaxHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class DrugsSAXBuilder {
    private Set<Drug> drugs;
    private DrugsSaxHandler handler;
    private XMLReader reader;

    public DrugsSAXBuilder() {
        handler = new DrugsSaxHandler();
        try{
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public Set<Drug> getDrugs() {
        return drugs;
    }

    public void buildDrugs(String fileName) {
        try{
            reader.parse(fileName);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        drugs = handler.getDrugs();
    }
}
