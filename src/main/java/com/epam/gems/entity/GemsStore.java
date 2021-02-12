package com.epam.gems.entity;

import java.util.ArrayList;
import java.util.List;

public class GemsStore {

    private List<Gem> gemsStore = new ArrayList<>();

    public GemsStore(List<Gem> gemsStore) {
        this.gemsStore = gemsStore;
    }

    public void setGemsStore(List<Gem> gemsStore) {
        this.gemsStore = gemsStore;
    }

    public boolean addGemStone(Gem gemStone){
        return gemsStore.add(gemStone);
    }

    @Override
    public String toString() {
        return "GemsStore{" +
                "gemsStore=" + gemsStore +
                '}';
    }
}
