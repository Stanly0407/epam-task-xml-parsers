package com.epam.gems.parsers;

import org.junit.Assert;
import org.junit.Test;

public class ParserFactoryTest {

    private static final String PARSER_TYPE = "jaxb";
    private static final String WRONG_PARSER_TYPE = "ssax";

    private Parser expectedParser;
    private ParserFactory parserFactory = new ParserFactory();

    @Test
    public void chooseParserTypeShouldReturnJaxbParser() {
        expectedParser =  parserFactory.chooseParserType(PARSER_TYPE);
        Assert.assertTrue(expectedParser instanceof  JaxbParser);
    }

    @Test
    public void chooseParserTypeShouldReturnSaxParserAsDefaultParser() {
        expectedParser =  parserFactory.chooseParserType(WRONG_PARSER_TYPE);
        Assert.assertTrue(expectedParser instanceof  SaxParser);
    }

}
