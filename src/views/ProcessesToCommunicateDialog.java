package views;

import models.MyProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProcessesToCommunicateDialog extends JDialog {

    private String actualProcess;

    public ProcessesToCommunicateDialog(ActionListener listener, ArrayList<MyProcess> processesAvailableToCommunicate,
                                        ArrayList<MyProcess> processesNotAvailableToCommunicate, String actualProcess){
        this.actualProcess = actualProcess;
        getContentPane().setLayout(new GridLayout(1,1));
        setModal(true);
        setUndecorated(true);
        setSize(500,500);
        initProcessesPanel(listener, processesAvailableToCommunicate, processesNotAvailableToCommunicate);
        setLocationRelativeTo(null);
    }

    private void initProcessesPanel(ActionListener listener,ArrayList<MyProcess> processesAvailableToCommunicate,
                                    ArrayList<MyProcess> processesNotAvailableToCommunicate){
        ProcessesToCommunicatePanel processes = new ProcessesToCommunicatePanel(listener, processesAvailableToCommunicate,
                                                        processesNotAvailableToCommunicate);
        add(processes);
    }

    public String getActualProcess() {
        return actualProcess;
    }
}
