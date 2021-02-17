package com.epam.gems.parsers;

import com.epam.gems.entities.*;
import com.epam.gems.exceptions.ParsingGemsException;
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
    private DocumentBuilder documentBuilder;

    @Override
    public List<? extends Gem> parse(String xmlFilename) throws ParsingGemsException {
        DomParser domParser = new DomParser();
        domParser.createGemsList(xmlFilename);
        return new ArrayList<>(domParser.getGems());
    }

    public DomParser() throws ParsingGemsException {
        gems = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new ParsingGemsException("DomParser configuration exception : ", e);
        }
    }

    public List<Gem> getGems() {
        return gems;
    }

    public void createGemsList(String filename) throws ParsingGemsException {
        Document document;
        try {
            document = documentBuilder.parse(filename);
            Element rootElement = document.getDocumentElement();
            NodeList preciousStonesList = rootElement.getElementsByTagName("precious-stone");
            NodeList semipreciousStonesList = rootElement.getElementsByTagName("semiprecious-stone");
            for (int i = 0; i < preciousStonesList.getLength(); i++) {
                Element preciousStoneElement = (Element) preciousStonesList.item(i);
                PreciousStone preciousStone = createPreciousStone(preciousStoneElement);
                gems.add(preciousStone);
            }
            for (int i = 0; i < semipreciousStonesList.getLength(); i++) {
                Element semipreciousStoneElement = (Element) semipreciousStonesList.item(i);
                SemipreciousStone semipreciousStone = createSemipreciousStone(semipreciousStoneElement);
                gems.add(semipreciousStone);
            }
        } catch (IOException | SAXException e) {
            throw new ParsingGemsException("Exception during object list creation : ", e);
        }
    }

    private PreciousStone createPreciousStone(Element gemElement) {
        PreciousStone preciousStone = new PreciousStone();

        preciousStone.setCertificateNumber(gemElement.getAttribute("certificate-number"));
        preciousStone.setName(getElementTextContent(gemElement, "name"));
        preciousStone.setExtractionPlace(getElementTextContent(gemElement, "extraction-place"));
        VisualParameters visualParameters = new VisualParameters();
        preciousStone.setVisualParameters(visualParameters);
        preciousStone.getVisualParameters().setColor(getElementTextContent(gemElement, "color"));
        preciousStone.getVisualParameters().setTransparentType(TransparentType.valueOf(getElementTextContent(gemElement, "transparent-type")));
        preciousStone.getVisualParameters().setColor(getElementTextContent(gemElement, "stone-planes"));
        preciousStone.setOriginType(OriginType.valueOf(getElementTextContent(gemElement, "origin-type")));
        double carats = Double.parseDouble(getElementTextContent(gemElement, "carats"));
        preciousStone.setCarat(carats);
        return preciousStone;
    }

    private SemipreciousStone createSemipreciousStone(Element gemElement) {
        SemipreciousStone semipreciousStone = new SemipreciousStone();

        semipreciousStone.setCertificateNumber(gemElement.getAttribute("certificate-number"));
        semipreciousStone.setName(getElementTextContent(gemElement, "name"));
        semipreciousStone.setExtractionPlace(getElementTextContent(gemElement, "extraction-place"));
        VisualParameters visualParameters = new VisualParameters();
        semipreciousStone.setVisualParameters(visualParameters);
        semipreciousStone.getVisualParameters().setColor(getElementTextContent(gemElement, "color"));
        semipreciousStone.getVisualParameters().setTransparentType(TransparentType.valueOf(getElementTextContent(gemElement, "transparent-type")));
        semipreciousStone.getVisualParameters().setColor(getElementTextContent(gemElement, "stone-planes"));
        int ornamentalType = Integer.parseInt(gemElement.getAttribute("ornamental-type"));
        semipreciousStone.setOrnamentalType(ornamentalType);
        double weight = Double.parseDouble(getElementTextContent(gemElement, "weight"));
        semipreciousStone.setWeight(weight);
        return semipreciousStone;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}
