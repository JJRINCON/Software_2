package views;

import presenters.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddProcessPanel extends MyGridPanel{

    private JTextField processNameTxt;
    private JTextField processTimeTxt;
    private JTextField processPriorityTxt;
    private JRadioButton isBlockedCb;
    private JRadioButton isSuspendedCb;
    private JRadioButton wakeUpCb;
    private JRadioButton isResumeCb;
    private JRadioButton isDetroyedCb;
    private JRadioButton isComunicateCb;
    private JButton addBtn;

    public AddProcessPanel(ActionListener listener, boolean isEditing) {
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
        initWakeUpCb();
        initIsResumeCb();
        initIsDestroyedCb();
        initIsCommunicateCb();
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
        isBlockedCb = new JRadioButton();
        isBlockedCb.setBackground(Color.WHITE);
        isBlockedCb.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(isBlockedCb, 4, 11, 1, 0.1);
    }

    private void initIsSuspendedCb(){
        JLabel isSuspendedLb = createLb(" Suspender: ", new Font("Arial", Font.BOLD, 14));
        addComponent(isSuspendedLb, 7, 11, 2, 0.1);
        isSuspendedCb = new JRadioButton();
        isSuspendedCb.setBackground(Color.WHITE);
        isSuspendedCb.setHorizontalAlignment(SwingConstants.RIGHT);
        addComponent(isSuspendedCb, 9, 11, 1, 0.1);
        addComponent(new JLabel(" "), 0, 12, 11, 0.1);
    }

    private void initWakeUpCb(){
        JLabel wakeUpLb = createLb(" Despertar: ", new Font("Arial", Font.BOLD, 14));
        addComponent(wakeUpLb, 2, 13, 2, 0.1);
        wakeUpCb = new JRadioButton();
        wakeUpCb.setBackground(Color.WHITE);
        wakeUpCb.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(wakeUpCb, 4, 13, 1, 0.1);
    }

    private void initIsResumeCb(){
        JLabel isResumeLb = createLb(" Reanudar: ", new Font("Arial", Font.BOLD, 14));
        addComponent(isResumeLb, 7, 13, 2, 0.1);
        isResumeCb = new JRadioButton();
        isResumeCb.setBackground(Color.WHITE);
        isResumeCb.setHorizontalAlignment(SwingConstants.RIGHT);
        addComponent(isResumeCb, 9, 13, 1, 0.1);
        addComponent(new JLabel(" "), 0, 14, 11, 0.1);
    }

    private void initIsDestroyedCb(){
        JLabel isDestroyedLb = createLb(" Destruir: ", new Font("Arial", Font.BOLD, 14));
        addComponent(isDestroyedLb, 2, 15, 2, 0.1);
        isDetroyedCb = new JRadioButton();
        isDetroyedCb.setBackground(Color.WHITE);
        isDetroyedCb.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(isDetroyedCb, 4, 15, 1, 0.1);
    }

    private void initIsCommunicateCb(){
        JLabel isCommunicateLb = createLb(" Comunicar: ", new Font("Arial", Font.BOLD, 14));
        addComponent(isCommunicateLb, 7, 15, 2, 0.1);
        isComunicateCb = new JRadioButton();
        isComunicateCb.setBackground(Color.WHITE);
        isComunicateCb.setHorizontalAlignment(SwingConstants.RIGHT);
        addComponent(isComunicateCb, 9, 15, 1, 0.1);
        addComponent(new JLabel(" "), 0, 16, 11, 0.1);
    }

    private void initButtons(ActionListener listener, String acceptEvent, String cancelEvent, boolean isEditing){
        String addBtnTxt = isEditing ? "Editar" : "Agregar";
        addBtn = createBtn(addBtnTxt, Color.decode("#27AE60"), listener, acceptEvent);
        addComponent(addBtn, 3, 17, 2, 0.12);
        JButton cancelBtn = createBtn("Cancelar", Color.decode("#E74C3C"), listener, cancelEvent);
        addComponent(cancelBtn, 7, 17, 2, 0.12);
        addComponent(new JLabel(" "), 0, 18, 11, 0.05);
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

    public String getProcessName() throws Exception {
        if(!processNameTxt.getText().isEmpty()){
            return processNameTxt.getText();
        }else{
            throw new Exception("El proceso debe tener un nombre");
        }
    }

    public int getProcessTime() throws Exception, NumberFormatException {
        String text = processTimeTxt.getText();
        if(!text.isEmpty()){
            return Integer.parseInt(text);
        }else{
            throw new Exception("El proceso debe tener un tiempo");
        }
    }

    public void setInitialInfo(String name, String time, boolean isLocked){
        processNameTxt.setText(name);
        processTimeTxt.setText(time);
        isBlockedCb.setSelected(isLocked);
        addBtn.setName(name);
    }

    public boolean getIsBlocked(){
        return isBlockedCb.isSelected();
    }
}
