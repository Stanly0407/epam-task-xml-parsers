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
        //when
        expectedParser =  parserFactory.chooseParserType(PARSER_TYPE);
        boolean isInstanceOfJaxbParser = expectedParser instanceof  JaxbParser;
        //then
        Assert.assertTrue(isInstanceOfJaxbParser);
    }

    @Test
    public void chooseParserTypeShouldReturnSaxParserAsDefaultParser() {
        //when
        expectedParser =  parserFactory.chooseParserType(WRONG_PARSER_TYPE);
        boolean isInstanceOfSaxParser = expectedParser instanceof  SaxParser;
        //then
        Assert.assertTrue(isInstanceOfSaxParser);
    }

}
