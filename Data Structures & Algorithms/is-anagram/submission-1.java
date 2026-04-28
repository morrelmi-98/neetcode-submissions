class Solution {
    private Map<String, Integer> getCharOccurrences(String s) {
        Map<String, Integer> result = new HashMap<>();

        for (int i = 0; i < s.length(); ++i) {
            String letter = s.substring(i, i + 1); // exclusive end
            if (result.get(letter) == null) {
                result.put(letter, 1);
            } else {
                result.put(letter, result.get(letter) + 1);
            }
        }
        return result;
    }

    public boolean isAnagram(String s, String t) {
        // anagram means same characters with different order
        // realistically i could sort both

        // ideas


        if (s.length() != t.length()) {
            return false;
        }

        //  could throw them all into a hashmap and compare both hashmaps, thats not time or memory efficient
        // iterate through each string in o(n), put counts into two maps
        // iterate through map keysets and see if they are equal
        //  this doesn't work because sets don't guarantee ordering
        //  so comparing the maps would suck and be messy
        // this was the best idea
        // Map<String, Integer> sCounts = getCharOccurrences(s);
        // Map<String, Integer> tCounts = getCharOccurrences(t);

        // // cop out, shallow equals, not deep equals
        // return Objects.equals(sCounts, tCounts);


        // IDEA 2
        // some sort of counter and removing characters from each of the strings
        // more memory but should be O(n)
        // using indexOf inside the loop would make it n^2 worst case
        // BAD

        // IDEA 1
        // could also put them into char arrays and sort both
        // nlogN + mlogM amortized, worst case n^2
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);

        // messy because you know they are same length but im only looping on one length
        for (int i = 0; i < sChars.length; ++i) {
            if (sChars[i] != tChars[i]) {
                return false;
            }
        }
        return true;
    }
}
