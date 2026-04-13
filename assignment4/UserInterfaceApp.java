package assignment4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class UserInterfaceApp extends JFrame {

    // Input components
    private JTextField billField;
    private JComboBox<String> tipComboBox;
    private JSpinner splitSpinner;

    // Output labels
    private JLabel tipAmountLabel;
    private JLabel totalAmountLabel;
    private JLabel perPersonLabel;

    // Tip percentage options
    private static final String[] TIP_OPTIONS = {"10%", "15%", "18%", "20%", "25%"};

    public UserInterfaceApp() {
        setTitle("Tip Calculator");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initComponents();
        initListeners();

        setVisible(true);
    }

    // Build and arrange all GUI components
    private void initComponents() {
        // Title panel (NORTH)
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Tip Calculator");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titlePanel.add(titleLabel);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        add(titlePanel, BorderLayout.NORTH);

        // Input panel (CENTER) — GridLayout for form-style alignment
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        inputPanel.add(new JLabel("Bill Amount ($):"));
        billField = new JTextField();
        inputPanel.add(billField);

        inputPanel.add(new JLabel("Tip Percentage:"));
        tipComboBox = new JComboBox<>(TIP_OPTIONS);
        tipComboBox.setSelectedIndex(1); // Default to 15%
        inputPanel.add(tipComboBox);

        inputPanel.add(new JLabel("Number of People:"));
        splitSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        inputPanel.add(splitSpinner);

        add(inputPanel, BorderLayout.CENTER);

        // Bottom panel - holds buttons and results
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        JButton calculateButton = new JButton("Calculate");
        JButton clearButton = new JButton("Clear");
        calculateButton.setActionCommand("calculate");
        clearButton.setActionCommand("clear");
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);

        // Result panel
        JPanel resultPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        tipAmountLabel = new JLabel("Tip Amount: $0.00");
        totalAmountLabel = new JLabel("Total Amount: $0.00");
        perPersonLabel = new JLabel("Per Person: $0.00");
        tipAmountLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        totalAmountLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        perPersonLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        resultPanel.add(tipAmountLabel);
        resultPanel.add(totalAmountLabel);
        resultPanel.add(perPersonLabel);
        bottomPanel.add(resultPanel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Attach event listeners to interactive components
    private void initListeners() {
        // Find buttons by their action commands
        JPanel bottomPanel = (JPanel) ((BorderLayout) getContentPane().getLayout())
                .getLayoutComponent(BorderLayout.SOUTH);
        JPanel buttonPanel = (JPanel) ((BorderLayout) bottomPanel.getLayout())
                .getLayoutComponent(BorderLayout.NORTH);

        for (Component comp : buttonPanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                if ("calculate".equals(button.getActionCommand())) {
                    button.addActionListener(e -> calculateAndDisplay());
                } else if ("clear".equals(button.getActionCommand())) {
                    button.addActionListener(e -> clearAll());
                }
            }
        }

        // Pressing Enter in the text field triggers calculation
        billField.addActionListener(e -> calculateAndDisplay());

        // Dynamic updates when tip percentage or split count changes
        tipComboBox.addActionListener(e -> dynamicUpdate());
        splitSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                dynamicUpdate();
            }
        });
    }

    // Parse inputs, compute tip/total/per-person, and update result labels
    private void calculateAndDisplay() {
        try {
            double bill = Double.parseDouble(billField.getText().trim());

            if (bill < 0) {
                tipAmountLabel.setText(
                    "<html><font color='red'>Bill amount cannot be negative</font></html>");
                totalAmountLabel.setText("");
                perPersonLabel.setText("");
                return;
            }

            double tipPercent = getTipPercentage();
            int numPeople = (int) splitSpinner.getValue();

            double tipAmount = bill * tipPercent / 100.0;
            double totalAmount = bill + tipAmount;
            double perPerson = totalAmount / numPeople;

            tipAmountLabel.setText(String.format("Tip Amount: $%.2f", tipAmount));
            totalAmountLabel.setText(String.format("Total Amount: $%.2f", totalAmount));
            perPersonLabel.setText(String.format("Per Person: $%.2f", perPerson));

        } catch (NumberFormatException ex) {
            tipAmountLabel.setText(
                "<html><font color='red'>Please enter a valid bill amount</font></html>");
            totalAmountLabel.setText("");
            perPersonLabel.setText("");
        }
    }

    // Recalculate silently when combo box or spinner changes (no error if field is empty)
    private void dynamicUpdate() {
        String text = billField.getText().trim();
        if (!text.isEmpty()) {
            try {
                Double.parseDouble(text);
                calculateAndDisplay();
            } catch (NumberFormatException ex) {
                // Ignore invalid input during dynamic updates
            }
        }
    }

    // Reset all inputs and output labels to defaults
    private void clearAll() {
        billField.setText("");
        tipComboBox.setSelectedIndex(1); // Back to 15%
        splitSpinner.setValue(1);
        tipAmountLabel.setText("Tip Amount: $0.00");
        totalAmountLabel.setText("Total Amount: $0.00");
        perPersonLabel.setText("Per Person: $0.00");
    }

    // Extract the numeric tip percentage from the combo box selection
    private double getTipPercentage() {
        String selected = (String) tipComboBox.getSelectedItem();
        return Double.parseDouble(selected.replace("%", ""));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserInterfaceApp());
    }
}
