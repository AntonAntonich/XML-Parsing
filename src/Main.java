import by.anton.xxssd.builder.DrugsSAXBuilder;

public class Main {
    public static void main(String[] args) {
        DrugsSAXBuilder saxBuilder = new DrugsSAXBuilder();
        saxBuilder.buildDrugs("resources/xml/drugs.xml");
        System.out.println(saxBuilder.getDrugs());

    }
}
