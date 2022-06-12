package views;

import models.MyProcess;
import presenters.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProcessesToCommunicatePanel extends MyGridPanel {

    private JPanel processesPanel;
    private JPanel processNotavailable;
    private ActionListener listener;

    public ProcessesToCommunicatePanel(ActionListener listener, ArrayList<MyProcess> processesAvailableToCommunicate,
                                       ArrayList<MyProcess> processesNotAvailableToCommunicate){
        this.listener = listener;
        setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        setBackground(Color.decode("#FDFEFE"));
        initComponents(processesAvailableToCommunicate, processesNotAvailableToCommunicate);
    }

    private void initComponents(ArrayList<MyProcess> processesAvailableToCommunicate, ArrayList<MyProcess> processesNotAvailableToCommunicate){
        addComponent(new JLabel(" "), 0,0,12, 0.01);
        initAvailableProcessLb();
        addComponent(new JLabel(" "), 0,2,12,0.01);
        initAvailableProcessesToCommunicatePanel(processesAvailableToCommunicate);
        addComponent(new JLabel(" "), 0,4,12,0.01);
        initNotAvailableProcessLb();
        addComponent(new JLabel(" "), 0,6,12,0.01);
        initNotAvailableProcessesCommunicatePanel(processesNotAvailableToCommunicate);
        addComponent(new JLabel(" "), 0,8,12,0.01);
        initCancelBtn();
        addComponent(new JLabel(" "), 0,10,12,0.01);
    }

    private void initAvailableProcessLb(){
        JLabel titleLb = new JLabel("Procesos disponibles para comunicarse");
        titleLb.setFont(new Font("Arial", Font.BOLD, 20));
        titleLb.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLb, 2, 1, 8, 0.2);
    }
    private void initAvailableProcessesToCommunicatePanel(ArrayList<MyProcess> processesToCommunicate){
        addComponent(new JScrollPane(verifyRowsNumber(processesToCommunicate, true)), 0,3,12,0.8);
    }

    private void initNotAvailableProcessLb(){
        JLabel titleLb = new JLabel("Procesos no disponibles para comunicarse");
        titleLb.setFont(new Font("Arial", Font.BOLD, 20));
        titleLb.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLb, 2, 5, 8, 0.2);
    }

    private void initNotAvailableProcessesCommunicatePanel(ArrayList<MyProcess> processesNotAvailableToCommunicate){
        addComponent(new JScrollPane(verifyRowsNumber(processesNotAvailableToCommunicate, false)), 0,7,12,0.8);
    }

    private JPanel verifyRowsNumber(ArrayList<MyProcess> processesToCommunicate, boolean isAvailable){
        JPanel panel;
        if(processesToCommunicate.size() < 4){
            panel = new JPanel(new GridLayout(4, 1, 5, 5));
            panel.setBackground(Color.decode("#FDFEFE"));
            addProcesses(processesToCommunicate, panel, isAvailable);
            for (int i = 0; i < (4 - processesToCommunicate.size()); i++) {
                panel.add(new JLabel(" "));
            }
        }else{
            panel = new JPanel(new GridLayout(processesToCommunicate.size(), 1, 5,5));
            panel.setBackground(Color.decode("#FDFEFE"));
            addProcesses(processesToCommunicate, panel, isAvailable);
        }
        return panel;
    }

    private void addProcesses(ArrayList<MyProcess> processesToCommunicate, JPanel panel, boolean isAvailable){
        for(MyProcess process : processesToCommunicate){
            ProcessToCommunicatePanel processPanel = new ProcessToCommunicatePanel(listener, process.getName(), isAvailable);
            panel.add(processPanel);
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
        addComponent(addProcessBtn, 5, 9, 2, 0.05);
    }
}
