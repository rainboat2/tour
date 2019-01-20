/*
 * Created by JFormDesigner on Thu Jan 17 09:22:20 CST 2019
 */

package gui.user_gui;

import gui.admin_gui.VertexShow;
import graph.Graph;
import graph.Vertex;
import tour.Notification;
import tour.Search_Sort;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * 本类为用户查找景点信息的GUI
 * 包括功能：
 * 1. 景点查找与排序
 * 2. 查看通告
 * 3. 景点信息展示
 */
public class InformationPanel extends JPanel {

    private Graph g;
    private Search_Sort search_sort;   //查找和排序的方法
    private Notification notification; //通告相关
    private VertexShow vertexShow;     //右侧的景点信息显示面板
    private Vertex[] verticesInTable;  //记录当前在表格中的节点

    public InformationPanel(Graph g) {
        initComponents();
        this.g = g;
        search_sort = new Search_Sort(g);
        notification = new Notification();
        vertexShow = new VertexShow();
        init();
    }

    /**
     * 初始化显示，将以下信息添加到面板中
     * 1. 所有的节点（添加到表格）
     * 2. 显示最新通告
     * 3. 右侧节点展示面板
     */
    private void init() {
        vertexShow.draw(g, null);
        showPanel.add(vertexShow);
        verticesInTable = search_sort.search("");
        showTable();
        showNotification();
    }

    /**
     * 显示最新通告
     */
    private void showNotification() {
        notifitionText.setText(notification.getNotification());
    }

    /**
     * 当表格中有节点被选中时触发该函数
     * 将选中的节点在右侧的显示面板中显示出来
     * @param e 事件监听对象
     */
    @SuppressWarnings("Duplicates")
    private void tableMouseClicked(MouseEvent e) {
        int row = table1.getSelectedRow();
        String name = (String) table1.getValueAt(row, 0);
        Vertex v = g.getVertex(name);
        vertexShow.draw(g, v);
    }

    /**
     * 点击搜索按钮时触发该函数，会按照指定的关键词获取所需所有匹配的节点，
     * 并且将其显示在表格中
     *
     * @param e 事件监听对象
     */
    private void searchMouseClicked(MouseEvent e) {
        String keyword = searchText.getText();
        verticesInTable = search_sort.search(keyword);
        search_sort.sort((String) searchPattern.getSelectedItem(), verticesInTable);
        showTable();
    }

    /**
     * 将vertices属性中保存的所有节点显示在表格中
     */
    @SuppressWarnings("Duplicates")
    private void showTable() {
        String[][] t = new String[verticesInTable.length][2];
        for (int i = 0; i < verticesInTable.length; i++) {
            t[i][0] = verticesInTable[i].getName();
            t[i][1] = verticesInTable[i].getDescription();
        }
        TableModel model;
        model = new DefaultTableModel(t, new String[]{"景点名称", "景点描述"});
        table1.setModel(model);
    }

    // 当用户选中下拉框的值时调用该方法，用于对数组重新排序
    private void searchPatternActionPerformed(ActionEvent e) {
        search_sort.sort((String) searchPattern.getSelectedItem(), verticesInTable);
        showTable();
    }

    /**
     * 该方法用来创建一个弹出显示所有的历史通告
     * @param e 事件监听对象
     */
    private void historyMouseClicked(MouseEvent e) {
        Iterable<String> it = notification.getHistory();
        StringBuilder s = new StringBuilder();
        for (String line : it)
            s.append(line).append("\n");
        // 创建对话框并显示历史通告
        JTextArea history = new JTextArea(20, 40);
        history.setText(s.toString());
        history.setEditable(false);
        history.setAutoscrolls(true);
        final JComponent[] inputs = new JComponent[]{
                new JLabel("历史通告"), history
        };
        JOptionPane.showMessageDialog(null, inputs, "通知", JOptionPane.PLAIN_MESSAGE);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        showPanel = new JPanel();
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        searchText = new JTextField();
        button1 = new JButton();
        searchPattern = new JComboBox<>();
        label2 = new JLabel();
        label3 = new JLabel();
        scrollPane2 = new JScrollPane();
        notifitionText = new JTextArea();
        button2 = new JButton();

        //======== this ========
        setBackground(UIManager.getColor("Button.background"));
        setLayout(null);

        //======== showPanel ========
        {
            showPanel.setBackground(Color.white);
            showPanel.setLayout(new GridLayout());
        }
        add(showPanel);
        showPanel.setBounds(565, 0, 345, 615);

        //---- label1 ----
        label1.setText("\u666f \u70b9 \u4fe1 \u606f \u67e5 \u8be2");
        label1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 30));
        add(label1);
        label1.setBounds(143, 50, 265, 50);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null},
                    {null, null},
                },
                new String[] {
                    "\u666f\u70b9\u540d\u79f0", "\u666f\u70b9\u4ecb\u7ecd"
                }
            ) {
                Class<?>[] columnTypes = new Class<?>[] {
                    String.class, String.class
                };
                boolean[] columnEditable = new boolean[] {
                    false, false
                };
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return columnTypes[columnIndex];
                }
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            {
                TableColumnModel cm = table1.getColumnModel();
                cm.getColumn(0).setResizable(false);
                cm.getColumn(0).setPreferredWidth(90);
                cm.getColumn(1).setResizable(false);
                cm.getColumn(1).setPreferredWidth(140);
            }
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tableMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1);
        scrollPane1.setBounds(110, 205, 340, 170);
        add(searchText);
        searchText.setBounds(110, 132, 213, 25);

        //---- button1 ----
        button1.setText("\u67e5\u8be2");
        button1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchMouseClicked(e);
            }
        });
        add(button1);
        button1.setBounds(360, 130, 85, button1.getPreferredSize().height);

        //---- searchPattern ----
        searchPattern.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u540d\u79f0",
            "\u6b22\u8fce\u5ea6",
            "\u4f11\u606f\u533a\u6570\u91cf",
            "\u5395\u6240\u6570\u91cf"
        }));
        searchPattern.addActionListener(e -> searchPatternActionPerformed(e));
        add(searchPattern);
        searchPattern.setBounds(195, 175, 90, searchPattern.getPreferredSize().height);

        //---- label2 ----
        label2.setText("\u6392\u5e8f\u65b9\u5f0f\uff1a");
        label2.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(label2);
        label2.setBounds(110, 173, 77, 25);

        //---- label3 ----
        label3.setText("\u6700\u65b0\u901a\u544a\uff1a");
        label3.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(label3);
        label3.setBounds(105, 415, 77, 25);

        //======== scrollPane2 ========
        {

            //---- notifitionText ----
            notifitionText.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 12));
            scrollPane2.setViewportView(notifitionText);
        }
        add(scrollPane2);
        scrollPane2.setBounds(105, 445, 345, 105);

        //---- button2 ----
        button2.setText("\u5386\u53f2\u901a\u544a");
        button2.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                historyMouseClicked(e);
            }
        });
        add(button2);
        button2.setBounds(354, 410, 94, 29);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < getComponentCount(); i++) {
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
    private JPanel showPanel;
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JTextField searchText;
    private JButton button1;
    private JComboBox<String> searchPattern;
    private JLabel label2;
    private JLabel label3;
    private JScrollPane scrollPane2;
    private JTextArea notifitionText;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
