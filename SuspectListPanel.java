package CSI_Project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SuspectListPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    public SuspectListPanel() {
        setLayout(new BorderLayout());
        setBackground(UIStyle.CARD_BG);
        setBorder(BorderFactory.createTitledBorder("Suspects"));

        model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Type");

        table = new JTable(model);
        UIStyle.styleTable(table);

        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        reloadTable();
    }

    public void reloadTable() {
        model.setRowCount(0);
        for (Suspect s : DataLoader.getSuspects()) {
            model.addRow(new Object[]{s.getName(), s.getType()});
        }
    }

    public Suspect getSelectedSuspect() {
        int row = table.getSelectedRow();
        if (row == -1) return null;
        return DataLoader.getSuspects().get(row);
    }
}
