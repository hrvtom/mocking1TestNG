package org.song.example;

import java.util.Random;

public class SingletonScoreGrader {
	private static SingletonScoreGrader instance = null;

	private SingletonScoreGrader() {
	}

	public static synchronized SingletonScoreGrader instance() {
		if (instance == null) {
			instance = new SingletonScoreGrader();
		}

		return instance;
	}

	public int getScore() {
		Random random = new Random();
		int score = 60 + (int) Math.round(40.0 * random.nextDouble());

		return score;

	}
}
