package task;

import task.exception.NotElementExistingException;
import task.parsers.HtmlParser;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, NotElementExistingException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Please set origin path and other path");
        }

        HtmlParser htmlParser = new HtmlParser(args[0], args[1]);

        htmlParser.setButtonElements();
        htmlParser.setOtherPageElements();

        htmlParser.setCountTimes();
    }
}
