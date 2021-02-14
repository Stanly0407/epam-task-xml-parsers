package com.epam.gems.parser;

import com.epam.gems.entity.Gem;

import java.util.List;

public interface Parser {

    List<? extends Gem> parse(String file);

}
