class Solution {




public List<List<String>> groupAnagrams(String[] strs) {

    Map<List<Integer>, List<String>> anagramGroups = new HashMap<>();

    for (String s : strs) {
        List<Integer> letterSumsForCurrentString = new ArrayList<>();
        
        for (int i = 0; i < 26; ++i) letterSumsForCurrentString.add(0);

        // populate letterSumsForCurrentString

        char[] chars = s.toCharArray();

        // max 1000 so not n^2
        for (int i = 0; i < chars.length; ++i) {
            int indexInLetterSums = chars[i] - 'a';
            letterSumsForCurrentString.set(indexInLetterSums, letterSumsForCurrentString.get(indexInLetterSums) + 1);
        }

        // check map, modify the value or add new entry

        if (anagramGroups.containsKey(letterSumsForCurrentString)) {
            anagramGroups.get(letterSumsForCurrentString).add(s);
        } else {
            List<String> newGroup = new ArrayList<>();
            newGroup.add(s);
            anagramGroups.put(letterSumsForCurrentString, newGroup);
        }
    }

    return anagramGroups.values().stream().toList();
}

    // private Map<String, Integer> getCharOccurrences(String s) {
    //     Map<String, Integer> result = new HashMap<>();

    //     for (int i = 0; i < s.length(); ++i) {
    //         String letter = s.substring(i, i + 1); // exclusive end
    //         if (result.get(letter) == null) {
    //             result.put(letter, 1);
    //         } else {
    //             result.put(letter, result.get(letter) + 1);
    //         }
    //     }
    //     return result;
    // }

    // private boolean isAnagram(String s, String t) {
    //     if (s.length() != t.length()) {
    //         return false;
    //     }
    //     // small optimization if they are equal why do we need to make all the mappings
    //     if (s.equals(t)) {
    //         return true;
    //     }

    //     Map<String, Integer> sCounts = getCharOccurrences(s);
    //     Map<String, Integer> tCounts = getCharOccurrences(t);

    //     return sCounts.equals(tCounts);
    // }

    // public List<List<String>> groupAnagrams(String[] strs) {
    //     // string in strs --> list of everything that's an anagram with string, including itself
    //     // each entry in a map is a category, there shouldn't be multiple keys in the map that are anagrams of eachother
    //     Map<String, List<String>> stringToAnagramList = new HashMap<>();

    //     for (String s : strs) {
    //         boolean foundGroupForS = false;

    //         for (String key : stringToAnagramList.keySet()) {
    //             if (isAnagram(s, key)) {
    //                 stringToAnagramList.get(key).add(s);
    //                 foundGroupForS = true;
    //                 break;
    //             }
    //         }
    //         // don't add something to the map if theres already a key that is an anagram of current string
    //         if (!foundGroupForS) {
    //             List<String> groupWithSingleString = new ArrayList<>();
    //             groupWithSingleString.add(s);
    //             stringToAnagramList.put(s, groupWithSingleString);
    //         }
    //     }

    //     return stringToAnagramList.values().stream().toList();
    // }
}
