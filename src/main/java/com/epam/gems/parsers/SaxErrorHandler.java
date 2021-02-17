package com.epam.gems.parsers;

import org.apache.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class SaxErrorHandler implements ErrorHandler {

    private static Logger LOGGER = Logger.getLogger(SaxErrorHandler.class);

    @Override
    public void warning(SAXParseException exception) {
        LOGGER.warn(getLineColumnNumber(exception) + "-" + exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception){
        LOGGER.error(getLineColumnNumber(exception) + "-" + exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) {
        LOGGER.fatal(getLineColumnNumber(exception) + "-" + exception.getMessage());
    }

    private String getLineColumnNumber(SAXParseException exception) {
        return exception.getLineNumber() +" <-LINE " + " : " +"  Column No -> " + exception.getColumnNumber();
    }

}
