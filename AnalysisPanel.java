package CSI_Project;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AnalysisPanel extends JPanel {

    private SuspectListPanel suspectPanel;
    private EvidenceBoardPanel evidencePanel;

    private JLabel matchLabel;
    private JLabel confidenceLabel;

    private JButton analyzeBtn;
    private JButton convictBtn;

    private Suspect guiltySuspect;

    public AnalysisPanel(SuspectListPanel suspectPanel, EvidenceBoardPanel evidencePanel) {
        this.suspectPanel = suspectPanel;
        this.evidencePanel = evidencePanel;

        setLayout(new BorderLayout());
        setBackground(UIStyle.CARD_BG);
        setBorder(BorderFactory.createTitledBorder("Analysis Results"));

        matchLabel = UIStyle.createSubtitleLabel("Matches: 0");
        confidenceLabel = UIStyle.createSubtitleLabel("Confidence: 0%");

        analyzeBtn = UIStyle.createButton("Analyze Selected");
        convictBtn = UIStyle.createButton("Convict Suspect");
        convictBtn.setEnabled(false);

        analyzeBtn.addActionListener(e -> analyzeSuspect());
        convictBtn.addActionListener(e -> convict());

        JPanel top = new JPanel();
        top.setBackground(UIStyle.CARD_BG);
        top.add(analyzeBtn);
        top.add(convictBtn);

        JPanel info = new JPanel(new GridLayout(2, 1));
        info.setBackground(UIStyle.CARD_BG);
        info.add(matchLabel);
        info.add(confidenceLabel);

        add(top, BorderLayout.NORTH);
        add(info, BorderLayout.CENTER);

        // Pick guilty suspect at random
        List<Suspect> suspects = DataLoader.getSuspects();
        if (!suspects.isEmpty()) {
            int idx = (int)(Math.random() * suspects.size());
            guiltySuspect = suspects.get(idx);
            guiltySuspect.markAsGuilty();

            // Give them EXACTLY 3 evidence types
            CaseAnalyzer.assignGuiltyMatches(guiltySuspect, DataLoader.getEvidence());
        }
    }

    private void analyzeSuspect() {
        Suspect s = suspectPanel.getSelectedSuspect();
        if (s == null) {
            JOptionPane.showMessageDialog(this, "Select a suspect first.");
            return;
        }

        int matches = CaseAnalyzer.countMatchesForSuspect(
                s,
                DataLoader.getEvidence()
        );

        matchLabel.setText("Matches: " + matches);

        int confidence = (matches * 100) / 3;
        confidenceLabel.setText("Confidence: " + confidence + "%");

        convictBtn.setEnabled(matches == 3);
    }

    private void convict() {
        Suspect s = suspectPanel.getSelectedSuspect();
        if (s == null) return;

        if (s.isGuilty()) {
            new GuiltyFrame(s);
        } else {
            new WrongSuspectFrame(s);
        }
    }
}
