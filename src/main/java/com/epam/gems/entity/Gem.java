package com.epam.gems.entity;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Objects;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Gem", propOrder = {
        "certificateNumber",
        "name",
        "extractionPlace",
        "visualParameters"
})
public abstract class Gem {
    @XmlAttribute(required = true)
    @XmlID
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    private String certificateNumber;
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String extractionPlace;
    @XmlElement(required = true)
    private VisualParameters visualParameters = new VisualParameters();

    public Gem() {
    }

    public Gem(String certificateNumber, String name, String extractionPlace, VisualParameters visualParameters) {
        this.certificateNumber = certificateNumber;
        this.name = name;
        this.extractionPlace = extractionPlace;
        this.visualParameters = visualParameters;
    }

    public String  getCertificateNumber() {
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
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
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


    @Override
    public String toString() {
        return "Gem{" +
                "certificateNumber='" + certificateNumber + '\'' +
                ", name='" + name + '\'' +
                ", extractionPlace='" + extractionPlace + '\'' +
                ", visualParameters=" + visualParameters +
                '}';
    }
}
