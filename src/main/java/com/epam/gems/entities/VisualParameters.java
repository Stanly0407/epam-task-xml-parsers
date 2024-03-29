package com.epam.gems.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class VisualParameters {

    @XmlElement(name = "color")
    private String color;

    @XmlElement(name = "transparent-type")
    private TransparentType transparentType;

    @XmlElement(name = "stone-planes")
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VisualParameters that = (VisualParameters) o;
        return stonePlanes == that.stonePlanes &&
                Objects.equals(color, that.color) &&
                transparentType == that.transparentType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, transparentType, stonePlanes);
    }

}
