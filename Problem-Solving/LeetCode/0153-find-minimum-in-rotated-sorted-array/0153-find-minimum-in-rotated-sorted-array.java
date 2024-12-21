class Solution {
    public int findMin(int[] nums) {
        int startIndex = 0;
        int endIndex = nums.length - 1;

        while (startIndex < endIndex) {
            int midIndex = (startIndex + endIndex) / 2;

            if (nums[midIndex] < nums[endIndex]) {
                // 최소값이 midIndex 또는 그 왼쪽에 있음
                endIndex = midIndex;
            } else {
                // 최소값이 midIndex + 1 또는 오른쪽에 있음
                startIndex = midIndex + 1;
            }

        }
        return nums[startIndex];
    }
}