package task.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import task.exception.NotElementExistingException;
import task.module.ElementTimes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class HtmlParser {
    private static final String ENCODING = "UTF-8";
    private final File ORIGIN_FILE;
    private final File OTHER_FILE;
    private List<ElementTimes> otherPageElements = new ArrayList<>();
    private List<Attribute> listWithAttributes;

    public HtmlParser(String ORIGIN_FILE, String OTHER_FILE) {
        this.ORIGIN_FILE = new File(ORIGIN_FILE);
        this.OTHER_FILE = new File(OTHER_FILE);
    }

    public void setElementsById(final String id) throws IOException, NotElementExistingException {
        Document html = Jsoup.parse(ORIGIN_FILE, ENCODING, ORIGIN_FILE.getAbsolutePath());

        Optional<Element> element = Optional.of(html.getElementById(id));

        listWithAttributes = element.orElseThrow(() -> new NotElementExistingException("Element with id " + id))
                .attributes().asList();
    }

    public void setOtherPageElements() throws IOException {
        Document html = Jsoup.parse(OTHER_FILE, ENCODING, OTHER_FILE.getAbsolutePath());

        html.getAllElements().stream().forEach(e -> otherPageElements.add(new ElementTimes(e)));
    }

    public void setCountTimes() {
        otherPageElements.stream().forEach(e -> checkAttr(e));

        Collections.sort(otherPageElements);
    }

    private void checkAttr(ElementTimes elementTimes) {
        listWithAttributes.stream().forEach(e -> {
            String attr = e.getKey();
            String val = e.getValue();

            hasAttr(attr, val, elementTimes);
        });
    }

    private void hasAttr(String attr, String val, ElementTimes elementTimes) {
        Element el = elementTimes.getElement();

        if (el.hasAttr(attr) && el.attr(attr).equals(val)) {
            elementTimes.increment();
        }
    }

    public void printResult() {
        ElementTimes elementTimes = otherPageElements.get(0);
        Element parent = elementTimes.getElement();

        StringBuilder output = new StringBuilder();

        while (parent != null) {
            if (output.length() != 0) {
                output.insert(0, parent.nodeName() + " > ");
            } else {
                output.insert(0, parent.nodeName());
            }

            parent = parent.parent();
        }

        System.out.println(elementTimes.getElement());
        System.out.println(output);
    }


}
