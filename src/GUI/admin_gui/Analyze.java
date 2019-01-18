/*
 * Created by JFormDesigner on Fri Jan 18 00:38:20 CST 2019
 */

package GUI.admin_gui;

import java.awt.*;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class Analyze extends JPanel {

    public Analyze() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();

        //======== this ========
        setLayout(null);

        //---- label1 ----
        label1.setText("\u8fde\u901a\u5206\u652f\u6570");
        add(label1);
        label1.setBounds(new Rectangle(new Point(75, 70), label1.getPreferredSize()));

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
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
