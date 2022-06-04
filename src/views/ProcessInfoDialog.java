package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProcessInfoDialog extends JDialog {

    private ProcessInfoPanel processInfoPanel;

    public ProcessInfoDialog(ActionListener actionListener, boolean isCommunicate){
        setSize(400, 450);
        setModal(true);
        setLayout(new BorderLayout());
        setResizable(false);
        setUndecorated(true);
        processInfoPanel = new ProcessInfoPanel(actionListener, isCommunicate);
        add(processInfoPanel);
        setLocationRelativeTo(null);
    }
}
