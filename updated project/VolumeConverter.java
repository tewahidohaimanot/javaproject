import javax.swing.*;
import java.awt.*;

public class VolumeConverter extends JFrame {

    private static final String[] VOLUME_UNITS = {"liters", "gallons", "cubic meters", "cubic centimeters", "quarts"};

    private JTextField valueField;
    private JComboBox<String> fromUnitCombo;
    private JComboBox<String> toUnitCombo;
    private JTextField resultField;

    public VolumeConverter() {
        // Set up the JFrame
        setTitle("Volume Unit Converter");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 5, 5));

        // Initialize components
        valueField = new JTextField(10);
        fromUnitCombo = new JComboBox<>(VOLUME_UNITS);
        toUnitCombo = new JComboBox<>(VOLUME_UNITS);
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

            double result = convertVolume(value, fromUnit, toUnit);
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

    public static double convertVolume(double value, String fromUnit, String toUnit) {
        double valueInLiters = convertToLiters(value, fromUnit);
        return convertFromLiters(valueInLiters, toUnit);
    }

    private static double convertToLiters(double value, String fromUnit) {
        switch (fromUnit.toLowerCase()) {
            case "liters":
                return value;
            case "gallons":
                return value * 3.78541;
            case "cubic centimeters":
                return value / 1000;
            case "cubic meters":
                return value * 1000;
            case "quarts":
                return value * 0.946353;
            default:
                throw new IllegalArgumentException("Invalid 'from' unit");
        }
    }

    private static double convertFromLiters(double valueInLiters, String toUnit) {
        switch (toUnit.toLowerCase()) {
            case "liters":
                return valueInLiters;
            case "gallons":
                return valueInLiters / 3.78541;
            case "cubic centimeters":
                return valueInLiters * 1000;
            case "cubic meters":
                return valueInLiters / 1000;
            case "quarts":
                return valueInLiters / 0.946353;
            default:
                throw new IllegalArgumentException("Invalid 'to' unit");
        }
    }
}
