package presenters;

import views.AddProcessDialog;
import views.MainFrame;
import views.ProcessInfoDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Presenter implements ActionListener {

    private MainFrame mainFrame;
    private AddProcessDialog addProcessDialog;
    private AddProcessDialog editProcessDialog;
    private ProcessInfoDialog processInfoDialog;

    public Presenter(){
        mainFrame = new MainFrame(this);
        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Events.valueOf(e.getActionCommand())){
            case ADD_PROCESS:
                manageAddProcessAction();
                break;
            case ACCEPT :
                manageAcceptAction();
                break;
            case CANCEL:
                manageCancelAction();
                break;
            case ACCEPT_EDIT:
                manageAcceptEditAction();
                break;
            case CANCEL_EDIT:
                manageCancelEditAction();
                break;
            case VIEW_INFO:
                manageViewInfoAction();
                break;
            case INIT_SIMULATION:
                break;
            case CLOSE_INFO:
                manaegeCloseInfoAction();
                break;
            case EXIT:
                System.exit(0);
                break;
        }
    }

    private void manageAddProcessAction() {
        addProcessDialog = new AddProcessDialog(this, false);
        addProcessDialog.setVisible(true);
    }

    private void manageAcceptAction() {
    }

    private void manageCancelAction() {
        addProcessDialog.dispose();
    }

    private void manageAcceptEditAction() {
    }

    private void manageCancelEditAction() {
    }

    private void manageViewInfoAction() {
        processInfoDialog = new ProcessInfoDialog(this, false);
        processInfoDialog.setVisible(true);
    }

    private void manaegeCloseInfoAction() {
        processInfoDialog.dispose();
    }
}
