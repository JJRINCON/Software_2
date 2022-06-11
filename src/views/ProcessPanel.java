package views;

import presenters.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProcessPanel extends MyGridPanel{

    private static final String DELETE_BTN_TXT = "Eliminar";
    private static final String EDIT_BTN_TXT = "Editar";
    private static final String VIEW_INFO_BTN_TXT = "Ver informacion";
    private static final String COMMUNICATE_PROCESS_BTN_TXT = "Comunicar Procesos";

    public ProcessPanel(ActionListener actionListener, String processName){
        setBackground(Color.decode("#FDFEFE"));
        initComponents(actionListener, processName);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
    }

    private void initComponents(ActionListener actionListener, String processName){
        addComponent(new JLabel(" "), 0, 0, 12, 0.2);
        JLabel lbName = new JLabel(processName);
        lbName.setFont(new Font("Arial", Font.BOLD, 16));
        addComponent(lbName, 1, 1, 1, 1);
        addComponent(new JLabel(" "), 0, 2, 12, 0.2);
        initButtons(actionListener,processName);
    }

    private void initButtons(ActionListener listener, String name){
        JButton viewInfoBtn = createBtn(VIEW_INFO_BTN_TXT, Color.decode("#839192"), listener, Events.VIEW_INFO.toString(), name);
        addComponent(viewInfoBtn, 4,1,1,0.5);
        JButton communicateProcessBtn = createBtn(COMMUNICATE_PROCESS_BTN_TXT, Color.decode("#884EA0"), listener,
                                                    Events.COMMUNICATE_PROCESS.toString(), name);
        addComponent(communicateProcessBtn, 6,1,1,0.5);
        JButton editBtn = createBtn(EDIT_BTN_TXT, Color.decode("#2471A3"), listener, Events.EDIT_PROCESS.toString(), name);
        addComponent(editBtn, 8,1,1,0.5);
        JButton deleteButton = createBtn(DELETE_BTN_TXT, Color.decode("#E74C3C"), listener, Events.DELETE_PROCESS.toString(), name);
        addComponent(deleteButton, 10, 1,1,0.5);
    }

    private JButton createBtn(String txt, Color color, ActionListener listener, String command, String name){
        JButton btn = new JButton(txt);
        btn.setName(name);
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setFont(new Font("Arial", Font.BOLD, 15));
        btn.addActionListener(listener);
        btn.setActionCommand(command);
        return btn;
    }
}
