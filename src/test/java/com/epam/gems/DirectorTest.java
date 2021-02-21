package com.epam.gems;

import com.epam.gems.entities.*;
import com.epam.gems.parsers.ParserFactory;
import com.epam.gems.parsers.SaxParser;
import com.epam.gems.parsers.XmlValidator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class DirectorTest {

    private static final String TEST_FILENAME = "src/test/resources/gemsTest.xml";
    private SaxParser saxParser = new SaxParser();
    // given
    private VisualParameters parametersFirst = new VisualParameters("red-pink", TransparentType.TRANSPARENT, 36);
    private VisualParameters parametersSecond = new VisualParameters("dark blue", TransparentType.SEMI_TRANSPARENT, 24);
    private VisualParameters parametersThird = new VisualParameters("violet", TransparentType.TRANSPARENT, 12);
    private VisualParameters parametersFourth = new VisualParameters("red", TransparentType.NOT_DETERMINED, 54);
    private List<Gem> expectedGemsList = new ArrayList<>(Arrays.asList(
            new PreciousStone("PS3O3377", "Rubine", "Southern Urals", parametersFirst, OriginType.MINERAL, 10.0),
            new PreciousStone("PS023455", "Sapphire", "Madagascar", parametersSecond, OriginType.MINERAL, 8.0),
            new SemipreciousStone("SP539698", "Amethyst", "Kola Peninsula", parametersThird, 5.0, 2),
            new SemipreciousStone("SP525858", "Garnet", "Kola Peninsula", parametersFourth, 10.0, 1)));

    @Test
    public void shouldCreateGemsListFromXmlFileUsingSaxParser() {
        // when
        XmlValidator xmlValidator = Mockito.mock(XmlValidator.class);
        when(xmlValidator.isValid(anyString())).thenReturn(true);

        ParserFactory parser = Mockito.mock(ParserFactory.class);
        when(parser.getParser()).thenReturn(saxParser);

        Director director = new Director(xmlValidator, parser);
        List<? extends Gem> actualGemsList = director.createGemsListFromXmlFile(TEST_FILENAME);

        // then
        Assert.assertEquals(expectedGemsList, actualGemsList);
    }

}
