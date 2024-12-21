class Solution {
    public int[] solution(int[] answers) {
        int[] firstStudent = {1, 2, 3, 4, 5};
        int[] secondStudent = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] thirdStudent = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int firstStudentCount = 0;
        int secondStudentCount = 0;
        int thirdStudentCount = 0;

        for (int i = 0; i < answers.length; i++) {
            int currentAnswer = answers[i];
            int firstStudentCurrentAnswer = firstStudent[i % firstStudent.length];
            int secondStudentCurrentAnswer = secondStudent[i % secondStudent.length];
            int thirdStudentCurrentAnswer = thirdStudent[i % thirdStudent.length];

            if (currentAnswer == firstStudentCurrentAnswer) {
                firstStudentCount++;
            }

            if (currentAnswer == secondStudentCurrentAnswer) {
                secondStudentCount++;
            }

            if (currentAnswer == thirdStudentCurrentAnswer) {
                thirdStudentCount++;
            }
        }

        if (firstStudentCount == secondStudentCount && secondStudentCount == thirdStudentCount) {
            return new int[]{1, 2, 3};
        }

        if (firstStudentCount > secondStudentCount && firstStudentCount > thirdStudentCount) {
            return new int[]{1};
        }

        if (secondStudentCount > firstStudentCount && secondStudentCount > thirdStudentCount) {
            return new int[]{2};
        }

        if (thirdStudentCount > secondStudentCount && thirdStudentCount > firstStudentCount) {
            return new int[]{3};
        }

        if (firstStudentCount > thirdStudentCount && secondStudentCount == firstStudentCount) {
            return new int[]{1, 2};
        }

        if (firstStudentCount > secondStudentCount && thirdStudentCount == firstStudentCount) {
            return new int[]{1, 3};
        }

        if (firstStudentCount < secondStudentCount && thirdStudentCount == secondStudentCount) {
            return new int[]{2, 3};
        }

        throw new RuntimeException("있을 수 없음");
    }
}