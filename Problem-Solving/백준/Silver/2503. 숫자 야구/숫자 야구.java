import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        List<Game> games = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int number = scanner.nextInt();
            int strike = scanner.nextInt();
            int ball = scanner.nextInt();
            games.add(new Game(number, strike, ball));
        }

        int answer = 0;
        // 100의 자리수
        for (int i = 1; i < 10; i++) {
            // 10의 자리수
            for (int j = 1; j < 10; j++) {
                // 1의 자리수
                for (int k = 1; k < 10; k++) {
                    if (i == j || j == k || k == i) {
                        continue;
                    }
                    boolean isValid = true;
                    for (Game game : games) {
                        int number = game.getNumber();
                        int strike = game.getStrike();
                        int ball = game.getBall();

                        int ballCount = 0;
                        int strikeCount = 0;

                        String numberStr = String.valueOf(number);
                        Integer hundred = Integer.valueOf(numberStr.substring(0, 1));
                        Integer ten = Integer.valueOf(numberStr.substring(1, 2));
                        Integer one = Integer.valueOf(numberStr.substring(2, 3));

                        if (hundred == i) {
                            strikeCount++;
                        }

                        if (ten == j) {
                            strikeCount++;
                        }

                        if (one == k) {
                            strikeCount++;
                        }

                        if (hundred == j || hundred == k) {
                            ballCount++;
                        }

                        if (ten == i || ten == k) {
                            ballCount++;
                        }

                        if (one == i || one == j) {
                            ballCount++;
                        }

                        if (ballCount != ball || strikeCount != strike) {
                            isValid = false;
                        }
                    }
                    if (isValid) {
                        answer++;
                    }
                }
            }
        }
        System.out.println(answer);
    }

    static class Game{
        int number;
        int strike;
        int ball;

        public Game(final int number, final int strike, final int ball) {
            this.number = number;
            this.strike = strike;
            this.ball = ball;
        }

        public int getNumber() {
            return number;
        }

        public int getStrike() {
            return strike;
        }

        public int getBall() {
            return ball;
        }
    }
}
