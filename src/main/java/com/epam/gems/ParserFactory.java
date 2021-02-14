package com.epam.gems;

import com.epam.gems.parser.DomParser;
import com.epam.gems.parser.JaxbParser;
import com.epam.gems.parser.Parser;
import com.epam.gems.parser.SaxParser;

public class ParserFactory {

    private enum ParserType {
        DOM, SAX, JAXB
    }

    public Parser createParser(String parserType) {
        ParserType type = ParserType.valueOf(parserType.toUpperCase());
        Parser parser;
        switch (type) {
            case DOM:
                parser = new DomParser();
                break;
            case SAX:
                parser = new SaxParser();
                break;
            case JAXB:
                parser = new JaxbParser();
                break;
            default:
                parser = new JaxbParser();
        }
        return parser;
    }
}
