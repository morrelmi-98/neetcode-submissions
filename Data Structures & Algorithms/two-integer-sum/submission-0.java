class Solution {
    public int[] twoSum(int[] nums, int target) {
        // nums always has exactly one pair of indices i & j that meet condition
        // nums[i] != nums[j]
        // nums[i] + nums[j] == target

        // without nums being sorted, brute force n^2 solution

        int indexOne = 0;
        int indexTwo = 0;
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                // i != j not necessary bc loop conditions
                if (nums[i] + nums[j] == target) {
                    indexOne = i;
                    indexTwo = j;
                    break;
                }
            }
        }
        int[] result = {indexOne, indexTwo};
        return result;
        // sorting nums beforehand (better)
        // should be nlogN + loop after sorting which should just be n
    }
}
