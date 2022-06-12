package views;

import models.MyProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProcessesToCommunicateDialog extends JDialog {

    private String actualProcess;

    public ProcessesToCommunicateDialog(ActionListener listener, ArrayList<MyProcess> processesToCommunicate,
                                        String actualProcess){
        this.actualProcess = actualProcess;
        getContentPane().setLayout(new GridLayout(1,1));
        setModal(true);
        setUndecorated(true);
        setSize(500,500);
        initProcessesPanel(listener, processesToCommunicate);
        setLocationRelativeTo(null);
    }

    private void initProcessesPanel(ActionListener listener, ArrayList<MyProcess> processesToCommunicate){
        ProcessesToCommunicatePanel processes = new ProcessesToCommunicatePanel(listener, processesToCommunicate);
        add(processes);
    }

    public String getActualProcess() {
        return actualProcess;
    }
}
