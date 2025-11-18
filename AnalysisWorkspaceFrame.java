package CSI_Project;

import javax.swing.*;
import java.awt.*;

/**
 * Modern analysis workspace combining suspects, evidence, and results.
 */
public class AnalysisWorkspaceFrame extends JFrame {

    private SuspectListPanel suspectPanel;
    private EvidenceBoardPanel evidencePanel;
    private AnalysisPanel analysisPanel;

    public AnalysisWorkspaceFrame() {
        setTitle("Case Analysis Workspace");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        UIStyle.styleFrame(this);

        setLayout(new BorderLayout());

        // Panels
        suspectPanel = new SuspectListPanel();
        evidencePanel = new EvidenceBoardPanel();
        analysisPanel = new AnalysisPanel(suspectPanel, evidencePanel);

        // Add to window
        add(suspectPanel, BorderLayout.WEST);
        add(evidencePanel, BorderLayout.CENTER);
        add(analysisPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
