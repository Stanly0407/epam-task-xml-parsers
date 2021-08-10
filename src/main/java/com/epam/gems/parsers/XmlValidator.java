package com.epam.gems.parsers;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {

    private static final Logger LOGGER = Logger.getLogger(XmlValidator.class);

    private String xsdFilename;

    public XmlValidator(String xsdFilename) {
        this.xsdFilename = xsdFilename;
    }

    public String getXsdFilename() {
        return xsdFilename;
    }

    public void setXsdFilename(String xsdFilename) {
        this.xsdFilename = xsdFilename;
    }

    public boolean isValid(String xmlFilename) {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        File schemaXsdLocation = new File(this.getXsdFilename());

        try {
            Schema schema = factory.newSchema(schemaXsdLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlFilename);
            validator.validate(source);
            return true;
        } catch (SAXException | IOException e) {
            LOGGER.error(xmlFilename + " is not correct or valid", e);
            return false;
        }
    }
}
