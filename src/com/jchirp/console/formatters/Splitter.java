package com.jchirp.console.formatters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Splitter {
    /*
     * Throws IllegalArgumentException when separator is empty.
     */
    public Output splitUserNameFromContent(String separator, String input) {

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
        private String userName = "";
        private String content = "";

        public Output(String userName, String content) {
            this.userName = userName;
            this.content = content;
        }

        public String getUserName() {
            return userName;
        }

        public String getContent() {
            return content;
        }
    }

}
