package com.epam.gems.entities;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = "http://gems.com/gems", name = "gems")
public class GemsStore {

    @XmlElementRef(name = "gem", namespace = "http://gems.com/gems", type = JAXBElement.class)
    private List<JAXBElement<? extends Gem>> gemsStore = new ArrayList<>();

    public GemsStore() {
        super();
    }

    public List<JAXBElement<? extends Gem>> getGemsStore() {
        if (gemsStore == null) {
            gemsStore = new ArrayList<>();
        }
        return gemsStore;
    }
}
