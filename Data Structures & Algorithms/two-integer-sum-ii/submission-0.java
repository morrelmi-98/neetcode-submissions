class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // other two sum solution was map<difference to get to target, index of number>
        // since input array is sorted, i can do the increment/decrement deal
        int start = 0; 
        int end = numbers.length - 1;
        int[] result = new int[2];

        while (start < end) {
            int sum = numbers[start] + numbers[end];

            if (sum < target) {
                ++start;
            } else if (sum > target) {
                --end;
            } else {
                result[0] = start + 1;
                result[1] = end + 1;
                break;
            }
        }

        return result;
    }
}
