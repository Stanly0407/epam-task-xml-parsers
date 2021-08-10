package com.epam.gems.parsers;

import com.epam.gems.entities.*;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class JaxbObjectFactory {
    private static final QName GEM_QNAME = new QName("http://gems.com/gems", "gem");
    private static final QName PRECIOUS_STONE_QNAME = new QName("http://gems.com/gems", "precious-stone");
    private static final QName SEMIPRECIOUS_STONE_QNAME = new QName("http://gems.com/gems", "semiprecious-stone");
    private static final QName VISUAL_PARAMETERS_QNAME = new QName("http://gems.com/gems", "visual-parameters");

    public JaxbObjectFactory() {
    }

    public GemsStore createGemsStore() {
        return new GemsStore();
    }

    public Gem createGem() {
        return new Gem();
    }

    public PreciousStone createPreciousStone() {
        return new PreciousStone();
    }

    public SemipreciousStone createSemipreciousStone() {
        return new SemipreciousStone();
    }

    public VisualParameters createVisualParameters() {
        return new VisualParameters();
    }

    @XmlElementDecl(namespace = "http://gems.com/gems", name = "gem")
    public JAXBElement<Gem> createGem(Gem value) {
        return new JAXBElement<>(GEM_QNAME, Gem.class, null, value);
    }

    @XmlElementDecl(namespace = "http://gems.com/gems", name = "precious-stone", substitutionHeadNamespace = "http://gems.com/gems", substitutionHeadName = "gem")
    public JAXBElement<PreciousStone> createPreciousStone(PreciousStone value) {
        return new JAXBElement<>(PRECIOUS_STONE_QNAME, PreciousStone.class, null, value);
    }

    @XmlElementDecl(namespace = "http://gems.com/gems", name = "semiprecious-stone", substitutionHeadNamespace = "http://gems.com/gems", substitutionHeadName = "gem")
    public JAXBElement<SemipreciousStone> createSemipreciousStone(SemipreciousStone value) {
        return new JAXBElement<>(SEMIPRECIOUS_STONE_QNAME, SemipreciousStone.class, null, value);
    }

    @XmlElementDecl(namespace = "http://gems.com/gems", name = "visual-parameters", substitutionHeadNamespace = "http://gems.com/gems", substitutionHeadName = "gem")
    public JAXBElement<VisualParameters> createSemipreciousStone(VisualParameters value) {
        return new JAXBElement<>(VISUAL_PARAMETERS_QNAME, VisualParameters.class, null, value);
    }
}
