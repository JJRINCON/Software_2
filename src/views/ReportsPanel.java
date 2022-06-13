package views;

import models.MyProcess;
import models.OperatingSystem;
import presenters.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReportsPanel extends JPanel {

    private static final String[] COLUMNS = {"Nombre", "Tiempo", "Bloqueo", "Suspende", "Destruye", "Comunica"};
    private static final String[] COMMUNICATE_COLUMNS = {"Nombre", "Se comunica con"};

    public ReportsPanel(ArrayList<MyProcess> readyProcess, ArrayList<MyProcess> dispatchedProcess,
                        ArrayList<MyProcess> executingProcess, ArrayList<MyProcess> toLockedProcess,
                        ArrayList<MyProcess> lockedProcess, ArrayList<MyProcess> wakeUpProcess,
                        ArrayList<MyProcess> expiredProcess, ArrayList<MyProcess> toSuspendedProcess,
                        ArrayList<MyProcess> suspendedProcess, ArrayList<MyProcess> reanudedProcess,
                        ArrayList<MyProcess> lockedToSuspendedProcess, ArrayList<MyProcess> lockedToDestroyedProcess,
                        ArrayList<MyProcess> suspendedToDestroyedProcess, ArrayList<MyProcess> toDestroyedProcess,
                        ArrayList<MyProcess> destroyedProcess, ArrayList<MyProcess> destroyedToExecutionProcess,
                        ArrayList<MyProcess> communicationProcess, ArrayList<MyProcess> terminatedProcess,
                        ActionListener listener) {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#FDFEFE"));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        initTitle(listener);
        JTabbedPane reports = new JTabbedPane();
        reports.setBackground(Color.decode("#FDFEFE"));
        TablePanel readyTable = new TablePanel(OperatingSystem.processInfo(readyProcess), COLUMNS);
        reports.add("Listos", readyTable);

        TablePanel dispatchedTable = new TablePanel(OperatingSystem.processInfo(dispatchedProcess), COLUMNS);
        reports.add("Despachados", dispatchedTable);

        TablePanel executingTable = new TablePanel(OperatingSystem.processInfo(executingProcess), COLUMNS);
        reports.add("En ejecucion", executingTable);

        TablePanel toLockedTable = new TablePanel(OperatingSystem.processInfo(toLockedProcess), COLUMNS);
        reports.add("A Bloqueados", toLockedTable);

        TablePanel lockedTable = new TablePanel(OperatingSystem.processInfo(lockedProcess), COLUMNS);
        reports.add("Bloqueados", lockedTable);

        TablePanel wakeUpTable = new TablePanel(OperatingSystem.processInfo(wakeUpProcess), COLUMNS);
        reports.add("Despertados", wakeUpTable);

        TablePanel expiredTable = new TablePanel(OperatingSystem.processInfo(expiredProcess), COLUMNS);
        reports.add("Tiempo expirado", expiredTable);

        TablePanel toSuspendedTable = new TablePanel(OperatingSystem.processInfo(toSuspendedProcess), COLUMNS);
        reports.add("A suspendidos", toSuspendedTable);

        TablePanel suspendedTable = new TablePanel(OperatingSystem.processInfo(suspendedProcess), COLUMNS);
        reports.add("Suspendidos", suspendedTable);

        TablePanel reanudeTable = new TablePanel(OperatingSystem.processInfo(reanudedProcess), COLUMNS);
        reports.add("Reanudados", reanudeTable);

        TablePanel lockedToSuspendedTable = new TablePanel(OperatingSystem.processInfo(lockedToSuspendedProcess), COLUMNS);
        reports.add("De bloqueo a suspendido", lockedToSuspendedTable);

        TablePanel lockedToDestroyedTable = new TablePanel(OperatingSystem.processInfo(lockedToDestroyedProcess), COLUMNS);
        reports.add("De bloqueo a destruir", lockedToDestroyedTable);

        TablePanel suspendedToDestroyedTable = new TablePanel(OperatingSystem.processInfo(suspendedToDestroyedProcess), COLUMNS);
        reports.add("De suspendido a destruido", suspendedToDestroyedTable);

        TablePanel toDestroyedTable = new TablePanel(OperatingSystem.processInfo(toDestroyedProcess), COLUMNS);
        reports.add("A destruir", toDestroyedTable);

        TablePanel destroyedTable = new TablePanel(OperatingSystem.processInfo(destroyedProcess), COLUMNS);
        reports.add("Destruidos", destroyedTable);

        TablePanel destroyedToExecutionTable = new TablePanel(OperatingSystem.processInfo(destroyedToExecutionProcess), COLUMNS);
        reports.add("De destruidos a ejecucion", destroyedToExecutionTable);

        TablePanel communicationTable = new TablePanel(OperatingSystem.processCommunicateInfo(communicationProcess), COMMUNICATE_COLUMNS);
        reports.add("Se comunican", communicationTable);

        TablePanel terminatedTable = new TablePanel(OperatingSystem.processInfo(terminatedProcess), COLUMNS);
        reports.add("terminados", terminatedTable);
        add(reports, BorderLayout.CENTER);

        JButton newSimulationBtn = createBtn("Nueva simulacion", Color.decode("#2980B9"), listener,
                Events.NEW_SIMULATION.toString());
        newSimulationBtn.setFont(new Font("Arial", Font.BOLD, 16));
        newSimulationBtn.setPreferredSize(new Dimension(100, 40));
        add(newSimulationBtn, BorderLayout.SOUTH);
    }

    private void initTitle(ActionListener listener){
        MyGridPanel titlePanel = new MyGridPanel();
        titlePanel.setBackground(Color.decode("#16A085"));
        JLabel titleLb = new JLabel("REPORTES");
        titleLb.setForeground(Color.WHITE);
        titleLb.setFont(new Font("Arial", Font.BOLD, 20));
        titleLb.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLb, BorderLayout.NORTH);
        titlePanel.addComponent(titleLb, 3, 1, 6, 0.1);
        //JButton exportBtn = createBtn("Exportar a PDF", Color.decode("#2980B9"), listener, Events.EXPORT.toString());
        //titlePanel.addComponent(exportBtn, 10, 1, 2, 0.1);
        add(titlePanel, BorderLayout.NORTH);
    }

    private JButton createBtn(String txt, Color color, ActionListener listener, String command){
        JButton btn = new JButton(txt);
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.addActionListener(listener);
        btn.setActionCommand(command);
        return btn;
    }
}
