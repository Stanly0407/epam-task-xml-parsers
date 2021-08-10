package com.epam.gems.parsers;

import com.epam.gems.entities.Gem;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxParser extends DefaultHandler implements Parser {

    private static final Logger LOGGER = Logger.getLogger(SaxParser.class);

    private SaxContentHandler handler = new SaxContentHandler();
    private SaxErrorHandler errorHandler = new SaxErrorHandler();

    @Override
    public List<? extends Gem> parse(String filename) throws ParsingGemsException {
        LOGGER.debug("SaxParser starts work");

        XMLReader reader;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
            if (reader != null) {
                reader.setContentHandler(handler);
                reader.setErrorHandler(errorHandler);
                reader.parse(filename);
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new ParsingGemsException("Exception during object list creation : ", e);
        }
        return new ArrayList<>(handler.getGems());
    }

}
