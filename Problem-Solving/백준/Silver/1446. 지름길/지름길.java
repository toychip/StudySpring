import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int D = Integer.parseInt(input[1]);

        List<Way> shortCourse = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] input2 = reader.readLine().split(" ");
            int startLocation = Integer.parseInt(input2[0]);
            int endLocation = Integer.parseInt(input2[1]);
            int weight = Integer.parseInt(input2[2]);
            // 지름길이 고속도로 길이를 넘지 않을 때만 추가
            if (endLocation <= D) {
                shortCourse.add(new Way(startLocation, endLocation, weight));
            }
        }

        int[] dist = new int[D + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        for (int i = 0; i < D; i++) {
            dist[i + 1] = Math.min(dist[i] + 1, dist[i + 1]);
            for (Way way : shortCourse) {
                // 고속도로가 존재한다면
                if (way.getStartLocation() == i) {
                    // 지름길 사용 시 거리가 더 짧으면 갱신
                    if (dist[i] + way.getWeight() < dist[way.getEndLocation()]) {
                        // 고속도로 탄 후 도착지에 현재 가중치와 고속도로 가중치를 추가
                        dist[way.getEndLocation()] = dist[i] + way.getWeight();
                    }
                }
            }
        }
        writer.write(String.valueOf(dist[D]));
        writer.flush();
        writer.close();
    }
}

class Way implements Comparable<Way> {
    private final int startLocation;
    private final int endLocation;
    private final int weight;

    public Way(final int startLocation, final int endLocation, final int weight) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.weight = weight;
    }

    public int getStartLocation() {
        return startLocation;
    }

    public int getEndLocation() {
        return endLocation;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(final Way o) {
        return Integer.compare(o.weight, this.weight);
    }
}