package design_patterns.template_method;

/*
 * Template Method Pattern
 * -----------------------
 * The base class defines the SKELETON of an algorithm and lets
 * subclasses fill in the variable steps.
 *
 *   DataProcessor.process()  // final — fixed order
 *      ├── readData()        // abstract — subclass decides
 *      ├── processData()     // abstract — subclass decides
 *      └── saveData()        // abstract — subclass decides
 *
 * When to use:
 *   - The high-level steps are the same, only details differ
 *     (parsers, ETL pipelines, test runners, request handlers).
 */
public class TemplateMethodExample {

    public static void main(String[] args) {
        DataProcessor csv  = new CsvProcessor();
        DataProcessor json = new JsonProcessor();

        csv.process();
        System.out.println("---");
        json.process();
    }
}

abstract class DataProcessor {

    // Template method — fixed order, can't be overridden
    public final void process() {
        readData();
        processData();
        saveData();
    }

    protected abstract void readData();
    protected abstract void processData();
    protected abstract void saveData();
}

class CsvProcessor extends DataProcessor {
    protected void readData()    { System.out.println("Reading CSV file"); }
    protected void processData() { System.out.println("Parsing CSV rows"); }
    protected void saveData()    { System.out.println("Saving CSV result"); }
}

class JsonProcessor extends DataProcessor {
    protected void readData()    { System.out.println("Reading JSON file"); }
    protected void processData() { System.out.println("Parsing JSON tree"); }
    protected void saveData()    { System.out.println("Saving JSON result"); }
}
