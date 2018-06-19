package task;

import task.exception.NotElementExistingException;
import task.parsers.HtmlParser;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, NotElementExistingException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Please set id, origin path and other path");
        }

        HtmlParser htmlParser = new HtmlParser(args[1], args[2]);

        htmlParser.setElementsById(args[0]);
        htmlParser.setOtherPageElements();

        htmlParser.setCountTimes();

        htmlParser.printResult();
    }
}
