class Solution {
    public int[] twoSum(int[] nums, int target) {
        // nums always has exactly one pair of indices i & j that meet condition
        // nums[i] != nums[j]
        // nums[i] + nums[j] == target

        // USING HINT, DO DIFFERENCE ARRAY

        // iterate through nums one time and store differences




        // [3,4,5,6], target is 7

        // first iteration
            // i = 0
            // difference is 7 - 3 = 4
            // 4 --> 0

        // second iteration
            // i = 1
            // difference is 7 - 4 = 3
            // 3 --> 1
            // before we map 3 --> 1
            // check if map has 4 which adds up to 7
        // 7 - 3 = difference is 4
        // 4 --> 0

        // differenceFromTarget --> indexInNums
        Map<Integer, Integer> differenceToIndex = new HashMap<>();
        int indexOne = 0;
        int indexTwo = 0;
        for (int i = 0; i < nums.length; ++i) {
            // find result case
            int difference = target - nums[i];

            if (differenceToIndex.containsKey(nums[i])) {
                indexOne = differenceToIndex.get(nums[i]);
                indexTwo = i;
                break;
            } else {
                differenceToIndex.put(difference, i);
            }
        }

        int[] result = {indexOne, indexTwo};
        return result;

        // CLOSE BUT BAD, OUTPUT WRONG ORDER, SHOULDNT HAVE WENT TO SORTING NUMS FOR TWO SUM
        // sorting nums beforehand (better)
        // should be nlogN + loop after sorting which should just be n

        // can I modify nums

        // if yes
        // Arrays.sort(nums);

        // if no
        // int[] numsSorted = nums.clone(); // O(n)
        // Arrays.sort(numsSorted); // O(nlogN)

        // brutal way would be
        // int[] numsSorted = new int[nums.length];
        // for loop to populate the numsSorted
        
        // then find the result
        // int i = 0;
        // int j = numsSorted.length - 1;
        // int valueOne = 0;
        // int valueTwo = 0;

        // while (i != j) {
        //     int twoSumCandidate = numsSorted[i] + numsSorted[j];
        //     if (twoSumCandidate == target) {
        //         valueOne = numsSorted[i];
        //         valueTwo = numsSorted[j];
        //         break;
        //     } else if (twoSumCandidate > target) {
        //         --j;
        //     } else { // twoSumCandidate < target
        //         ++i;
        //     }
        // }

        // int indexOne = 0;
        // int indexTwo = 0;

        // System.out.println("valueOne is " + valueOne);
        // System.out.println("valueTwo is " + valueTwo);
        // // valueOne is -3
        // for (int x = 0; x < nums.length; ++x) {
        //     if (nums[x] == valueOne) {
        //         indexOne = x;
        //         break;
        //     }
        // }

        // for (int x = nums.length - 1; x > 0; --x) {
        //     if (nums[x] == valueTwo) {
        //         indexTwo = x;
        //         break;
        //     }
        // }
        // int[] result = {indexOne, indexTwo};
        // return result;

        // without nums being sorted, brute force n^2 solution

        // int indexOne = 0;
        // int indexTwo = 0;
        // for (int i = 0; i < nums.length; ++i) {
        //     for (int j = i + 1; j < nums.length; ++j) {
        //         // i != j not necessary bc loop conditions
        //         if (nums[i] + nums[j] == target) {
        //             indexOne = i;
        //             indexTwo = j;
        //             break;
        //         }
        //     }
        // }
        // int[] result = {indexOne, indexTwo};
        // return result;

    }
}
