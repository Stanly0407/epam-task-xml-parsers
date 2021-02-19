package com.epam.gems.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class PreciousStone extends Gem {

    @XmlElement(required = true, name = "origin-type")
    private OriginType originType;

    @XmlElement(required = true, name = "carats")
    private double carats;

    public PreciousStone(String certificateNumber, String name, String extractionPlace, VisualParameters visualParameters, OriginType originType, double carats) {
        super(certificateNumber, name, extractionPlace, visualParameters);
        this.originType = originType;
        this.carats = carats;
    }

    //for jaxb parser
    public PreciousStone() {
    }

    //for sax parser
    public PreciousStone(Gem temporaryStone, PreciousStone temporaryPreciousStone) {
        super(temporaryStone.getCertificateNumber(), temporaryStone.getName(), temporaryStone.getExtractionPlace(), temporaryStone.getVisualParameters());
        this.originType = temporaryPreciousStone.getOriginType();
        this.carats = temporaryPreciousStone.getCarats();
    }

    //for dom parser
    public PreciousStone(Gem temporaryStone) {
        super(temporaryStone.getCertificateNumber(), temporaryStone.getName(), temporaryStone.getExtractionPlace(), temporaryStone.getVisualParameters());
    }

    public OriginType getOriginType() {
        return originType;
    }

    public void setOriginType(OriginType originType) {
        this.originType = originType;
    }

    public double getCarats() {
        return carats;
    }

    public void setCarats(double carats) {
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

}
