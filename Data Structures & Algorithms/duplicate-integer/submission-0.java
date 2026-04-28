class Solution {
    public boolean hasDuplicate(int[] nums) {
        // O(n) time where n is size of nums
        // O(n) memory at worst case store entire array in map

        Map<Integer, Integer> counts = new HashMap<>();

        for (int value : nums) {
            if (counts.get(value) != null && counts.get(value) > 0) {
                return true;
            }
            counts.put(value, counts.get(value) == null ? 1 : counts.get(value) + 1);
        }

        return false;
    }
}