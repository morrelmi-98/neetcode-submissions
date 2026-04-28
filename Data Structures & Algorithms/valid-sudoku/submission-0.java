class Solution {

    private static int BOARD_HEIGHT = 9;
    private static int BOARD_WIDTH = 9;
    private static int SUB_BOX_HEIGHT = 3;
    private static int SUB_BOX_WIDTH = 3;

    public boolean isValidSudoku(char[][] board) {


        // board is 9x9, has to sum up to 10
        // for now hardcode to 9x9 & 10 sum but make these generic 
        // if sum can be computed off of dimensions somehow   


        // board is a 2D array, need to iterate everything at least once so n^2 or 9^2


        // compute subBox array // not necessary unless interviewer said yes

        int numSubBoxesVertical = BOARD_HEIGHT / SUB_BOX_HEIGHT;
        int numSubBoxesHorizontal = BOARD_WIDTH / SUB_BOX_WIDTH;

        int[][] subBoxes = new int[numSubBoxesVertical][numSubBoxesHorizontal];

        int subBoxIndex = 0;
        for (int i = 0; i < subBoxes.length; ++i) {
            for (int j = 0; j < subBoxes[i].length; ++j) {
                subBoxes[i][j] = subBoxIndex++;
            }
        }

        // sub box indexed like
        //  0   1   2
        //  3   4   5
        //  6   7   8


        // while iterating
            // row has SUBSET of digits 1-9 <=
            // row has unique digits no duplicates
            // column has SUBSET of digits 1-9 <=
            // column has unique digits no duplicates
            // current sub box has to have subset of digits 1-9, no duplicates
            // current sub box sum does NOT matter as long as no duplicates

        // unique digits solution
        //  Map<rowIndex, Set<numbers encountered in row or col>>
        //  if set contains current digit, return false
        //  exclude zeros

        // subset of 1-9 solution
        //  check each digit, if any isn't 0-9 from getNumericalValue return false

        // sub box has subset of digits problem
        //  Map<subBoxIndex, Set<numbers encountered in sub box>>
        //  exclude zeros

        Map<Integer, Set<Integer>> rowIndexToDigitsEncountered = new HashMap<>();
        Map<Integer, Set<Integer>> colIndexToDigitsEncountered = new HashMap<>();
        Map<Integer, Set<Integer>> subBoxIndexToDigitsEncountered = new HashMap<>();

        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                Integer currentSquareValue = getNumericalValue(board[row][col]);
                Integer currentSubBoxIndex = getSubBoxIndex(row, col, subBoxes);

                if (!isEntryValidForEncounteredMap(row, currentSquareValue, rowIndexToDigitsEncountered)) {
                    return false;
                }

                if (!isEntryValidForEncounteredMap(col, currentSquareValue, colIndexToDigitsEncountered)) {
                    return false;
                }

                if (!isEntryValidForEncounteredMap(currentSubBoxIndex, currentSquareValue, subBoxIndexToDigitsEncountered)) {
                    return false;
                }
            }
        }

        return true;
    }

    // TODO better name
    //  populates map entry if not initialized
    // returns false if invalid thing was encountered, true otherwise
    private boolean isEntryValidForEncounteredMap(Integer key, Integer value, Map<Integer, Set<Integer>> map) {
        // invalid because digit not in subset of 1-9
        if (value < 0 || value > 9) {
            return false;
        }
        
        // don't add zeros to the encountered list
        if (value == 0) {
            return true;
        }

        // initialize entry if not initialized
        if (map.get(key) == null) {
            map.put(key, new HashSet<>());
        }

        // check if it has been encountered before in that encountered map (row, col, subbox)
        // if it has, that means duplicate, which means invalid board, return false
        if (map.get(key).contains(value)) {
            // invalid because duplicate was encountered
            return false;
        }
        // add encountered value to the map
        map.get(key).add(value);

        // if it passed all checks return true
        return true;
    }

    // TODO math here to make it generic for other types of boards, hardcode to 9X9 right now
    private Integer getSubBoxIndex(int row, int col, int[][] subBoxIndices) {
        return subBoxIndices[row / SUB_BOX_HEIGHT][col / SUB_BOX_WIDTH];
    }

    private Integer getNumericalValue(char c) {
        if (c == '.') {
            return 0;
        } else {
            return c - '0';
        }
    }
}
