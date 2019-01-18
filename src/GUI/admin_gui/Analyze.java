/*
 * Created by JFormDesigner on Fri Jan 18 22:45:10 CST 2019
 */

package GUI.admin_gui;

import graph.Graph;
import graph.algorithm.other.UnionFind;

import java.awt.*;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class Analyze extends JPanel {

    private Graph g;
    private UnionFind uf;

    public Analyze(Graph g) {
        this.g = g;
        this.uf = new UnionFind(g);
        initComponents();
    }


    @SuppressWarnings("Duplicates")
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        panel1 = new JPanel();
        label2 = new JLabel();
        label1 = new JLabel();
        UnionCount = new JLabel();
        label4 = new JLabel();
        numberOfVertices = new JLabel();
        label6 = new JLabel();
        numberOfPath = new JLabel();
        label8 = new JLabel();
        hamilionRoad = new JLabel();
        flush = new JButton();
        matrix = new JButton();
        label3 = new JLabel();
        label5 = new JLabel();
        tourAnalyze = new JButton();
        scrollPane2 = new JScrollPane();
        textArea1 = new JTextArea();

        //======== this ========
        setLayout(null);

        //======== scrollPane1 ========
        {

            //======== panel1 ========
            {
                panel1.setLayout(null);

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
            scrollPane1.setViewportView(panel1);
        }
        add(scrollPane1);
        scrollPane1.setBounds(new Rectangle(new Point(0, 110), scrollPane1.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u666f\u70b9\u5206\u6790");
        label2.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 30));
        add(label2);
        label2.setBounds(435, 25, 160, 45);

        //---- label1 ----
        label1.setText("\u8fde\u901a\u5206\u652f\u6570\uff1a");
        label1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(label1);
        label1.setBounds(110, 122, 90, 30);

        //---- UnionCount ----
        UnionCount.setText("\u672a\u77e5");
        UnionCount.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(UnionCount);
        UnionCount.setBounds(205, 125, 70, 25);

        //---- label4 ----
        label4.setText("\u666f\u70b9\u603b\u6570\uff1a");
        label4.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(label4);
        label4.setBounds(294, 121, 80, 30);

        //---- numberOfVertices ----
        numberOfVertices.setText("\u672a\u77e5");
        numberOfVertices.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(numberOfVertices);
        numberOfVertices.setBounds(379, 121, 81, 30);

        //---- label6 ----
        label6.setText("\u8def\u5f84\u603b\u6570\uff1a");
        label6.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(label6);
        label6.setBounds(470, 122, 95, 30);

        //---- numberOfPath ----
        numberOfPath.setText("\u672a\u77e5");
        numberOfPath.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(numberOfPath);
        numberOfPath.setBounds(570, 122, 70, 30);

        //---- label8 ----
        label8.setText("\u6c49\u5bc6\u5c14\u987f\u56de\u8def\uff1a");
        label8.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(label8);
        label8.setBounds(655, 122, 115, 30);

        //---- hamilionRoad ----
        hamilionRoad.setText("\u672a\u77e5");
        hamilionRoad.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(hamilionRoad);
        hamilionRoad.setBounds(771, 122, 70, 30);

        //---- flush ----
        flush.setText("\u5237\u65b0\u4fe1\u606f");
        flush.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(flush);
        flush.setBounds(110, 177, 115, 30);

        //---- matrix ----
        matrix.setText("\u90bb\u63a5\u77e9\u9635");
        matrix.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(matrix);
        matrix.setBounds(275, 177, 120, 30);

        //---- label3 ----
        label3.setText("\u57fa\u672c\u4fe1\u606f\uff1a");
        label3.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 18));
        add(label3);
        label3.setBounds(110, 70, 200, 45);

        //---- label5 ----
        label5.setText("\u5bfc\u6e38\u56fe\u8def\u5f84\u5206\u6790\uff1a");
        label5.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 18));
        add(label5);
        label5.setBounds(110, 245, 200, 45);

        //---- tourAnalyze ----
        tourAnalyze.setText("\u5f00\u59cb\u5206\u6790");
        tourAnalyze.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(tourAnalyze);
        tourAnalyze.setBounds(110, 300, 115, 30);

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(textArea1);
        }
        add(scrollPane2);
        scrollPane2.setBounds(110, 350, 370, 235);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JPanel panel1;
    private JLabel label2;
    private JLabel label1;
    private JLabel UnionCount;
    private JLabel label4;
    private JLabel numberOfVertices;
    private JLabel label6;
    private JLabel numberOfPath;
    private JLabel label8;
    private JLabel hamilionRoad;
    private JButton flush;
    private JButton matrix;
    private JLabel label3;
    private JLabel label5;
    private JButton tourAnalyze;
    private JScrollPane scrollPane2;
    private JTextArea textArea1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
