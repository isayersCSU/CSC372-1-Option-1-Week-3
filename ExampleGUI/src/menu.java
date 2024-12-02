import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class menu extends JFrame {

    static JMenuBar mb;

    static JMenu x;

    static JMenuItem m1, m2, m3, m4;

    static JTextArea t;

    static JFrame f;

    static Color previousColor;

    public static void main(String[] args)
    {

        f = new JFrame("Menu demo");

        t = new JTextArea();
        t.setPreferredSize(new Dimension(100, 100));

        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();
        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();

        f.setLayout(new BorderLayout());
        f.add(t, BorderLayout.CENTER);

        f.add(northPanel, BorderLayout.NORTH);
        northPanel.setBackground(new Color(0,0,0,0));

        f.add(southPanel, BorderLayout.SOUTH);
        southPanel.setBackground(new Color(0,0,0,0));

        f.add(eastPanel, BorderLayout.EAST);
        eastPanel.setBackground(new Color(0, 0, 0, 0));

        f.add(westPanel, BorderLayout.WEST);
        westPanel.setBackground(new Color(0, 0, 0, 0));
        mb = new JMenuBar();

        x = new JMenu("Menu");

        m1 = new JMenuItem("Print Date and Time");
        m2 = new JMenuItem("Copy Text to Log");
        m3 = new JMenuItem("Random Color");
        m4 = new JMenuItem("Exit");

        m4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the program
            }
        });

        m3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                int r = rand.nextInt(156) + 100; // Random red value between 100 and 255
                int b = rand.nextInt(156) + 100; // Random blue value between 100 and 255
                Color randomGreen = new Color(r, 255, b);
                f.getContentPane().setBackground(randomGreen);
                if (previousColor != null) {
                    String rgbString = "Previous Color: R: " + previousColor.getRed() + ", G: " +
                            previousColor.getGreen() + ", B: " + previousColor.getBlue();
                    JOptionPane.showMessageDialog(f, rgbString);
                }

                previousColor = randomGreen; // Store the current color
                f.getContentPane().setBackground(randomGreen);

            }
        });

        m2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter writer = new FileWriter("log.txt", true); // Append to file
                    writer.write(t.getText() + "\n");
                    writer.close();
                    JOptionPane.showMessageDialog(f, "Text copied to log.txt");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(f, "Error writing to log file.");
                }
            }
        });

        m1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String formattedDate = format.format(calendar.getTime());
                t.setText(formattedDate);
            }
        });

        x.add(m1);
        x.add(m2);
        x.add(m3);
        x.add(m4);

        mb.add(x);

        f.setJMenuBar(mb);



        f.setSize(500, 500);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}