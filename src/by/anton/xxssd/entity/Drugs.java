package by.anton.xxssd.entity;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="drugs")
public class Drugs {
    @XmlElement(name = "drug")
    private List<? extends Drug> drugsList;

    public Drugs(){}

    public List<? extends Drug> getDrugsList() {
        if(drugsList == null) {
            drugsList = new ArrayList<>();
        }
        return drugsList;
    }

//        public boolean addDrug(Drug drug) {
//        return drugsList.add(drug);
//      }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Drugs{");
        sb.append("drugsList=").append(drugsList);
        sb.append('}');
        return sb.toString();
    }
}
