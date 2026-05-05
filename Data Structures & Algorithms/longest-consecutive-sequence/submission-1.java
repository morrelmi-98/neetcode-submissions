class Solution {
    public int longestConsecutive(int[] nums) {

        // OPTION 1 - DUMP INTO A HASHSET

        Set<Integer> uniqueNums = Arrays.stream(nums).boxed().collect(Collectors.toCollection(HashSet::new));
        // set has constant size, for every number in set, find num - 1 start and track back to see length, track max length

        int longestLengthSeen = 0;

        for (Integer num : uniqueNums) { // O(N)
            if (!uniqueNums.contains(num - 1)) {
                int lengthOfSequence = 0;
                int currentNum = num;

                // only runs for sequence start, total work for all of these inner walks bounded by n, so O(2N) or O(N)
                while (uniqueNums.contains(currentNum)) { 
                    ++lengthOfSequence;
                    ++currentNum;
                }

                longestLengthSeen = Math.max(longestLengthSeen, lengthOfSequence);
            }
        }

        return longestLengthSeen;
        // OPTION 2 - UNION FIND, FIGURE THIS OUT


        // ============ BELOW THIS IS ALL WRONG OR BAD ====================
        // brute force go through it twice n^2
        //  track max consecutive sequence for starting i
        //  save length

        // need to do O(N)

        // no special data structure was the hint i took
        // values can be 10^9
        // length can only be 1000 so constant

        // FIRST IDEA ============================
        // map is going to assemble a bunch of disjoint sets
        // highest encountered in sequence -> initial encountered in sequence

        // first pass through, populate that map^

        // [2, 20, 4, 10, 3, 4, 5]
        
        // 2 -> 2 XXXXX
        // 3 -> 2 XXXXX
        // 4 -> 2 XXXXX

        // 20 -> 20
        // 4 -> 4
        // 10 -> 10
        // 5 -> 2

        // if num - 1 is NOT key in the map, add new entry num -> num
        // if num - 1 is in the map
            // store key, value for num - 1 entry
            // remove that entry from the map
            // add a new entry which is current num -> the old value
            // for example when we hit 3, remove 2 -> 2 and add entry for 3 -> 2

        // Final map looks like this        
            // 20 -> 20
            // 4 -> 4
            // 10 -> 10
            // 5 -> 2
            // 1 -> 1

        // space O(n) worst case map has entry for every unique number in nums
        // Map<Integer, Integer> highestToInitialEncountered = new HashMap<>();
        
        // for (int i = 0; i < nums.length; ++i) {
        //     int currentNum = nums[i];

        //     if (highestToInitialEncountered.containsKey(currentNum) 
        //             && highestToInitialEncountered.containsKey(currentNum - 1)) {
        //         // Contains BOTH               
        //         // 6 -> 4 & 7 -> 7
        //         // OR
        //         // 6 -> 6 & 7 -> 7
        //         // OR
        //         // 7 -> 4 & 6 -> 6 (maybe not possible, but make sure it's safe)
        //         int currentNumInitialEncountered = highestToInitialEncountered.get(currentNum);
        //         int currentNumMinusOneInitialEncountered = highestToInitialEncountered.get(currentNum - 1);

        //         highestToInitialEncountered.remove(currentNum);
        //         highestToInitialEncountered.remove(currentNum - 1);

        //         highestToInitialEncountered.put(currentNum, Math.min(currentNumInitialEncountered, currentNumMinusOneInitialEncountered));
        //     } else if (highestToInitialEncountered.containsKey(currentNum) 
        //             && !highestToInitialEncountered.containsKey(currentNum - 1)) {
        //         // YES currentNum and NO currentNum - 1
        //         // that means it's either currentNum -> currentNum or currentNum -> (currentNum - X)
        //         // do nothing, TODO CLEAN UP THIS CASE
        //     } else if (!highestToInitialEncountered.containsKey(currentNum) 
        //             && highestToInitialEncountered.containsKey(currentNum - 1)) {
        //         // NO currentNum and YES currentNum - 1
        //         int previousHighestEncounteredForSequence = currentNum - 1;
        //         int previousInitialEncounteredInSequence = highestToInitialEncountered.get(previousHighestEncounteredForSequence);

        //         highestToInitialEncountered.remove(previousHighestEncounteredForSequence);
        //         highestToInitialEncountered.put(currentNum, previousInitialEncounteredInSequence);
        //     } else {
        //         // Contains NEITHER
        //         highestToInitialEncountered.put(currentNum, currentNum);
        //     }
        // }

        // boolean mergedSetsAtLeastOnce = true;

        // while (mergedSetsAtLeastOnce) {
        //     mergedSetsAtLeastOnce = false;

        //     for (Map.Entry<Integer, Integer> entry : new ArrayList<>(highestToInitialEncountered.entrySet())) {
        //         Integer entryHighest = entry.getKey();
        //         Integer entryLowest = entry.getValue();
        //         boolean containsHigherConnectedSet = highestToInitialEncountered.containsKey(entryHighest + 1);
        //         boolean containsLowerConnectedSet = highestToInitialEncountered.containsKey(entryLowest - 1);

        //         // TODO i could just merge if has lower connected, and then merge if higher connected separately, this could be heuristic
        //         if (containsHigherConnectedSet && containsLowerConnectedSet) {
        //             // entry is 4 -> 2
        //             // map contains 5 -> 3 & 1 -> 1
        //             // or map contains 5 -> -1 & 1 -> 1

        //             Integer newHighest = entryHighest + 1;
        //             Integer newLowest = Math.min(entryLowest, Math.min(highestToInitialEncountered.get(newHighest), highestToInitialEncountered.get(entryLowest - 1)));

        //             highestToInitialEncountered.remove(entryHighest);
        //             highestToInitialEncountered.remove(entryHighest + 1);
        //             highestToInitialEncountered.remove(entryLowest - 1);
        //             highestToInitialEncountered.put(newHighest, newLowest);
        //             mergedSetsAtLeastOnce = true;
        //         } else if (containsHigherConnectedSet && !containsLowerConnectedSet) {
        //             // entry is 4 -> 2
        //             // map contains 5 -> 1 or 5 -> 3

        //             Integer newLowestEncountered = Math.min(entryLowest, highestToInitialEncountered.get(entryHighest + 1));

        //             highestToInitialEncountered.remove(entryHighest);
        //             highestToInitialEncountered.remove(entryHighest + 1);
        //             highestToInitialEncountered.put(entryHighest + 1, newLowestEncountered);
        //             mergedSetsAtLeastOnce = true;
        //         } else if (!containsHigherConnectedSet && containsLowerConnectedSet) {
        //             // entry is 4 -> 2
        //             // map contains 1 -> 1 or 1 -> -3

        //             Integer newLowestEncountered = Math.min(entryLowest, highestToInitialEncountered.get(entryLowest - 1));

        //             highestToInitialEncountered.remove(entryHighest);
        //             highestToInitialEncountered.remove(entryLowest - 1);
        //             highestToInitialEncountered.put(entryHighest, newLowestEncountered);
        //             mergedSetsAtLeastOnce = true;
        //         } else {
        //             // NO CONNECTED SETS FOR ENTRY, N/A
        //         }
        //     }
        // }

        // int longestConsecutive = 0;
        // for (Map.Entry<Integer, Integer> entry : highestToInitialEncountered.entrySet()) {
        //     longestConsecutive = Math.max(longestConsecutive, entry.getKey() - entry.getValue() + 1);
        // }

        // return longestConsecutive;


        // iterate through entries of the map, can not sort them
        // biggest sequence was 5 - 2 + 1, but need to figure out how to chain them


        // for every entry in the map
        //  highest encountered in that set is key
        //  lowest encountered in that value is val
        //  if highest plus 1 is key in the map
        //      merge sets
                    // 4 -> 4 & 5 -> 2 remove 4 -> 4 and keep 5 -> 2 BETWEEN
                    // over would be like a 6 -> 6 or something, remove 6 -> 6 and 5 -> 2 and update to 6 -> 2
        //  if lowest minus 1 is key in the map   
        //      merge sets
        //          // 5 -> 2 & 1 -> 1, remove 1->1 and update to 5 -> 1
        //          // 5 -> 3 & 2 -> 0, remove both and add 5 -> 0
        //          // 5 -> 2 & 3 -> 1, possible? i dont think so based on how i populated the map, 4 would have been encountered
        //          TODO check this case could be detrimental

        // keep going through the map entries until i haven't performed a merge through the entire loop


        // [5, 20, 4, 3]
        
        // 5 -> 5
        // 20 -> 20
        // 4 -> 4
        // 3 -> 3

        // 5 -> 5 gets removed and 4->4 gets removed and 5 -> 4 gets added to the map
        // 5 -> 4 gets removed and 3 -> 3 gets removed and 5 -> 3 gets added to the map


        // 5 -> 3
        // 20 -> 20

        // pass through all 2 entries, no more merge, 
        // loop through entries one more time to find the largest and return length, maybe could optimize that


        // see 2, start sequence 1
            // [2]
        // see 20, 20 not 1 greater from 2, start sequence 2
            // [20]
        // see 4, not 1 greater from 2 or 20, start sequence 3
            // [4]
        // see 10, not 1 greater from 2 or 4 or 20, start sequence 4
            // [10]
        // see 3, 1 greater than 2, 1 less than 4, union [2], [4], [3] together
            // nowe we have [2, 3, 4], [20], [10]
        // see 4, we already have it???? idek here
        // see 5, union [5] with [2, 3, 4]
        
        // TBD on how to implement this, its a map or a set

        // maybe a nested set
    }
}
