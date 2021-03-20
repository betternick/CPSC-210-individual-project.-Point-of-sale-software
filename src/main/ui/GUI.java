package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    POS pos = new POS();
    JTextArea displayWindow = new JTextArea();

    public GUI() {
        super("UBC Cafe POS");
        // Code for Grid Layout taken from
        // https://docs.oracle.com/javase/tutorial/uiswing/layout/grid.html
        GridLayout myFramesLayout = new GridLayout(2,1);
        setLayout(myFramesLayout);

        displayWindow.setFont(new Font("Arial", Font.PLAIN, 40));
        add(displayWindow);

        GridLayout buttonsLayout = new GridLayout(2,4);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(buttonsLayout);

        createButtons1to4(buttonsPanel);
        createButtons5to8(buttonsPanel);

        add(buttonsPanel);

        setSize(1200,1200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

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


    @Override
    public void actionPerformed(ActionEvent e) {
        // Code taken from LabelChanger from CPSC 210 Phase 3 instructions.
        String buttonInput = e.getActionCommand();

        if (buttonInput.equals("Add Menu Item")) {
            pos.addItemToMenuUI(this);
        } else if (buttonInput.equals("Remove Menu Item")) {
            pos.removeMenuItem(this);
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
