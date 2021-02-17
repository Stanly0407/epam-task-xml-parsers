package com.epam.gems.parsers;

import com.epam.gems.entities.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;


public class SaxContentHandler extends DefaultHandler {
    private List<Gem> gems;
    private PreciousStone currentPreciousStone;
    private SemipreciousStone currentSemipreciousStone;
    private GemXmlTags currentXmlTag;
    private boolean isCurrentPreciousStone;
    private EnumSet<GemXmlTags> gemTags;
    private static final String ELEMENT_PRECIOUS_STONE = "precious-stone";
    private static final String ELEMENT_SEMIPRECIOUS_STONE = "semiprecious-stone";
    private static final String TAG_ELEMENT = "-";
    private static final String ENUM_ELEMENT = "_";

    public SaxContentHandler() {
        gems = new ArrayList<>();
        gemTags = EnumSet.range(GemXmlTags.NAME, GemXmlTags.ORNAMENTAL_TYPE);
    }

    public List<Gem> getGems() {
        return gems;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (ELEMENT_PRECIOUS_STONE.equals(qName)) {
            currentPreciousStone = new PreciousStone();
            currentPreciousStone.setCertificateNumber(attributes.getValue(0));
            isCurrentPreciousStone = true;
        } else if (ELEMENT_SEMIPRECIOUS_STONE.equals(qName)) {
            currentSemipreciousStone = new SemipreciousStone();
            currentSemipreciousStone.setCertificateNumber(attributes.getValue(0));
            if (attributes.getLength() == 2) {
                currentSemipreciousStone.setOrnamentalType(Integer.parseInt(attributes.getValue(1)));
            }
            isCurrentPreciousStone = false;
        } else {
            String tag = transformTagToEnumElement(qName);
            GemXmlTags temporary = GemXmlTags.valueOf(tag);
            if (gemTags.contains(temporary)) {
                currentXmlTag = temporary;
            }
        }
    }

    public String transformTagToEnumElement(String qName) {
        return qName.replace(TAG_ELEMENT, ENUM_ELEMENT).toUpperCase();
    }

    public void endElement(String uri, String localName, String qName) {
        if (ELEMENT_PRECIOUS_STONE.equals(qName)) {
            gems.add(currentPreciousStone);
        } else if (ELEMENT_SEMIPRECIOUS_STONE.equals(qName)) {
            gems.add(currentSemipreciousStone);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).trim();
        if (currentXmlTag != null) {
            if (isCurrentPreciousStone) {
                switch (currentXmlTag) {
                    case NAME:
                        currentPreciousStone.setName(data);
                        break;
                    case EXTRACTION_PLACE:
                        currentPreciousStone.setExtractionPlace(data);
                        break;
                    case VISUAL_PARAMETERS:
                        VisualParameters visualParameters = new VisualParameters();
                        currentPreciousStone.setVisualParameters(visualParameters);
                        break;
                    case COLOR:
                        currentPreciousStone.getVisualParameters().setColor(data);
                        break;
                    case TRANSPARENT_TYPE:
                        currentPreciousStone.getVisualParameters().setTransparentType(TransparentType.valueOf(data));
                        break;
                    case STONE_PLANES:
                        currentPreciousStone.getVisualParameters().setStonePlanes(Integer.parseInt(data));
                        break;
                    case ORIGIN_TYPE:
                        currentPreciousStone.setOriginType(OriginType.valueOf(data));
                        break;
                    case CARATS:
                        currentPreciousStone.setCarat(Double.parseDouble(data));
                        break;
                    default:
                        throw new EnumConstantNotPresentException(
                                currentXmlTag.getDeclaringClass(), currentXmlTag.name());
                }
            } else {
                switch (currentXmlTag) {
                    case NAME:
                        currentSemipreciousStone.setName(data);
                        break;
                    case EXTRACTION_PLACE:
                        currentSemipreciousStone.setExtractionPlace(data);
                        break;
                    case VISUAL_PARAMETERS:
                        VisualParameters visualParameters = new VisualParameters();
                        currentSemipreciousStone.setVisualParameters(visualParameters);
                        break;
                    case COLOR:
                        currentSemipreciousStone.getVisualParameters().setColor(data);
                        break;
                    case TRANSPARENT_TYPE:
                        currentSemipreciousStone.getVisualParameters().setTransparentType(TransparentType.valueOf(data));
                        break;
                    case STONE_PLANES:
                        currentSemipreciousStone.getVisualParameters().setStonePlanes(Integer.parseInt(data));
                        break;
                    case WEIGHT:
                        currentSemipreciousStone.setWeight(Integer.parseInt(data));
                        break;
                    default:
                        throw new EnumConstantNotPresentException(
                                currentXmlTag.getDeclaringClass(), currentXmlTag.name());
                }
            }

        }
        currentXmlTag = null;
    }
}



