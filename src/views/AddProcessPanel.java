package views;

import exceptions.EmptyProcessNameException;
import exceptions.EmptyProcessPriorityException;
import exceptions.EmptyProcessTimeException;
import presenters.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddProcessPanel extends MyGridPanel{

    private JTextField processNameTxt;
    private JTextField processTimeTxt;
    private JTextField processPriorityTxt;
    private JCheckBox isBlockedCb;
    private JCheckBox isSuspendedCb;
    private JCheckBox isDetroyedCb;
    private JCheckBox isComunicateCb;
    private JButton addBtn;

    public AddProcessPanel(ActionListener listener, boolean isEditing) {
        setBackground(Color.decode("#FDFEFE"));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        initComponents(listener, isEditing);
    }

    public AddProcessPanel(ActionListener listener, boolean isEditing, boolean isCommunicate) {
        setBackground(Color.decode("#FDFEFE"));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        initComponents(listener, isEditing);
    }

    private void initComponents(ActionListener listener, boolean isEditing) {
        initTitle(isEditing);
        initProcessNameTxt();
        initProcessTimeTxt();
        initProcessPriorityTxt();
        initIsBlockedCb();
        initIsSuspendedCb();
        initIsDestroyedCb();
        initIsCommunicateCb();
        verifyIsEditing(listener, isEditing);
    }

    private void verifyIsEditing(ActionListener listener, boolean isEditing){
        if(isEditing){
            initButtons(listener, Events.ACCEPT_EDIT.toString(), Events.CANCEL_EDIT.toString(), isEditing);
        }else{
            initButtons(listener, Events.ACCEPT.toString(), Events.CANCEL.toString(), isEditing);
        }
    }

    private void initTitle(boolean isEditing){
        String title = isEditing ? "Editar Proceso" : "Nuevo Proceso";
        JLabel titleLb = createLb(title, new Font("Arial", Font.BOLD, 20));
        addComponent(new JLabel(" "), 0, 0, 11, 0.1);
        addComponent(titleLb, 4, 1, 4, 0.2);
        addComponent(new JLabel(" "), 0, 3, 11, 0.1);
    }

    private void initProcessNameTxt(){
        JLabel nameLb = createLb(" Nombre:", new Font("Arial", Font.BOLD, 14));
        addComponent(nameLb, 2, 5, 2, 0.1);
        processNameTxt = new JTextField();
        processNameTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        addComponent(processNameTxt, 4, 5, 6, 0.1);
        addComponent(new JLabel(" "), 0, 6, 11, 0.1);
    }

    private void initProcessTimeTxt() {
        JLabel timeLb = createLb(" Tiempo: ", new Font("Arial", Font.BOLD, 14));
        addComponent(timeLb, 2, 7, 2, 0.1);
        processTimeTxt = new JTextField();
        processTimeTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        processTimeTxt.setText("");
        addComponent(processTimeTxt, 4, 7, 6, 0.1);
        addComponent(new JLabel(" "), 0, 8, 11, 0.1);
    }

    private void initProcessPriorityTxt(){
        JLabel priorityLb = createLb(" Prioridad: ", new Font("Arial", Font.BOLD, 14));
        addComponent(priorityLb, 2, 9, 2, 0.1);
        processPriorityTxt = new JTextField();
        processPriorityTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        processPriorityTxt.setText("");
        addComponent(processPriorityTxt, 4, 9, 6, 0.1);
        addComponent(new JLabel(" "), 0, 10, 11, 0.1);
    }

    private void initIsBlockedCb(){
        JLabel isBlockedLb = createLb(" Bloqueo: ", new Font("Arial", Font.BOLD, 14));
        addComponent(isBlockedLb, 2, 11, 2, 0.1);
        isBlockedCb = new JCheckBox();
        isBlockedCb.setBackground(Color.WHITE);
        isBlockedCb.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(isBlockedCb, 4, 11, 1, 0.1);
    }

    private void initIsSuspendedCb(){
        JLabel isSuspendedLb = createLb(" Suspender: ", new Font("Arial", Font.BOLD, 14));
        addComponent(isSuspendedLb, 7, 11, 2, 0.1);
        isSuspendedCb = new JCheckBox();
        isSuspendedCb.setBackground(Color.WHITE);
        isSuspendedCb.setHorizontalAlignment(SwingConstants.RIGHT);
        addComponent(isSuspendedCb, 9, 11, 1, 0.1);
        addComponent(new JLabel(" "), 0, 12, 11, 0.1);
    }

    private void initIsDestroyedCb(){
        JLabel isDestroyedLb = createLb(" Destruir: ", new Font("Arial", Font.BOLD, 14));
        addComponent(isDestroyedLb, 2, 15, 2, 0.1);
        isDetroyedCb = new JCheckBox();
        isDetroyedCb.setBackground(Color.WHITE);
        isDetroyedCb.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(isDetroyedCb, 4, 15, 1, 0.1);
    }

    private void initIsCommunicateCb() {
        JLabel isCommunicateLb = createLb(" Comunicar: ", new Font("Arial", Font.BOLD, 14));
        addComponent(isCommunicateLb, 7, 15, 2, 0.1);
        isComunicateCb = new JCheckBox();
        isComunicateCb.setBackground(Color.WHITE);
        isComunicateCb.setHorizontalAlignment(SwingConstants.RIGHT);
        addComponent(isComunicateCb, 9, 15, 1, 0.1);
        addComponent(new JLabel(" "), 0, 16, 11, 0.1);
    }

    private void initButtons(ActionListener listener, String acceptEvent, String cancelEvent, boolean isEditing){
        String addBtnTxt = isEditing ? "Editar" : "Agregar";
        addBtn = createBtn(addBtnTxt, Color.decode("#27AE60"), listener, acceptEvent);
        addComponent(addBtn, 3, 18, 2, 0.12);
        JButton cancelBtn = createBtn("Cancelar", Color.decode("#E74C3C"), listener, cancelEvent);
        addComponent(cancelBtn, 7, 18, 2, 0.12);
        addComponent(new JLabel(" "), 0, 19, 11, 0.05);
    }

    private JLabel createLb(String txt, Font font){
        JLabel lb = new JLabel(txt);
        lb.setFont(font);
        return lb;
    }

    private JButton createBtn(String txt, Color color, ActionListener listener, String command){
        JButton btn = new JButton(txt);
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.addActionListener(listener);
        btn.setActionCommand(command);
        return btn;
    }

    public String getProcessName() throws EmptyProcessNameException {
        if(!processNameTxt.getText().isEmpty()){
            return processNameTxt.getText();
        }else{
            throw new EmptyProcessNameException();
        }
    }

    public int getProcessTime() throws EmptyProcessTimeException, NumberFormatException {
        String text = processTimeTxt.getText();
        if(!text.isEmpty()){
            return Integer.parseInt(text);
        }else{
            throw new EmptyProcessTimeException();
        }
    }

    public int getProcessPriority() throws EmptyProcessPriorityException, NumberFormatException {
        String text = processPriorityTxt.getText();
        if(!text.isEmpty()){
            return Integer.parseInt(text);
        }else{
            throw new EmptyProcessPriorityException();
        }
    }

    public void setInitialInfo(String name, String time, String priority,boolean[] states){
        processNameTxt.setText(name);
        processTimeTxt.setText(time);
        processPriorityTxt.setText(priority);
        isBlockedCb.setSelected(states[0]);
        isSuspendedCb.setSelected(states[1]);
        isDetroyedCb.setSelected(states[2]);
        isComunicateCb.setSelected(states[3]);
        addBtn.setName(name);
    }

    public boolean getIsBlocked(){
        return isBlockedCb.isSelected();
    }

    public boolean getIsSuspended(){
        return isSuspendedCb.isSelected();
    }

    public boolean getIsDestroyed(){
        return isDetroyedCb.isSelected();
    }

    public boolean getIsComunicate(){
        return isComunicateCb.isSelected();
    }
}
