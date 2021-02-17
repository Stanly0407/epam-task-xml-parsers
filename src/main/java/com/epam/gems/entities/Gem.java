package com.epam.gems.entities;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Objects;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Gem",
        propOrder = {"certificateNumber", "name", "extractionPlace", "visualParameters"})
@XmlSeeAlso({PreciousStone.class, SemipreciousStone.class})
public class Gem {

    @XmlAttribute(required = true, name = "certificate-number")
    @XmlID
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    private String certificateNumber;

    @XmlElement(required = true, name = "name")
    private String name;

    @XmlElement(required = true, name = "extraction-place")
    private String extractionPlace;

    @XmlElement(required = true, name = "visual-parameters")
    private VisualParameters visualParameters = new VisualParameters();

    public Gem() {
    }

    public Gem(String certificateNumber, String name, String extractionPlace, VisualParameters visualParameters) {
        this.certificateNumber = certificateNumber;
        this.name = name;
        this.extractionPlace = extractionPlace;
        this.visualParameters = visualParameters;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtractionPlace() {
        return extractionPlace;
    }

    public void setExtractionPlace(String extractionPlace) {
        this.extractionPlace = extractionPlace;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Gem gem = (Gem) o;
        return Objects.equals(certificateNumber, gem.certificateNumber) &&
                Objects.equals(name, gem.name) &&
                Objects.equals(extractionPlace, gem.extractionPlace) &&
                Objects.equals(visualParameters, gem.visualParameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(certificateNumber, name, extractionPlace, visualParameters);
    }

}
