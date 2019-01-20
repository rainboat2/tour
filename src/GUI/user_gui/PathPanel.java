/*
 * Created by JFormDesigner on Thu Jan 17 21:29:18 CST 2019
 */

package GUI.user_gui;

import java.awt.event.*;

import graph.Graph;
import graph.algorithm.other.UnionFind;
import graph.algorithm.short_path.Dijkstra;
import graph.algorithm.small_tree.Prim;
import tour.ShortPathSearch;
import tour.TourPath;

import java.awt.*;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class PathPanel extends JPanel {

    private Graph g;
    private ShortPathSearch shortPathSearch;
    private TourPath tourPath;
    private UnionFind uf;

    public PathPanel(Graph g) {
        this.g = g;
        uf = new UnionFind(g);
        shortPathSearch = new ShortPathSearch(g);
        tourPath = new TourPath(g);
        initComponents();
    }

    private void searchTourMouseClicked(MouseEvent e) {
        try {
            if (uf.count() != 1)
                JOptionPane.showMessageDialog(null, "检测到部分路径不可达，本次规划路径仅包括可达节点");
            String start = tourStart.getText();
            String path = tourPath.tourPath(start);
            resultArea.setText(path);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }



    private void shortPathButtonMouseClicked(MouseEvent e) {
        try {
            String star = shortPathStart.getText();
            String end = shortPathEnd.getText();
            resultArea.setText(shortPathSearch.shortPath(star, end, new Dijkstra(g)));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }


    @SuppressWarnings("Duplicates")
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        shortPathStart = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        shortPathEnd = new JTextField();
        shortPathButton = new JButton();
        label4 = new JLabel();
        label5 = new JLabel();
        tourStart = new JTextField();
        searchTourButton = new JButton();
        scrollPane1 = new JScrollPane();
        resultArea = new JTextArea();
        label6 = new JLabel();
        label7 = new JLabel();

        //======== this ========
        setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        setLayout(null);

        //---- label1 ----
        label1.setText("\u6700\u77ed\u8def\u5f84\uff1a");
        label1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 18));
        add(label1);
        label1.setBounds(65, 65, 105, 30);
        add(shortPathStart);
        shortPathStart.setBounds(195, 105, 155, shortPathStart.getPreferredSize().height);

        //---- label2 ----
        label2.setText("\u8d77\u70b9\uff1a");
        label2.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(label2);
        label2.setBounds(130, 105, 45, 20);

        //---- label3 ----
        label3.setText("\u7ec8\u70b9\uff1a");
        label3.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(label3);
        label3.setBounds(390, 105, 45, 20);
        add(shortPathEnd);
        shortPathEnd.setBounds(450, 105, 155, 21);

        //---- shortPathButton ----
        shortPathButton.setText("\u67e5\u8be2");
        shortPathButton.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        shortPathButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                shortPathButtonMouseClicked(e);
            }
        });
        add(shortPathButton);
        shortPathButton.setBounds(650, 100, 75, shortPathButton.getPreferredSize().height);

        //---- label4 ----
        label4.setText("\u5bfc\u6e38\u56fe\uff1a");
        label4.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 18));
        add(label4);
        label4.setBounds(65, 150, 105, 30);

        //---- label5 ----
        label5.setText("\u8d77\u70b9\uff1a");
        label5.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(label5);
        label5.setBounds(126, 190, 46, 20);
        add(tourStart);
        tourStart.setBounds(195, 190, 155, 21);

        //---- searchTourButton ----
        searchTourButton.setText("\u67e5\u8be2");
        searchTourButton.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        searchTourButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchTourMouseClicked(e);
            }
        });
        add(searchTourButton);
        searchTourButton.setBounds(385, 185, 75, 29);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(resultArea);
        }
        add(scrollPane1);
        scrollPane1.setBounds(120, 295, 635, 185);

        //---- label6 ----
        label6.setText("\u7ed3\u679c\u663e\u793a\uff1a");
        label6.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 18));
        add(label6);
        label6.setBounds(65, 250, 105, 30);

        //---- label7 ----
        label7.setText("\u8def\u5f84\u67e5\u8be2");
        label7.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 30));
        add(label7);
        label7.setBounds(330, 15, 125, 50);

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
    private JLabel label1;
    private JTextField shortPathStart;
    private JLabel label2;
    private JLabel label3;
    private JTextField shortPathEnd;
    private JButton shortPathButton;
    private JLabel label4;
    private JLabel label5;
    private JTextField tourStart;
    private JButton searchTourButton;
    private JScrollPane scrollPane1;
    private JTextArea resultArea;
    private JLabel label6;
    private JLabel label7;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
