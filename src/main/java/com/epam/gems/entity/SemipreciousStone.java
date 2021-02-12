package com.epam.gems.entity;

public class SemipreciousStone extends Gem {

    private double weight;
    private int ornamentalType;

    public SemipreciousStone() { }

    public SemipreciousStone(double weight, int ornamentalType) {
        this.weight = weight;
        this.ornamentalType = ornamentalType;
    }

    public SemipreciousStone(long certificateNumber, String name, String extractionPlace, VisualParameters visualParameters, double weight, int ornamentalType) {
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
    public String toString() {
        return "SemipreciousStone{" +
                "weight=" + weight +
                ", ornamentalType=" + ornamentalType +
                '}';
    }
}
