package com.epam.gems.parsers;

import com.epam.gems.entities.*;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser implements Parser {

    private static final Logger LOGGER = Logger.getLogger(DomParser.class);

    private List<Gem> gems;

    @Override
    public List<? extends Gem> parse(String xmlFilename) throws ParsingGemsException {
        LOGGER.info("DomParser starts work");

        DomParser domParser = new DomParser();
        domParser.createGemsList(xmlFilename);
        return new ArrayList<>(domParser.getGems());
    }

    public DomParser() {
        gems = new ArrayList<>();
    }

    public List<Gem> getGems() {
        return gems;
    }


    public void createGemsList(String filename) throws ParsingGemsException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(filename);
            Element rootElement = document.getDocumentElement();

            NodeList preciousStonesList = rootElement.getElementsByTagName(GemTags.PRECIOUS_STONE.getValue());
            for (int i = 0; i < preciousStonesList.getLength(); i++) {
                Element preciousStoneElement = (Element) preciousStonesList.item(i);
                PreciousStone preciousStone = createPreciousStone(preciousStoneElement);
                gems.add(preciousStone);

                LOGGER.info("Precious stone - " + preciousStone.getName() +
                        " (Ser.No: " + preciousStone.getCertificateNumber() + ") - was added to gems list.");
            }

            NodeList semipreciousStonesList = rootElement.getElementsByTagName(GemTags.SEMIPRECIOUS_STONE.getValue());
            for (int i = 0; i < semipreciousStonesList.getLength(); i++) {
                Element semipreciousStoneElement = (Element) semipreciousStonesList.item(i);
                SemipreciousStone semipreciousStone = createSemipreciousStone(semipreciousStoneElement);
                gems.add(semipreciousStone);

                LOGGER.info("Precious stone - " + semipreciousStone.getName() +
                        " (Ser.No: " + semipreciousStone.getCertificateNumber() + ") - was added to gems list.");
            }

        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new ParsingGemsException("Exception during gems list creation  : ", e);
        }
    }

    private PreciousStone createPreciousStone(Element gemElement) {
        PreciousStone preciousStone = new PreciousStone(createCommonGemParameters(gemElement));

        String originType = getElementTextContent(gemElement, GemTags.ORIGIN_TYPE.getValue());
        preciousStone.setOriginType(OriginType.valueOf(originType));

        String carats = getElementTextContent(gemElement, GemTags.CARATS.getValue());
        preciousStone.setCarats(Double.parseDouble(carats));

        return preciousStone;
    }

    private SemipreciousStone createSemipreciousStone(Element gemElement) {
        SemipreciousStone semipreciousStone = new SemipreciousStone(createCommonGemParameters(gemElement));

        String ornamentalType = getElementTextContent(gemElement, GemTags.ORNAMENTAL_TYPE.getValue());
        semipreciousStone.setOrnamentalType(Integer.parseInt(ornamentalType));

        String weight = getElementTextContent(gemElement, GemTags.WEIGHT.getValue());
        semipreciousStone.setWeight(Double.parseDouble(weight));

        return semipreciousStone;
    }

    private Gem createCommonGemParameters(Element gemElement) {
        Gem temporaryStone = new Gem();

        String certificateNumber = gemElement.getAttribute(GemTags.CERTIFICATE_NUMBER.getValue());
        temporaryStone.setCertificateNumber(certificateNumber);

        temporaryStone.setName(getElementTextContent(gemElement, GemTags.NAME.getValue()));

        temporaryStone.setExtractionPlace(getElementTextContent(gemElement, GemTags.EXTRACTION_PLACE.getValue()));

        temporaryStone.setVisualParameters(new VisualParameters());
        String color = getElementTextContent(gemElement, GemTags.COLOR.getValue());
        temporaryStone.getVisualParameters().setColor(color);

        String transparentType = getElementTextContent(gemElement, GemTags.TRANSPARENT_TYPE.getValue());
        temporaryStone.getVisualParameters().setTransparentType(TransparentType.valueOf(transparentType));

        String stonePlanes = getElementTextContent(gemElement, GemTags.STONE_PLANES.getValue());
        temporaryStone.getVisualParameters().setStonePlanes(Integer.parseInt(stonePlanes));

        return temporaryStone;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}
