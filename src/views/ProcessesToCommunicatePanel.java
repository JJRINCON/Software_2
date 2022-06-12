package views;

import models.MyProcess;
import presenters.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProcessesToCommunicatePanel extends MyGridPanel {

    private JPanel processesPanel;
    private ActionListener listener;

    public ProcessesToCommunicatePanel(ActionListener listener, ArrayList<MyProcess> processesToCommunicate){
        this.listener = listener;
        setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        setBackground(Color.decode("#FDFEFE"));
        initComponents(processesToCommunicate);
    }

    private void initComponents(ArrayList<MyProcess> processesToCommunicate){
        addComponent(new JLabel(" "), 0,0,12, 0.01);
        initTitleLb();
        addComponent(new JLabel(" "), 0,3,12,0.01);
        initProcessesToCommunicatePanel(processesToCommunicate);
        addComponent(new JLabel(" "), 0,5,12,0.01);
        initCancelBtn();
        addComponent(new JLabel(" "), 0,7,12,0.01);
    }

    private void initTitleLb(){
        JLabel titleLb = new JLabel("Procesos disponibles para comunicarse");
        titleLb.setFont(new Font("Arial", Font.BOLD, 20));
        titleLb.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLb, 2, 1, 8, 0.2);
    }

    private void initProcessesToCommunicatePanel(ArrayList<MyProcess> processesToCommunicate){
        verifyRowsNumber(processesToCommunicate);
        addComponent(new JScrollPane(processesPanel), 0,4,12,0.8);
    }

    private void verifyRowsNumber(ArrayList<MyProcess> processesToCommunicate){
        if(processesToCommunicate.size() < 4){
            processesPanel = new JPanel(new GridLayout(4, 1, 5, 5));
            processesPanel.setBackground(Color.decode("#FDFEFE"));
            addProcesses(processesToCommunicate);
            for (int i = 0; i < (4 - processesToCommunicate.size()); i++) {
                processesPanel.add(new JLabel(" "));
            }
        }else{
            processesPanel = new JPanel(new GridLayout(processesToCommunicate.size(), 1, 5,5));
            processesPanel.setBackground(Color.decode("#FDFEFE"));
            addProcesses(processesToCommunicate);
        }
    }

    private void addProcesses(ArrayList<MyProcess> processesToCommunicate){
        for(MyProcess process : processesToCommunicate){
            ProcessToCommunicatePanel processPanel = new ProcessToCommunicatePanel(listener, process.getName());
            processesPanel.add(processPanel);
        }
    }

    public void initCancelBtn(){
        JButton addProcessBtn = new JButton("Cancelar");
        addProcessBtn.setBackground(Color.decode("#E74C3C"));
        addProcessBtn.setForeground(Color.WHITE);
        addProcessBtn.setFont(new Font("Arial", Font.BOLD, 16));
        addProcessBtn.setPreferredSize(new Dimension(500, 40));
        addProcessBtn.addActionListener(listener);
        addProcessBtn.setActionCommand(Events.CANCEL_COMMUNICATE.toString());
        addComponent(addProcessBtn, 5, 6, 2, 0.05);
    }
}
