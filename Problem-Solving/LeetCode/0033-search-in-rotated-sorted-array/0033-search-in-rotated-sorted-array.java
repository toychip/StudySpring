class Solution {
public int search(int[] nums, int target) {
        int startIndex = 0;
        int endIndex = nums.length - 1;

        while (startIndex <= endIndex) {
            int mid = (startIndex + endIndex) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // 왼쪽이 정렬된 경우
            if (nums[startIndex] <= nums[mid]) {
                // targget이 왼쪽에 있음
                if (nums[startIndex] <= target && target < nums[mid]) {
                    endIndex = mid - 1;
                } else {
                    startIndex = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[endIndex]) {
                    startIndex = mid + 1;
                } else {
                    endIndex = mid - 1;
                }
            }
        }
        return -1;
    }
}