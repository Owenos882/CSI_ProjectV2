package CSI_Project;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Shows the final result of an analysis and can save it to a CSV file.
 */
public class CaseResultsFrame extends JFrame {

    public CaseResultsFrame(JFrame parent, Suspect suspect, double score) {
        setTitle("Case Results");
        setSize(450, 300);
        UIStyle.styleFrame(this);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(UIStyle.TEXT_FONT);

        String summary = "Case Result:\n" +
                "Suspect: " + suspect.getName() + "\n" +
                "Type: " + suspect.getType() + "\n" +
                "Score: " + String.format("%.1f", score) + " / 100\n";

        area.setText(summary);

        JScrollPane scroll = new JScrollPane(area);

        JButton btnSave = UIStyle.createButton("Save Result to CSV");
        JButton btnClose = UIStyle.createSecondaryButton("Close");

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.setBackground(UIStyle.BG);
        buttons.add(btnSave);
        buttons.add(btnClose);

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(UIStyle.BG);
        content.add(UIStyle.wrapWithPadding(scroll, 16, 16, 8, 16), BorderLayout.CENTER);
        content.add(UIStyle.wrapWithPadding(buttons, 0, 16, 16, 16), BorderLayout.SOUTH);

        setContentPane(content);

        btnSave.addActionListener(e -> saveResult(summary));
        btnClose.addActionListener(e -> dispose());

        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void saveResult(String text) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save Result CSV");
        int result = chooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                PrintWriter out = new PrintWriter(chooser.getSelectedFile());
                // Simple CSV with one line
                out.println("Suspect,Score");
                String[] lines = text.split("\\n");
                String suspectName = "";
                String scoreStr = "";
                for (String line : lines) {
                    if (line.startsWith("Suspect:")) {
                        suspectName = line.substring("Suspect:".length()).trim();
                    } else if (line.startsWith("Score:")) {
                        scoreStr = line.substring("Score:".length()).trim();
                    }
                }
                out.println(suspectName + "," + scoreStr);
                out.close();
                JOptionPane.showMessageDialog(this,
                        "Result saved successfully.",
                        "Saved",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Error saving file: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
