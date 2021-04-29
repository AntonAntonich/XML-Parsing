package by.anton.xxssd.handler;

public enum DrugsTags {
    DRUGS("drugs"),
    HEMATOTROPIC_DRUG("hematotropic-drug"),
    NEUROTROPIC_DRUG("neurotropic-drug"),
    UNIT_DOSES("unit-doses"),
    INTERNATIONAL_NONPROPRIETARY_NAME("international-nonproprietary-name"),
    PHARMACEUTICAL_GROUP("pharmaceutical-group"),
    BRAND_NAME("brand-name"),
    DOSAGE_TYPE("dosage-type"),
    NERVOUS_SYSTEM_TYPE("nervous-system-type"),
    PHARMACEUTICAL_COMPANY("pharmaceutical-company"),
    HEMATOTROPIC_SUBGROUP("hematotropic-subgroup");

    private  final String value;

    public String getValue() {
        return value;
    }

    DrugsTags(String value) {
        this.value = value;
    }
}
