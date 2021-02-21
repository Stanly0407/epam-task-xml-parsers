package com.epam.gems;

import com.epam.gems.entities.Gem;
import com.epam.gems.parsers.ParserFactory;
import com.epam.gems.parsers.ParsingGemsException;
import com.epam.gems.parsers.XmlValidator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Director {
    private static final Logger LOGGER = Logger.getLogger(Director.class);

    private XmlValidator xmlValidator;
    private ParserFactory parser;

    public Director(XmlValidator xmlValidator, ParserFactory parser) {
        this.xmlValidator = xmlValidator;
        this.parser = parser;
    }

    public List<? extends Gem> createGemsListFromXmlFile(String xmlFilename) {
        List<? extends Gem> gemsList = new ArrayList<>();

        try {
            if (xmlValidator.isValid(xmlFilename)) {
                gemsList = parser.getParser().parse(xmlFilename);
            }
        } catch (ParsingGemsException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return gemsList;
    }

}
