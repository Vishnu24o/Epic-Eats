package EPICEATS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Backend Logic Class
class BillCalculator {
    int frenchFriesPrice = 100;
    int pizzaPrice = 200;
    int cocaColaPrice = 50;
    int chickenPrice = 300;
    int burgerPrice = 150;
    int carryBagPrice = 10;

    public double calculateTotal(int ff, int p, int cc, int ch, int b, boolean carryBag) {
        int total = (ff * frenchFriesPrice) + (p * pizzaPrice) +
                (cc * cocaColaPrice) + (ch * chickenPrice) +
                (b * burgerPrice);

        if (carryBag) total += carryBagPrice;

        double gst = 0.05 * total;
        return total + gst;
    }
}

// Frontend GUI Class
public class EPICEATSproject extends JFrame implements ActionListener {

    JTextField ffField, pizzaField, cokeField, chickenField, burgerField;
    JCheckBox carryBagBox;
    JComboBox<String> orderTypeBox;
    JTextArea billArea;

    BillCalculator calculator = new BillCalculator();

    EPICEATSproject() {
        setTitle("Epic Eats Billing System");
        setSize(400, 500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new JLabel("French Fries:"));
        ffField = new JTextField(5);
        add(ffField);

        add(new JLabel("Pizza:"));
        pizzaField = new JTextField(5);
        add(pizzaField);

        add(new JLabel("Coca Cola:"));
        cokeField = new JTextField(5);
        add(cokeField);

        add(new JLabel("Chicken:"));
        chickenField = new JTextField(5);
        add(chickenField);

        add(new JLabel("Burger:"));
        burgerField = new JTextField(5);
        add(burgerField);

        orderTypeBox = new JComboBox<>(new String[]{"Dine-in", "Take-away"});
        add(orderTypeBox);

        carryBagBox = new JCheckBox("Add Carry Bag (₹10)");
        add(carryBagBox);

        JButton btn = new JButton("Generate Bill");
        btn.addActionListener(this);
        add(btn);

        billArea = new JTextArea(10, 30);
        add(billArea);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            int ff = Integer.parseInt(ffField.getText());
            int p = Integer.parseInt(pizzaField.getText());
            int cc = Integer.parseInt(cokeField.getText());
            int ch = Integer.parseInt(chickenField.getText());
            int b = Integer.parseInt(burgerField.getText());

            boolean carryBag = carryBagBox.isSelected();
            String orderType = (String) orderTypeBox.getSelectedItem();

            double total = calculator.calculateTotal(ff, p, cc, ch, b, carryBag);

            billArea.setText("--- EPIC EATS BILL ---\n");
            billArea.append("Order Type: " + orderType + "\n");
            billArea.append("Total (incl. GST): ₹" + String.format("%.2f", total) + "\n");

            if (carryBag) billArea.append("Carry Bag Added\n");

            billArea.append("\nThank you! Visit Again!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers!");
        }
    }

    public static void main(String[] args) {
        new EPICEATSproject();
    }
}
