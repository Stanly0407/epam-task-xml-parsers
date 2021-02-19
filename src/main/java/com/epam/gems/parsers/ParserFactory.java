package com.epam.gems.parsers;


import org.apache.log4j.Logger;

public class ParserFactory {
    private static final Logger LOGGER = Logger.getLogger(ParserFactory.class);

    private Parser parser;

    private static final String SAX = "SAX";
    private static final String JAXB = "JAXB";
    private static final String DOM = "DOM";

    public ParserFactory() {
    }

    public ParserFactory(String parserType) {
        this.parser = chooseParserType(parserType);
    }

    public Parser chooseParserType(String parserType) {
        String type = parserType.toUpperCase();
        if (!type.equals(SAX) && !type.equals(JAXB) && !type.equals(DOM)) {
            type = SAX;
            LOGGER.info("Unknown parser type. Sax parser was set as default parser");
        }
        switch (type) {
            case SAX:
                parser = new SaxParser();
                break;
            case JAXB:
                parser = new JaxbParser();
                break;
            case DOM:
                parser = new DomParser();
                break;
        }
        return parser;
    }


    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }
}
