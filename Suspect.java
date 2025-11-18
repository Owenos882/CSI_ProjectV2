package CSI_Project;

public class Suspect extends Person implements Analyzable {

    private int age;
    private String alibi;
    private String type;
    private boolean guilty;

    public Suspect(String name, int age, String alibi, String type) {
        super(name);
        this.age = age;
        this.alibi = alibi;
        this.type = type;
        this.guilty = false;
    }

    public int getAge() { return age; }
    public String getAlibi() { return alibi; }
    public String getType() { return type; }

    public void markAsGuilty() { this.guilty = true; }
    public void markAsInnocent() { this.guilty = false; }
    public boolean isGuilty() { return guilty; }

    @Override
    public String getSummary() {
        return "Suspect: " + name +
                " (" + type + "), Age " + age +
                ", Alibi: " + alibi +
                (guilty ? " [GUILTY]" : "");
    }

    @Override
    public double getWeight() {
        double base = 10.0;
        if ("Known Criminal".equalsIgnoreCase(type)) base += 20;
        else if ("External".equalsIgnoreCase(type)) base += 10;
        return base;
    }
}
