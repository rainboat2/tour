package tour;

import javax.swing.*;
import java.io.*;
import java.util.Calendar;
import java.util.Stack;

public class Notification {

    private Stack<String> stack; //使用栈来记录停车场信息

    public Notification() {
        stack = new Stack<>();
        load();
    }

    public String getNotification() {
        return stack.peek();
    }

    public Iterable<String> getHistory() {
        return stack;
    }

    public void addNotification() {
        JTextArea notification = new JTextArea(10, 40);
        final JComponent[] inputs = new JComponent[]{
                new JLabel("输入通知"), notification
        };
        int rs = JOptionPane.showConfirmDialog(null, inputs, "输入通告内容", JOptionPane.YES_NO_OPTION);
        if (rs != JOptionPane.YES_OPTION) return;
        String line = "[ " + getCurrentTime() + " ] " + notification.getText();
        stack.add(line);
        JOptionPane.showMessageDialog(null, "通告发布成功", "警告", JOptionPane.WARNING_MESSAGE);
    }

    public static String getCurrentTime() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        int hours = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);

        return String.format("%s年%s月%s日%s时%s分%s秒", year, month, date, hours, minute, second);
    }

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
