package com.example.numplay;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class BaseballGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // 메인 메뉴 출력
            System.out.println("1. 게임 시작하기");
            System.out.println("2. 게임 기록 보기");
            System.out.println("3. 종료하기");
            System.out.print("선택하세요: ");
            int menuOption = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 처리

            // 메뉴 선택에 따른 동작
            if (menuOption == 1) {
                // 게임 시작하기 선택 시
                Set<Integer> numberSet = new HashSet<>();

                // 중복 없는 3자리 숫자 생성
                while (numberSet.size() < 3) {
                    int num = (int)(Math.random() * 9) + 1;
                    numberSet.add(num);
                }

                // Set을 배열로 변환
                Integer[] randomNumbers = numberSet.toArray(new Integer[0]);

                // 테스트용으로 생성된 숫자 출력 (실제 게임에서는 주석 처리 가능)
                System.out.println("생성된 숫자: ");
                for (int num : randomNumbers) {
                    System.out.print(num);
                }
                System.out.println();

                int strikes = 0; // 초기 스트라이크 값

                // 3 스트라이크가 될 때까지 게임 반복
                while (strikes < 3) {
                    String userInput = "";

                    // 사용자 입력받기 (유효한 3자리 숫자 입력될 때까지 반복)
                    while (true) {
                        try {
                            System.out.print("3자리 숫자를 입력하세요 (예: 123): ");
                            userInput = scanner.nextLine();

                            // 입력이 숫자인지 확인하고 길이 검사
                            if (userInput.length() != 3) {
                                throw new InputMismatchException("3자리 숫자를 입력하세요.");
                            }

                            // 숫자인지 확인
                            Integer.parseInt(userInput);  // 숫자가 아니면 NumberFormatException 발생

                            // 중복 숫자 확인
                            Set<Character> userNumberSet = new HashSet<>();
                            for (int i = 0; i < 3; i++) {
                                if (!userNumberSet.add(userInput.charAt(i))) {
                                    throw new InputMismatchException("중복된 숫자는 입력할 수 없습니다.");
                                }
                            }

                            break; // 유효한 입력이면 반복 탈출
                        } catch (NumberFormatException e) {
                            System.out.println("숫자만 입력 가능합니다.");
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage()); // 3자리 숫자 및 중복 예외 메시지 출력
                        }
                    }

                    // 입력받은 숫자를 배열로 변환
                    int[] userNumbers = new int[3];
                    for (int i = 0; i < 3; i++) {
                        userNumbers[i] = Character.getNumericValue(userInput.charAt(i));
                    }

                    // 스트라이크와 볼을 계산
                    strikes = 0;
                    int balls = 0;

                    for (int i = 0; i < 3; i++) {
                        if (userNumbers[i] == randomNumbers[i]) {
                            strikes++; // 숫자와 위치 모두 같으면 스트라이크
                        } else if (numberSet.contains(userNumbers[i])) {
                            balls++; // 숫자만 같으면 볼
                        }
                    }

                    // 결과 출력
                    System.out.println("결과:");
                    System.out.println(strikes + " 스트라이크");
                    System.out.println(balls + " 볼");

                    if (strikes < 3 && strikes == 0 && balls == 0) {
                        System.out.println("아웃");
                    }
                }

                // 3 스트라이크가 된 경우
                System.out.println("축하합니다! 3 스트라이크로 승리하셨습니다.");
                System.out.println("메뉴로 돌아갑니다.");
            } else if (menuOption == 2) {
                // 게임 기록 보기 선택 시
                System.out.println("\"게임 기록 보기\"는 lv3에서 제시됩니다.");
            } else if (menuOption == 3) {
                // 종료하기 선택 시
                System.out.println("게임을 종료합니다.");
                break;
            } else {
                System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
            }
        }

        scanner.close();
    }
}