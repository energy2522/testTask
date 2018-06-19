package task.module;


import org.jsoup.nodes.Element;


public class ElementTimes implements Comparable{
    private final Element element;
    private int times;

    public ElementTimes(Element element) {
        this.element = element;
        this.times = 0;
    }

    public void increment() {
        times++;
    }

    public Element getElement() {
        return element;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ElementTimes) {
            ElementTimes elementTimes = (ElementTimes) o;

            return elementTimes.element.equals(this.element) && elementTimes.times == this.times;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int compareTo(Object o) {
        ElementTimes elementTimes = (ElementTimes) o;

        return elementTimes.times - this.times;
    }
}
