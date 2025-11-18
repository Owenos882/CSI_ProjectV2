package CSI_Project;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {
        setTitle("CSI Investigator Dashboard");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UIStyle.styleFrame(this);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UIStyle.BG);

        JPanel header = UIStyle.createCardPanel();
        header.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        JLabel title = UIStyle.createTitleLabel("Crime Scene Investigation");
        JLabel subtitle = UIStyle.createSubtitleLabel("Select an area to work on:");
        JPanel headerText = new JPanel();
        headerText.setBackground(UIStyle.CARD_BG);
        headerText.setLayout(new BoxLayout(headerText, BoxLayout.Y_AXIS));
        headerText.add(title);
        headerText.add(Box.createVerticalStrut(4));
        headerText.add(subtitle);
        header.add(headerText, BorderLayout.CENTER);

        JPanel center = new JPanel(new GridLayout(2, 2, 16, 16));
        center.setBackground(UIStyle.BG);
        center.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JButton btnSuspects = UIStyle.createButton("Manage Suspects");
        JButton btnEvidence = UIStyle.createButton("Manage Evidence");
        JButton btnAnalyze = UIStyle.createButton("Analyze Case");
        JButton btnExit = UIStyle.createSecondaryButton("Exit");

        center.add(btnSuspects);
        center.add(btnEvidence);
        center.add(btnAnalyze);
        center.add(btnExit);

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(center, BorderLayout.CENTER);

        setContentPane(mainPanel);

        btnSuspects.addActionListener(e -> new SuspectFrame(this));
        btnEvidence.addActionListener(e -> new EvidenceFrame(this));
        btnAnalyze.addActionListener(e -> new CaseAnalysisWorkspace());
        btnExit.addActionListener(e -> System.exit(0));

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
