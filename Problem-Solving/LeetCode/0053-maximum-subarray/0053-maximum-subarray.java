class Solution {
     public static int maxSubArray(int[] nums) {
        int asInt = Arrays.stream(nums).max().getAsInt();
        if (asInt < 0) {
            return asInt;
        }

        int[] prefix = new int[nums.length + 1];
        for (int i = 1; i < nums.length + 1; i++) {
            prefix[i] = Math.max(nums[i - 1] + prefix[i - 1], nums[i - 1]);
        }

        int answer = 0;
        for (int i : prefix) {
            answer = Math.max(i, answer);
        }
        return answer;
    }

}