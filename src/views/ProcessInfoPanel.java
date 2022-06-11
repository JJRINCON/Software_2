package views;

import models.MyProcess;
import presenters.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProcessInfoPanel extends MyGridPanel {

    private static final String CLOSE_BTN_TXT = "Cerrar";

    public ProcessInfoPanel(ActionListener actionListener, MyProcess process){
        setBackground(Color.decode("#FDFEFE"));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        initComponents(actionListener, process);
    }

    private void initComponents(ActionListener actionListener, MyProcess process){
        initName(process.getName());
        initProcessInfo(process);
        initIsBlockedRb(process.isLocked());
        initIsSuspendedRb(process.isSuspended());
        initIsDestroyedRb(process.isDestroid());
        initIsCommunicateRb(process.isComunication());
        initCloseBtn(process.getName(), actionListener);
    }

    private void initName(String name){
        addComponent(new JLabel(" "), 0, 0, 11, 0.1);
        JLabel titleLb = createLb("  " +name, new Font("Arial", Font.BOLD, 20));
        titleLb.setHorizontalTextPosition(SwingConstants.CENTER);
        titleLb.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLb, 3, 1, 4, 0.2);
        addComponent(new JLabel(" "), 0, 3, 11, 0.1);
    }

    private void initProcessInfo(MyProcess process){
        JLabel timeLb = createLb("Tiempo: " + process.getTime(), new Font("Arial", Font.PLAIN, 14));
        addComponent(timeLb, 2, 4, 3, 0.1);
        JLabel priorityLb = createLb("Prioridad: " + process.getPriority(), new Font("Arial", Font.PLAIN, 14));
        addComponent(priorityLb, 7, 4, 3, 0.1);
        addComponent(new JLabel(" "), 0, 5, 11, 0.1);
    }

    private void initIsBlockedRb(boolean isBlocked){
        JLabel isBlockedLb = createLb("Bloqueo: ", new Font("Arial", Font.BOLD, 14));
        addComponent(isBlockedLb, 2, 6, 1, 0.1);
        JRadioButton isBlockedCb = createRB(isBlocked);
        addComponent(isBlockedCb, 4, 6, 1, 0.1);
    }

    private void initIsSuspendedRb(boolean isSuspended){
        JLabel isSuspendedLb = createLb("Suspender: ", new Font("Arial", Font.BOLD, 14));
        addComponent(isSuspendedLb, 7, 6, 1, 0.1);
        JRadioButton isSuspendedCb = createRB(isSuspended);
        addComponent(isSuspendedCb, 9, 6, 1, 0.1);
        addComponent(new JLabel(" "), 0, 7, 11, 0.1);
    }

    private void initIsDestroyedRb(boolean isDestroyed){
        JLabel isDestroyedLb = createLb("Destruir: ", new Font("Arial", Font.BOLD, 14));
        addComponent(isDestroyedLb, 2, 10, 1, 0.1);
        JRadioButton isDestroyedCb = createRB(isDestroyed);
        addComponent(isDestroyedCb, 4, 10, 1, 0.1);
    }

    private void initIsCommunicateRb(boolean isCommnicate){
        JLabel isCommunicateLb = createLb("Comunicar: ", new Font("Arial", Font.BOLD, 14));
        addComponent(isCommunicateLb, 7, 10, 1, 0.1);
        JRadioButton isCommunicateCb = createRB(isCommnicate);
        addComponent(isCommunicateCb, 9, 10, 1, 0.1);
        addComponent(new JLabel(" "), 0, 11, 11, 0.1);
    }

    private void initCloseBtn(String name, ActionListener actionListener){
        JButton closeBtn = new JButton(CLOSE_BTN_TXT);
        closeBtn.setName(name);
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setBackground(Color.decode("#E74C3C"));
        closeBtn.setFont(new Font("Arial", Font.BOLD, 15));
        closeBtn.addActionListener(actionListener);
        closeBtn.setActionCommand(Events.CLOSE_INFO.toString());
        addComponent(closeBtn, 5, 12, 2, 0.1);
        addComponent(new JLabel(" "), 0, 13, 11, 0.1);
    }

    private JLabel createLb(String txt, Font font){
        JLabel lb = new JLabel(txt);
        lb.setFont(font);
        return lb;
    }

    private JRadioButton createRB(boolean selected){
        JRadioButton rb = new JRadioButton();
        rb.setBackground(Color.WHITE);
        rb.setSelected(selected);
        rb.setEnabled(false);
        rb.setHorizontalAlignment(SwingConstants.CENTER);
        return rb;
    }
}
