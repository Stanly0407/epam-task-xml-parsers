package com.epam.gems;

import com.epam.gems.entities.Gem;
import com.epam.gems.exceptions.ParserTypeException;
import com.epam.gems.exceptions.ParsingGemsException;
import com.epam.gems.parsers.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Director {

    private static final Logger LOGGER = Logger.getLogger(Director.class);

    private Parser parser;
    private XmlValidator xmlValidator;

    public Director(Parser parser, XmlValidator xmlValidator) {
        this.parser = parser;
        this.xmlValidator = xmlValidator;
    }

    private List<? extends Gem> createGemsListFromXmlFile(String xmlFilename, String xsdFilename, String parserType)
            throws ParserTypeException {
        List<? extends Gem> gemsList = new ArrayList<>();
        ParserType type = ParserType.valueOf(parserType);
        try {
            switch (type) {
                case JAXB:
                    parser = new JaxbParser();
                    break;
                case SAX:
                    parser = new SaxParser();
                    break;
                case DOM:
                    parser = new DomParser();
                    break;
                default:
                    throw new ParserTypeException("Unknown parser type");
            }
            xmlValidator.setXsdFilename(xsdFilename);
            if (xmlValidator.isValid(xmlFilename)) {
                gemsList = parser.parse(xmlFilename);
            }
        } catch (ParsingGemsException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return gemsList;
    }

    public enum ParserType {
        SAX, JAXB, DOM
    }

}
