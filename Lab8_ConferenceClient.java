import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.rmi.Naming;

public class ConferenceClient {
    private JFrame frame;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField orgField;
    private ConferenceRegistration conferenceRegistration;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ConferenceClient window = new ConferenceClient();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ConferenceClient() {
        try {
            conferenceRegistration = (ConferenceRegistration) Naming.lookup("//localhost/ConferenceRegistration");
        } catch (Exception e) {
            e.printStackTrace();
        }
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(10, 30, 80, 14);
        frame.getContentPane().add(lblName);

        nameField = new JTextField();
        nameField.setBounds(100, 27, 200, 20);
        frame.getContentPane().add(nameField);
        nameField.setColumns(10);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(10, 70, 80, 14);
        frame.getContentPane().add(lblEmail);

        emailField = new JTextField();
        emailField.setBounds(100, 67, 200, 20);
        frame.getContentPane().add(emailField);
        emailField.setColumns(10);

        JLabel lblOrg = new JLabel("Organization:");
        lblOrg.setBounds(10, 110, 80, 14);
        frame.getContentPane().add(lblOrg);

        orgField = new JTextField();
        orgField.setBounds(100, 107, 200, 20);
        frame.getContentPane().add(orgField);
        orgField.setColumns(10);

        JButton btnRegister = new JButton("Register");
        btnRegister.setBounds(150, 150, 100, 23);
        frame.getContentPane().add(btnRegister);
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    String email = emailField.getText();
                    String organization = orgField.getText();
                    Participant participant = new Participant(name, email, organization);
                    conferenceRegistration.registerParticipant(participant);
                    JOptionPane.showMessageDialog(frame, "Participant registered successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}


