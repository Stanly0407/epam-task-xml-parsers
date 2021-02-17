package com.epam.gems.parsers;

import com.epam.gems.entities.*;
import com.epam.gems.exceptions.ParsingGemsException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.*;

public class SaxParser extends DefaultHandler implements Parser {

    private static final Logger LOGGER = Logger.getLogger(SaxParser.class);

    private SaxContentHandler handler = new SaxContentHandler();
    private XMLReader reader;
    private static final SaxErrorHandler SAX_ERROR_HANDLER = new SaxErrorHandler();

    public SaxParser() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
            if (reader != null) {
                reader.setContentHandler(handler);
                reader.setErrorHandler(SAX_ERROR_HANDLER);
            }
        } catch (ParserConfigurationException | SAXException e) {
            LOGGER.error("Exception during Sax Parser configuration : ", e);
        }
    }

    @Override
    public List<? extends Gem> parse(String filename) throws ParsingGemsException {
        try {
            reader.parse(filename);
        } catch (IOException | SAXException e) {
            throw new ParsingGemsException("Exception during object list creation : ", e);
        }
        return new ArrayList<>(handler.getGems());
    }

}
