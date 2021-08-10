package com.epam.gems.parsers;

import com.epam.gems.entities.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractParserTest {

    private static final String TEST_FILENAME = "src/test/resources/gemsTest.xml";

    private Parser parser;
    private List<? extends Gem> actualGemsList = new ArrayList<>();

    private VisualParameters parametersFirst = new VisualParameters("red-pink", TransparentType.TRANSPARENT, 36);
    private VisualParameters parametersSecond = new VisualParameters("dark blue", TransparentType.SEMI_TRANSPARENT, 24);
    private VisualParameters parametersThird = new VisualParameters("violet", TransparentType.TRANSPARENT, 12);
    private VisualParameters parametersFourth = new VisualParameters("red", TransparentType.NOT_DETERMINED, 54);

    //given
    private List<Gem> expectedGemsList = new ArrayList<>(Arrays.asList(
            new PreciousStone("PS3O3377", "Rubine", "Southern Urals", parametersFirst, OriginType.MINERAL, 10.0),
            new PreciousStone("PS023455", "Sapphire", "Madagascar", parametersSecond, OriginType.MINERAL, 8.0),
            new SemipreciousStone("SP539698", "Amethyst", "Kola Peninsula", parametersThird, 5.0, 2),
            new SemipreciousStone("SP525858", "Garnet", "Kola Peninsula", parametersFourth, 10.0, 1)));

    public abstract Parser getParser();

    @Before
    public void init() {
        parser = getParser();
    }

    @Test
    public void parseTest() throws ParsingGemsException {
        //when
        actualGemsList = parser.parse(TEST_FILENAME);
        //then
        Assert.assertEquals(expectedGemsList, actualGemsList);
    }

}
