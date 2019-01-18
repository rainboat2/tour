/*
 * Created by JFormDesigner on Wed Jan 16 13:51:21 CST 2019
 */

package GUI;

import GUI.admin_gui.Admin;
import GUI.user_gui.User;
import graph.Graph;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class Main extends JFrame {

    public static void main(String[] args) {
        Main m = new Main();
        m.setVisible(true);
    }

    private Graph g = Graph.getGraph();

    public Main() {
        initComponents();
    }

    private void createUIComponents() {
        // TODO: addVertex custom component creation code here
    }

    @SuppressWarnings("deprecation")
    private void adminMouseClicked(MouseEvent e) {
        JTextField account = new JTextField();
        JPasswordField password = new JPasswordField();
        final JComponent[] inputs = new JComponent[]{
                new JLabel("account"), account,
                new JLabel("password"), password
        };
        JOptionPane.showConfirmDialog(null, inputs, "login", JOptionPane.PLAIN_MESSAGE);
        //  if (account.getText().equals("20175377") && password.getText().equals("20175377")){
        Admin a = new Admin(g);
        a.setVisible(true);
        this.dispose();
        //}
    }

    private void visitorMouseClicked(MouseEvent e) {
        User user = new User(g);
        user.setVisible(true);
        this.dispose();
    }

    @SuppressWarnings("Duplicates")
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        label1 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 12));
        setResizable(false);
        setTitle("tour system");
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout());

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- button1 ----
            button1.setText("\u7ba1\u7406\u5458\u767b\u9646");
            button1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    adminMouseClicked(e);
                }
            });
            panel1.add(button1);
            button1.setBounds(295, 195, 140, 40);

            //---- button2 ----
            button2.setText("\u6e38\u5ba2\u767b\u9646");
            button2.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    visitorMouseClicked(e);
                }
            });
            panel1.add(button2);
            button2.setBounds(295, 260, 140, 40);

            //---- label1 ----
            label1.setIcon(new ImageIcon("D:\\workplace\\tour\\img\\\u80cc\u666f\u56fe.jpg"));
            label1.setForeground(UIManager.getColor("Button.foreground"));
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
            panel1.add(label1);
            label1.setBounds(0, 5, 775, 458);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel1);
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
