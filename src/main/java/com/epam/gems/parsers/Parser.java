package com.epam.gems.parsers;

import com.epam.gems.entities.Gem;

import java.util.List;

public interface Parser {

    List<? extends Gem> parse(String filename) throws ParsingGemsException;

}
