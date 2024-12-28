import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
        recur(n, 0);
        System.out.println("라고 답변하였지.");
    }

    private static void recur(int n, final int dept) {
        if (n == 0) {
            StringBuilder builder = new StringBuilder();

            StringBuilder builder1 = addPrefix(dept, builder);
            builder1.append("\"재귀함수가 뭔가요?\"\n");

            StringBuilder builder2 = addPrefix(dept, builder1);
            builder2.append("\"재귀함수는 자기 자신을 호출하는 함수라네\"");

            String currentString = builder2.toString();
            System.out.println(currentString);
            return;
        }

        StringBuilder builder = new StringBuilder();

        StringBuilder builder1 = addPrefix(dept, builder);
        builder1.append("\"재귀함수가 뭔가요?\"\n");

        StringBuilder builder2 = addPrefix(dept, builder1);
        builder2.append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");

        StringBuilder builder3 = addPrefix(dept, builder2);
        builder3.append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");

        StringBuilder builder4 = addPrefix(dept, builder3);
        builder4.append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
        System.out.println(builder4.toString());
        recur(n - 1, dept + 1);
        StringBuilder suffixBuilder = new StringBuilder();
        StringBuilder suffixBuilder1 = addSuffix(dept, suffixBuilder);
        suffixBuilder1.append("라고 답변하였지.");
        String suffix = suffixBuilder1.toString();
        System.out.println(suffix);
    }

    private static StringBuilder addPrefix(final int dept, final StringBuilder builder) {
        for (int i = 0; i < dept; i++) {
            builder.append("____");
        }
        return builder;
    }

    private static StringBuilder addSuffix(final int dept, final StringBuilder builder) {
        for (int i = 0; i <= dept; i++) {
            builder.append("____");
        }
        return builder;
    }
}
