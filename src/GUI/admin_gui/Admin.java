/*
 * Created by JFormDesigner on Wed Jan 16 16:04:55 CST 2019
 */

package GUI.admin_gui;

import GUI.Main;
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


public class Admin extends JFrame {

    private Graph g;
    private VertexShow vertexShow;     //负责节点信息显示
    private Search_Sort search_sort;   //负责节点信息查找
    private GraphModify graphModify;     //负责节点和边的增删查改
    private Notification notification; //负责通知的相关管理

    public Admin(Graph g) {
        initComponents();
        this.g = g;
        MyComponents();
        init();
    }

    // 用于初始一些自定义的参数
    private void init(){
        search_sort = new Search_Sort(g);
        graphModify = new GraphModify(g);
        notification = new Notification();
        //获得图中所有节点，用于初始显示表格
        Vertex[] vertices = new Vertex[g.size()];
        int i = 0;
        for (Vertex v : g.getAllVertex())
            vertices[i++] = v;
        showVertex(vertices);
    }

    @SuppressWarnings("Duplicates")
    private void showVertex(Vertex[] vertices){
        String[][] t = new String[vertices.length][2];
        for (int i = 0; i < vertices.length; i++){
            t[i][0] = vertices[i].getName();
            t[i][1] = vertices[i].getDescription();
        }
        TableModel model;
        model = new DefaultTableModel(t, new String[]{"景点名称", "景点描述"});
        table1.setModel(model);
    }

    private void searchAndShow(){
        String keyword = searchTextField.getText();
        Vertex[] vertices = search_sort.search(keyword);
        showVertex(vertices);
    }

    private void MyComponents() {
        // addVertex custom component creation code here
        vertexShow = new VertexShow();
        showPanel.add(vertexShow);
        vertexShow.draw(g, null);

        Analyze analyze = new Analyze();
        panel2.add(analyze);
    }

    @SuppressWarnings("Duplicates")
    private void tableMouseClicked(MouseEvent e) {
        int row = table1.getSelectedRow();
        String name = (String)table1.getValueAt(row, 0);
        Vertex v = g.getVertex(name);
        vertexShow.draw(g, v);
    }

    // 执行搜索操作并显示在表格中
    private void searchMouseClicked(MouseEvent e) {
        searchAndShow();
    }

    private void modifyMouseClicked(MouseEvent e) {
        try{
            Vertex v = getSelectVertex();
            graphModify.modify(v);
            vertexShow.draw(g, v);
        }catch (UnsupportedOperationException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Vertex getSelectVertex(){
        int row = table1.getSelectedRow();
        if (row == -1) throw new UnsupportedOperationException("请先选择需要修改的节点");
        String name = (String)table1.getValueAt(row, 0);
        Vertex v = g.getVertex(name);
        return v;
    }

    private void addMouseClicked(MouseEvent e) {
        try{
            graphModify.addVertex();
            // 删除后重新执行搜索，更新表格内容
            searchAndShow();
        }catch (UnsupportedOperationException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }catch (RuntimeException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 在删除节点按钮点击的时候被调用
    private void deleteMouseClicked(MouseEvent e) {
        try{
            Vertex v = getSelectVertex();
            graphModify.deleteVertex(v);
            searchAndShow();  //调用该方法更新表格
        }catch (NoSuchElementException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addEdgeMouseClicked(MouseEvent e) {
        try{
            graphModify.addEdge();
            vertexShow.draw(g, g.getVertex(0));
        }catch (NumberFormatException | NoSuchElementException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteEdgeMouseClicked(MouseEvent e) {
        try{
            graphModify.deleteEdge();
            vertexShow.draw(g, g.getVertex(0));
        }catch (NoSuchElementException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void notificationMouseClicked(MouseEvent e) {
        notification.addNotification();
    }

    private void thisWindowClosing(WindowEvent e) {
        end();
    }

    // 在退出前执行保存和打开新窗口的操作
    // 非常重要，必需调用
    private void end(){
        g.save();
        notification.save();
        Main m = new Main();
        m.setVisible(true);
    }

    @SuppressWarnings("Duplicates")
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();
        controlPanel = new JPanel();
        label1 = new JLabel();
        searchTextField = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button7 = new JButton();
        showPanel = new JPanel();
        panel2 = new JPanel();

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

            //======== panel1 ========
            {
                panel1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
                panel1.setLayout(null);

                //======== controlPanel ========
                {
                    controlPanel.setAutoscrolls(true);
                    controlPanel.setBorder(null);
                    controlPanel.setLayout(null);

                    //---- label1 ----
                    label1.setText("\u666f\u70b9\u4fe1\u606f\u7ef4\u62a4");
                    label1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 30));
                    controlPanel.add(label1);
                    label1.setBounds(205, 15, 210, 65);
                    controlPanel.add(searchTextField);
                    searchTextField.setBounds(145, 105, 165, 25);

                    //---- button1 ----
                    button1.setText("\u67e5\u627e");
                    button1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
                    button1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            searchMouseClicked(e);
                        }
                    });
                    controlPanel.add(button1);
                    button1.setBounds(370, 100, 80, 30);

                    //---- button2 ----
                    button2.setText("\u4fee\u6539\u666f\u70b9");
                    button2.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
                    button2.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            modifyMouseClicked(e);
                        }
                    });
                    controlPanel.add(button2);
                    button2.setBounds(160, 370, 125, 30);

                    //---- button3 ----
                    button3.setText("\u5220\u9664\u8def\u5f84");
                    button3.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
                    button3.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            deleteEdgeMouseClicked(e);
                        }
                    });
                    controlPanel.add(button3);
                    button3.setBounds(160, 505, 125, 30);

                    //---- button4 ----
                    button4.setText("\u6dfb\u52a0\u666f\u70b9");
                    button4.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
                    button4.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            addMouseClicked(e);
                        }
                    });
                    controlPanel.add(button4);
                    button4.setBounds(330, 370, 125, 30);

                    //---- button5 ----
                    button5.setText("\u6dfb\u52a0\u8def\u5f84");
                    button5.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
                    button5.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            addEdgeMouseClicked(e);
                        }
                    });
                    controlPanel.add(button5);
                    button5.setBounds(330, 440, 125, 30);

                    //---- button6 ----
                    button6.setText("\u53d1\u5e03\u901a\u544a");
                    button6.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
                    button6.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            notificationMouseClicked(e);
                        }
                    });
                    controlPanel.add(button6);
                    button6.setBounds(330, 505, 125, 30);

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
                    controlPanel.add(scrollPane1);
                    scrollPane1.setBounds(145, 180, 340, 170);

                    //---- button7 ----
                    button7.setText("\u5220\u9664\u666f\u70b9");
                    button7.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
                    button7.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            deleteMouseClicked(e);
                        }
                    });
                    controlPanel.add(button7);
                    button7.setBounds(160, 440, 125, 30);

                    { // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < controlPanel.getComponentCount(); i++) {
                            Rectangle bounds = controlPanel.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = controlPanel.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        controlPanel.setMinimumSize(preferredSize);
                        controlPanel.setPreferredSize(preferredSize);
                    }
                }
                panel1.add(controlPanel);
                controlPanel.setBounds(0, 0, 655, 620);

                //======== showPanel ========
                {
                    showPanel.setBackground(Color.white);
                    showPanel.setLayout(new GridLayout());
                }
                panel1.add(showPanel);
                showPanel.setBounds(660, 0, 360, 625);

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
            tabbedPane1.addTab("\u666f\u70b9\u4fe1\u606f\u7ef4\u62a4", panel1);

            //======== panel2 ========
            {
                panel2.setLayout(new GridLayout());
            }
            tabbedPane1.addTab("\u666f\u70b9\u5206\u6790", panel2);
        }
        contentPane.add(tabbedPane1);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JPanel controlPanel;
    private JLabel label1;
    private JTextField searchTextField;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button7;
    private JPanel showPanel;
    private JPanel panel2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
