package com.epam.gems.parsers;

import com.epam.gems.entities.*;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SaxContentHandler extends DefaultHandler {
    private static final Logger LOGGER = Logger.getLogger(SaxContentHandler.class);

    private List<Gem> gems;
    private PreciousStone temporaryPreciousStone = new PreciousStone();
    private SemipreciousStone temporarySemipreciousStone = new SemipreciousStone();
    private Gem temporaryStone = new Gem();
    private String currentXmlTag;
    private boolean isCurrentPreciousStone;

    private static final String[] TAGS_ARRAY = new String[]{"name", "extraction-place", "visual-parameters", "color",
            "transparent-type", "stone-planes", "origin-type", "carats", "weight", "ornamental-type"};
    private static final List<String> GEM_TAGS = new ArrayList<>(Arrays.asList(TAGS_ARRAY));

    public SaxContentHandler() {
        gems = new ArrayList<>();
    }

    public List<Gem> getGems() {
        return gems;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals(GemTags.PRECIOUS_STONE.getValue())) {
            temporaryStone.setCertificateNumber(attributes.getValue(0));
            isCurrentPreciousStone = true;
        } else if (qName.equals(GemTags.SEMIPRECIOUS_STONE.getValue())) {
            temporaryStone.setCertificateNumber(attributes.getValue(0));
            isCurrentPreciousStone = false;
        } else {
            if (GEM_TAGS.contains(qName)) {
                currentXmlTag = qName;
            }
        }
    }

    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).trim();
        if (currentXmlTag != null) {
            setCommonStoneParameters(data);
            if (isCurrentPreciousStone) {
                setCurrentPreciousStoneParameters(data);
            } else {
                setCurrentSemipreciousStoneParameters(data);
            }
        }
        currentXmlTag = null;
    }

    public void endElement(String uri, String localName, String qName) {
        if (qName.equals(GemTags.PRECIOUS_STONE.getValue())) {
            PreciousStone currentPreciousStone = new PreciousStone(temporaryStone, temporaryPreciousStone);
            gems.add(currentPreciousStone);

            LOGGER.info("Precious stone - " + currentPreciousStone.getName() +
                    " (Ser.No: " + currentPreciousStone.getCertificateNumber() + ") - was added to gems list.");

        } else if (qName.equals(GemTags.SEMIPRECIOUS_STONE.getValue())) {
            SemipreciousStone currentSemipreciousStone = new SemipreciousStone(temporaryStone, temporarySemipreciousStone);
            gems.add(currentSemipreciousStone);

            LOGGER.info("Semiprecious stone - " + currentSemipreciousStone.getName() +
                    " (Ser.No: " + currentSemipreciousStone.getCertificateNumber() + ") - was added to gems list.");
        }
    }

    private void setCommonStoneParameters(String data) {
        if (currentXmlTag.equals(GemTags.NAME.getValue())) {
            temporaryStone.setName(data);
        } else if (currentXmlTag.equals(GemTags.EXTRACTION_PLACE.getValue())) {
            temporaryStone.setExtractionPlace(data);
        } else if (currentXmlTag.equals(GemTags.VISUAL_PARAMETERS.getValue())) {
            VisualParameters visualParameters = new VisualParameters();
            temporaryStone.setVisualParameters(visualParameters);
        } else if (currentXmlTag.equals(GemTags.COLOR.getValue())) {
            temporaryStone.getVisualParameters().setColor(data);
        } else if (currentXmlTag.equals(GemTags.TRANSPARENT_TYPE.getValue())) {
            TransparentType transparentType = TransparentType.valueOf(data);
            temporaryStone.getVisualParameters().setTransparentType(transparentType);
        } else if (currentXmlTag.equals(GemTags.STONE_PLANES.getValue())) {
            int stonePlanesQuantity = Integer.parseInt(data);
            temporaryStone.getVisualParameters().setStonePlanes(stonePlanesQuantity);
        }
    }

    private void setCurrentPreciousStoneParameters(String data) {
        if (currentXmlTag.equals(GemTags.ORIGIN_TYPE.getValue())) {
            OriginType originType = OriginType.valueOf(data);
            temporaryPreciousStone.setOriginType(originType);
        } else if (currentXmlTag.equals(GemTags.CARATS.getValue())) {
            double carats = Double.parseDouble(data);
            temporaryPreciousStone.setCarats(carats);
        }
    }

    private void setCurrentSemipreciousStoneParameters(String data) {
        if (currentXmlTag.equals(GemTags.WEIGHT.getValue())) {
            double weight = Double.parseDouble(data);
            temporarySemipreciousStone.setWeight(weight);
        } else if (currentXmlTag.equals(GemTags.ORNAMENTAL_TYPE.getValue())) {
            int ornamentalType = Integer.parseInt(data);
            temporarySemipreciousStone.setOrnamentalType(ornamentalType);
        }
    }
}
