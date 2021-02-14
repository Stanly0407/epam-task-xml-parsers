package com.epam.gems.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement
public class GemsStore {

    @XmlElement(name = "gem")
    private List<Gem> gemsStore = new ArrayList<>();

    public GemsStore(List<Gem> gemsStore) {
        this.gemsStore = gemsStore;
    }

    public GemsStore() {
        super();
    }

    public void setGemsStore(List<Gem> gemsStore) {
        this.gemsStore = gemsStore;
    }

    public boolean addGemStone(Gem gemStone) {
        return gemsStore.add(gemStone);
    }


    @Override
    public String toString() {
        return "GemsStore{" +
                "gemsStore=" + gemsStore +
                '}';
    }
}
