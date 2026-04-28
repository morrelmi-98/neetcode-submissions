class Solution {


    public int[] topKFrequent(int[] nums, int k) {

        // WORST
        // nlogN sort the list by frequency of each number
            // get frequencies in a map
            // sort with a lambda that returns rank from map that has frequency

        // Map<Integer, Integer> itemToOccurrenceCount = new HashMap<>();
        
        // for (int i = 0; i < nums.length; ++i) {
        //     itemToOccurrenceCount.put(nums[i], itemToOccurrenceCount.getOrDefault(nums[i], 0) + 1);
        // }

        // return itemToOccurrenceCount.keySet().stream()
        //     .sorted((a, b) -> itemToOccurrenceCount.get(b) - itemToOccurrenceCount.get(a))
        //     .limit(k) // gets first k elements, which means the sort does descending based on occurrence
        //     .mapToInt(Integer::intValue)
        //     .toArray();

        
        // NEXT BEST
        // priority queue, somehow maintains ranking, tbd on how
        // Map<Integer, Integer> itemToOccurrenceCount = new HashMap<>();
        
        // for (int i = 0; i < nums.length; ++i) {
        //     itemToOccurrenceCount.put(nums[i], itemToOccurrenceCount.getOrDefault(nums[i], 0) + 1);
        // }

        // Comparator<Integer> compareBasedOnFrequency = (a, b) -> itemToOccurrenceCount.get(b) - itemToOccurrenceCount.get(a);

        // PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(compareBasedOnFrequency);

        // for (Integer num : itemToOccurrenceCount.keySet()) {
        //     priorityQueue.add(num);
        // }

        // int[] result = new int[k];

        // for (int i = 0; i < k; ++i) {
        //     result[i] = priorityQueue.poll();
        // }

        // return result;

        // IDEAL?
        // bucket sort creates n buckets

        Map<Integer, Integer> itemToOccurrenceCount = new HashMap<>();
        
        for (int i = 0; i < nums.length; ++i) {
            itemToOccurrenceCount.put(nums[i], itemToOccurrenceCount.getOrDefault(nums[i], 0) + 1);
        }

        List<List<Integer>> occurrenceToNumbers = new ArrayList<>();

        IntStream.range(0, nums.length + 1)
            .forEach(i -> occurrenceToNumbers.add(new ArrayList()));

        for (Integer num : itemToOccurrenceCount.keySet()) {
            Integer occurrenceForNum = itemToOccurrenceCount.get(num);
            occurrenceToNumbers.get(occurrenceForNum).add(num);
        }

        int[] result = new int[k];
        int numSet = 0;

        for (int i = occurrenceToNumbers.size() - 1; i > 0; --i) {
            List<Integer> numbersThatOccurITimes = occurrenceToNumbers.get(i);

            if (numbersThatOccurITimes != null) {
                for (int j = 0; j < numbersThatOccurITimes.size(); ++j) {
                    result[numSet] = numbersThatOccurITimes.get(j);
                    numSet++;

                    if (numSet == k) {
                        break;
                    }
                }
            }

            if (numSet == k) {
                break;
            }
        }

        return result;
    }

    // public int[] topKFrequent(int[] nums, int k) {
    //     // need to store the numbers in the list and the frequency of each number


    //     // that would require storing, then probably getting the entries, sorting by value, and returning k top
    //     // that would be n + nlogn so O(nlogN)
    //     // TODO CHECK THAT THAT WOULD BE nlogN

    //     // improved idea, i know k ahead of time, so i can maintain a list of the K most frequent

    //     // k most frequent or frequency at which the k most frequent elements exist at or above??


    //     // winner list of max size k where first element is least frequent, last element is most frequent
    //     // could treat it like a dequeue where most frequent is at the end
    //     Set<Integer> winnerSet = new HashSet<>();
    //     // map of frequencies (numberToFrequency)
    //     Map<Integer, Integer> numberToFrequency = new HashMap<>();
    //     // thresholdFrequency (frequency at which you need to make the winner list), maintained through loop
    //     int thresholdFrequency = 0;

    //     for (int i = 0; i < nums.length; ++i) {
    //         int currentNumber = nums[i];

    //         // update numberToFrequency with occurrence of currentNumber (computeIfAbsent)
    //         numberToFrequency.put(currentNumber, numberToFrequency.getOrDefault(currentNumber, 0) + 1);

    //         // check the frequency of current number after updating numberToFrequency
    //         int frequencyOfCurrentNumber = numberToFrequency.get(currentNumber);

    //         // compare frequencyOfCurrentNumber to thresholdFrequency

    //         // todo maybe optimization with the >= part of this
    //         if (frequencyOfCurrentNumber > thresholdFrequency || winnerSet.size() < k) {

    //             // always add current number to winner list, remove one element with lowest threshold if size exceeds K
    //             int previousThresholdFrequency = thresholdFrequency;
    //             thresholdFrequency = frequencyOfCurrentNumber;
    //             winnerSet.add(currentNumber);
    //             if (winnerSet.size() > k) {
    //                 for (Integer potentialWinner : winnerSet) {
    //                     int frequencyOfPotentialWinner = numberToFrequency.get(potentialWinner);

    //                     if (previousThresholdFrequency < thresholdFrequency 
    //                             && frequencyOfPotentialWinner < thresholdFrequency) {
    //                         // this means i found something more frequent than something else in the list
    //                         // and the winner list is at max size, so i need to remove one of those less frequent things out
    //                         winnerSet.remove(potentialWinner);
    //                         break;
    //                     } else if (previousThresholdFrequency == thresholdFrequency 
    //                             && frequencyOfPotentialWinner <= thresholdFrequency) {
    //                         // this means that there is something else with the same frequency as what i just added to the list
    //                         // so i can remove that or the one i just added
    //                         winnerSet.remove(potentialWinner);
    //                         break;
    //                     } 
    //                     // prev threshold > new threshold
    //                     //  this should never happen unless the size of the set is < k
    //                 }
    //             }
    //         }

    //         // if current number exceeds threshold
    //             // set thresholdFrequency to frequencyOfCurrentNumber
    //             // add currentNumber to winner list
    //             // check frequencies of items in winner list?? this is another loop so would also make it log n
    //             //  or it doesn't because K is constant up to 2000 unique numbers
    //             //  this is good, that means go through and remove anything in winner list with less than threshold frequency
    //         // if current number does not exceed threshold
    //             // continue
    //     }

    //     return winnerSet.stream().mapToInt(Integer::intValue).toArray();
    // }
}
