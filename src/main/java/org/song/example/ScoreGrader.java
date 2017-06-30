package org.song.example;

import java.util.Random;

public class ScoreGrader {

	public int getScore() {
		Random random = new Random();
		int score = 60 + (int) Math.round(40.0 * random.nextDouble());

		return score;
	}

}
