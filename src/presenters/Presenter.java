package presenters;

import models.MyProcess;
import models.OperatingSystem;
import views.AddProcessDialog;
import views.MainFrame;
import views.ProcessInfoDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Presenter implements ActionListener {

    private OperatingSystem operatingSystem;
    private MainFrame mainFrame;
    private AddProcessDialog addProcessDialog;
    private AddProcessDialog editProcessDialog;
    private ProcessInfoDialog processInfoDialog;

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
            case INIT_SIMULATION:
                break;
            case CLOSE_INFO:
                manaegeCloseInfoAction();
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
        editProcessDialog = new AddProcessDialog(this, true);
        MyProcess process = operatingSystem.search(processName);
        editProcessDialog.setInitialInfo(process.getName(), String.valueOf((int) process.getTime()),
                String.valueOf(process.getPriority()), new boolean[]{process.isLocked(), process.isSuspended(),
                        process.isDestroid(), process.isComunication()});
        editProcessDialog.setVisible(true);
    }
    private void manageAcceptAction() {
        try {
            operatingSystem.verifyProcessName(addProcessDialog.getProcessName());
            boolean[] states = {addProcessDialog.getIsBlocked(), addProcessDialog.getIsSuspended(),
                    addProcessDialog.getIsDestroyed(), addProcessDialog.getIsComunicate()};
            MyProcess newProcess = new MyProcess(addProcessDialog.getProcessName(), addProcessDialog.getProcessTime(),
                    addProcessDialog.getProcessPriority(), states);
            operatingSystem.addProcess(newProcess);
            addProcessDialog.dispose();
            mainFrame.updateProcesses(operatingSystem.getProcessQueue());
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(mainFrame, "Debe ingresar unicamente numeros", "ERROR!!!",
                    JOptionPane.ERROR_MESSAGE);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(mainFrame, e.getMessage(), "ERROR!!!", JOptionPane.ERROR_MESSAGE);
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
            boolean[] states = {editProcessDialog.getIsBlocked(), editProcessDialog.getIsSuspended(),
                    editProcessDialog.getIsDestroyed(), editProcessDialog.getIsComunicate()};
            operatingSystem.editProcess(actualName, editProcessDialog.getProcessName(),
                    editProcessDialog.getProcessTime(), editProcessDialog.getProcessPriority(), states);
            editProcessDialog.dispose();
            mainFrame.updateProcesses(operatingSystem.getProcessQueue());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainFrame, "Debe ingresar unicamente numeros", "ERROR!!!",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ne){
            JOptionPane.showMessageDialog(mainFrame, ne.getMessage(), "ERROR!!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void manageCancelEditAction() {
        editProcessDialog.dispose();
    }

    private void manageViewInfoAction(ActionEvent e) {
        String processName = ((JButton) e.getSource()).getName();
        processInfoDialog = new ProcessInfoDialog(this, operatingSystem.search(processName));
        processInfoDialog.setVisible(true);
    }

    private void manaegeCloseInfoAction() {
        processInfoDialog.dispose();
    }
}
