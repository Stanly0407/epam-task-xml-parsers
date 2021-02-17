package com.epam.gems.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PreciousStone",
        propOrder = {"originType", "carats"})
public class PreciousStone extends Gem {

    @XmlElement(required = true, name = "origin-type")
    private OriginType originType;

    @XmlElement(required = true, name = "carats")
    private double carats;

    private static final String ID_QUALIFIER_PRECIOUS = "PS";

    public PreciousStone() {
    }

    public PreciousStone(OriginType originType, double carats) {
        this.originType = originType;
        this.carats = carats;
    }

    public PreciousStone(String certificateNumber, String name, String extractionPlace, VisualParameters visualParameters,
                         OriginType originType, double carats) {
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
    public void setCertificateNumber(String certificateNumber) {
        super.setCertificateNumber(ID_QUALIFIER_PRECIOUS + certificateNumber);
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

}
