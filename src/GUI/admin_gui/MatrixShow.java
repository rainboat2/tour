package GUI.admin_gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * 以表格的形式呈现一个二维数组
 */
public class MatrixShow extends JPanel {


    public MatrixShow() {
        initComponents();
    }

    /**
     *  数组的第一列用作表格的列索引，其他列作为数据
     * @param a 用于显示的二维数组
     */
    public void showMatrix(String[][] a){
        String[] name = a[0];
        String[][] data = new String[a.length-1][a.length];
        System.arraycopy(a, 1, data, 0, data.length);
        table1.setModel(new DefaultTableModel(data, name));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        //======== this ========
        setLayout(new GridLayout());

        //======== scrollPane1 ========
        {
            scrollPane1.setPreferredSize(new Dimension(600, 280));

            //---- table1 ----
            table1.setAutoCreateRowSorter(true);
            table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table1.setColumnSelectionAllowed(true);
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                },
                new String[] {
                    null, null, null, null, null, null, null, null, null, null, null, null, null, null
                }
            ));
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
