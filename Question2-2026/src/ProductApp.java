import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.PrintWriter;


public class ProductApp extends JFrame {

    String[] PRODUCTS = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};

    JTextField txtName = new JTextField(15);
    JComboBox<String> cmbProduct = new JComboBox<>(PRODUCTS);
    JTextField txtPrice = new JTextField(15);
    JButton btnProcess = new JButton("Process");

    public ProductApp(){
        super("Product App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buildMenu();
        buildForm();
        pack();
        setLocationRelativeTo(null);
    }

    private void buildMenu(){
        JMenuBar bar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        file.add(exit);

        JMenu edit = new JMenu("Edit");
        JMenuItem process = new JMenuItem("Process");
        JMenuItem clear = new JMenuItem("Clear");
        edit.add(process);
        edit.add(clear);

        bar.add(file);
        bar.add(edit);
        setJMenuBar(bar);

        //Exit closes the application
        exit.addActionListener(e -> dispose());

        //Process menu item uses the same logic as the Process button
        process.addActionListener(e -> processSale());

        //Clear resets the text boxes and the combo box index
        clear.addActionListener(e -> clearForm);
    }

    private void buildForm(){
        JPanel form = new JPanel(new GridLayout(4, 2, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(12,12, 12, 12));

        form.add(new JLabel("Customer Name:"));
        form.add(txtName);
        form.add(new JLabel("Select Product"));
        form.add(cmbProduct);
        form.add(new JLabel("Enter Price:"));
        form.add(txtPrice);
        form.add(new JLabel());
        form.add(btnProcess);

        btnProcess.addActionListener(e -> processSale());
        add(form);
    }

    /** Shared by the Process button and the Process menu item. */

    private void processSale(){
        String name = txtName.getText().trim();
        String product = (String) cmbProduct.getSelectedItem();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the customer's name.",
                    "Input error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double price;
        try{
            price = Double.parseDouble(txtPrice.getText().trim());
            if (price < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid price.",
                    "Input error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        IProductCalculations calculations = new ProductCalculations(price);
        double vat = calculations.GetVat();
        double commission = calculations.GetCommission();
        double total = calculations.GetTotal();

        String details = "Customer Name: " + name + "\n"
                + "Product:" + product + "\n"
                + "VAT: R " + vat + "\n"
                + "COMMISSION: R " + commission + "\n"
                + "TOTAL: R " + total;
        JOptionPane.showMessageDialog(this, details, "Message", JOptionPane.INFORMATION_MESSAGE);
        saveToFile(name, product, vat, commission, total);


    }

    private void saveToFile(String name, String product, double vat, double commission, double total){
        // true = append so earlier sales are kept
        try(PrintWriter out = new PrintWriter(new FileWriter("data.txt", true))){
            out.println("DATA LOG");
            out.println("*****************************************");
            out.println("Customer Name: " + name);
            out.println("Product: " + product);
            out.println("VAT: R " + vat);
            out.println("COMMISSION: R " + commission);
            out.println("TOTAL: R " + total);
            out.println("*****************************************");
            out.println();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not write to data.txt: " + ex.getMessage(),
                    "File error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void clearForm() {
        txtName.setText("");
        txtPrice.setText("");
        cmbProduct.setSelectedIndex(0);
        txtName.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductApp().setVisible(true));
    }

}

