class Solution {
    static int answer = 0;

    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.print(solution(numbers, target));
    }

    public static int solution(int[] numbers, int target) {
        dfs(0, 0, numbers, target);
        return answer;
    }

    private static void dfs(int index, int sum, int[] numbers, int target) {
        if (index == numbers.length) {
            if (target == sum) {
                answer++;
            }    
            return;
        }

        int currentNumber = numbers[index];
        dfs(index + 1, sum + currentNumber, numbers, target);
        dfs(index + 1, sum - currentNumber, numbers, target);
    }
}