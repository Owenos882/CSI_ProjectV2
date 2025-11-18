package CSI_Project;

import javax.swing.*;
import java.awt.*;

public class EvidenceInspectorFrame extends JFrame {

    public EvidenceInspectorFrame(EvidenceFrame parent, Evidence evidence) {
        setTitle("Evidence Details");
        setSize(400, 300);
        UIStyle.styleFrame(this);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(UIStyle.TEXT_FONT);
        area.setText(evidence.getSummary());

        JScrollPane scroll = new JScrollPane(area);

        JButton btnSendNote = UIStyle.createButton("Send Note to Evidence Window");
        JButton btnClose = UIStyle.createSecondaryButton("Close");

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.setBackground(UIStyle.BG);
        buttons.add(btnSendNote);
        buttons.add(btnClose);

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(UIStyle.BG);
        content.add(UIStyle.wrapWithPadding(scroll, 16, 16, 8, 16), BorderLayout.CENTER);
        content.add(UIStyle.wrapWithPadding(buttons, 0, 16, 16, 16), BorderLayout.SOUTH);

        setContentPane(content);

        btnSendNote.addActionListener(e -> {
            parent.appendLog("Inspector note: Reviewed evidence " + evidence.getId());
        });

        btnClose.addActionListener(e -> dispose());

        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
