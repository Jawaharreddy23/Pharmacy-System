package PharmacyInventoryApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class PharmacyManagementSystem extends JFrame {

    private Map<String, Integer> inventory;

    private JTextArea inventoryTextArea;

    public PharmacyManagementSystem() {
        setTitle("Pharmacy Management System");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inventory = new HashMap<>();

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Pharmacy Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(60, 10, 280, 25);
        panel.add(titleLabel);

        JLabel medicationLabel = new JLabel("Medication Name:");
        medicationLabel.setBounds(20, 50, 150, 25);
        panel.add(medicationLabel);

        JTextField medicationTextField = new JTextField();
        medicationTextField.setBounds(180, 50, 150, 25);
        panel.add(medicationTextField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(20, 80, 80, 25);
        panel.add(quantityLabel);

        JTextField quantityTextField = new JTextField();
        quantityTextField.setBounds(180, 80, 50, 25);
        panel.add(quantityTextField);

        JButton addButton = new JButton("Add to Inventory");
        addButton.setBounds(20, 110, 150, 25);
        panel.add(addButton);

        inventoryTextArea = new JTextArea();
        inventoryTextArea.setBounds(20, 150, 330, 250);
        inventoryTextArea.setEditable(false);
        panel.add(inventoryTextArea);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToInventory(medicationTextField.getText(), quantityTextField.getText());
            }
        });

        JButton generateReportButton = new JButton("Generate Report");
        generateReportButton.setBounds(20, 410, 150, 25);
        panel.add(generateReportButton);

        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReport();
            }
        });
    }

    private void addToInventory(String medication, String quantityText) {
        try {
            int quantity = Integer.parseInt(quantityText);
            if (quantity > 0) {
                inventory.put(medication, inventory.getOrDefault(medication, 0) + quantity);
                updateInventoryText();
            } else {
                JOptionPane.showMessageDialog(null, "Quantity must be a positive integer.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid quantity. Please enter a numeric value.");
        }
    }

    private void updateInventoryText() {
        StringBuilder inventoryText = new StringBuilder("Inventory:\n");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            inventoryText.append(entry.getKey()).append(" - ").append(entry.getValue()).append(" units\n");
        }
        inventoryTextArea.setText(inventoryText.toString());
    }

    private void generateReport() {
        StringBuilder report = new StringBuilder("Inventory Report:\n");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            report.append(entry.getKey()).append(" - ").append(entry.getValue()).append(" units\n");
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PharmacyManagementSystem();
            }
        });
    }
}
