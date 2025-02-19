import javax.swing.*;
import java.awt.*;

public class Days extends JFrame {

    private static final String[] TIME_UNITS = {"Days", "Weeks", "Months", "Years", "Centuries"};

    private JTextField valueField;
    private JComboBox<String> fromUnitCombo;
    private JComboBox<String> toUnitCombo;
    private JTextField resultField;

    public Days() {
        // Set up the JFrame
        setTitle("Days Unit Converter");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 5, 5));

        // Initialize components
        valueField = new JTextField(10);
        fromUnitCombo = new JComboBox<>(TIME_UNITS);
        toUnitCombo = new JComboBox<>(TIME_UNITS);
        resultField = new JTextField(10);
        resultField.setEditable(false);

        JButton convertButton = new JButton("Convert");
        JButton clearButton = new JButton("Clear");
        JButton homePage = new JButton("Return to Home Page");

        // Add components to frame
        add(new JLabel("Enter value:"));
        add(valueField);
        add(new JLabel("From unit:"));
        add(fromUnitCombo);
        add(new JLabel("To unit:"));
        add(toUnitCombo);
        add(convertButton);
        add(clearButton);
        add(new JLabel("Result:"));
        add(resultField);
        add(homePage);

        // Action listeners
        convertButton.addActionListener(e -> convert());
        clearButton.addActionListener(e -> clearFields());
        homePage.addActionListener(e -> returnToHome());

        // Display the frame
        setVisible(true);
    }

    private void convert() {
        try {
            double value = Double.parseDouble(valueField.getText());
            String fromUnit = (String) fromUnitCombo.getSelectedItem();
            String toUnit = (String) toUnitCombo.getSelectedItem();

            double result = convertTime(value, fromUnit, toUnit);
            resultField.setText(String.format("%.2f %s", result, toUnit));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        valueField.setText("");
        resultField.setText("");
        fromUnitCombo.setSelectedIndex(0);
        toUnitCombo.setSelectedIndex(0);
    }

    private void returnToHome() {
        dispose();
        new Home();
    }

    public static double convertTime(double value, String fromUnit, String toUnit) {
        double valueInDays = convertToDays(value, fromUnit);
        return convertFromDays(valueInDays, toUnit);
    }

    private static double convertToDays(double value, String fromUnit) {
        switch (fromUnit.toLowerCase()) {
            case "days":
                return value;
            case "weeks":
                return value * 7;
            case "months":
                return value * 30.00;
            case "years":
                return value * 365.00;
            case "centuries":
                return value * 36525;
            default:
                throw new IllegalArgumentException("Invalid 'from' unit");
        }
    }

    private static double convertFromDays(double valueInDays, String toUnit) {
        switch (toUnit.toLowerCase()) {
            case "days":
                return valueInDays;
            case "weeks":
                return valueInDays / 7;
            case "months":
                return valueInDays / 30.00;
            case "years":
                return valueInDays / 365.00;
            case "centuries":
                return valueInDays / 36525;
            default:
                throw new IllegalArgumentException("Invalid 'to' unit");
        }
    }

    public static void main(String[] args) {
        new Days();
    }
}
