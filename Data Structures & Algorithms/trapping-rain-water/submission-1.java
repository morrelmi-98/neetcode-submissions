class Solution {
    public int trap(int[] height) {
        // non negative integers
        // height[i] is a height of a bar
        // each bar has width of 1

        // max area of water that can be trapped between the bars

        // ============= brute force solution O(n^2)
        // iterate through twice i,j 
        // compute each area when i!=j and absValue(i-j) > 1

        // ========== Better, o(nlogn) ??? 
        // no sorting involved, doesn't seem likely, TODO TBD

        // ========== better, o(n) ????
        // why doesn't classic 2 pointer solution work here for container w most water?
        // because for a given i,j starting at beginning and end, i can't guarantee there's water inbetween them
        // example would be [0,2,0,2,1,0,1] when i is 1 and k is len-1, theres nothing valid i can compute there

        // loop through array, store some information, loop through a second time, compute result
        


        // Array with same size n where n i height.length
        // iterate left to right
        // compute how much is stored at each level

        // [0,2,0,3,1,0,1,3,2,1]

        // [0,0,0,0,0,0,0,0,0,0]

        // i = 0, nothing unless there's a bar, store height of bar
        //  prevbar = 0
        //  prevbarindex = 0
        // i = 1, store height of 2, check previous bar and previous bar index, no space because prev bar index is 1 away
        //  prevbar = 2
        //  prevbarindex = 1
        //  prevbar was updated because current bar is >= prevbar
        // i = 2
        //  prevbar = 2
        //  prevbarindex = 1
        // need some nested loop that increments as well and traps water in container

        // [2,0,2]
        // should give
        // [0,2,0]

        // [3,1,0,1,3]
        // should give
        // [0,2,3,2,0]

        // [2,0,2,4,0,4]
        // should give
        // [0,2,0,0,4,0] left to right
        // [2,4,2,0,4,0] right to left
        // result would be
        // [0, 2, 0, 0, 4, 0]
        
        // need to traverse both ways because
        // [4,0,2]
        // left to right would give [0,4,2]
        // but right to left it would give [0,2,0]
        // if i pass through right and left and take the min of both, that would work
        
        // Left -> right
        int[] leftToRightWaterValues = new int[height.length];
        for (int i = 0; i < height.length; ++i) {
            leftToRightWaterValues[i] = 0;
        }
        // [4,1,2]
        int highestBarToLeftIndex = 0;
        for (int i = 1; i < height.length; ++i) {
            //  check bar at i
            int currentBarHeight = height[i];
            if (currentBarHeight < height[highestBarToLeftIndex]) {
                //  if bar at i is lower than highest bar to left, add water for this bar
                leftToRightWaterValues[i] = height[highestBarToLeftIndex] - currentBarHeight;
            } else {
                //  if bar at i is >= than highest bar to left, update highest bar to left index, no water added
                highestBarToLeftIndex = i;
            }
        }

        // Right -> left
        int[] rightToLeftWaterValues = new int[height.length];
        for (int i = 0; i < height.length; ++i) {
            rightToLeftWaterValues[i] = 0;
        }

        int highestBarToRightIndex = height.length - 1;
        for (int i = height.length - 2; i >= 0; --i) {
            int currentBarHeight = height[i];

            if (currentBarHeight < height[highestBarToRightIndex]) {
                rightToLeftWaterValues[i] = height[highestBarToRightIndex] - currentBarHeight;
            } else {
                highestBarToRightIndex = i;
            }
        }

        int result = 0;
        for (int i = 0; i < height.length; ++i) {
            result += Math.min(rightToLeftWaterValues[i], leftToRightWaterValues[i]);
        }
        return result;
    }
}
