class Solution {
    public int solution(int[][] sizes) {
        int maxWidth = 0;
        int maxHeight = 0;
        for (int[] size : sizes) {

            // 각 명함의 가로와 세로 중 큰 값을 가로, 작은 값을 세로로 설정
            int width = Math.max(size[0], size[1]);
            int height = Math.min(size[0], size[1]);

            maxWidth = Math.max(width, maxWidth);
            maxHeight = Math.max(height, maxHeight);
        }

        return maxWidth * maxHeight;
    }
}