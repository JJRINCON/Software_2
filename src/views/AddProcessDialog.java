package views;


import exceptions.EmptyProcessNameException;
import exceptions.EmptyProcessPriorityException;
import exceptions.EmptyProcessTimeException;
import models.MyProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddProcessDialog extends JDialog {

    private AddProcessPanel addProcessPanel;

    public AddProcessDialog(ActionListener listener, boolean isEditing) {
        setSize(400, 450);
        setModal(true);
        setLayout(new BorderLayout());
        setResizable(false);
        setUndecorated(true);
        addProcessPanel = new AddProcessPanel(listener, isEditing);
        add(addProcessPanel);
        setLocationRelativeTo(null);
    }

    public AddProcessDialog(ActionListener listener, boolean isEditing, boolean isCommunicate) {
        setSize(400, 450);
        setModal(true);
        setLayout(new BorderLayout());
        setResizable(false);
        setUndecorated(true);
        addProcessPanel = new AddProcessPanel(listener, isEditing, isCommunicate);
        add(addProcessPanel);
        setLocationRelativeTo(null);
    }

    public String getProcessName() throws EmptyProcessNameException {
        return addProcessPanel.getProcessName();
    }

    public int getProcessTime() throws EmptyProcessTimeException , NumberFormatException{
        return addProcessPanel.getProcessTime();
    }

    public int getProcessPriority() throws EmptyProcessPriorityException , NumberFormatException {
        return addProcessPanel.getProcessPriority();
    }

    public boolean getIsBlocked(){
        return addProcessPanel.getIsBlocked();
    }

    public boolean getIsSuspended(){
        return addProcessPanel.getIsSuspended();
    }

    public boolean getIsDestroyed(){
        return addProcessPanel.getIsDestroyed();
    }

    public boolean getIsComunicate(){
        return addProcessPanel.getIsComunicate();
    }

    public void setInitialInfo(MyProcess process){
        boolean[] states = {process.isLocked(), process.isSuspended(), process.isDestroid(), process.isComunication()};
        addProcessPanel.setInitialInfo(process.getName(), String.valueOf(process.getTime()),
                String.valueOf(process.getPriority()), states);
    }

    public int getProcessChangePriority() {
       return  addProcessPanel.getChangePriority();
    }
}