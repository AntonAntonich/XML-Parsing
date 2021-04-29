package by.anton.xxssd.marshaller;

import by.anton.xxssd.entity.Drug;
import by.anton.xxssd.entity.Drugs;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class DrugsMarshaller {

    private static void writeDrugClassesToXmlFile( List<? extends Drug> drugsList, String uri) {
        File file = new File(uri);
        try{
            JAXBContext context = JAXBContext.newInstance(Drugs.class);
            Marshaller marshaller = context.createMarshaller();
            Drugs drugs = new Drugs();
//            for (Drug drug : drugsList) {
//                drugs.getDrugsList().add(drug);
//            }
            marshaller.marshal(drugs, new FileOutputStream(file));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
