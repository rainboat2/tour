/*
 * Created by JFormDesigner on Wed Jan 16 16:12:05 CST 2019
 */

package GUI.admin_gui;

import graph.Graph;
import graph.Vertex;

import java.awt.*;
import java.io.File;
import javax.swing.*;

/**
 * VertexShow 为一个专门用来显示景点信息的 panel
 * 在其它GUI中，若需要显示景点信息，只需要以下两步：
 * 1. 创建VertexShow对象，添加到面板容器中
 * 2. 调用draw方法
 */
public class VertexShow extends JPanel {

    public VertexShow() {
        initComponents();
    }

    /**
     * 用于绘制显示景点信息的面板
     * @param g 景点所在的图
     * @param v 景点对象
     */
    public void draw(Graph g, Vertex v){
        if (v == null)
            v = new Vertex("节点名称", "暂无描述", 0, 0, 0, "img/default.png");
        restArea.setText((v.getRestArea() > 0) ? v.getRestArea() + "个" : "无");
        toilet.setText((v.getToilet() > 0) ? v.getToilet() + "个" : "无");
        popular.setText(v.getPopular() + "");
        describe.setText(v.getDescription());
        name.setText(v.getName());

        setImage(v.getImagePath());

        Iterable<Vertex> it = g.getAdjacentVertex(g.indexOf(v));
        StringBuilder s = new StringBuilder();
        if (it == null) s.append("无");
        else{
            for (Vertex w : g.getAdjacentVertex(g.indexOf(v)))
                s.append(w.getName()).append(",");
        }
        adjecent.setText(s.toString());
    }

    private void setImage(String path){
        File f = new File(path);
        ImageIcon image;
        if (f.exists())  image = new ImageIcon(path);
        else             image = new ImageIcon("img/default.png");
        Image img = image.getImage();
        img = img.getScaledInstance(400, 160, Image.SCALE_DEFAULT);
        image.setImage(img);
        imageLabel.setIcon(image);
    }

    private void createUIComponents() {
        // TODO: addVertex custom component creation code here
    }


    @SuppressWarnings("Duplicates")
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        imageLabel = new JLabel();
        name = new JLabel();
        label2 = new JLabel();
        restArea = new JLabel();
        label3 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        toilet = new JLabel();
        popular = new JLabel();
        adjecent = new JLabel();
        scrollPane1 = new JScrollPane();
        describe = new JTextArea();

        //======== this ========
        setBackground(Color.white);
        setLayout(null);

        //---- imageLabel ----
        imageLabel.setIcon(new ImageIcon("D:\\workplace\\tour\\img\\temp.jpg"));
        add(imageLabel);
        imageLabel.setBounds(0, 0, 400, 160);

        //---- name ----
        name.setText("\u666f\u70b9\u540d\u79f0");
        name.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 24));
        add(name);
        name.setBounds(20, 175, 135, 50);

        //---- label2 ----
        label2.setText("\u4f11\u606f\u533a\uff1a");
        label2.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(label2);
        label2.setBounds(20, 235, 60, 25);

        //---- restArea ----
        restArea.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(restArea);
        restArea.setBounds(85, 235, 110, 25);

        //---- label3 ----
        label3.setText("\u5395\u6240\uff1a");
        label3.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(label3);
        label3.setBounds(20, 270, 45, 25);

        //---- label7 ----
        label7.setText("\u6b22\u8fce\u5ea6\uff1a");
        label7.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(label7);
        label7.setBounds(20, 303, 60, 25);

        //---- label8 ----
        label8.setText("\u76f8\u90bb\u666f\u533a\uff1a");
        label8.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(label8);
        label8.setBounds(20, 336, 75, 25);

        //---- label9 ----
        label9.setText("\u7b80\u4ecb\uff1a");
        label9.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(label9);
        label9.setBounds(20, 370, 65, 25);

        //---- toilet ----
        toilet.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(toilet);
        toilet.setBounds(70, 270, 110, 25);

        //---- popular ----
        popular.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(popular);
        popular.setBounds(80, 305, 110, 25);

        //---- adjecent ----
        adjecent.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
        add(adjecent);
        adjecent.setBounds(90, 335, 235, 25);

        //======== scrollPane1 ========
        {
            scrollPane1.setBorder(null);

            //---- describe ----
            describe.setLineWrap(true);
            describe.setBorder(null);
            describe.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
            scrollPane1.setViewportView(describe);
        }
        add(scrollPane1);
        scrollPane1.setBounds(70, 375, 245, 105);

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
    private JLabel imageLabel;
    private JLabel name;
    private JLabel label2;
    private JLabel restArea;
    private JLabel label3;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel toilet;
    private JLabel popular;
    private JLabel adjecent;
    private JScrollPane scrollPane1;
    private JTextArea describe;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
