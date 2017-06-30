package org.song.example;

public class Student {
	public int getMathScore() {
		ScoreGrader grader = new ScoreGrader();

		return grader.getScore();
	}
}
