package views;


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

    public String getProcessName() throws Exception {
        return addProcessPanel.getProcessName();
    }

    public int getProcessTime() throws Exception, NumberFormatException{
        return addProcessPanel.getProcessTime();
    }

    public int getProcessPriority() throws Exception, NumberFormatException {
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

    public void setInitialInfo(String name, String time, String priority,boolean[] states){
        addProcessPanel.setInitialInfo(name, time, priority, states);
    }
}
