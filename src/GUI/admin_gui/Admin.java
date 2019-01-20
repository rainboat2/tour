package GUI.admin_gui;

import GUI.Main;
import graph.Graph;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * 本类为管理员功能页面的框架，本身不包括任何功能，只作为其他功能面板的容器
 */
public class Admin extends JFrame {

    private Graph g;
    private Management management;

    public Admin(Graph g) {
        initComponents();
        this.g = g;
        init();
    }

    /**
     * 将所需的功能面板加入到本框架中
     */
    private void init() {
        Analyze analyze = new Analyze(g);
        anlayzePanel.add(analyze);

        management = new Management(g);
        managmentPanel.add(management);
    }

    /**
     * 在本框架关闭的时候，执行基本的保存操作（非常重要）
     * 将登陆界面打开（Main对象）
     * @param e 事件监听对象
     */
    private void thisWindowClosing(WindowEvent e) {
        management.save();
        Main m = new Main();
        m.setVisible(true);
    }

    @SuppressWarnings("Duplicates")
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tabbedPane1 = new JTabbedPane();
        managmentPanel = new JPanel();
        anlayzePanel = new JPanel();

        //======== this ========
        setTitle("Admin System");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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

            //======== managmentPanel ========
            {
                managmentPanel.setLayout(new GridLayout());
            }
            tabbedPane1.addTab("\u666f\u70b9\u4fe1\u606f\u7ef4\u62a4", managmentPanel);

            //======== anlayzePanel ========
            {
                anlayzePanel.setLayout(new GridLayout());
            }
            tabbedPane1.addTab("\u666f\u70b9\u5206\u6790", anlayzePanel);
        }
        contentPane.add(tabbedPane1);
        setSize(1125, 655);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane tabbedPane1;
    private JPanel managmentPanel;
    private JPanel anlayzePanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
