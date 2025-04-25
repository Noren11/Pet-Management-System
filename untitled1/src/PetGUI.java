import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PetGUI extends JFrame {
    private JTextField nameField, typeField, ageField, ownerField;
    private JButton addButton, viewButton, updateButton, deleteButton;
    private JTextArea displayArea;

    public PetGUI() {

        setTitle("Pet Management System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());


        nameField = new JTextField(20);
        typeField = new JTextField(20);
        ageField = new JTextField(20);
        ownerField = new JTextField(20);

        addButton = new JButton("Add Pet");
        viewButton = new JButton("View Pets");
        updateButton = new JButton("Update Pet");
        deleteButton = new JButton("Delete Pet");

        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);


        add(new JLabel("Pet Name:"));
        add(nameField);
        add(new JLabel("Pet Type:"));
        add(typeField);
        add(new JLabel("Pet Age:"));
        add(ageField);
        add(new JLabel("Owner Name:"));
        add(ownerField);

        add(addButton);
        add(viewButton);
        add(updateButton);
        add(deleteButton);

        add(scrollPane);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPet();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewPets();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePet();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletePet();
            }
        });
    }


    private void addPet() {
        try {
            String name = nameField.getText();
            String type = typeField.getText();
            int age = Integer.parseInt(ageField.getText());
            String owner = ownerField.getText();

            if (name.isEmpty() || type.isEmpty() || owner.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            Pet pet = new Pet(name, type, age, owner);
            PetManager.addPet(pet);
            clearFields();
            JOptionPane.showMessageDialog(this, "Pet added successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for age.");
        }
    }


    private void viewPets() {
        List<Pet> pets = PetManager.getAllPets();
        displayArea.setText("");
        if (pets.isEmpty()) {
            displayArea.append("No pets found in the database.\n");
        } else {
            for (Pet pet : pets) {
                displayArea.append("ID: " + pet.getId() + "\n");
                displayArea.append("Name: " + pet.getName() + "\n");
                displayArea.append("Type: " + pet.getType() + "\n");
                displayArea.append("Age: " + pet.getAge() + "\n");
                displayArea.append("Owner: " + pet.getOwnerName() + "\n");
                displayArea.append("-------------------------\n");
            }
        }
    }


    private void updatePet() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Pet ID to update:"));
            if (id <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid Pet ID.");
                return;
            }

            String name = nameField.getText();
            String type = typeField.getText();
            int age = Integer.parseInt(ageField.getText());
            String owner = ownerField.getText();

            if (name.isEmpty() || type.isEmpty() || owner.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }


            if (age <= 0) {
                JOptionPane.showMessageDialog(this, "Age must be a positive integer.");
                return;
            }

            Pet pet = new Pet(name, type, age, owner);
            pet.setId(id);
            PetManager.updatePet(pet);
            clearFields();
            JOptionPane.showMessageDialog(this, "Pet updated successfully!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for age.");
        }
    }

    // Method to delete a pet
    private void deletePet() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Pet ID to delete:"));
            if (id <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid Pet ID.");
                return;
            }
            PetManager.deletePet(id);
            JOptionPane.showMessageDialog(this, "Pet deleted successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for Pet ID.");
        }
    }


    private void clearFields() {
        nameField.setText("");
        typeField.setText("");
        ageField.setText("");
        ownerField.setText("");
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PetGUI().setVisible(true);
            }
        });
    }
}
