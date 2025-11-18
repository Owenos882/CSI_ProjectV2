package CSI_Project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UIStyle {

    public static final Color BG = new Color(245, 246, 250);
    public static final Color CARD_BG = Color.WHITE;
    public static final Color ACCENT = new Color(0, 120, 215);
    public static final Color ACCENT_SOFT = new Color(229, 241, 251);
    public static final Color BORDER_SOFT = new Color(220, 224, 235);
    public static final Color TEXT_MAIN = new Color(30, 30, 30);
    public static final Color TEXT_MUTED = new Color(90, 90, 90);

    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);
    public static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.PLAIN, 18);
    public static final Font TEXT_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    public static void styleFrame(JFrame frame) {
        frame.getContentPane().setBackground(BG);
        frame.setLocationRelativeTo(null);
    }

    public static JPanel createCardPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(CARD_BG);
        panel.setBorder(new LineBorder(BORDER_SOFT, 1, true));
        panel.setLayout(new BorderLayout());
        return panel;
    }

    public static JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(TEXT_FONT);
        btn.setFocusPainted(false);
        btn.setBackground(ACCENT);
        btn.setForeground(Color.WHITE);
        btn.setBorder(new LineBorder(ACCENT, 1, true));
        btn.setOpaque(true);
        return btn;
    }

    public static JButton createSecondaryButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(TEXT_FONT);
        btn.setFocusPainted(false);
        btn.setBackground(CARD_BG);
        btn.setForeground(TEXT_MAIN);
        btn.setBorder(new LineBorder(BORDER_SOFT, 1, true));
        btn.setOpaque(true);
        return btn;
    }

    public static JLabel createTitleLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(TITLE_FONT);
        label.setForeground(TEXT_MAIN);
        return label;
    }

    public static JLabel createSubtitleLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(SUBTITLE_FONT);
        label.setForeground(TEXT_MUTED);
        return label;
    }

    public static void styleTable(JTable table) {
        table.setFont(TEXT_FONT);
        table.getTableHeader().setFont(TEXT_FONT.deriveFont(Font.BOLD));
        table.setRowHeight(24);
        table.setGridColor(BORDER_SOFT);
        table.setSelectionBackground(ACCENT_SOFT);
        table.setSelectionForeground(TEXT_MAIN);
    }

    public static JPanel wrapWithPadding(Component comp, int top, int left, int bottom, int right) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(BG);
        wrapper.setBorder(new EmptyBorder(top, left, bottom, right));
        wrapper.add(comp, BorderLayout.CENTER);
        return wrapper;
    }
}
