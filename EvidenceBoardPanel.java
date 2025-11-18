package CSI_Project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EvidenceBoardPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    public EvidenceBoardPanel() {
        setLayout(new BorderLayout());
        setBackground(UIStyle.CARD_BG);
        setBorder(BorderFactory.createTitledBorder("Evidence Board"));

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Type");
        model.addColumn("Location");
        model.addColumn("Relevance");

        table = new JTable(model);
        UIStyle.styleTable(table);

        add(new JScrollPane(table), BorderLayout.CENTER);

        refresh();
    }

    public void refresh() {
        model.setRowCount(0);
        List<Evidence> list = DataLoader.getEvidence();
        for (Evidence e : list) {
            model.addRow(new Object[]{
                    e.getId(),
                    e.getType(),
                    e.getLocation(),
                    e.getRelevance()
            });
        }
    }

    public Evidence getSelectedEvidence() {
        int row = table.getSelectedRow();
        if (row == -1) return null;
        return DataLoader.getEvidence().get(row);
    }
}
