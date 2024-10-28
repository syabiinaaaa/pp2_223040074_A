package TugasStudyCase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectTaskManagerApp extends JFrame {

    private DefaultTableModel tableModel;
    private JTable taskTable;

    public ProjectTaskManagerApp() {
        setTitle("Aplikasi Manajemen Tugas Proyek");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Membuat MenuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuItemAddTask = new JMenuItem("Tambah Tugas");
        menu.add(menuItemAddTask);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Panel Utama
        JPanel mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel);

        // Tabel untuk menampilkan daftar tugas
        tableModel = new DefaultTableModel(new String[]{"Nama Tugas", "Deskripsi", "Semester", "Status", "Tenggat Waktu"}, 0);
        taskTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(taskTable);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Panel Form Input Tugas
        TaskFormPanel formPanel = new TaskFormPanel();
        mainPanel.add(formPanel, BorderLayout.NORTH);

        // Menampilkan form ketika menu dipilih
        menuItemAddTask.addActionListener(e -> formPanel.setVisible(true));

        // Mendengarkan event submit form untuk menambah data ke JTable
        formPanel.setFormListener(task -> {
            Object[] row = new Object[]{
                    task.getTaskName(),
                    task.getDescription(),
                    task.getPriority(),
                    task.getStatus(),
                    task.getDeadline()
            };
            tableModel.addRow(row);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProjectTaskManagerApp().setVisible(true));
    }
}

// Panel Form Input Tugas
class TaskFormPanel extends JPanel {

    private JTextField taskNameField;
    private JTextArea descriptionArea;
    private JComboBox<String> priorityComboBox;
    private JRadioButton ongoingRadio;
    private JRadioButton completedRadio;
    private JSpinner deadlineSpinner;
    private JButton submitButton;
    private FormListener formListener;

    public TaskFormPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Form Input Tugas"));

        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5, 5, 5, 5);

        // Komponen Form Input Tugas
        taskNameField = new JTextField(15);
        descriptionArea = new JTextArea(3, 15);
        priorityComboBox = new JComboBox<>(new String[]{"Semester 1", "Semester 2", "Semester 3","Semester 4", "Semester 5", "Semester 6", "Semester 7", "Semester 8"});
        ongoingRadio = new JRadioButton("Berjalan");
        completedRadio = new JRadioButton("Selesai");
        ButtonGroup statusGroup = new ButtonGroup();
        statusGroup.add(ongoingRadio);
        statusGroup.add(completedRadio);
        deadlineSpinner = new JSpinner(new SpinnerDateModel());

        // Label dan Input Form
        gc.gridx = 0; gc.gridy = 0;
        add(new JLabel("Nama Tugas: "), gc);
        gc.gridx = 1;
        add(taskNameField, gc);

        gc.gridx = 0; gc.gridy++;
        add(new JLabel("Deskripsi: "), gc);
        gc.gridx = 1;
        add(new JScrollPane(descriptionArea), gc);

        gc.gridx = 0; gc.gridy++;
        add(new JLabel("Semester: "), gc);
        gc.gridx = 1;
        add(priorityComboBox, gc);

        gc.gridx = 0; gc.gridy++;
        add(new JLabel("Status: "), gc);
        gc.gridx = 1;
        add(ongoingRadio, gc);
        gc.gridx = 2;
        add(completedRadio, gc);

        gc.gridx = 0; gc.gridy++;
        add(new JLabel("Tenggat Waktu: "), gc);
        gc.gridx = 1;
        add(deadlineSpinner, gc);

        submitButton = new JButton("Tambah Tugas");
        gc.gridx = 1; gc.gridy++;
        add(submitButton, gc);

        // Event Listener untuk tombol submit
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName = taskNameField.getText();
                String description = descriptionArea.getText();
                String priority = (String) priorityComboBox.getSelectedItem();
                String status = ongoingRadio.isSelected() ? "Berjalan" : "Selesai";
                Object deadline = deadlineSpinner.getValue();

                // Mengirim data ke listener
                if (formListener != null) {
                    formListener.formEventOccurred(new Task(taskName, description, priority, status, deadline.toString()));
                }

                // Reset form input setelah submit
                taskNameField.setText("");
                descriptionArea.setText("");
                priorityComboBox.setSelectedIndex(0);
                statusGroup.clearSelection();
                deadlineSpinner.setValue(new java.util.Date());
            }
        });
    }

    // Method untuk set listener
    public void setFormListener(FormListener listener) {
        this.formListener = listener;
    }
}

// Interface untuk Form Listener
interface FormListener {
    void formEventOccurred(Task task);
}

// Kelas Task untuk data tugas
class Task {
    private String taskName;
    private String description;
    private String priority;
    private String status;
    private String deadline;

    public Task(String taskName, String description, String priority, String status, String deadline) {
        this.taskName = taskName;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.deadline = deadline;
    }

    // Getters
    public String getTaskName() { return taskName; }
    public String getDescription() { return description; }
    public String getPriority() { return priority; }
    public String getStatus() { return status; }
    public String getDeadline() { return deadline; }
}