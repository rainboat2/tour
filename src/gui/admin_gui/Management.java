package gui.admin_gui;

import graph.Graph;
import graph.Vertex;
import tour.GraphModify;
import tour.Notification;
import tour.Search_Sort;

import java.awt.*;
import java.awt.event.*;
import java.util.NoSuchElementException;
import javax.swing.*;
import javax.swing.table.*;

/**
 * 本模块为景点信息修改的页面，
 * 定义了界面的基本组件，和各种监听事件
 */
public class Management extends JPanel {

    private Graph g;
    private VertexShow vertexShow;     //负责节点信息显示
    private Search_Sort search_sort;   //负责节点信息查找
    private GraphModify graphModify;   //负责节点和边的增删查改
    private Notification notification; //负责通知的相关管理

    public Management(Graph g) {
        this.g = g;
        initComponents();
        init();
    }

    /**
     * 保存图和通告到文本文件
     */
    public void save(){
        g.save();
        notification.save();
    }

    // 用于自定义的初始化
    private void init() {
        search_sort = new Search_Sort(g);
        graphModify = new GraphModify(g);
        notification = new Notification();
        //获得图中所有节点，用于初始显示表格
        Vertex[] vertices = new Vertex[g.size()];
        int i = 0;
        for (Vertex v : g.getAllVertex())
            vertices[i++] = v;
        showVertexInTable(vertices);
        // 调用显示的方法
        vertexShow = new VertexShow();
        showPanel.add(vertexShow);
        vertexShow.draw(g, null);
    }

    /**
     * 传入一个结点数组，将其显示在表格中
     * @param vertices 节点数组
     */
    @SuppressWarnings("Duplicates")
    private void showVertexInTable(Vertex[] vertices) {
        String[][] t = new String[vertices.length][2];
        for (int i = 0; i < vertices.length; i++) {
            t[i][0] = vertices[i].getName();
            t[i][1] = vertices[i].getDescription();
        }
        TableModel model;
        model = new DefaultTableModel(t, new String[]{"景点名称", "景点描述"});
        table1.setModel(model);
    }

    /**
     * 按照关键字搜索节点，并显示在表格中
     */
    private void searchAndShow() {
        String keyword = searchTextField.getText();
        Vertex[] vertices = search_sort.search(keyword);
        showVertexInTable(vertices);
    }

    private void searchMouseClicked(MouseEvent e) {
        searchAndShow();
    }

    /**
     * 获取表格中选中的行，将其代表的节点显示在右侧的面板中
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
     * 调用修改函数，弹出修改信息的弹窗，执行修改节点信息操作
     * @param e 事件监听对象
     */
    private void modifyMouseClicked(MouseEvent e) {
        try {
            String name = getSelectVertexName();
            graphModify.modify(name);
            vertexShow.draw(g, g.getVertex(name));  //更新右侧面板显示
            searchAndShow();  //更新表格
        } catch (UnsupportedOperationException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * 在鼠标选中表中任意一行时触发该对象
     * 获取表格中被选中的行，没有行被选中，则抛出异常信息
     * @return 被选中的行对应的节点的名字
     */
    private String getSelectVertexName() {
        int row = table1.getSelectedRow();
        if (row == -1) throw new UnsupportedOperationException("请先选择需要修改的节点");
        String name = (String) table1.getValueAt(row, 0);
        return name;
    }

    /**
     * 删除选中的节点，在删除节点的按钮按下时候被触发
     * @param e 事件监听信息
     */
    private void deleteMouseClicked(MouseEvent e) {
        try {
            String name = getSelectVertexName();
            graphModify.deleteVertex(name);
            searchAndShow();  //调用该方法更新表格
            vertexShow.draw(g, null); //刷新显示面板
        } catch (NoSuchElementException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * 删除用户指定的边，在删除边按下的时候触发
     * @param e 事件监听对象
     */
    private void deleteEdgeMouseClicked(MouseEvent e) {
        try {
            graphModify.deleteEdge();
            vertexShow.draw(g, null);  //更新右侧面板
        } catch (NoSuchElementException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * 弹出文本输入框，获取需要输入的通知并保存
     * @param e 事件监听对象
     */
    private void notificationMouseClicked(MouseEvent e) {
        notification.addNotification();
    }

    /**
     * 调用添点边的方法
     * @param e 事件监听对象
     */
    private void addEdgeMouseClicked(MouseEvent e) {
        try {
            graphModify.addEdge();
            vertexShow.draw(g, null);
        } catch (NumberFormatException | NoSuchElementException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }


    /**
     * 调用添加节点的方法
     * @param e 事件监听对象
     */
    private void addMouseClicked(MouseEvent e) {
        try {
            graphModify.addVertex();
            // 添加后重新执行搜索，更新表格内容
            searchAndShow();
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }


    @SuppressWarnings("Duplicates")
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        label1 = new JLabel();
        searchTextField = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        button7 = new JButton();
        button3 = new JButton();
        button6 = new JButton();
        button5 = new JButton();
        button4 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        showPanel = new JPanel();

        //======== this ========
        setLayout(null);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("\u666f\u70b9\u4fe1\u606f\u7ef4\u62a4");
            label1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 30));
            panel1.add(label1);
            label1.setBounds(220, 23, 210, 65);
            panel1.add(searchTextField);
            searchTextField.setBounds(160, 113, 165, 25);

            //---- button1 ----
            button1.setText("\u67e5\u627e");
            button1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    searchMouseClicked(e);
                }
            });
            panel1.add(button1);
            button1.setBounds(385, 108, 80, 30);

            //---- button2 ----
            button2.setText("\u4fee\u6539\u666f\u70b9");
            button2.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    modifyMouseClicked(e);
                }
            });
            panel1.add(button2);
            button2.setBounds(175, 378, 125, 30);

            //---- button7 ----
            button7.setText("\u5220\u9664\u666f\u70b9");
            button7.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
            button7.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    deleteMouseClicked(e);
                }
            });
            panel1.add(button7);
            button7.setBounds(175, 448, 125, 30);

            //---- button3 ----
            button3.setText("\u5220\u9664\u8def\u5f84");
            button3.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
            button3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    deleteEdgeMouseClicked(e);
                }
            });
            panel1.add(button3);
            button3.setBounds(175, 513, 125, 30);

            //---- button6 ----
            button6.setText("\u53d1\u5e03\u901a\u544a");
            button6.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
            button6.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    notificationMouseClicked(e);
                }
            });
            panel1.add(button6);
            button6.setBounds(345, 513, 125, 30);

            //---- button5 ----
            button5.setText("\u6dfb\u52a0\u8def\u5f84");
            button5.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
            button5.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    addEdgeMouseClicked(e);
                }
            });
            panel1.add(button5);
            button5.setBounds(345, 448, 125, 30);

            //---- button4 ----
            button4.setText("\u6dfb\u52a0\u666f\u70b9");
            button4.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
            button4.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    addMouseClicked(e);
                }
            });
            panel1.add(button4);
            button4.setBounds(345, 378, 125, 30);

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
            panel1.add(scrollPane1);
            scrollPane1.setBounds(150, 170, 340, 170);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
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
        add(panel1);
        panel1.setBounds(0, 0, 680, 605);

        //======== showPanel ========
        {
            showPanel.setBackground(Color.white);
            showPanel.setLayout(new GridLayout());
        }
        add(showPanel);
        showPanel.setBounds(680, 0, 375, 605);

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
    private JPanel panel1;
    private JLabel label1;
    private JTextField searchTextField;
    private JButton button1;
    private JButton button2;
    private JButton button7;
    private JButton button3;
    private JButton button6;
    private JButton button5;
    private JButton button4;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel showPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
