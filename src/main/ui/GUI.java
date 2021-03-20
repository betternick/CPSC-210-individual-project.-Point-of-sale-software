package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//The Graphical User Interface for the POS
public class GUI extends JFrame implements ActionListener {
    POS pos = new POS();
    JTextArea displayWindow = new JTextArea();


    /*
     * MODIFIES: this
     * EFFECTS: Creates the GUI. Creates display window and buttons.
     */
    public GUI() {
        super("UBC Cafe - Point Of Sale System");
        // Code for Grid Layout as explained in
        // https://docs.oracle.com/javase/tutorial/uiswing/layout/grid.html
        // Setting an icon as explained in
        // https://stackoverflow.com/questions/1614772/how-to-change-jframe-icon
        GridLayout myFramesLayout = new GridLayout(2, 1);
        setLayout(myFramesLayout);

        ImageIcon img = new ImageIcon("./data/ubcIcon.png");

        displayWindow.setFont(new Font("Arial", Font.PLAIN, 40));
        add(displayWindow);

        GridLayout buttonsLayout = new GridLayout(2, 4);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(buttonsLayout);

        createButtons1to4(buttonsPanel);
        createButtons5to8(buttonsPanel);

        add(buttonsPanel);

        setSize(1200, 1200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setIconImage(img.getImage());
    }

    /*
     * MODIFIES: this
     * EFFECTS: Creates the buttons 1, 2, 3 and 4 and sets what action they do.
     */
    public void createButtons1to4(JPanel buttonsPanel) {
        JButton showMenuButton = new JButton("Show Menu");
        showMenuButton.setFont(new Font("Arial", Font.PLAIN, 30));
        showMenuButton.setActionCommand("Show Menu");
        showMenuButton.addActionListener(this);
        JButton addMenuItemButton = new JButton("Add Menu Item");
        addMenuItemButton.setFont(new Font("Arial", Font.PLAIN, 30));
        addMenuItemButton.setActionCommand("Add Menu Item");
        addMenuItemButton.addActionListener(this);
        JButton removeMenuItemButton = new JButton("Remove Menu Item");
        removeMenuItemButton.setFont(new Font("Arial", Font.PLAIN, 30));
        removeMenuItemButton.setActionCommand("Remove Menu Item");
        removeMenuItemButton.addActionListener(this);
        JButton loadMenuButton = new JButton("Load Menu");
        loadMenuButton.setFont(new Font("Arial", Font.PLAIN, 30));
        loadMenuButton.setActionCommand("Load Menu");
        loadMenuButton.addActionListener(this);

        buttonsPanel.add(showMenuButton);
        buttonsPanel.add(addMenuItemButton);
        buttonsPanel.add(removeMenuItemButton);
        buttonsPanel.add(loadMenuButton);


    }

    /*
     * MODIFIES: this
     * EFFECTS: Creates the buttons 5, 6 and 7 and sets what action they do.
     */
    public void createButtons5to8(JPanel buttonsPanel) {
        JButton saveMenuButton = new JButton("Save Menu");
        saveMenuButton.setFont(new Font("Arial", Font.PLAIN, 30));
        saveMenuButton.setActionCommand("Save Menu");
        saveMenuButton.addActionListener(this);
        JButton orderButton = new JButton("Place Order");
        orderButton.setFont(new Font("Arial", Font.PLAIN, 30));
        orderButton.setActionCommand("Place Order");
        orderButton.addActionListener(this);
        JButton viewOrdersButton = new JButton("View Orders");
        viewOrdersButton.setFont(new Font("Arial", Font.PLAIN, 30));
        viewOrdersButton.setActionCommand("View Orders");
        viewOrdersButton.addActionListener(this);
        buttonsPanel.add(saveMenuButton);
        buttonsPanel.add(orderButton);
        buttonsPanel.add(viewOrdersButton);
    }

    /*
     * EFFECTS: Takes button input and runs method prescribed by it.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Code taken from LabelChanger from CPSC 210 Phase 3 instructions.
        String buttonInput = e.getActionCommand();

        if (buttonInput.equals("Add Menu Item")) {
            pos.addItemToMenuUI(this);
        } else if (buttonInput.equals("Remove Menu Item")) {
            pos.removeMenuItemUI(this);
        }
        if (buttonInput.equals("Show Menu")) {
            pos.displayMenu(displayWindow);
        }
        if (buttonInput.equals("Load Menu")) {
            pos.loadMenu();
            displayWindow.append("Menu loaded successfully! \n");
        }
        if (buttonInput.equals("Save Menu")) {
            pos.saveMenu();
            displayWindow.append("Menu saved successfully! \n");
        }
        if (buttonInput.equals("View Orders")) {
            pos.viewOrderUI(displayWindow);
        }
        if (buttonInput.equals("Place Order")) {
            pos.placeOrderUI(this);
        }
    }
}
