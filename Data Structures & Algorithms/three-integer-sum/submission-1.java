class Solution {

    public List<List<Integer>> threeSum(int[] nums) {
        // ask to sort array to improve time complexity
        int[] numsSorted = nums.clone();
        Arrays.sort(numsSorted);

        // i != j != k
        // nums[i] + nums[j] + nums[k] = 0

        // have to preserve indices from original array, track original indices with map
        // num -> indices in nums that num exists in
        Map<Integer, List<Integer>> numToIndicesInOriginalArray = new HashMap<>();

        for (int i = 0; i < nums.length; ++i) {
            if (numToIndicesInOriginalArray.containsKey(nums[i])) {
                numToIndicesInOriginalArray.get(nums[i]).add(i);
            } else {
                List<Integer> singleIndex = new ArrayList<>();
                singleIndex.add(i);
                numToIndicesInOriginalArray.put(nums[i], singleIndex);
            }
        }

        // have to account for duplicates in original array
        // duplicates should count because i have indices of each number, so i don't think
        // i need to worry about it, i can compute those indices and make a unique set of triplets

        // two sum had a couple solutions
        //  there was a walking start/end pointer thing where while != target, increment or decrement accordingly
        //  there was also loading up a map with differences to target in one loop, then another loop checking the map for differences

        // Each element is sorted list of indices i,j,k where nums[ijk] sum up to be 0
        int targetSum = 0;
        Set<List<Integer>> uniqueTriplets = new HashSet<>();

        // [-1, 0, 1]
        // i = 0, j = 1, k = 2 (or end)
        // [-2, -1, 0, 1, 2]
        // i = 0, j = 1, k = 4
        // i = 0, j = 2, k = 4 found one

        // i = 1, j,k should be to right because i already exhausted possibilities for i = 0

        // nums=[-1,0,1,2,-1,-4]
        // numsSorted=[-4,-1,-1,0,1,2]
        // i = 1, j = 2, k = 5
        // first sum = 0
        for (int i = 0; i <= numsSorted.length - 2; ++i) {
            // numsSorted[i] is fixed, based off of numsSorted[i] find numsSorted[j & k] that add to 0
            int j = i + 1;
            int k = numsSorted.length - 1;

            // increment jIndex
            while (j < k) {
                int sum = numsSorted[i] + numsSorted[j] + numsSorted[k];

                if (sum > targetSum) {
                    --k;
                } else if (sum < targetSum) {
                    ++j;
                } else {

                    // nums=[-1,0,1,2,-1,-4]
                    // numsSorted=[-4,-1,-1,0,1,2]
                    // i = 1, j = 2, k = 5
                    // first sum = 0
                    // iValue -1, jvalue -1, kvalue 2
                    // ivalueindices = 0,4
                    // jvalueindices = 0,4
                    // kvalueindices = 3
                    int iValue = numsSorted[i];
                    int jValue = numsSorted[j];
                    int kValue = numsSorted[k];

                    List<Integer> iValueIndices = numToIndicesInOriginalArray.get(iValue);
                    List<Integer> jValueIndices = numToIndicesInOriginalArray.get(jValue);
                    List<Integer> kValueIndices = numToIndicesInOriginalArray.get(kValue);

                    for (Integer iIndex : iValueIndices) {
                        for (Integer jIndex : jValueIndices) {
                            for (Integer kIndex : kValueIndices) {
                                if (iIndex != jIndex && jIndex != kIndex && iIndex != kIndex) {
                                    List<Integer> potentialTriplet = new ArrayList<>();
                                    potentialTriplet.add(nums[iIndex]);
                                    potentialTriplet.add(nums[jIndex]);
                                    potentialTriplet.add(nums[kIndex]);
                                    potentialTriplet.sort(Comparator.naturalOrder());
                                    uniqueTriplets.add(potentialTriplet);
                                }
                            }
                        }
                    }
                    ++j;
                }
            }
        }

        return new ArrayList<>(uniqueTriplets);
    }


    // public List<List<Integer>> threeSum(int[] nums) {
    //     // brute force go through array 3 times which would be n^3

    //     // hashmap alone doesn't feel like it works
    //     // two pointer solution alone would be weird, because you'd need 3 pointers and one arbitrarily starts in middle


    //     // some two pointer solution in combination with the hashmap could keep it o(n) with using some space

    //     // or populating a hashmap with multiple o(n) run throughs could work


    //     // difference to zero -> single index in array // o(n)
    //     // two pointer solution to find the next two indices, but it needs to be sorted for that (nlogn)

    //     // NOT SORTED
    //     // ====== could brute force n^3

    //     // ===== doesn't work because what if indices 2 & 4 summed up to -2, and indices 3 & 5 summed up to -2, previous indices values would be overwritten
    //     //  difference to zero -> [indices i, j that summed to something that need that difference to zero]
    //     // Map<Integer, List<Integer>> differenceToTwoIndexSum = new HashMap<>();

    //     // for (int i = 0; i < nums.length; ++i) {
    //     //     for (int j = i + 1; j < nums.length; ++j) {

    //     //         List<Integer> indices = new ArrayList();
    //     //         indices.put( )
    //     //         differenceToTwoIndexSum.put(nums[i] + nums[j],)
    //     //     }
    //     // }

    //     // then go through o(n) and check every element against the map, and take out when k == i or i ==j from map values

    //     // SORTED - ASK THE INTERVIEWER
    //     // nlogn right off the bat from sorting nums
    //     // [-4, -1, -1, 0, 1, 2]

    //     Map<Integer, List<Integer>> numToIndicesInNums = new HashMap<>();

    //     for (int i = 0; i < nums.length; ++i) {
    //         if (!numToIndicesInNums.containsKey(nums[i])) {
    //             numToIndicesInNums.put(nums[i], new ArrayList<>());
    //         }
    //         numToIndicesInNums.get(nums[i]).add(i);
    //     }

    //     int[] numsSorted = nums.clone();
    //     Arrays.sort(numsSorted); // nlogn

    //     // once sorted, pin one number i and do the two pointer solution with every index not equal to i
    //     // looping through for every i, j and k are start and end
    //     // i could store difference in map but that would use o(n) space and looping through i still keeps it n^2
    //     // TODO try to do it with map maybe after, just do a fixed plus 2 pointer to start
    //     Set<List<Integer>> uniqueIndices = new HashSet<>();

    //     for (int fixed = 0; fixed < numsSorted.length; ++fixed) {

    //         int start = fixed + 1; // TO THE RIGHT OF FIXED BC FIXED ALREADY ACCOUNTED FOR THE LEFT
    //         int end = numsSorted.length - 1;

    //         while (start < end) {
    //             int sum = numsSorted[fixed] + numsSorted[start] + numsSorted[end];

    //             if (sum > 0) {
    //                 --end;
    //             } else if (sum < 0) {
    //                 ++start;
    //             } else {
    //                 int startVal = numsSorted[start];
    //                 int endVal = numsSorted[end];
    //                 int fixedVal = numsSorted[fixed];

    //                 List<Integer> startValIndices = numToIndicesInNums.get(startVal);
    //                 List<Integer> endValIndices = numToIndicesInNums.get(endVal);
    //                 List<Integer> fixedValIndices = numToIndicesInNums.get(fixedVal);

    //                 startValIndices.forEach(startIndex -> {
    //                     endValIndices.forEach(endIndex -> {
    //                         fixedValIndices.forEach(fixedIndex -> {
    //                             if (!startIndex.equals(endIndex) && !startIndex.equals(fixedIndex) && !endIndex.equals(fixedIndex)) {
    //                                 List<Integer> tripletThatAddsToZero = new ArrayList<>();
    //                                 tripletThatAddsToZero.add(nums[startIndex]);
    //                                 tripletThatAddsToZero.add(nums[endIndex]);
    //                                 tripletThatAddsToZero.add(nums[fixedIndex]);
    //                                 Collections.sort(tripletThatAddsToZero); // always 3 so constant
    //                                 uniqueIndices.add(tripletThatAddsToZero);
    //                             }
    //                         });
    //                     });
    //                 });

    //                 ++start;
    //             }
    //         }
    //     }
    //     // if i kept a map of number to indices in original nums, i could just use that map

    //     // numToIndicesInNums
    //     // -1 -> [0, 4]
    //     // 0 -> [1]
    //     // 1 -> [2]
    //     // 2 -> [3]
    //     // -4 -> [5]

    //     // [-4, -1, -1, 0, 1, 2]
    //     // fixed = 1
    //     // start = 2
    //     // end = 5
    //     // numsSorted[fixed] = -1
    //     // numsSorted[start] = -1
    //     // numsSorted[end] = 2
    //     // sum = 0

    //     // found one but sorted loses original indices
    //     return new ArrayList<>(uniqueIndices);
    // }
}
















