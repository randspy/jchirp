package com.jchirp.console.formatters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Splitter {
    /*
     * Throws IllegalArgumentException when separator is empty.
     */
    public Output splitIntoTwoValues(String separator, String input) {

        if(separator.isEmpty()) {
            throw new IllegalArgumentException("Separator is empty.");
        }

        List<String> split = splitBySeparator(separator, input);

        return new Output(split.get(0).trim(), split.get(1).trim());
    }

    private List<String> splitBySeparator(String separator, String input) {
        int expectedNumberOfElementsAfterSplit = 2;
        List<String> split =
                new ArrayList<String>(Arrays.asList(
                        input.split(separator,
                                    expectedNumberOfElementsAfterSplit)));

        for(int idx = split.size(); idx < expectedNumberOfElementsAfterSplit; ++idx) {
            split.add("");
        }

        return split;
    }

    public class Output{
        private String elementBeforeSplitToken = "";
        private String elementAfterSplitToken = "";

        public Output(String elementBeforeSplitToken, String elementAfterSplitToken) {
            this.elementBeforeSplitToken = elementBeforeSplitToken;
            this.elementAfterSplitToken = elementAfterSplitToken;
        }

        public String getElementBeforeSplitToken() {
            return elementBeforeSplitToken;
        }

        public String getElementsAfterSplitToken() {
            return elementAfterSplitToken;
        }
    }

}
