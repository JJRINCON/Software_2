package views;

import presenters.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainPanel extends MyGridPanel {

    private ActionListener actionListener;
    private ProcessesPanel processesPanel;
    private MyGridPanel startSimulationPanel;

    public MainPanel(ActionListener actionListener){
        this.actionListener = actionListener;
        setBackground(Color.decode("#FDFEFE"));
        processesPanel = new ProcessesPanel(actionListener);
        addComponent(processesPanel, 0, 1, 2, 1);
        initStartSimulationPanel();
        initExitBtn();
    }

    private void initExitBtn(){
        MyGridPanel exitBtnPanel = new MyGridPanel();
        exitBtnPanel.setBackground(Color.decode("#34495E"));
        JButton exitBtn = createBtn("Salir", Color.RED, actionListener, Events.EXIT.toString());
        exitBtnPanel.addComponentWithInsets(exitBtn, 11, 1, 1, 0.1, new Insets(5,0,5,0));
        addComponent(exitBtnPanel, 0,0, 12, 0.005);
    }

    private JButton createBtn(String txt, Color color, ActionListener listener, String command){
        JButton btn = new JButton(txt);
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.addActionListener(listener);
        btn.setActionCommand(command);
        return btn;
    }

    public void initStartSimulationPanel(){
        //hideReportsPanel();
        startSimulationPanel = new MyGridPanel();
        JButton startSimulationBtn = createBtn("Iniciar Simulacion", Color.decode("#2980B9"),
                actionListener, Events.INIT_SIMULATION.toString());
        startSimulationPanel.setBackground(Color.decode("#FDFEFE"));
        startSimulationPanel.addComponent(new JLabel(" "), 0, 3, 12, 0.3);
        startSimulationPanel.addComponent(startSimulationBtn, 5, 4, 5, 0.05);
        startSimulationPanel.addComponent(new JLabel(" "), 0, 5, 12, 0.4);
        addComponent(startSimulationPanel, 2, 1, 9, 1);
        updateUI();
    }
}
