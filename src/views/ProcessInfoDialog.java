package views;

import models.MyProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProcessInfoDialog extends JDialog {

    private ProcessInfoPanel processInfoPanel;

    public ProcessInfoDialog(ActionListener actionListener, MyProcess process){
        setSize(400, 450);
        setModal(true);
        setLayout(new BorderLayout());
        setResizable(false);
        setUndecorated(true);
        processInfoPanel = new ProcessInfoPanel(actionListener, process);
        add(processInfoPanel);
        setLocationRelativeTo(null);
    }
}
