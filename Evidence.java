package CSI_Project;

/**
 * Evidence model class.
 * Now includes a "pointsToGuilty" flag so only certain evidence matches the true guilty suspect.
 */
public class Evidence implements Analyzable {

    private String id;
    private String type;
    private String description;
    private String location;
    private double relevance; // 0-1 from your CSV

    private boolean pointsToGuilty = false; // NEW matching system

    public Evidence(String id, String type, String description, String location, double relevance) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.location = location;
        this.relevance = relevance;
    }

    public String getId() { return id; }
    public String getType() { return type; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public double getRelevance() { return relevance; }

    @Override
    public double getWeight() {
        return relevance;
    }

    // -------------------------------
    // New Guilty Match System
    // -------------------------------

    public boolean pointsToGuilty() {
        return pointsToGuilty;
    }

    public void markAsGuiltyEvidence() {
        this.pointsToGuilty = true;
    }

    public String getSummary() {
        return "Evidence " + id + " (" + type + ") at " + location +
                " [Relevance: " + relevance + "]" +
                (pointsToGuilty ? " **MATCHES GUILTY**" : "");
    }
}
