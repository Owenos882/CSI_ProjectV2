package CSI_Project;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CaseAnalyzerFrame extends JFrame {

    private JComboBox<String> suspectCombo;
    private JComboBox<String> evidenceCombo;
    private JLabel matchLabel;
    private JButton convictBtn;

    private int matchCount = 0;

    public CaseAnalyzerFrame(JFrame parent) {
        setTitle("Case Analyzer");
        setSize(600, 400);
        UIStyle.styleFrame(this);

        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(UIStyle.BG);

        JPanel top = UIStyle.createCardPanel();
        top.setBorder(BorderFactory.createEmptyBorder(16,16,16,16));
        JLabel title = UIStyle.createTitleLabel("Analyze Evidence Against Suspect");
        top.add(title, BorderLayout.CENTER);

        // ----- CENTER PANEL -----
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(UIStyle.BG);

        // Suspect dropdown
        suspectCombo = new JComboBox<>();
        for (Suspect s : DataLoader.getSuspects()) {
            suspectCombo.addItem(s.getName());
        }

        // Evidence dropdown
        evidenceCombo = new JComboBox<>();
        for (Evidence e : DataLoader.getEvidence()) {
            evidenceCombo.addItem(e.getId() + " - " + e.getType());
        }

        JButton checkBtn = UIStyle.createButton("Check Evidence");
        convictBtn = UIStyle.createButton("CONVICT");
        convictBtn.setEnabled(false);

        matchLabel = UIStyle.createSubtitleLabel("Matches: 0 / 3");

        // Layout
        center.add(new JLabel("Select Suspect:"));
        center.add(suspectCombo);
        center.add(Box.createVerticalStrut(10));

        center.add(new JLabel("Select Evidence:"));
        center.add(evidenceCombo);
        center.add(Box.createVerticalStrut(10));

        center.add(checkBtn);
        center.add(Box.createVerticalStrut(10));
        center.add(matchLabel);
        center.add(Box.createVerticalStrut(10));
        center.add(convictBtn);

        // ----- BUTTON ACTIONS -----
        checkBtn.addActionListener(e -> checkEvidence());
        convictBtn.addActionListener(e -> convictSuspect());

        main.add(top, BorderLayout.NORTH);
        main.add(UIStyle.wrapWithPadding(center, 16, 16, 16, 16), BorderLayout.CENTER);

        setContentPane(main);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void checkEvidence() {
        List<Suspect> suspects = DataLoader.getSuspects();
        List<Evidence> evidenceList = DataLoader.getEvidence();

        int sIndex = suspectCombo.getSelectedIndex();
        int eIndex = evidenceCombo.getSelectedIndex();

        Suspect s = suspects.get(sIndex);
        Evidence ev = evidenceList.get(eIndex);

        boolean match = CaseAnalyzer.evidenceMatches(s, ev);

        if (match) {
            matchCount++;
            JOptionPane.showMessageDialog(this,
                    "MATCH! This evidence supports this suspect.",
                    "Match",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "NO MATCH. This evidence does not implicate the suspect.",
                    "No Match",
                    JOptionPane.WARNING_MESSAGE);
        }

        matchLabel.setText("Matches: " + matchCount + " / 3");

        if (matchCount >= 3) {
            convictBtn.setEnabled(true);
        }
    }

    private void convictSuspect() {
        int sIndex = suspectCombo.getSelectedIndex();
        Suspect s = DataLoader.getSuspects().get(sIndex);

        if (s.isGuilty()) {
            JOptionPane.showMessageDialog(this,
                    "Correct! " + s.getName() + " WAS the true culprit.",
                    "Case Closed",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Incorrect. " + s.getName() + " was INNOCENT.",
                    "Wrong Conviction",
                    JOptionPane.ERROR_MESSAGE);
        }

        this.dispose();
    }
}
