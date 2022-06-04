package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private static final String TITLE = "Software 2";
    private ActionListener actionlistener;
    private MainPanel mainPanel;

    public MainFrame(ActionListener actionlistener){
        this.actionlistener = actionlistener;
        getContentPane().setLayout(new GridLayout(1,1));
        setTitle(TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        initMainPanel();
    }

    public void initMainPanel(){
        mainPanel = new MainPanel(actionlistener);
        add(mainPanel);
    }
}
