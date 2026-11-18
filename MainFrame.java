import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    private DataStore dataStore;

    // 1. Updated constants for the 3-file system
    private static final String CUST_FILE = "customers.dat";
    private static final String ROOM_FILE = "rooms.dat";
    private static final String RES_FILE = "reservations.dat";

    private Image backgroundImage;

    public MainFrame() {
        super("Hotel Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        // Load Background Image
        // Load Background Image
        ImageIcon icon = new ImageIcon("hotel_sketch.png");

        System.out.println("Width = " + icon.getIconWidth());

        backgroundImage = icon.getImage();
        BackgroundPanel bgPanel = new BackgroundPanel();
        bgPanel.setLayout(null);
        setContentPane(bgPanel);

        // 2. FIXED: Load logic for three sequential access files
        dataStore = new DataStore(); // Initialize first
        try {
            dataStore.loadAll(CUST_FILE, ROOM_FILE, RES_FILE);
        } catch (Exception ex) {
            // No action needed; starts with empty lists if files don't exist yet
            System.out.println("Starting with fresh data store.");
        }

        // Animated Title Label (Formatting preserved)
        JLabel title = new JLabel("Hotel Reservation System") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(Color.BLACK); // Shadow
                g2.drawString(getText(), 3, 33);

                g2.setColor(Color.WHITE); // Main text
                g2.drawString(getText(), 0, 30);
            }
        };
        title.setFont(new Font("Segoe UI", Font.BOLD, 36));
        title.setSize(title.getPreferredSize());

        final int startY = -50;
        final int targetY = 20;
        title.setLocation((getWidth() - title.getWidth()) / 2, startY);
        bgPanel.add(title);

        // Title Animation
        Timer titleTimer = new Timer(15, null);
        final int[] step = { 0 };
        titleTimer.addActionListener(e -> {
            int y = Math.min(startY + step[0], targetY);
            title.setLocation((getWidth() - title.getWidth()) / 2, y);
            float ratio = (float) ((Math.sin(step[0] * 0.05) + 1) / 2);
            int r = (int) (255 * (1 - ratio) + 212 * ratio);
            int g = (int) (255 * (1 - ratio) + 175 * ratio);
            int b = (int) (255 * (1 - ratio) + 55 * ratio);
            title.setForeground(new Color(r, g, b));
            step[0] += 2;
        });
        titleTimer.start();

        // Buttons Panel
        JPanel btnPanel = new JPanel(new GridLayout(1, 3, 30, 0));
        btnPanel.setOpaque(false);
        Color goldColor = new Color(101, 67, 33);
        btnPanel.add(createAnimatedButton(
                "Manage Customers",
                goldColor,
                "customer_icon.png",
                () -> new CustomerFrame(dataStore)));

        btnPanel.add(createAnimatedButton(
                "Manage Rooms",
                goldColor,
                "room_icon.png",
                () -> new RoomFrame(dataStore)));

        btnPanel.add(createAnimatedButton(
                "Manage Reservations",
                goldColor,
                "reservation_icon.png",
                () -> new ReservationFrame(dataStore)));

        btnPanel.setBounds((getWidth() - 800) / 2, 320, 800, 150);
        bgPanel.add(btnPanel);
        addComponentListener(new java.awt.event.ComponentAdapter() {
    @Override
    public void componentResized(java.awt.event.ComponentEvent e) {

        btnPanel.setBounds(
                (getWidth() - 800) / 2, // center horizontally
                320,
                800,
                150);

        
    }
});

        // 3. FIXED: Save All Button for three sequential files
        JButton btnSave = createAnimatedButton("Save All", goldColor, null, () -> {
    try {
        dataStore.saveAll(CUST_FILE, ROOM_FILE, RES_FILE);
        JOptionPane.showMessageDialog(this, "All data saved to separate files.", "Success",
                JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error saving: " + ex.getMessage(), "Error",
                JOptionPane.ERROR_MESSAGE);
    }
});
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnSave.setBounds((getWidth() - 160) / 2, 500, 160, 50);
        bgPanel.add(btnSave);

        setVisible(true);

        // Fade-in effect
        setOpacity(0f);
        Timer fadeTimer = new Timer(20, null);
        fadeTimer.addActionListener(e -> {
            float opacity = getOpacity();
            opacity += 0.05f;
            if (opacity >= 1f) {
                setOpacity(1f);
                ((Timer) e.getSource()).stop();
            } else {
                setOpacity(opacity);
            }
        });
        fadeTimer.start();
    }

    class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            } else {
                g.setColor(new Color(240, 230, 210));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        }
    }

    private JButton createAnimatedButton(String text, Color baseColor, String imagePath, Runnable action) {
        JButton btn = new JButton(text);
        System.out.println("Loading: " + imagePath);
        System.out.println("Current Dir: " + System.getProperty("user.dir"));

        if (imagePath != null && !imagePath.isBlank()) {
            ImageIcon icon = new ImageIcon(imagePath);

            if (icon.getIconWidth() > 0) {
                Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                btn.setIcon(new ImageIcon(img));
            }
        }

        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn.setBackground(baseColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 215, 0), 2, true),
                BorderFactory.createLineBorder(new Color(120, 80, 30), 2, true)));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBorder(BorderFactory.createLineBorder(
                        new Color(255, 215, 0), 4, true));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBorder(BorderFactory.createLineBorder(
                        Color.WHITE, 3, true));
            }
        });

        btn.addActionListener(e -> action.run());
        return btn;
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(MainFrame::new);
    }
}