package json;

/**
 * Simple POJO used by the Jackson examples.
 *
 * Requirements for Jackson to work:
 *   - a public no-arg constructor (used during deserialization)
 *   - public getters/setters for every field that should be exposed in JSON
 */
public class Student {

    private String name;
    private int age;
    private double average;
    private String major;

    // No-arg constructor required by Jackson for deserialization.
    public Student() {
    }

    public Student(String name, int age, double average, String major) {
        this.name = name;
        this.age = age;
        this.average = average;
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age
                + ", average=" + average + ", major='" + major + "'}";
    }
}
