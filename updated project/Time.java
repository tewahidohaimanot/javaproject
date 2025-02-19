import javax.swing.*;
import java.awt.*;

public class Time extends JFrame {

    private static final String[] TIME_UNITS = {"hours", "minutes", "seconds", "microseconds"};

    private JTextField valueField;
    private JComboBox<String> fromUnitCombo;
    private JComboBox<String> toUnitCombo;
    private JTextField resultField;

    public Time() {
        // Set up the JFrame
        setTitle("Time Unit Converter");
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
        double valueInSeconds;

        switch (fromUnit.toLowerCase()) {
            case "microseconds":
                valueInSeconds = value / 1_000_000;
                break;
            case "seconds":
                valueInSeconds = value;
                break;
            case "minutes":
                valueInSeconds = value * 60;
                break;
            case "hours":
                valueInSeconds = value * 3600;
                break;
            default:
                throw new IllegalArgumentException("Invalid 'from' unit");
        }

        switch (toUnit.toLowerCase()) {
            case "microseconds":
                return valueInSeconds * 1_000_000;
            case "seconds":
                return valueInSeconds;
            case "minutes":
                return valueInSeconds / 60;
            case "hours":
                return valueInSeconds / 3600;
            default:
                throw new IllegalArgumentException("Invalid 'to' unit");
        }
    }
}
