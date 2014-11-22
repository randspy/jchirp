package com.jchirp.test.console.utils;

import com.jchirp.console.utils.Splitter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class SplitterTest {

    public static final String SEPARATOR = "->";
    private Splitter splitter;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private void assertSplit(String input, String user, String content) {
        Splitter.Output output = splitter.splitUserNameFromContent(SEPARATOR, input);
        assertEquals(user, output.getUserName());
        assertEquals(content, output.getContent());
    }

    @Before
    public void setUp(){
        splitter = new Splitter();
    }

    @Test
    public void whenInputIsEmptyReturnsEmptyValues(){
        assertSplit("", "", "");
    }

    @Test
    public void whenSeparatorEmptyThrow(){
        exception.expect(IllegalArgumentException.class);
        splitter.splitUserNameFromContent("", "");
    }

    @Test
    public void  whenInputAsExpectedReturnValues(){
        assertSplit("USER->CONTENT", "USER", "CONTENT");
    }

    @Test
    public void whenNoContentReturnsNothing(){
        assertSplit("USER->", "USER", "");
        }

    @Test
    public void whenNoUserReturnsNothing(){
        assertSplit("->CONTENT", "", "CONTENT");
    }

    @Test
    public void whenNoUserAndContentReturnsNothing(){
        assertSplit("->", "", "");
    }

    @Test
    public void whiteSpacesDoesNotMatter(){
        assertSplit(" USER -> CONTENT ", "USER", "CONTENT");
    }

    @Test
    public void whenSeparatorPresentMoreThanOnceSplitOnlyWithFirtsOccurance(){
        assertSplit("USER -> CONTENT -> MORE CONTENT", "USER", "CONTENT -> MORE CONTENT");
    }
}