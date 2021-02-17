package com.epam.gems.entities;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SemipreciousStone",
        propOrder = {"weight", "ornamentalType"})
public class SemipreciousStone extends Gem {

    @XmlElement(required = true, name = "weight")
    private double weight;

    @XmlAttribute(name = "ornamental-type")
    private int ornamentalType;

    private static final String ID_QUALIFIER_SEMIPRECIOUS = "SPS";

    public SemipreciousStone() {
    }

    public SemipreciousStone(double weight, int ornamentalType) {
        this.weight = weight;
        this.ornamentalType = ornamentalType;
    }

    public SemipreciousStone(String certificateNumber, String name, String extractionPlace, VisualParameters visualParameters, double weight, int ornamentalType) {
        super(certificateNumber, name, extractionPlace, visualParameters);
        this.weight = weight;
        this.ornamentalType = ornamentalType;
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
    public void setCertificateNumber(String certificateNumber) {
        super.setCertificateNumber(ID_QUALIFIER_SEMIPRECIOUS + certificateNumber);
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
