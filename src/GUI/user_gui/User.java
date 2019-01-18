/*
 * Created by JFormDesigner on Thu Jan 17 09:14:11 CST 2019
 */

package GUI.user_gui;

import java.awt.event.*;

import GUI.Main;
import graph.Graph;

import java.awt.*;
import javax.swing.*;

public class User extends JFrame {

    private Graph g;
    private InformationPanel information;
    private PathPanel path;
    private Parking parking;

    public User(Graph g) {
        this.g = g;
        initComponents();
        init();
    }

    //初始化自定义组件
    private void init() {
        information = new InformationPanel(g);
        information.setVisible(true);
        sceneryPanel.add(information);

        path = new PathPanel(g);
        path.setVisible(true);
        pathPanel.add(path);

        parking = new Parking();
        parking.setVisible(true);
        parkingPanel.add(parking);
    }

    // add custom component creation code here
    private void createUIComponents() {
    }

    private void thisWindowClosing(WindowEvent e) {
        Main m = new Main();
        m.setVisible(true);
    }

    @SuppressWarnings("Duplicates")
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tabbedPane1 = new JTabbedPane();
        sceneryPanel = new JPanel();
        scrollPanel = new JScrollPane();
        pathPanel = new JPanel();
        parkingPanel = new JPanel();

        //======== this ========
        setTitle("User System");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout());

        //======== tabbedPane1 ========
        {
            tabbedPane1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
            tabbedPane1.setTabPlacement(SwingConstants.LEFT);

            //======== sceneryPanel ========
            {
                sceneryPanel.setLayout(new GridLayout());
            }
            tabbedPane1.addTab("\u666f\u70b9\u4fe1\u606f", sceneryPanel);

            //======== scrollPanel ========
            {

                //======== pathPanel ========
                {
                    pathPanel.setLayout(new GridLayout());
                }
                scrollPanel.setViewportView(pathPanel);
            }
            tabbedPane1.addTab("\u8def\u5f84\u67e5\u8be2", scrollPanel);

            //======== parkingPanel ========
            {
                parkingPanel.setLayout(new GridLayout());
            }
            tabbedPane1.addTab("\u505c\u8f66\u573a\u7cfb\u7edf", parkingPanel);
        }
        contentPane.add(tabbedPane1);
        setSize(965, 585);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane tabbedPane1;
    private JPanel sceneryPanel;
    private JScrollPane scrollPanel;
    private JPanel pathPanel;
    private JPanel parkingPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
