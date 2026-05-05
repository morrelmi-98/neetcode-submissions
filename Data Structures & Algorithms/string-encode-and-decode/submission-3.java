class Solution {

    public String encode(List<String> strs) {
        // need to know length for each of the next string so decode can parse it

        // input "ab", "cd"
        // "2#ab2#cd"
        StringBuilder result = new StringBuilder();

        for (String s : strs) {
            Integer size = s.length();
            result.append(size.toString());
            result.append('#');
            result.append(s);
        }
        return result.toString();
    }

    public List<String> decode(String str) {
        // 2hi3top4dogs
        // edge case ""
        // 02hi3cat or 2hi03cat
        // might need a hash or something to handle this

        List<String> result = new ArrayList<>();

        Integer currentCharIndex = 0;
        while (currentCharIndex < str.length()) { // TODO rethink this

            // careful here, it would just give ascii number if you dont subtract from zero
            StringBuilder nextStringLengthString = new StringBuilder();
            while (str.charAt(currentCharIndex) != '#') {
                nextStringLengthString.append(str.charAt(currentCharIndex));
                ++currentCharIndex;
            }
            ++currentCharIndex; // move past the #



            Integer nextStringLength = new Integer(nextStringLengthString.toString());
            Integer startingIndex = currentCharIndex;

            StringBuilder elementInResult = new StringBuilder();
            while (currentCharIndex < startingIndex + nextStringLength) {
                elementInResult.append(str.charAt(currentCharIndex));
                ++currentCharIndex;
            }
            
            result.add(elementInResult.toString());
        }

        // currentcharindex 3 + 2 + 1
        // nextstringlength 2
        // numcharsadded 2
        // element in result cd

        return result;
    }
}
