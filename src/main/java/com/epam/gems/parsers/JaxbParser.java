package com.epam.gems.parsers;

import com.epam.gems.entities.Gem;
import com.epam.gems.entities.GemsStore;
import com.epam.gems.exceptions.ParsingGemsException;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JaxbParser implements Parser {

    private static final Logger LOGGER = Logger.getLogger(JaxbParser.class);

    @Override
    public List<? extends Gem> parse(String xmlFilename) throws ParsingGemsException {
        LOGGER.debug("JaxbParser starts work");

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(JaxbObjectFactory.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            GemsStore gemsStore = (GemsStore) unmarshaller.unmarshal(new File(xmlFilename));

            List<JAXBElement<? extends Gem>> gemsList = gemsStore.getGemsStore();

            List<Gem> gems = new ArrayList<>();

            for (JAXBElement<? extends Gem> g : gemsList) {
                gems.add(g.getValue());
            }

            return gems;

        } catch (JAXBException e) {
            throw new ParsingGemsException("Exception during object list creation : ", e);  //rename all messages
        }
    }
}
