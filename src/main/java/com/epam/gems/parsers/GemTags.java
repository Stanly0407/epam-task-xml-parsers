package com.epam.gems.parsers;

public enum GemTags {

//tags for sax and dom parsers

    PRECIOUS_STONE("precious-stone"),
    SEMIPRECIOUS_STONE("semiprecious-stone"),

    CERTIFICATE_NUMBER("certificate-number"),
    NAME("name"),
    EXTRACTION_PLACE("extraction-place"),
    VISUAL_PARAMETERS("visual-parameters"),
    COLOR("color"),
    TRANSPARENT_TYPE("transparent-type"),
    STONE_PLANES("stone-planes"),
    ORIGIN_TYPE("origin-type"),
    CARATS("carats"),
    WEIGHT("weight"),
    ORNAMENTAL_TYPE("ornamental-type");

    private String value;

    GemTags(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
