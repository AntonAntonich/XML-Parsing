package by.anton.xxssd.unmarshaller;

import by.anton.xxssd.entity.Drugs;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class DrugsUnMarshaller {
    public static void unmarhsalXmlToJavaDrugClasses(String uri) {
        File file = new File(uri);
        try {
            JAXBContext context = JAXBContext.newInstance(Drugs.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Drugs drugs = (Drugs) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void unmarhsalXmlToJavaDrugClassesUsingXSD(String uri,
                                                             String schemaUri,
                                                             String xmlUri) {
        JAXBContext context = null;

        try {
            context = JAXBContext.newInstance(uri);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaLocation = new File(schemaUri);
            Schema schema = factory.newSchema(schemaLocation);
            unmarshaller.setSchema(schema);
            Drugs drugs = (Drugs) unmarshaller.unmarshal(new File(xmlUri));

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
