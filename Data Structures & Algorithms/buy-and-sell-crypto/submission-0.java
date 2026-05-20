class Solution {
    public int maxProfit(int[] prices) {
        // i,j where prices[j] - prices[i] is the highest possible number
        
        // [1] -> 0
        // [1,1] -> 0
        // [1,2] -> 1
        // [1,2,3] -> 2
        // [1,0,2] -> 2
        
        // brute force is go through the array twice n^2, find min and max value where i,j indices i<j

        int minPriceEncounteredIndex = 0;
        int maxProfit = 0;

        for (int i = 1; i < prices.length; ++i) {
            int profit = prices[i] - prices[minPriceEncounteredIndex];
            maxProfit = Math.max(profit, maxProfit);
            if (prices[i] < prices[minPriceEncounteredIndex]) {
                minPriceEncounteredIndex = i;
            }
            // compute profit if you bought at minPriceEncounteredIndex and sold here
            // assign maxProfit if applicable
            // update minPriceEncounteredIndex if applicable
        }

        return maxProfit;
    }
}
