package com.epam.gems.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class SemipreciousStone extends Gem {

    @XmlElement(required = true, name = "weight")
    private double weight;

    @XmlElement(name = "ornamental-type")
    private int ornamentalType;

    public SemipreciousStone(String certificateNumber, String name, String extractionPlace,
                             VisualParameters visualParameters, double weight, int ornamentalType) {
        super(certificateNumber, name, extractionPlace, visualParameters);
        this.weight = weight;
        this.ornamentalType = ornamentalType;
    }

    //for jaxb parser
    public SemipreciousStone() {
    }

    //for sax parser
    public SemipreciousStone(Gem temporaryStone, SemipreciousStone temporarySemipreciousStone) {
        super(temporaryStone.getCertificateNumber(), temporaryStone.getName(), temporaryStone.getExtractionPlace(),
                temporaryStone.getVisualParameters());
        this.weight = temporarySemipreciousStone.getWeight();
        this.ornamentalType = temporarySemipreciousStone.ornamentalType;
    }

    //for dom parser
    public SemipreciousStone(Gem temporaryStone) {
        super(temporaryStone.getCertificateNumber(), temporaryStone.getName(), temporaryStone.getExtractionPlace(),
                temporaryStone.getVisualParameters());
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getOrnamentalType() {
        return ornamentalType;
    }

    public void setOrnamentalType(int ornamentalType) {
        this.ornamentalType = ornamentalType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        SemipreciousStone that = (SemipreciousStone) o;
        return Double.compare(that.weight, weight) == 0 &&
                ornamentalType == that.ornamentalType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), weight, ornamentalType);
    }

}
