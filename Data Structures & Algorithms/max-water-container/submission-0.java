class Solution {
    public int maxArea(int[] heights) {
        // array of heights
        // i and j are indices in array
        // area is Min(heights[i], heights[j]) * (j - i)

        // need to maximize (j-i) or Min(heights[i], heights[j])
        // two pointer, ALWAYS move smaller height value, 
        //  THIS IS BECAUSE because width is always getting smaller, so smaller height will always have smaller area

        int i = 0; 
        int j = heights.length - 1;
        int maxArea = 0;

        while (i < j) {
            int currentArea = Math.min(heights[i], heights[j]) * (j - i);
            maxArea = Math.max(maxArea, currentArea);

            if (heights[i] < heights[j]) {
                ++i;
            } else {
                --j;
            }
        }

        return maxArea;

        // ======== DISREGARD, MISUNDERSTOOD QUESTION
        // brute force n^2 go through multiply value when i != j and track largest
        //  not worth implementing this
        
        // sorting nlogN optimization?
        // [1,2,3]
        // always would be 6 or multiples of the last 2 in the sorted array

        // O(N) ??? has to be an o(n) way with maps or sets
    }
}
