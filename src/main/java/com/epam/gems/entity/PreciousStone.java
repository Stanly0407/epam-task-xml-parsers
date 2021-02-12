package com.epam.gems.entity;


public class PreciousStone extends Gem {

    private OriginType originType;
    private double carats;

    public PreciousStone() {
    }

    public PreciousStone(OriginType originType, double carats) {
        this.originType = originType;
        this.carats = carats;
    }

    public PreciousStone(long certificateNumber, String name, String extractionPlace, VisualParameters visualParameters, OriginType originType, double carats) {
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
    public String toString() {
        return "PreciousStone{" +
                "originType=" + originType +
                ", carats=" + carats +
                '}';
    }
}
