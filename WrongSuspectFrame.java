package CSI_Project;

import javax.swing.*;
import java.awt.*;

public class WrongSuspectFrame extends JFrame {

    public WrongSuspectFrame(Suspect s) {
        setTitle("Incorrect Conviction");
        setSize(400, 250);
        UIStyle.styleFrame(this);

        JLabel emoji = new JLabel("✖");
        emoji.setFont(new Font("Segoe UI", Font.BOLD, 90));
        emoji.setForeground(Color.RED);
        emoji.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel title = UIStyle.createTitleLabel("Wrong Suspect!");
        JLabel msg = UIStyle.createSubtitleLabel(s.getName() + " was innocent.");

        JButton close = UIStyle.createButton("Close");
        close.addActionListener(e -> dispose());

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(UIStyle.CARD_BG);
        panel.add(emoji, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(2, 1));
        center.setBackground(UIStyle.CARD_BG);
        center.add(title);
        center.add(msg);

        panel.add(center, BorderLayout.CENTER);
        panel.add(close, BorderLayout.SOUTH);

        setContentPane(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
