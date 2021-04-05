package myStore;/**
 * @author Michael Silveira
 * @studentID 101145789
 * @date March 21th 2021
 * @milestone 3
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import static java.awt.BorderLayout.*;

/**
 * The StoreView class is a UI frontend for each shopping cart.
 */
public class StoreView {
    private final JFrame view;
    private JPanel contentPane, inventoryPanel, cartPanel;
    private final StoreManager manager;
    private ShoppingCart cart;
    private String nick;

    public static final Dimension buttonDimension = new Dimension(200,25);

    /**
     * This constructor innitializes Storeview.
     * @param name is a String for nickname.
     * @param manager is a Manager Object.
     */
    public StoreView(String name, StoreManager manager){
        this.manager = manager;
        this.nick = name;
        this.cart = this.manager.getCart(this.nick);

        view = new JFrame("StoreView ID: " + name);
        view.setSize(800,800);
        view.setPreferredSize(new Dimension(800, 800));
        view.setLayout(new BorderLayout());
        view.setResizable(true);
        view.setLocationRelativeTo(null);
        view.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout());
        inventoryPanel = getInventoryPanel();
        contentPane.add(inventoryPanel);
        cartPanel = getCartPanel();
        contentPane.add(cartPanel);

        view.add(contentPane, CENTER);
        view.add(getControlPane(), PAGE_END);

    }

    /**
     * This method is an accessor for the nickname.
     * @return is a String.
     */
    public String getNick() {
        return nick;
    }

    /**
     * This method is an accessor for the cart.
     * @return is a ShoppingCart Object.
     */
    public ShoppingCart getCart() {
        return cart;
    }

    /**
     * This method sets the nickname from a given String.
     * @param nick is a String.
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * This method sets the cart from a given ShoppingCart
     * @param cart is a ShoppingCart Object.
     */
    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    /**
     * this method creates the control panel with the buttons.
     * @return is a JPanel
     */
    private JPanel getControlPane(){
        JPanel pane = new JPanel();
        pane.add(getCheckoutButton());
        pane.add(getQuitButton());
        return pane;
    }

    /**
     * this method creates a singular cart item pane
     * @param p is a Product Object
     * @return is a JPanel
     */
    private JPanel getCartPane(Product p){
        JPanel pane = new JPanel();
        pane.setLayout(new BorderLayout());
        pane.setSize(150,30);
        pane.setPreferredSize(new Dimension(75,50));
        pane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pane.add(new JLabel(p.getName() + " X " + cart.getQty(p.getId())), PAGE_START);

        return pane;
    }

    /**
     * this method creates a panel of cart panels
     * @return is a JPanel
     */
    private JPanel getCartPanel(){
        JPanel pane = new JPanel();
        pane.setSize(150,800);
        pane.setLayout(new GridLayout(8,2));
        pane.add(new JLabel("---CART---"));
        for (Product p: this.getCart().getProducts()){
            pane.add(getCartPane(p));
        }
        return pane;
    }

    /**
     * this creates a JLabel with an image
     * @return is a JLabel
     */
    private static JLabel getImage(){
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("src/myStore/icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel img = new JLabel(new ImageIcon(myPicture));
        return img;
    }

    /**
     * this method creates a singular inventory item pane
     * @param cart is a ShoppingCart Object
     * @param p is a Product Object
     * @return is a JPanel
     */
    private JPanel getInventoryPane(ShoppingCart cart, Product p){
        JPanel pane = new JPanel();
        pane.setLayout(new BorderLayout());
        pane.setSize(200,200);
        pane.setPreferredSize(new Dimension(200,200));
        pane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pane.add(new JLabel(p.getName() + " - ID:" + p.getId()), PAGE_START);
        pane.add(getImage(), CENTER);
        JPanel ctrl = new JPanel(new GridLayout(1,3));
        ctrl.add(new JLabel("$" + p.getPrice().toPlainString()));
        JTextField qty = new JTextField("1", 2);
        ctrl.add(qty);
        JButton button = new JButton("Add To Cart");
        button.setSize(buttonDimension);
        button.addActionListener(i -> {
            cart.addProduct(p, Integer.parseInt(qty.getText()));
            this.cartPanel.add(getCartPane(cart.getProducts().get(cart.getProducts().size()-1)));
            this.refresh();
        });
        ctrl.add(button);
        pane.add(ctrl, PAGE_END);

        return pane;
    }

    /**
     * this method creates a panel of inventory panes.
     * @return is a JPanel
     */
    private JPanel getInventoryPanel(){
        JPanel pane = new JPanel();
        pane.setLayout(new GridLayout(2,3));
        for (Product i: this.manager.getInventory().getProducts()){
            pane.add(getInventoryPane(cart, i));
        }
        return pane;
    }

    /**
     * this creates a checkout button
     * @return is a JButton
     */
    private JButton getCheckoutButton(){
        JButton button = new JButton("Checkout");
        button.setSize(buttonDimension);
        button.addActionListener(i -> {
            JFrame checkout = new JFrame();
            checkout.setSize(300, 300);
            checkout.setPreferredSize(new Dimension(300, 300));
            checkout.setResizable(false);
            checkout.setLocationRelativeTo(null);
            checkout.setLayout(new BorderLayout());

            checkout.add(new JLabel("RECEIPT:"), PAGE_START);
            checkout.add(new JLabel("Your total is: " + this.cart.checkout(manager.getInventory()).toString()), PAGE_END);

            checkout.pack();
            checkout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            checkout.setVisible(true);
        });
        return button;
    }

    /**
     * this method creates a Quit button
     * @return is a JButton
     */
    private JButton getQuitButton(){
        JButton button = new JButton("Quit");
        button.setSize(buttonDimension);
        button.addActionListener(i -> {
            this.setVisible(false);
        });
        return button;
    }

    /**
     * This is a static method used in main to list the storeviews available.
     * @param view is a Storeview Object
     * @return is a JButton
     */
    private static JButton getStoreViewButton(StoreView view){
        JButton button = new JButton(view.getNick());
        button.setSize(buttonDimension);
        button.addActionListener(i -> view.setVisible(true));
        return button;
    }

    /**
     * this is a static method used in main to create new storeViews.
     * @param manager is a StoreManager object
     * @return is a JButton
     */
    private static JButton getNewStoreViewButton(StoreManager manager) {
        JButton button = new JButton("Add New StoreView");
        button.setSize(buttonDimension);
        button.addActionListener(i ->
                manager.newView());

        return button;
    }

    /**
     * This is the StoreView Sub-Routine that runs the UI
     */
    public void run() {
        boolean quitStoreView = false;
        String prodName;
        char program;
        int qty;
        char receipt;

        while (!quitStoreView) {
            System.out.println("\nPlease select a Function:");
            System.out.println("Function                        Type\n");
            System.out.println("Display Cart		        	C");
            System.out.println("Display Inventory		       	I");
            System.out.println("Add to Cart		            	A");
            System.out.println("Remove from Cart		       	R");
            System.out.println("Checkout		            	H");
            System.out.println("Return to Main Menu             Q");

            program = Keyboard.getCharacter();
            switch (program){
                case 'c':
                case 'C':
                    System.out.println(this.cart);
                    break;

                case 'i':
                case 'I':
                    System.out.println(this.manager.getInventory());
                    break;

                case 'a':
                case 'A':
                    System.out.println("Enter the name of the product you want to add to your cart: ");
                    prodName = Keyboard.getString();
                    System.out.println("Enter the quantity of items you want to add to your cart: ");
                    qty = Keyboard.getInteger();
                    this.cart.addProduct(manager.getInventory().getProduct(prodName), qty);
                    break;

                case 'r':
                case 'R':
                    System.out.println("Enter the name of the product you want to remove from your cart: ");
                    prodName = Keyboard.getString();
                    System.out.println("Enter the quantity of items you want to remove from your cart: ");
                    qty = Keyboard.getInteger();
                    this.cart.removeProduct(getCart().getProduct(prodName), qty);
                    break;
                case 'h':
                case 'H':
                    System.out.println("Do you want a receipt? (y/n)");
                    receipt = Keyboard.getCharacter();
                    if(receipt == 'y' || receipt == 'Y'){
                        System.out.println(this.cart);
                        System.out.println("Total                   $" + this.cart.checkout(manager.getInventory()));
                    }
                    else{
                        System.out.println("Your total is $" + this.cart.checkout(manager.getInventory()));
                    }



                case 'q':
                case 'Q':
                    quitStoreView = true;
                    break;

                default :
                    break;
            }
        }
    }

    /**
     * this method sets the storeview instance visibility to the given boolean.
     * @param i is a Boolean
     */
    private void setVisible(Boolean i) {
        this.view.setVisible(i);
    }

    /**
     * this method repaints and again packs the screen to refresh.
     */
    private void refresh(){
        this.view.repaint();
        this.view.pack();
    }

    /**
     * this is main...
     * @param args is a String array
     */
    public static void main(String[] args) {
        StoreManager manager = new StoreManager();

        Product p1 = new Product("Carrot", "24907", new BigDecimal("2.99"));
        Product p2 = new Product("Cucumber", "34676", new BigDecimal("3.99"));
        Product p3 = new Product("Orange", "34587", new BigDecimal("5.99"));
        Product p4 = new Product("Lemon", "23458", new BigDecimal("7.99"));
        Product p5 = new Product("Excel Gum", "00040", new BigDecimal("1.99"));

        manager.getInventory().addProduct(p1, 7);
        manager.getInventory().addProduct(p2,2);
        manager.getInventory().addProduct(p3, 5);
        manager.getInventory().addProduct(p4,9);
        manager.getInventory().addProduct(p5,72);

        JFrame mainMenu = new JFrame("My Store");
        mainMenu.setSize(300, 500);
        mainMenu.setPreferredSize(new Dimension(300, 500));
        mainMenu.setResizable(false);
        mainMenu.setLocationRelativeTo(null);
        mainMenu.setLayout(new BorderLayout());

        JPanel views = new JPanel();
        views.setLayout(new GridLayout(14,4));
        for (StoreView i: manager.getViews()){
            views.add(getStoreViewButton(i));
        }

        JButton newStoreViewButton = new JButton("Add New StoreView");
        newStoreViewButton.setSize(buttonDimension);
        newStoreViewButton.addActionListener(i -> {
            manager.newView();
            views.add(getStoreViewButton(manager.getViews().get(manager.getViews().size()-1)));
            mainMenu.pack();
        });

        JButton managementButton = new JButton("Manage Store");
        managementButton.setSize(buttonDimension);
        managementButton.addActionListener(i -> {
            manager.enableManagementMode();//ToDo write manager UI...
        });

        JPanel controlPane = new JPanel();
        controlPane.setLayout(new GridLayout());
        controlPane.add(newStoreViewButton);
        controlPane.add(managementButton);

        mainMenu.add(views, PAGE_START);
        mainMenu.add(controlPane, PAGE_END);

        mainMenu.pack();
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu.setVisible(true);




        boolean quitMain = false;

        System.out.println("Welcome to StoreView");
        System.out.println("A program created by Michael Silveira");


        char program;

        while (!quitMain) {
            System.out.println("\nPlease select a Function:");
            System.out.println("Function                        Type\n");
            System.out.println("Add New StoreView		        	N");
            System.out.println("Display StoreViews		        	D");
            System.out.println("Select StoreView		           	S");
            System.out.println("Remove StoreView		           	R");
            System.out.println("Manage Store		            	M");
            System.out.println("Quit			                    Q");

            program = Keyboard.getCharacter();
            switch (program){
                case 'n':
                case 'N':
                    System.out.println(manager.newView());
                    break;

                case 'd':
                case 'D':
                    System.out.println(manager.displayStoreViews());
                    break;

                case 's':
                case 'S':
                    System.out.println("Enter the name of your desired storeview: ");
                    manager.selectStoreView(Keyboard.getString());
                    break;

                case 'r':
                case 'R':
                    System.out.println("Enter the name of your desired storeview: ");
                    manager.removeStoreView(Keyboard.getString());
                    break;
                case 'm':
                case 'M':
                    manager.run();
                    break;
                case 'q':
                case 'Q':
                    quitMain = true;

                default :
                    break;
            }
        }
    }
}
