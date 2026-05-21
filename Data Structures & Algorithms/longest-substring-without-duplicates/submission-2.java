class Solution {

    public int lengthOfLongestSubstring(String s) {
        Set<Character> charSet = new HashSet<>();
        int start = 0;
        int end = 0;
        int maxEncountered = 0;

        // while end is before end of string
        while (end < s.length()) {
            // remove chars from set at start and increment start until it doesn't contain the char end is at
            while (charSet.contains(s.charAt(end))) {
                charSet.remove(s.charAt(start));
                ++start;
            }
            charSet.add(s.charAt(end));
            maxEncountered = Math.max(maxEncountered, end - start + 1);
            ++end;
        }

        return maxEncountered;
    }

    // ============= so sloppy, better sliding window above
    // public int lengthOfLongestSubstring(String s) {
    //     // "" -> "" -> 0
    //     // "dvdf" -> "vdf" -> 3
    //     // "zxyzxyz" -> "xyz" -> 3

    //     // sliding window
    //     // start ptr at 0
    //     // end ptr at 1
    //     // while end ptr less than length of string
    //     //  increment end ptr until duplicate encountered or end of string, keep track of size
    //     //      track characters in a set
    //     //  when set contains character, increment start pointer until set contains no duplicates
    //     //      "dvvd"
    //     //      start -> d
    //     //      end -> first v
    //     //      set -> [v,d]
    //     //      increment end to second v
    //     //      identify set already contains v, v is character in question
    //     //      increment start until it encounters v, remove anything prior to the v from the set
    //     //          then increment start one past v

    //     if (s.length() == 0 || s.length() == 1) {
    //         return s.length();
    //     }
    //     int start = 0;
    //     int end = 1;
    //     int maxLengthEncountered = 0;

    //     // "dvvd"
    //     // start 2
    //     // end 3
    //     // uniqueChars = [d,v]
    //     // maxLengthEncountered = 0

    //     // "abcabcbb"
    //     // start 0
    //     // end 3
    //     // uniqueChars = [a,b,c]
    //     // maxLengthEncountered = 0
    //     while (start < s.length() && end < s.length()) {
    //         Set<String> uniqueChars = new HashSet<>();

    //         for (int i = start; i < end; ++i) {
    //             uniqueChars.add(s.substring(i, i + 1));
    //         }
    //         uniqueChars.add(s.substring(start, start + 1));

    //         while (end < s.length() && !uniqueChars.contains(s.substring(end, end + 1))) {
    //             uniqueChars.add(s.substring(end, end + 1));
    //             ++end;
    //         }

    //         // when we get out of this loop, we either hit the end of the string or a duplicate
    //         maxLengthEncountered = Math.max(maxLengthEncountered, end - start);

    //         if (end == s.length()) {
    //             break;
    //         }

    //         String duplicateCharEncountered = s.substring(end, end + 1);
    //         while (start < s.length() && !s.substring(start, start + 1).equals(duplicateCharEncountered)) {
    //             ++start;
    //         }
    //         ++start; // one more increment past the last occurrence of duplicate character
    //         if (start == end) {
    //             ++end;
    //         }
    //     }

    //     return maxLengthEncountered;
    // }
    // public int lengthOfLongestSubstring(String s) {

    //     // ======= TODO THIS IS SUPER WRONG BECAUSE OF DVDF CASE, NEED SLIDING WINDOW APPROACH

    //     // set index, loop through, within the loop keep map of characters and increment within, track longest length
    //     int index = 0;
    //     int maxLengthEncountered = 0;
        
    //     // "zxyzxyz"
    //     // index 6
    //     // lettersEncountered []
    //     // currentSubstringLength 0
    //     // maxLengthEncountered 3

    //     // "xxx"
    //     // index 1
    //     // lettersEncountered []
    //     // currentSubstringLength 0
    //     // maxLengthEncountered 1

    //     // "dvdf"
    //     while (index < s.length()) {
    //         Set<String> lettersEncountered = new HashSet<>();
    //         int currentSubstringLength = 0; // TODO
    //         while (index < s.length() 
    //             && !lettersEncountered.contains(s.substring(index, index + 1))) {
    //             lettersEncountered.add(s.substring(index, index + 1));
    //             ++index;
    //             ++currentSubstringLength;
    //         }
    //         maxLengthEncountered = Math.max(maxLengthEncountered, currentSubstringLength);
    //     }

    //     return maxLengthEncountered;
    // }
}
