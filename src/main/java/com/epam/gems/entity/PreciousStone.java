package com.epam.gems.entity;


import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PreciousStone", propOrder = {
        "originType",
        "carats"
})
public class PreciousStone extends Gem {
    @XmlElement(required = true)
    private OriginType originType;
    @XmlElement(required = true)
    private double carats;

    public PreciousStone() {
    }

    public PreciousStone(OriginType originType, double carats) {
        this.originType = originType;
        this.carats = carats;
    }

    public PreciousStone(String certificateNumber, String name, String extractionPlace, VisualParameters visualParameters, OriginType originType, double carats) {
        super(certificateNumber, name, extractionPlace, visualParameters);
        this.originType = originType;
        this.carats = carats;
    }

    public OriginType getOriginType() {
        return originType;
    }

    public void setOriginType(OriginType originType) {
        this.originType = originType;
    }

    public double getCarat() {
        return carats;
    }

    public void setCarat(double carats) {
        this.carats = carats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PreciousStone that = (PreciousStone) o;
        return Double.compare(that.carats, carats) == 0 &&
                originType == that.originType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(originType, carats);
    }

    @Override
    public String toString() {
        return "PreciousStone{" +
                "originType=" + originType +
                ", carats=" + carats +
                '}';
    }
}
