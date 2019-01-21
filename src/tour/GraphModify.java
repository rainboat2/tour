package tour;

import graph.Graph;
import graph.Vertex;

import javax.swing.*;
import java.io.File;
import java.util.NoSuchElementException;

/**
 * GraphModify 封装边和节点的添加，删除和修改方法
 * 为GUI管理员景点信息模块提供支持
 *
 * 通用流程：
 * 1. 根据所需的内容生成相应的对话框
 * 2. 从对话框中获取输入
 * 3. 向用户确认信息，并根据用户回答（yes/no）来确定是否执行下一步
 * 4. 调用图中方法执行操作
 */
public class GraphModify {

    private Graph g;

    public GraphModify(Graph g) {
        this.g = g;
    }

    /**
     * 弹出对话框，获取用户的输入（起点，终点，距离）
     * 根据这三个参数向图中添加一条边
     *
     * @throws NumberFormatException 用户输入的距离无法转化为一个数
     * @throws NoSuchElementException 输入了图中不存在的结点
     */
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

    /**
     * 弹出对话框，获取用户输入（起点，终点）
     * 根据这两个输入删除图中的一条边
     *
     * @throws NoSuchElementException 用户的输入了图中不存在的结点
     */
    public void deleteEdge() throws NoSuchElementException {
        JTextField start = new JTextField();
        JTextField end = new JTextField();
        final JComponent[] inputs = new JComponent[]{
                new JLabel("景点一"), start,
                new JLabel("景点二"), end,
        };
        JOptionPane.showConfirmDialog(null, inputs, "删除路径", JOptionPane.PLAIN_MESSAGE);

        int rs = JOptionPane.showConfirmDialog(null, "确认删除该路径信息？", "警告", JOptionPane.YES_NO_OPTION);
        if (rs == JOptionPane.NO_OPTION) return;
        g.removeEdge(start.getText(), end.getText());  //调用g的相关方法以删除节点
        JOptionPane.showMessageDialog(null, String.format("成功删除从%s到%s的路径", start.getText(), end.getText()),
                "提示", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * 传入一个结点名称，在图中找到并且删除该节点
     * @param v 需要删除的结点对象
     * @throws NoSuchElementException 图中不存在指定的结点
     */
    public void deleteVertex(String name) throws NoSuchElementException {
        Vertex v = g.getVertex(name);
        if (v == null)
            throw new NoSuchElementException("找不到要删除的结点");
        int rs = JOptionPane.showConfirmDialog(null, "确定删除选中节点", "警告", JOptionPane.YES_NO_OPTION);
        if (rs != JOptionPane.YES_OPTION) return;
        g.removeVertex(v.getName());
        JOptionPane.showMessageDialog(null, String.format("成功删除节点%s", v.getName()), "提示", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * 弹出对话框，要求用户输入需要创建的结点的信息，
     * 创建对象并添加到图中
     * @throws RuntimeException 输入的景点名字为空
     */
    public void addVertex() throws RuntimeException {
        String[] info = showDialog(new Vertex("", "暂无说明", 0, 0, 0, ""));
        if (info == null) return;
        int rs = JOptionPane.showConfirmDialog(null, "确认添加该景点信息？", "警告", JOptionPane.YES_NO_OPTION);
        if (rs != JOptionPane.YES_OPTION) return;
        if (info[0].equals(""))
            throw new RuntimeException("景点的名字不能为空");
        g.addVertex(new Vertex(info));
        JOptionPane.showMessageDialog(null, "成功添加景点" + info[0]);
    }

    /**
     * 弹出对话框，用户输入修改的景点的信息，获取输入并对结点信息进行修改
     * 注意：修改结点名称必须要调用 Graph中的 renameVertex方法来执行
     * @param name 需要修改信息的结点名称
     */
    public void modify(String name) {
        Vertex v = g.getVertex(name);
        String[] info = showDialog(v);
        if (info == null) return;
        int rs = JOptionPane.showConfirmDialog(null, "确认修改信息？", "警告", JOptionPane.YES_NO_OPTION);
        if (rs != JOptionPane.YES_OPTION) return;
        //info[] = {name, popular, toilet, restArea, description, imagePath}
        g.renameVertex(v, info[0]);
        v.setPopular(info[1]);
        v.setToilet(info[2]);
        v.setRestArea(info[3]);
        v.setDescription(info[4]);
        v.setImagePath(info[5]);
    }

    /**
     * 调用该函数会显示一个修改景点信息的对话框
     * @return {name, popular, toilet, restArea, description, imagePath}
     */
    private String[] showDialog(Vertex v) {
        //----------------------构建对话框--------------------------
        JFileChooser chooser = new JFileChooser(new File("file/img/default.jpg"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        JTextField name = new JTextField(v.getName());
        JTextField restArea = new JTextField(v.getRestArea() + "");
        JTextField toilet = new JTextField(v.getToilet() + "");
        JTextField popular = new JTextField(v.getPopular() + "");
        JTextArea description = new JTextArea(4, 20);
        description.setText(v.getDescription());
        description.setAutoscrolls(true);
        description.setLineWrap(true);
        description.setSize(300, 300);
        final JComponent[] inputs = new JComponent[]{
                new JLabel("名称"), name,
                new JLabel("欢迎度"), popular,
                new JLabel("厕所数量"), toilet,
                new JLabel("休息区数量"), restArea,
                new JLabel("简介"), description,
                new JLabel("图片"), chooser
        };
        int rs = JOptionPane.showConfirmDialog(null, inputs, "景点信息", JOptionPane.YES_NO_OPTION);
        if (rs != JOptionPane.YES_OPTION)
            return null;
        //----------------------从对话框中获取输入值--------------------------
        // info[] = {name, popular, toilet, restArea, description, imagePath}
        String[] info = new String[6];
        info[0] = name.getText();
        info[1] = popular.getText();
        info[2] = toilet.getText();
        info[3] = restArea.getText();
        info[4] = description.getText();
        File f = chooser.getSelectedFile();
        if (f != null) info[5] = f.getPath();
        else info[5] = v.getImagePath();
        return info;
    }


}
