package com.epam.gems.entity;


public abstract class Gem {
    private long certificateNumber;
    private String name;
    private String extractionPlace;
    private VisualParameters visualParameters = new VisualParameters();

    public Gem() {
    }

    public Gem(long certificateNumber, String name, String extractionPlace, VisualParameters visualParameters) {
        this.certificateNumber = certificateNumber;
        this.name = name;
        this.extractionPlace = extractionPlace;
        this.visualParameters = visualParameters;
    }

    public long getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(long certificateNumber) {
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
    public String toString() {
        return "Gem{" +
                "certificateNumber=" + certificateNumber +
                ", name='" + name + '\'' +
                ", extractionPlace='" + extractionPlace + '\'' +
                ", visualParameters=" + visualParameters +
                '}';
    }


    public static class VisualParameters {
        private String color;
        private TransparentType transparentType;
        private int stonePlanes;

        public VisualParameters() {
        }

        public VisualParameters(String color, TransparentType transparentType, int stonePlanes) {
            this.color = color;
            this.transparentType = transparentType;
            this.stonePlanes = stonePlanes;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public TransparentType getTransparentType() {
            return transparentType;
        }

        public void setTransparentType(TransparentType transparentType) {
            this.transparentType = transparentType;
        }

        public int getStonePlanes() {
            return stonePlanes;
        }

        public void setStonePlanes(int stonePlanes) {
            this.stonePlanes = stonePlanes;
        }

        @Override
        public String toString() {
            return "VisualParameters{" +
                    "color='" + color + '\'' +
                    ", transparentType=" + transparentType +
                    ", stonePlanes=" + stonePlanes +
                    '}';
        }
    }
}
