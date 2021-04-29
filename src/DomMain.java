import by.anton.xxssd.builder.DrugsDomBuilder;

public class DomMain {
    public static void main(String[] args) {
        DrugsDomBuilder drugsDomBuilder = new DrugsDomBuilder();
        drugsDomBuilder.buildSetDrugs("resources/xml/drugs.xml");

    }
}
