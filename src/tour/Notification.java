package tour;

import javax.swing.*;
import java.io.*;
import java.util.Calendar;
import java.util.Stack;

/**
 * 本类用于管理员发布通告和用户读取通告
 */
public class Notification {

    private Stack<String> stack; //使用栈来记录停车场信息

    public Notification() {
        stack = new Stack<>();
        load();
    }

    /**
     * 由于所有的消息使用栈保存，按照信息加入栈中的顺序，栈顶保存的就是最新的消息
     * @return 栈顶的消息
     */
    public String getNotification() {
        return stack.peek();
    }

    /**
     * 获取栈中所有元素
     * 注意：Java中stack的迭代是从栈底开始，到栈顶结束
     * @return 可迭代对象
     */
    public Iterable<String> getHistory() {
        return stack;
    }

    /**
     * 弹出信息输入框，获取输入的通告加入到栈中
     * 获取的输入会自动在前面加入系统的当前时间
     */
    public void addNotification() {
        //创建对话框
        JTextArea notification = new JTextArea(10, 40);
        final JComponent[] inputs = new JComponent[]{
                new JLabel("输入通知"), notification
        };
        int rs = JOptionPane.showConfirmDialog(null, inputs, "输入通告内容", JOptionPane.YES_NO_OPTION);
        if (rs != JOptionPane.YES_OPTION) return;
        //获取对话框输入
        String line = "[" + getCurrentTime() + "] " + notification.getText();
        stack.add(line);
        JOptionPane.showMessageDialog(null, "通告发布成功", "警告", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * 获取系统当前时间
     * @return 系统当前时间（字符串格式）
     */
    private static String getCurrentTime() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        int hours = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);

        return String.format("%s/%s/%s %s:%s:%s", year, month, date, hours, minute, second);
    }

    /**
     * 读取保存的所有通告
     */
    private void load() {
        try {
            FileReader reader = new FileReader("file/notification.txt");
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null)
                stack.push(line);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用该方法，保存所有新添加的信息到文本文件中
     */
    public void save() {
        try {
            FileWriter w = new FileWriter("file/notification.txt");
            BufferedWriter bw = new BufferedWriter(w);
            for (String line : stack)
                bw.write(line + "\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
