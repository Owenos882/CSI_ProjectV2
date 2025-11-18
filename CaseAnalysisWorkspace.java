package CSI_Project;

import javax.swing.*;
import java.awt.*;

public class CaseAnalysisWorkspace extends JFrame {

    public CaseAnalysisWorkspace() {
        setTitle("Case Analysis Workspace");
        setSize(1200, 700);
        UIStyle.styleFrame(this);

        // panels
        SuspectListPanel suspectsPanel = new SuspectListPanel();
        EvidenceBoardPanel evidencePanel = new EvidenceBoardPanel();
        AnalysisPanel analysisPanel = new AnalysisPanel(suspectsPanel, evidencePanel);

        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(UIStyle.BG);

        JPanel centerSplit = new JPanel(new GridLayout(1, 2));
        centerSplit.add(suspectsPanel);
        centerSplit.add(evidencePanel);

        main.add(centerSplit, BorderLayout.CENTER);
        main.add(analysisPanel, BorderLayout.SOUTH);

        setContentPane(main);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
