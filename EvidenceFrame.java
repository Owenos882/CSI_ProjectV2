package CSI_Project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EvidenceFrame extends JFrame {

    private DefaultTableModel model;
    private JTable table;
    private JTextArea logArea;

    public EvidenceFrame(JFrame parent) {
        setTitle("Evidence");
        setSize(800, 500);
        UIStyle.styleFrame(this);

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Type");
        model.addColumn("Location");
        model.addColumn("Relevance");

        table = new JTable(model);
        UIStyle.styleTable(table);

        JScrollPane tableScroll = new JScrollPane(table);

        JButton btnLoad = UIStyle.createButton("Load Evidence CSV");
        JButton btnInspect = UIStyle.createSecondaryButton("Inspect Selected");
        JButton btnClose = UIStyle.createSecondaryButton("Close");

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.setBackground(UIStyle.BG);
        topBar.add(btnLoad);
        topBar.add(btnInspect);
        topBar.add(btnClose);

        logArea = new JTextArea(4, 40);
        logArea.setEditable(false);
        logArea.setFont(UIStyle.TEXT_FONT);
        JScrollPane logScroll = new JScrollPane(logArea);

        JPanel bottom = UIStyle.wrapWithPadding(logScroll, 8, 16, 16, 16);

        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(UIStyle.BG);
        center.add(tableScroll, BorderLayout.CENTER);

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(UIStyle.BG);
        content.add(topBar, BorderLayout.NORTH);
        content.add(center, BorderLayout.CENTER);
        content.add(bottom, BorderLayout.SOUTH);

        setContentPane(content);

        btnLoad.addActionListener(e -> {
            DataLoader.loadEvidenceWithChooser(this);
            refreshTable();
        });

        btnInspect.addActionListener(e -> openInspector());
        btnClose.addActionListener(e -> dispose());

        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void refreshTable() {
        List<Evidence> evidenceList = DataLoader.getEvidence();
        model.setRowCount(0);
        for (Evidence ev : evidenceList) {
            model.addRow(new Object[]{
                    ev.getId(),
                    ev.getType(),
                    ev.getLocation(),
                    ev.getRelevance()
            });
        }
        logArea.append("Loaded " + evidenceList.size() + " evidence items.\n");
    }

    private void openInspector() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select an evidence item first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        Evidence ev = DataLoader.getEvidence().get(row);
        new EvidenceInspectorFrame(this, ev);
    }

    public void appendLog(String msg) {
        logArea.append(msg + "\n");
    }
}
