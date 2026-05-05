class Solution {
    public int[] productExceptSelf(int[] nums) {

        // brute force, loop through nums twice, compute product when i != j

        // better! product of all nonzero's, then iterate again and divide
        // int totalProductExcludingZeros = 1;

        // Set<Integer> zeroIndices = new HashSet<>();

        // for (int i = 0; i < nums.length; ++i) {
        //     int currentNum = nums[i];
            
        //     if (currentNum == 0) {
        //         zeroIndices.add(i);
        //     } else {
        //         totalProductExcludingZeros *= currentNum;
        //     }
        // }

        // int[] result = new int[nums.length];

        // for (int i = 0; i < nums.length; ++i) {

        //     Boolean isCurrentElementAZero = nums[i] == 0;
        //     // current element is zero and zeroIndices has >1 element
        //     // OR
        //     // current element is not zero and zeroIndes has >=1 element
        //     Boolean isElementExceptSelfAZero = 
        //         ((isCurrentElementAZero && zeroIndices.size() > 1) || (!isCurrentElementAZero && zeroIndices.size() >= 1));

        //     if (isElementExceptSelfAZero) {
        //         result[i] = 0;
        //     } else {

        //         result[i] = isCurrentElementAZero 
        //                         ? totalProductExcludingZeros 
        //                         : totalProductExcludingZeros / nums[i];
        //     }
        // }
        
        // return result;

        // // [-1,0,1,2,3]

        // // zeroIndices = [1]
        // // totalProductExcludingZeros = -6

        // // result = [0,x,x,x,x]
        // // isElementExceptSelfAZero = 

        // BEST - prefix and suffix
            // loop left to right and store products aside from i
            // loop right to left and store products from the other side aside from i
        
        int[] prefix = new int[nums.length];
        int[] suffix = new int[nums.length];
        int[] result = new int[nums.length];

        for (int i = 0; i < nums.length; ++i) {
            if (i == 0) {
                prefix[i] = 1 * nums[i];
            } else {
                prefix[i] = prefix[i - 1] * nums[i];
            }
        }

        for (int i = nums.length - 1; i >= 0; --i) {
            if (i == nums.length - 1) {
                suffix[i] = 1 * nums[i];
            } else {
                suffix[i] = suffix[i + 1] * nums[i];
            }
        }

        for (int i = 0; i < nums.length; ++i) {
            if (i == 0) {
                result[i] = suffix[i + 1];
            } else if (i == nums.length - 1) {
                result[i] = prefix[i - 1];
            } else {
                result[i] = prefix[i - 1] * suffix[i + 1];
            }
        }

        return result;


        // case without zeros
        // [1,2,4,6]
        // prefix (left to right)
        // [1,2,8,48]
        // suffix (right to left)
        // [48,48,24,6]
        // result
        // [48,24,12,8]

        // case with zeros
        // [-1,0,1,2,3]
        // prefix (left to right)
        // [-1,0,0,0,0]
        // suffix (right to left)
        // [0,0,6,6,3]
        // result
        // [0,-6,0,0,0]


        // for index i 
        //  - product except self is prefix[i-1] * suffix[i + 1]
        // if i == 0 
        //  - product except self is suffix[i+1]
        // if i == nums.length - 1 
        //  - product except self is prefix[i-1]
    }
}  
