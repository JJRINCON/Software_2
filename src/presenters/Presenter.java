package presenters;

import exceptions.*;
import models.MyProcess;
import models.OperatingSystem;
import views.AddProcessDialog;
import views.MainFrame;
import views.ProcessInfoDialog;
import views.ProcessesToCommunicateDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Presenter implements ActionListener {

    private OperatingSystem operatingSystem;
    private MainFrame mainFrame;
    private AddProcessDialog addProcessDialog;
    private AddProcessDialog editProcessDialog;
    private ProcessInfoDialog processInfoDialog;
    private ProcessesToCommunicateDialog processesToCommunicateDialog;

    public Presenter() {
        operatingSystem = new OperatingSystem();
        mainFrame = new MainFrame(this);
        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Events.valueOf(e.getActionCommand())) {
            case ADD_PROCESS:
                manageAddProcessAction();
                break;
            case DELETE_PROCESS:
                manageDeleteProcessAction(e);
                break;
            case EDIT_PROCESS:
                manageEditProcessAction(e);
                break;
            case ACCEPT:
                manageAcceptAction();
                break;
            case CANCEL:
                manageCancelAction();
                break;
            case ACCEPT_EDIT:
                manageAcceptEditAction(e);
                break;
            case CANCEL_EDIT:
                manageCancelEditAction();
                break;
            case VIEW_INFO:
                manageViewInfoAction(e);
                break;
            case CLOSE_INFO:
                manageCloseInfoAction();
                break;
            case INIT_SIMULATION:
                manageInitSimulationAction();
                break;
            case NEW_SIMULATION:
                manageNewSimulationAction();
                break;
            case COMMUNICATE_PROCESS:
                manageCommunicateProcessAction(e);
                break;
            case COMMUNICATE:
                manageCommunicateAction(e);
                break;
            case CANCEL_COMMUNICATE:
                manageCancelCommunicateAction();
                break;
            case DELETE_COMMUNICATE:
                manageDeleteCommunicateAction(e);
                break;
            case EXIT:
                System.exit(0);
                break;
        }
    }

    private void manageAddProcessAction() {
        addProcessDialog = new AddProcessDialog(this, false);
        addProcessDialog.setVisible(true);
    }

    private void manageDeleteProcessAction(ActionEvent e) {
        String processName = ((JButton) e.getSource()).getName();
        operatingSystem.deleteProccess(processName);
        mainFrame.updateProcesses(operatingSystem.getProcessQueue());
    }
    private void manageEditProcessAction(ActionEvent e) {
        String processName = ((JButton) e.getSource()).getName();
        MyProcess process = operatingSystem.search(processName);
        editProcessDialog = new AddProcessDialog(this, true, process.isComunication());
        editProcessDialog.setInitialInfo(process);
        editProcessDialog.setVisible(true);
    }
    private void manageAcceptAction() {
        try {
            operatingSystem.verifyProcessName(addProcessDialog.getProcessName());
            operatingSystem.verifyProcessPriority(addProcessDialog.getProcessPriority());
            boolean[] states = {addProcessDialog.getIsBlocked(), addProcessDialog.getIsSuspended(),
                    addProcessDialog.getIsDestroyed(), addProcessDialog.getIsComunicate()};
            MyProcess newProcess = new MyProcess(addProcessDialog.getProcessName(), addProcessDialog.getProcessTime(),
                    addProcessDialog.getProcessPriority(), states);
            operatingSystem.addProcess(newProcess);
            addProcessDialog.dispose();
            mainFrame.updateProcesses(operatingSystem.getProcessQueue());
        } catch (NumberFormatException | RepeatedNameException | EmptyProcessTimeException | EmptyProcessNameException |
                 EmptyProcessPriorityException | RepeatedPriorityException e) {
            JOptionPane.showMessageDialog(mainFrame, e.getMessage(), "ERROR!!!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void manageCancelAction() {
        addProcessDialog.dispose();
    }

    private void manageAcceptEditAction(ActionEvent event) {
        try {
            String actualName = ((JButton) event.getSource()).getName();
            if(!actualName.equals(editProcessDialog.getProcessName())){
                operatingSystem.verifyProcessName(editProcessDialog.getProcessName());
            }
            if(operatingSystem.search(actualName).getPriority() != editProcessDialog.getProcessPriority()){
                operatingSystem.verifyProcessPriority(editProcessDialog.getProcessPriority());
            }
            if(!editProcessDialog.getIsComunicate() && !operatingSystem.search(actualName).getNameComunicationProcess().isEmpty()){
                operatingSystem.search(actualName).setNameComunicationProcess("");
            }
            boolean[] states = {editProcessDialog.getIsBlocked(), editProcessDialog.getIsSuspended(),
                    editProcessDialog.getIsDestroyed(), editProcessDialog.getIsComunicate()};
            operatingSystem.editProcess(actualName, editProcessDialog.getProcessName(),
                    editProcessDialog.getProcessTime(), editProcessDialog.getProcessPriority(), states);
            editProcessDialog.dispose();
            mainFrame.updateProcesses(operatingSystem.getProcessQueue());
        } catch (EmptyProcessTimeException | EmptyProcessNameException | RepeatedNameException |
                 EmptyProcessPriorityException | RepeatedPriorityException e) {
            JOptionPane.showMessageDialog(mainFrame, e.getMessage(), "ERROR!!!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void manageCancelEditAction() {
        editProcessDialog.dispose();
    }

    private void manageViewInfoAction(ActionEvent e) {
        String processName = ((JButton) e.getSource()).getName();
        JButton btn = ((JButton) e.getSource());
        System.out.println("padre: " + btn.getParent().getName());
        processInfoDialog = new ProcessInfoDialog(this, operatingSystem.search(processName));
        processInfoDialog.setVisible(true);
    }

    private void manageCloseInfoAction() {
        processInfoDialog.dispose();
    }

    private void manageInitSimulationAction() {
        if(!operatingSystem.getProcessQueue().isEmpty()){
            operatingSystem.startSimulation();
            mainFrame.initReportsPanel(operatingSystem.getReadyProccess(), operatingSystem.getProcessDespachados(),
            operatingSystem.getExecuting(), operatingSystem.getProcessToLocked(), operatingSystem.getProcessLocked(),
            operatingSystem.getProcessWakeUp(), operatingSystem.getProcessExpired(), operatingSystem.getToSuspended(),
            operatingSystem.getSuspended(), operatingSystem.getReanude(), operatingSystem.getLockedToSuspended(),
            operatingSystem.getLockedToDestroyed(), operatingSystem.getSuspendedToDestroyed(), operatingSystem.toDestroyed(),
            operatingSystem.getDestroyed(), operatingSystem.getDestroyedToExecution(), operatingSystem.getComunicationProcess(),
                    operatingSystem.getProcessTerminated());
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe haber almenos 1 proceso para poder iniciar la simulacion",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void manageNewSimulationAction() {
        operatingSystem = new OperatingSystem();
        mainFrame.newSimulation();
    }

    private void manageCommunicateProcessAction(ActionEvent e) {
        String processName = ((JButton) e.getSource()).getName();
        processesToCommunicateDialog = new ProcessesToCommunicateDialog(this,
                operatingSystem.getProcessToCommunicate(processName), processName);
        processesToCommunicateDialog.setVisible(true);
    }

    private void manageCommunicateAction(ActionEvent e) {
        String processToCommunicate = ((JButton) e.getSource()).getName();
        String actualProcess = processesToCommunicateDialog.getActualProcess();
        operatingSystem.search(actualProcess).setNameComunicationProcess(processToCommunicate);
        processesToCommunicateDialog.dispose();
    }

    private void manageCancelCommunicateAction() {
        processesToCommunicateDialog.dispose();
    }

    private void manageDeleteCommunicateAction(ActionEvent e) {
        String processName = ((JButton) e.getSource()).getName();
        operatingSystem.search(processName).setNameComunicationProcess("");
        processInfoDialog.update(this, operatingSystem.search(processName));
    }
}
