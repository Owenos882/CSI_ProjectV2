package CSI_Project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SuspectFrame extends JFrame {

    private DefaultTableModel model;
    private JTable table;
    private JTextArea logArea;

    public SuspectFrame(JFrame parent) {
        setTitle("Suspects");
        setSize(800, 500);
        UIStyle.styleFrame(this);

        model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Age");
        model.addColumn("Type");
        model.addColumn("Alibi");

        table = new JTable(model);
        UIStyle.styleTable(table);

        JScrollPane tableScroll = new JScrollPane(table);

        JButton btnLoad = UIStyle.createButton("Load Suspects CSV");
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
            DataLoader.loadSuspectsWithChooser(this);
            refreshTable();
        });

        btnInspect.addActionListener(e -> openInspector());
        btnClose.addActionListener(e -> dispose());

        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void refreshTable() {
        List<Suspect> suspects = DataLoader.getSuspects();
        model.setRowCount(0);
        for (Suspect s : suspects) {
            model.addRow(new Object[]{
                    s.getName(),
                    s.getAge(),
                    s.getType(),
                    s.getAlibi()
            });
        }
        logArea.append("Loaded " + suspects.size() + " suspects.\n");
    }

    private void openInspector() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a suspect first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        Suspect s = DataLoader.getSuspects().get(row);
        new SuspectInspectorFrame(this, s);
    }

    public void appendLog(String msg) {
        logArea.append(msg + "\n");
    }
}
