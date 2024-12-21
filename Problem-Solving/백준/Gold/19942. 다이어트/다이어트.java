import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int answerPrice = Integer.MAX_VALUE;
    static List<Food> foods;
    static Satisfy satisfy;
    static List<Integer> answerUseFoodIndex;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(reader.readLine());
        foods = new ArrayList<>();
        answerUseFoodIndex = new ArrayList<>();

        String[] input1 = reader.readLine().split(" ");
        satisfy = new Satisfy(Integer.parseInt(input1[0]), Integer.parseInt(input1[1]), Integer.parseInt(input1[2]),
                Integer.parseInt(input1[3]));
        for (int i = 0; i < N; i++) {
            String[] input2 = reader.readLine().split(" ");
            Food food = new Food(Integer.parseInt(input2[0]), Integer.parseInt(input2[1]), Integer.parseInt(input2[2]),
                    Integer.parseInt(input2[3]), Integer.parseInt(input2[4]));
            foods.add(food);
        }

        recur(0, 0, 0, 0, 0, 0, new ArrayList<>());

        if (answerPrice == Integer.MAX_VALUE) {
            writer.write(String.valueOf(-1));
        } else {
            Collections.sort(answerUseFoodIndex);
            writer.write(String.valueOf(answerPrice));
            writer.write("\n");
            for (Integer foodIndex : answerUseFoodIndex) {
                writer.write(foodIndex + " ");
            }
        }
        writer.flush();
        writer.close();
    }

    private static void recur(int index, int protein, int fat, int carbohydrate, int vitamin, int price,
                              List<Integer> useFoodIndex) {
        if (price >= answerPrice) {
            return;
        }

        boolean satisfaction =
                protein >= satisfy.getProtein() && fat >= satisfy.getFat()
                        && carbohydrate >= satisfy.getCarbohydrate()
                        && vitamin >= satisfy.getVitamin();
        if (satisfaction) {
            if (answerPrice > price) {
                answerPrice = price;
                answerUseFoodIndex = new ArrayList<>(useFoodIndex);
            }
            return;
        }

        // 모든 재료를 다 탐색한 경우, 종료
        if (index == N) {
            return;
        }

        Food food = foods.get(index);

        useFoodIndex.add(index + 1);
        recur(index + 1, protein + food.getProtein(), fat + food.getFat(), carbohydrate + food.getCarbohydrate(),
                vitamin + food.getVitamin(), price + food.getPrice(), useFoodIndex);
        useFoodIndex.remove(useFoodIndex.size() - 1);
        recur(index + 1, protein, fat, carbohydrate, vitamin, price, useFoodIndex);
    }


}

class Food {
    private final int protein;
    private final int fat;
    private final int carbohydrate;
    private final int vitamin;
    private final int price;

    public Food(final int protein, final int fat, final int carbohydrate, final int vitamin, final int price) {
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.vitamin = vitamin;
        this.price = price;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }

    public int getVitamin() {
        return vitamin;
    }

    public int getPrice() {
        return price;
    }
}

class Satisfy {
    private final int protein;
    private final int fat;
    private final int carbohydrate;
    private final int vitamin;

    public Satisfy(final int protein, final int fat, final int carbohydrate, final int vitamin) {
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.vitamin = vitamin;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }

    public int getVitamin() {
        return vitamin;
    }
}