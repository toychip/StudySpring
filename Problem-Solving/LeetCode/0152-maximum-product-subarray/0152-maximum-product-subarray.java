class Solution {
    public int maxProduct(int[] nums) {
        int maxProduct = nums[0];
        int minProduct = nums[0];
        int answer = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            int tempMax = Math.max(current, Math.max(current * maxProduct, current * minProduct));
            minProduct = Math.min(current, Math.min(current * maxProduct, current * minProduct));

            maxProduct = tempMax;
            answer = Math.max(maxProduct, answer);
        }

        return answer;
    }
}