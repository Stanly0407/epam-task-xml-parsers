package com.epam.gems.parsers;

import org.junit.Assert;
import org.junit.Test;

public class XmlValidatorTest {

    private static final String WRONG_XML_FILENAME = "src/test/resources/gemsInvalid.xml";
    private static final String XML_FILENAME = "src/test/resources/gemsTest.xml";
    private static final String XSD_FILENAME = "src/test/resources/gemsTest.xsd";

    private XmlValidator xmlValidator = new XmlValidator(XSD_FILENAME);

    @Test
    public void isValidShouldReturnTrue() {
        boolean expected = xmlValidator.isValid(XML_FILENAME);
        Assert.assertTrue(expected);
    }

    @Test
    public void isValidShouldReturnFalse() {
        boolean expected = xmlValidator.isValid(WRONG_XML_FILENAME);
        Assert.assertFalse(expected);
    }

}
