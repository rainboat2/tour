/*
 * Created by JFormDesigner on Thu Jan 17 23:34:57 CST 2019
 */

package gui.user_gui;

import tour.ParkingSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * 停车场管理系统
 * 用于模拟汽车进场和出场信息并将其显示
 */
public class Parking extends JPanel {

    private ParkingSystem parkingSystem = new ParkingSystem();

    public Parking() {
        initComponents();
    }

    /**
     * 给定需要进场汽车的车牌号，给出模拟进场的信息
     * @param e 事件监听信息
     */
    private void inMouseClicked(MouseEvent e) {
        try{
            String license = getInput();
            String info = parkingSystem.arrive(license);
            information.append(info);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    /**
     * 从输入框中获取用户的输入，如果为空，则抛出异常
     * @return 输入框中的值
     */
    private String getInput(){
        String license = licenseText.getText();
        if (license.equals(""))
            throw new RuntimeException("输入不能为空");
        licenseText.setText("");
        return license;
    }

    /**
     * 给定需要进场的汽车车牌，给出模拟出场的信息
     * @param e 事件监听对象
     */
    private void outMouseClicked(MouseEvent e) {
        try{
            String license = getInput();
            String info = parkingSystem.leave(license);
            information.append(info);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @SuppressWarnings("Duplicates")
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        licenseText = new JTextField();
        in = new JButton();
        out = new JButton();
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        information = new JTextArea();
        label2 = new JLabel();
        label3 = new JLabel();

        //======== this ========
        setLayout(null);

        //---- licenseText ----
        licenseText.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(licenseText);
        licenseText.setBounds(250, 100, 290, 30);

        //---- in ----
        in.setText("\u8fdb\u573a");
        in.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        in.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                inMouseClicked(e);
            }
        });
        add(in);
        in.setBounds(250, 160, 85, 30);

        //---- out ----
        out.setText("\u51fa\u573a");
        out.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        out.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                outMouseClicked(e);
            }
        });
        add(out);
        out.setBounds(455, 160, 85, 30);

        //---- label1 ----
        label1.setText("\u8f66\u724c\u53f7\uff1a");
        label1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 18));
        add(label1);
        label1.setBounds(150, 95, 80, 35);

        //======== scrollPane1 ========
        {

            //---- information ----
            information.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 12));
            scrollPane1.setViewportView(information);
        }
        add(scrollPane1);
        scrollPane1.setBounds(250, 235, 300, 250);

        //---- label2 ----
        label2.setText("\u4fe1\u606f\u663e\u793a\uff1a");
        label2.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 18));
        add(label2);
        label2.setBounds(150, 200, 95, 35);

        //---- label3 ----
        label3.setText("\u505c\u8f66\u573a\u7ba1\u7406");
        label3.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 30));
        add(label3);
        label3.setBounds(315, 25, 180, label3.getPreferredSize().height);

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
    private JTextField licenseText;
    private JButton in;
    private JButton out;
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTextArea information;
    private JLabel label2;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
