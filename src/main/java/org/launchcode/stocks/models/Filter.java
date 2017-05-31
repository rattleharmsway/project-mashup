package org.launchcode.stocks.models;

import java.awt.Color;

import org.launchcode.stocks.models.Picture;

public interface Filter {
	Picture process(Picture original, int m, Color mono);

}

