package views;

import presenters.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProcessToCommunicatePanel extends MyGridPanel{

    public ProcessToCommunicatePanel(ActionListener listener, String name){
        setBackground(Color.decode("#FDFEFE"));
        initComponents(listener, name);
        setBorder(BorderFactory.createLineBorder(Color.black, 1,true));
    }

    private void initComponents(ActionListener listener ,String name){
        addComponent(new JLabel(" "), 0, 0, 11, 0.1);
        initNameLb(name);
        initButtons(listener, name);
        addComponent(new JLabel(" "), 0, 3, 11, 0.1);
    }

    private void initNameLb(String name){
        JLabel nameLb = new JLabel(name);
        nameLb.setFont(new Font("Arial", Font.PLAIN, 14));
        addComponent(nameLb, 1,1,3,0.8);
    }

    private void initButtons(ActionListener listener, String name){
        JButton viewInfoBtn = createBtn("Ver informacion", Color.decode("#8E44AD"), listener, Events.VIEW_INFO.toString(), name);
        addComponent(viewInfoBtn, 6,1,2,0.8);
        JButton communicateBtn = createBtn("Comunicar", Color.decode("#16A085"), listener, Events.COMMUNICATE.toString(), name);
        addComponent(communicateBtn, 9,1,2,0.8);
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
