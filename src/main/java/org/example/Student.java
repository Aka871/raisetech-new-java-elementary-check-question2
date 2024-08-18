package org.example;

public class Student {

  private String name;
  private int score;

  public Student(String name, int score) {
    this.name = name;
    this.score = score;
  }

  public String getName() {
    return name;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getScore() {
    return score;
  }
}
