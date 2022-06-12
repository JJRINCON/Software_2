package views;

import models.MyProcess;
import models.Queue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private static final String TITLE = "Software 2";
    private ActionListener actionlistener;
    private MainPanel mainPanel;

    public MainFrame(ActionListener actionlistener){
        this.actionlistener = actionlistener;
        getContentPane().setLayout(new GridLayout(1,1));
        setTitle(TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        initMainPanel();
    }

    public void initMainPanel(){
        mainPanel = new MainPanel(actionlistener);
        add(mainPanel);
    }

    public void initReportsPanel(ArrayList<MyProcess> readyProcess, ArrayList<MyProcess> dispatchedProcess,
                                 ArrayList<MyProcess> executingProcess, ArrayList<MyProcess> toLockedProcess,
                                 ArrayList<MyProcess> lockedProcess, ArrayList<MyProcess> wakeUpProcess,
                                 ArrayList<MyProcess> expiredProcess, ArrayList<MyProcess> toSuspendedProcess,
                                 ArrayList<MyProcess> suspendedProcess, ArrayList<MyProcess> reanudedProcess,
                                 ArrayList<MyProcess> lockedToSuspendedProcess, ArrayList<MyProcess> lockedToDestroyedProcess,
                                 ArrayList<MyProcess> suspendedToDestroyedProcess, ArrayList<MyProcess> toDestroyedProcess,
                                 ArrayList<MyProcess> destroyedProcess, ArrayList<MyProcess> destroyedToExecutionProcess,
                                 ArrayList<MyProcess> communicationProcess, ArrayList<MyProcess> terminatedProcess){
        mainPanel.initReportsPanel(readyProcess, dispatchedProcess, executingProcess, toLockedProcess, lockedProcess,
                                wakeUpProcess, expiredProcess, toSuspendedProcess, suspendedProcess, reanudedProcess,
                                lockedToSuspendedProcess, lockedToDestroyedProcess, suspendedToDestroyedProcess,
                                toDestroyedProcess, destroyedProcess, destroyedToExecutionProcess, communicationProcess,
                                terminatedProcess);
    }

    public void updateProcesses(Queue<MyProcess> processQueue){
        mainPanel.updateProcesses(processQueue);
    }

    public void newSimulation(){
        getContentPane().remove(mainPanel);
        mainPanel = new MainPanel(actionlistener);
        add(mainPanel);
        getContentPane().revalidate();
    }
}
