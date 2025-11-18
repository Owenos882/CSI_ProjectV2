package CSI_Project;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataLoader {

    private static final List<Suspect> suspects = new ArrayList<>();
    private static final List<Evidence> evidence = new ArrayList<>();

    public static List<Suspect> getSuspects() { return suspects; }
    public static List<Evidence> getEvidence() { return evidence; }

    public static void loadSuspectsWithChooser(JFrame parent) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select Suspects CSV File");

        if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {

            suspects.clear();
            Path filePath = chooser.getSelectedFile().toPath();

            try {
                Scanner sc = new Scanner(filePath);
                if (sc.hasNextLine()) sc.nextLine();

                while (sc.hasNextLine()) {
                    String[] p = sc.nextLine().split(",");
                    if (p.length < 4) continue;

                    suspects.add(new Suspect(
                            p[0].trim(),
                            Integer.parseInt(p[1].trim()),
                            p[2].trim(),
                            p[3].trim()
                    ));
                }
                sc.close();

                JOptionPane.showMessageDialog(parent,
                        "Loaded " + suspects.size() + " suspects.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(parent, ex.getMessage());
            }
        }
    }

    public static void loadEvidenceWithChooser(JFrame parent) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select Evidence CSV File");

        if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {

            evidence.clear();
            Path filePath = chooser.getSelectedFile().toPath();

            try {
                Scanner sc = new Scanner(filePath);
                if (sc.hasNextLine()) sc.nextLine();

                while (sc.hasNextLine()) {
                    String[] p = sc.nextLine().split(",");
                    if (p.length < 5) continue;

                    evidence.add(new Evidence(
                            p[0].trim(),
                            p[1].trim(),
                            p[2].trim(),
                            p[3].trim(),
                            Double.parseDouble(p[4].trim())
                    ));
                }
                sc.close();

                JOptionPane.showMessageDialog(parent,
                        "Loaded " + evidence.size() + " items.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(parent, ex.getMessage());
            }
        }
    }
}
