package tour;

import graph.Graph;
import graph.Vertex;

import javax.swing.*;
import java.io.File;
import java.util.NoSuchElementException;

/**
 * GraphModify 封装边和节点的添加，删除和修改方法
 * 为GUI模块提供相关方法的接口
 *
 * 通用流程：
 * 1. 根据所需的内容生成相应的对话框
 * 2. 从对话框中获取输入
 * 3. 向用户确认信息，并根据用户回答（yes/no）来确定是否执行相应操作
 */
public class GraphModify {

    private Graph g;

    public GraphModify(Graph g){
        this.g = g;
    }

    public void addEdge() throws NumberFormatException, NoSuchElementException {
        JTextField start = new JTextField();
        JTextField end = new JTextField();
        JTextField distance = new JTextField();
        final JComponent[] inputs = new JComponent[]{
                new JLabel("景点一"), start,
                new JLabel("景点二"), end,
                new JLabel("距离"), distance
        };
        JOptionPane.showConfirmDialog(null, inputs, "添加节点", JOptionPane.PLAIN_MESSAGE);

        int dis = Integer.parseInt(distance.getText());
        g.addEdge(start.getText(), end.getText(), dis);
        JOptionPane.showMessageDialog(null, "成功添加路径", "警告", JOptionPane.WARNING_MESSAGE);
    }

    public void deleteEdge() throws NoSuchElementException{
        JTextField start = new JTextField();
        JTextField end = new JTextField();
        final JComponent[] inputs = new JComponent[]{
                new JLabel("景点一"), start,
                new JLabel("景点二"), end,
        };
        JOptionPane.showConfirmDialog(null, inputs, "删除路径", JOptionPane.PLAIN_MESSAGE);

        int rs = JOptionPane.showConfirmDialog(null, "确认删除该路径信息？", "警告", JOptionPane.YES_NO_OPTION);
        if (rs == JOptionPane.NO_OPTION) return;
        g.removeEdge(start.getText(), end.getText());
        JOptionPane.showMessageDialog(null, String.format("成功删除从%s到%s的路径", start.getText(), end.getText()),
                "提示", JOptionPane.WARNING_MESSAGE);
    }

    public void deleteVertex(Vertex v) throws NoSuchElementException{
        int rs = JOptionPane.showConfirmDialog(null, "确定删除选中节点", "警告", JOptionPane.YES_NO_OPTION);
        if (rs != JOptionPane.YES_OPTION) return;
        g.removeVertex(v.getName());
        JOptionPane.showMessageDialog(null, String.format("成功删除节点%s", v.getName()), "提示", JOptionPane.WARNING_MESSAGE);
    }

    public void addVertex() throws RuntimeException{
        String[] info = showDialog(new Vertex("", "暂无说明", 0, 0, 0, ""));
        int rs = JOptionPane.showConfirmDialog(null, "确认添加该景点信息？", "警告", JOptionPane.YES_NO_OPTION);
        if (rs != JOptionPane.YES_OPTION) return;
        if (info[0].equals(""))
            throw new RuntimeException("景点的名字不能为空");
        g.addVertex(new Vertex(info));
        JOptionPane.showMessageDialog(null, "成功添加景点" + info[0]);
    }

    public void modify(Vertex v) throws UnsupportedOperationException{
        String[] info = showDialog(v);
        int rs = JOptionPane.showConfirmDialog(null, "确认修改信息？", "警告", JOptionPane.YES_NO_OPTION);
        if (rs == JOptionPane.NO_OPTION) return;
        Vertex w = g.getVertex(info[0]);
        if (v != w)
            throw new UnsupportedOperationException("添加失败，节点的名字不能重复");
        v.setName(info[0]);
        v.setPopular(info[1]);
        v.setToilet(info[2]);
        v.setRestArea(info[3]);
        v.setDescription(info[4]);
        v.setImagePath(info[5]);
    }

    // 添加节点信息和修改节点信息共用这一个对话框
    private String[] showDialog(Vertex v){
        //----------------------构建对话框--------------------------
        JFileChooser chooser = new JFileChooser(new File("img/default.png"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        JTextField name = new JTextField(v.getName());
        JTextField restArea = new JTextField(v.getRestArea()+"");
        JTextField toilet = new JTextField(v.getToilet()+"");
        JTextField popular = new JTextField(v.getPopular()+"");
        JTextArea description = new JTextArea(v.getDescription()+"");
        description.setAutoscrolls(true);
        description.setSize(300, 300);
        final JComponent[] inputs = new JComponent[]{
                new JLabel("名称"),       name,
                new JLabel("欢迎度"),     popular,
                new JLabel("厕所数量"),   toilet,
                new JLabel("休息区数量"), restArea,
                new JLabel("简介"),       description,
                new JLabel("图片"),       chooser
        };
        JOptionPane.showConfirmDialog(null, inputs, "景点信息", JOptionPane.PLAIN_MESSAGE);

        //----------------------从对话框中获取输入值--------------------------
        String[] info = new String[6];
        info[0] = name.getText();
        info[1] = popular.getText();
        info[2] = toilet.getText();
        info[3] = restArea.getText();
        info[4] = description.getText();
        File f = chooser.getSelectedFile();
        if (f != null) info[5] = f.getPath();
        else           info[5] = v.getImagePath();
        return info;
    }


}
