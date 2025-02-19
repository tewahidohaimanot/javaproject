import javax.swing.*;
import java.awt.*;

public class Weight extends JFrame {

    private static final String[] WEIGHT_UNITS = {"grams", "milligrams", "metric tons", "kilograms", "pounds"};

    private JTextField valueField;
    private JComboBox<String> fromUnitCombo;
    private JComboBox<String> toUnitCombo;
    private JTextField resultField;

    public Weight() {
        // Set up the JFrame
        setTitle("Weight Unit Converter");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 5, 5));

        // Initialize components
        valueField = new JTextField(10);
        fromUnitCombo = new JComboBox<>(WEIGHT_UNITS);
        toUnitCombo = new JComboBox<>(WEIGHT_UNITS);
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

            double result = convertWeight(value, fromUnit, toUnit);
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

    public static double convertWeight(double value, String fromUnit, String toUnit) {
        // Conversion logic (same as before)
        double valueInGrams;
        switch (fromUnit.toLowerCase()) {
            case "grams":
                valueInGrams = value;
                break;
            case "milligrams":
                valueInGrams = value * 0.001;
                break;
            case "metric tons":
                valueInGrams = value * 1_000_000;
                break;
            case "kilograms":
                valueInGrams = value * 1_000;
                break;
            case "pounds":
                valueInGrams = value * 453.592;
                break;
            default:
                throw new IllegalArgumentException("Invalid 'from' unit");
        }

        switch (toUnit.toLowerCase()) {
            case "grams":
                return valueInGrams;
            case "milligrams":
                return valueInGrams / 0.001;
            case "metric tons":
                return valueInGrams / 1_000_000;
            case "kilograms":
                return valueInGrams / 1_000;
            case "pounds":
                return valueInGrams / 453.592;
            default:
                throw new IllegalArgumentException("Invalid 'to' unit");
        }
    }
}