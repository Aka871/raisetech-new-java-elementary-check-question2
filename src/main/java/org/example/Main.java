package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static List<Student> students = new ArrayList<>();
  public static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    while (true) {
      displayMenu();
      int choice = getValidChoice();

      switch (choice) {
        case 1:
          addStudent();
          break; //このケースの処理を終了し、switch文を抜けよ

        case 2:
          removeStudent();
          break;

        case 3:
          updateScore();
          break;

        case 4:
          calculateAverage();
          break;

        case 5:
          displayAllStudents();
          break;

        case 6:
          System.out.print("\nプログラムを終了します。");
          scanner.close();
          return;

        default:
          System.out.print("エラー： 1から6の数字を入力してください。");
      }
    }
  }

  private static void displayMenu() {
    System.out.print(
        "\n1: 学生の追加\n2: 学生の削除\n3: 点数の更新\n4: 平均点の計算\n5: 全学生の情報を表示\n6: 終了\n選択してください: ");
  }

  private static int getValidChoice() {
    while (true) {
      try {
        //空白(スペース)を含む文字列1行を読み込み、Integer.parseInt()で、文字列をint型の値として返す
        int choice = Integer.parseInt(scanner.nextLine());
        if (1 <= choice && choice <= 6) {
          return choice;
        } else {
          System.out.println("エラー： 1から6の数字を入力してください。");
        }
      } catch (NumberFormatException e) {
        System.out.println("エラー： 数字を入力してください。");
      }
    }
  }

  private static void addStudent() {
    System.out.print("追加する学生の名前を入力してください: ");

    //文字列全体からあらゆる空白文字を削除
    String addName = scanner.nextLine().replaceAll("[\\s　]+", "");

    if (addName.isEmpty()) {
      System.out.println("エラー： 名前を入力してください。");
      return;
    }

    if (students.stream().anyMatch(student -> student.getName().equals(addName))) {
      System.out.println("エラー： 同じ名前の学生がすでに存在します。");
      return;
    }

    System.out.print(addName + "の点数を入力してください: ");
    int addScore = getValidScore();
    //addメソッドでstudentsリストに、生成したStudentクラスのインスタンスを追加。コンストラクタに引数を渡し、初期化。
    students.add(new Student(addName, addScore));
    System.out.println(addName + ": " + addScore + "点 を追加しました。");
  }

  private static int getValidScore() {
    while (true) {
      try {
        int score = Integer.parseInt(scanner.nextLine());
        if (0 <= score && score <= 100) {
          return score;
        } else {
          System.out.println("エラー： 点数は0から100の間で入力してください。");
        }
      } catch (NumberFormatException e) {
        System.out.println("エラー： 点数は整数で入力してください。");
      }
    }
  }

  private static void removeStudent() {
    System.out.print("削除する学生の名前を入力してください: ");
    String removeName = scanner.nextLine().replaceAll("[\\s　]+", "");

    if (removeName.isEmpty()) {
      System.out.println("エラー： 名前を入力してください。");
      return;
    }

    boolean isRemoved = students.removeIf(student -> student.getName().equals(removeName));
    if (isRemoved) {
      System.out.println(removeName + "を削除しました。");
    } else {
      System.out.println(removeName + "は見つかりませんでした。");
    }
  }

  private static void updateScore() {
    System.out.print("点数を更新する学生の名前を入力してください: ");
    String updateName = scanner.nextLine().replaceAll("[\\s　]+", "");

    if (updateName.isEmpty()) {
      System.out.println("エラー： 名前を入力してください。");
      return;
    }

    for (Student student : students) {
      if (student.getName().equals(updateName)) {
        System.out.print(updateName + "の新しい点数を入力してください: ");
        int newScore = getValidScore();
        student.setScore(newScore);
        System.out.println(updateName + "の点数を" + newScore + "点に更新しました。");
        return;
      }
    }
    System.out.println(updateName + "は見つかりませんでした。");
  }

  private static void calculateAverage() {
    if (students.isEmpty()) {
      System.out.println("学生は登録されていません。平均点: 0.0点");
      return;
    }

    int sum = students.stream().mapToInt(Student::getScore).sum();
    double average = (double) sum / students.size();
    System.out.printf("平均点: %.1f点%n", average);
  }

  private static void displayAllStudents() {
    if (students.isEmpty()) {
      System.out.println("学生は登録されていません。");
      return;
    }

    System.out.println("学生一覧: ");
    students.stream().map(student -> student.getName() + ": " + student.getScore() + "点")
        .forEach(System.out::println);
  }
}
