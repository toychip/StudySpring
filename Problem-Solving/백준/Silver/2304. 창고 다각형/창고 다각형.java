import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    static List<Pillar> pillars;

    public static void main(String[] args) throws IOException {
        getInput();
        Pillar highestPillar = getHighestPillar();
        int highestPillarIndexInList = pillars.indexOf(highestPillar);
        solution(highestPillarIndexInList, highestPillar);
    }

    private static void solution(final int highestPillarIndexInList, final Pillar highestPillar) {
        int answer = 0;

        // 좌측 누적합
        Pillar pastPillar = pillars.get(0);
        for (int i = 1; i <= highestPillarIndexInList; i++) {
            Pillar currentPillar = pillars.get(i);

            if (currentPillar.getHeight() >= pastPillar.getHeight()) {
                int pastPillarHeight = pastPillar.getHeight();
                int addArea = pastPillarHeight * (currentPillar.getIndex() - pastPillar.getIndex());
                answer += addArea;
                pastPillar = currentPillar;
            }
        }

        // 우측 누적합
        Pillar lastIndexPillar = pillars.get(pillars.size() - 1);
        for (int i = pillars.size() - 2; i >= highestPillarIndexInList; i--) {
            Pillar currentPillar = pillars.get(i);

            if (currentPillar.getHeight() >= lastIndexPillar.getHeight()) {
                int lastPillarHeight = lastIndexPillar.getHeight();
                int addArea = Math.abs(lastPillarHeight * (currentPillar.getIndex() - lastIndexPillar.getIndex()));
                answer += addArea;
                lastIndexPillar = currentPillar;
            }
        }

        answer += highestPillar.getHeight();
        System.out.print(answer);
    }

    private static Pillar getHighestPillar() {
        Pillar highestPillar = pillars.stream()
                .max(Comparator.comparingInt(Pillar::getHeight))
                .orElseThrow();
        return highestPillar;
    }

    private static void getInput() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        pillars = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] input = bf.readLine().split(" ");
            pillars.add(new Pillar(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }

        Collections.sort(pillars);
    }

    static class Pillar implements Comparable<Pillar> {
        private final int index;
        private final int height;

        public Pillar(final int index, final int height) {
            this.index = index;
            this.height = height;
        }

        public int getIndex() {
            return index;
        }

        public int getHeight() {
            return height;
        }

        @Override
        public int compareTo(final Pillar o) {
            return this.index - o.index;
        }

        @Override
        public String toString() {
            return "Pillar{" +
                    "index=" + index +
                    ", height=" + height +
                    '}';
        }
    }
}
