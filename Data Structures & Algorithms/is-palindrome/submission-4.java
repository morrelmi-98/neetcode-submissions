class Solution {

    // abba -> true
    // aa -> true
    // a -> true

    // ignore spaces and non letters or numbers

    private boolean isAlphaNumeric(char c) {
        return ('a' <= c && c <= 'z') || ('0' <= c && c <= '9');
    }

    public boolean isPalindrome(String s) {
        // go through s and store all chars that are alphanumerical lowercase into a new string, then check that
        // o(n) space and o(n) time where n is number of chars

        // go through start and end and skip non alphanumeric chars along the way
        // ill opt for this one even though it's less readable because it requires less space taken
        // constant space and o(n) where n is number of chars

        if (s.length() == 0 || s.length() == 1) {
            return true;
        }

        String lowerCaseS = s.toLowerCase();

        int start = 0;
        int end = s.length() - 1;
        boolean isPalindrome = true;
        // " xx"
        // "x x"
        // "xx?"
        // "x?X"
        // "??"

        // ".,"
        while (start <= end) {

            while (!isAlphaNumeric(lowerCaseS.charAt(start)) && start < s.length() - 1) ++start;
            
            while (!isAlphaNumeric(lowerCaseS.charAt(end)) && end > 0) --end;

            if (start < end && lowerCaseS.charAt(start) != lowerCaseS.charAt(end)) {
                // xyy would hit this because x != y at start=0 end=2 
                return false;
            }

            ++start;
            --end;
        }
        return true;
    }
}
