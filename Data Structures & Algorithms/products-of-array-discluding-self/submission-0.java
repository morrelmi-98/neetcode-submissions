class Solution {
    public int[] productExceptSelf(int[] nums) {

        // brute force, loop through nums twice, compute product when i != j

        // better! product of all nonzero's, then iterate again and divide
        int totalProductExcludingZeros = 1;

        Set<Integer> zeroIndices = new HashSet<>();

        for (int i = 0; i < nums.length; ++i) {
            int currentNum = nums[i];
            
            if (currentNum == 0) {
                zeroIndices.add(i);
            } else {
                totalProductExcludingZeros *= currentNum;
            }
        }

        int[] result = new int[nums.length];

        for (int i = 0; i < nums.length; ++i) {

            Boolean isCurrentElementAZero = nums[i] == 0;
            // current element is zero and zeroIndices has >1 element
            // OR
            // current element is not zero and zeroIndes has >=1 element
            Boolean isElementExceptSelfAZero = 
                ((isCurrentElementAZero && zeroIndices.size() > 1) || (!isCurrentElementAZero && zeroIndices.size() >= 1));

            if (isElementExceptSelfAZero) {
                result[i] = 0;
            } else {

                result[i] = isCurrentElementAZero 
                                ? totalProductExcludingZeros 
                                : totalProductExcludingZeros / nums[i];
            }
        }
        
        return result;

        // [-1,0,1,2,3]

        // zeroIndices = [1]
        // totalProductExcludingZeros = -6

        // result = [0,x,x,x,x]
        // isElementExceptSelfAZero = 

        // best - prefix and suffix
            // loop left to right and store products aside from i
            // loop right to left and store products from the other side aside from i
    }
}  
