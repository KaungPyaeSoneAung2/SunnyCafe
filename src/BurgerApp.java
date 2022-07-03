import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BurgerApp extends JFrame{

    /*
     *	  Declarations
     */

    private final int width = 800;
    private final int height = 600;
    private JPanel parent;
    private JPanel panel1;
    private JPanel panel2a;
    private JPanel panel2b;
    private JPanel panel2c;
    private JPanel panel3;
    private JLabel title;
    private JPanel buttons;

    private JRadioButton product1;
    private JRadioButton product2;
    private JRadioButton product3;
    private ButtonGroup productgroup;

    private JPanel subpanel1;
    private JPanel subpanel2;
    private JPanel subpanel3;
    private JPanel subpanel4;
    private JPanel subpanel5;
    private JPanel subpanel6;
    private JPanel subpanel7;
    private JPanel subpanel8;
    private JPanel subpanel9;

    private JTextArea text;

    private JScrollPane scroll;

    //	PANEL 2A

    private JButton button1;
    private JButton button2;
    private JButton button3;

    private JRadioButton small;
    private JRadioButton medium;
    private JRadioButton large;
    private ButtonGroup group1;

    private JRadioButton Expresso;
    private JRadioButton regular;
    private JRadioButton roast;
    private ButtonGroup group2;

    private JCheckBox milk;
    private JCheckBox sugar;

    // PANEL 2B

    private JRadioButton Egg;
    private JRadioButton Chicken;
    private JRadioButton Pork;
    private JRadioButton Beef;
    private JRadioButton PulledPork;
    private ButtonGroup group4;

    private JRadioButton classic;
    private JRadioButton lcc;
    private JRadioButton bbq;
    private JRadioButton butter;
    private JRadioButton Mayonaise;
    private ButtonGroup group5;

    private JCheckBox lox;
    private JCheckBox novalox;

    // PANEL 2C

    private final String[] pastrylist = {"Apricot Danish", "Prune Danish", "Crossant", "English Muffin", "Blueberry Muffin"};
    private JList list;
    private int[] indices = {};

    // OTHERS

    private JLabel quantitylabel;
    private JTextField quantity;
    private JRadioButton membery;
    private JRadioButton membern;
    private ButtonGroup membergroup;
    private JButton delete;
    private int q = 1;

    private BorderLayout layout;

    private boolean isMember;

    private Order order;
    private Coffee coffee;
    private Burger Burger;
    private Pastry pastry;

    private BurgerApp()
    {
        setTitle("Sunny Cafe Menu");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));
        setBackground(Color.LIGHT_GRAY);

        buildPanel();
        add(panel3, BorderLayout.CENTER);
        add(title, BorderLayout.NORTH);
        add(buttons, BorderLayout.SOUTH);
        add(parent, BorderLayout.WEST);

        setVisible(true);

        // CALL CLASSES
        order = new Order();
        coffee = new Coffee();
        Burger = new Burger();
        pastry = new Pastry();
    }

    private void buildPanel()
    {
        /* A very big build method because
         * all the panels are pre-made
         */

        // PARENT PANEL

        parent = new JPanel();
        parent.setLayout(new BorderLayout(10,10));

        title = new JLabel("Order Entry Screen");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        panel1 = new JPanel();
        panel2a = new JPanel();
        panel2b = new JPanel();
        panel2c = new JPanel();
        panel3 = new JPanel();

        // PANEL1

        panel1.setBorder(BorderFactory.createTitledBorder("Products"));
        panel1.setLayout(new GridLayout(3,1));

        product1 = new JRadioButton("Coffee");
        product2 = new JRadioButton("Burger");
        product3 = new JRadioButton("Pastry");
        productgroup = new ButtonGroup();

        productgroup.add(product1);
        productgroup.add(product2);
        productgroup.add(product3);

        product1.addActionListener(new ButtonListener());
        product2.addActionListener(new ButtonListener());
        product3.addActionListener(new ButtonListener());

        panel1.add(product1);
        panel1.add(product2);
        panel1.add(product3);

        panel2a.setLayout(new GridLayout(3,1));
        panel2a.setPreferredSize(new Dimension(180, 650));
        panel2b.setLayout(new GridLayout(3,1));
        panel2b.setPreferredSize(new Dimension(180, 650));
        panel2c.setLayout(new GridLayout(2,1));
        panel2c.setPreferredSize(new Dimension(180, 650));

        subpanel1 = new JPanel();
        subpanel2 = new JPanel();
        subpanel3 = new JPanel();

        // PANEL3

        panel3.setLayout(new BorderLayout());

        subpanel8 = new JPanel();
        subpanel9 = new JPanel();
        subpanel9.setLayout(new BorderLayout());

        quantitylabel = new JLabel("Quantity: ");
        quantity = new JTextField(5);
        membery = new JRadioButton("Member");
        membern = new JRadioButton("Not Member");
        membergroup = new ButtonGroup();
        delete = new JButton("Delete Previous");

        membery.setActionCommand("Member");
        membern.setActionCommand("Not Member");

        delete.addActionListener(new ButtonListener());
        quantity.addActionListener(new ButtonListener());
        membern.addActionListener(new ButtonListener());
        membery.addActionListener(new ButtonListener());

        membergroup.add(membery);
        membergroup.add(membern);

        text = new JTextArea();
        scroll = new JScrollPane(text);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);

        subpanel8.add(quantitylabel);
        subpanel8.add(quantity);
        subpanel8.add(membery);
        subpanel8.add(membern);
        subpanel8.add(delete);
        subpanel9.add(scroll, BorderLayout.CENTER);

        panel3.add(subpanel8, BorderLayout.SOUTH);
        panel3.add(subpanel9, BorderLayout.CENTER);

        // BUTTON PANEL

        buttons = new JPanel();
        button1 = new JButton("Enter Item");
        button2 = new JButton("Total");
        button3 = new JButton("New Order");
        buttons.setBackground(Color.black);

        button1.addActionListener(new ButtonListener());
        button2.addActionListener(new ButtonListener());
        button3.addActionListener(new ButtonListener());

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);

        //panel2a

        subpanel1.setLayout(new GridLayout(3,1));
        subpanel2.setLayout(new GridLayout(3,1));
        subpanel3.setLayout(new GridLayout(2,1));

        subpanel1.setBorder(BorderFactory.createTitledBorder("Size"));

        small = new JRadioButton("Small");
        medium = new JRadioButton("Medium");
        large = new JRadioButton("Large");
        group1 = new ButtonGroup();

        small.setActionCommand("Small");
        medium.setActionCommand("Medium");
        large.setActionCommand("Large");

        small.addActionListener(new ButtonListener());
        medium.addActionListener(new ButtonListener());
        large.addActionListener(new ButtonListener());

        group1.add(small);
        group1.add(medium);
        group1.add(large);

        subpanel1.add(small);
        subpanel1.add(medium);
        subpanel1.add(large);

        subpanel2.setBorder(BorderFactory.createTitledBorder("Type"));

        regular = new JRadioButton("Regular");
        Expresso = new JRadioButton("Expresso");
        roast = new JRadioButton("French Roast");
        group2 = new ButtonGroup();

        regular.setActionCommand("Regular");
        Expresso.setActionCommand("Expresso");
        roast.setActionCommand("French Roast");

        regular.addActionListener(new ButtonListener());
        Expresso.addActionListener(new ButtonListener());
        roast.addActionListener(new ButtonListener());

        group2.add(regular);
        group2.add(Expresso);
        group2.add(roast);

        subpanel2.add(regular);
        subpanel2.add(Expresso);
        subpanel2.add(roast);

        subpanel3.setBorder(BorderFactory.createTitledBorder("Extras"));

        milk = new JCheckBox("milk");
        sugar = new JCheckBox("Sugar");

        milk.addActionListener(new ButtonListener());
        sugar.addActionListener(new ButtonListener());

        subpanel3.add(milk);
        subpanel3.add(sugar);

        panel2a.add(subpanel1);
        panel2a.add(subpanel2);
        panel2a.add(subpanel3);

        //panel2b

        subpanel4 = new JPanel();
        subpanel5 = new JPanel();
        subpanel6 = new JPanel();

        subpanel4.setLayout(new GridLayout(5,1));
        subpanel5.setLayout(new GridLayout(5,1));
        subpanel6.setLayout(new GridLayout(2,1));

        subpanel4.setBorder(BorderFactory.createTitledBorder("Burger"));

        Egg = new JRadioButton("Egg");
        Chicken = new JRadioButton("Chicken");
        Pork = new JRadioButton("Pork");
        Beef = new JRadioButton("Beef");
        PulledPork = new JRadioButton("PulledPork");
        group4 = new ButtonGroup();

        Egg.setActionCommand("Egg");
        Chicken.setActionCommand("Chicken");
        Pork.setActionCommand("Pork");
        Beef.setActionCommand("Beef");
        PulledPork.setActionCommand("PulledPork");

        Egg.addActionListener(new ButtonListener());
        Chicken.addActionListener(new ButtonListener());
        Pork.addActionListener(new ButtonListener());
        Beef.addActionListener(new ButtonListener());
        PulledPork.addActionListener(new ButtonListener());

        group4.add(Egg);
        group4.add(Chicken);
        group4.add(Pork);
        group4.add(Beef);
        group4.add(PulledPork);

        subpanel4.add(Egg);
        subpanel4.add(Chicken);
        subpanel4.add(Pork);
        subpanel4.add(Beef);
        subpanel4.add(PulledPork);

        subpanel5.setBorder(BorderFactory.createTitledBorder("Type of Sauce"));

        classic = new JRadioButton("Classic ");
        lcc = new JRadioButton("Cheese ");
        bbq = new JRadioButton("Bbq ");
        butter = new JRadioButton("Butter ");
        Mayonaise = new JRadioButton("Mayonaise ");
        group5 = new ButtonGroup();


        classic.setActionCommand("Classic ");
        lcc.setActionCommand("Cheese ");
        bbq.setActionCommand("Bbq ");
        butter.setActionCommand("Butter ");
        Mayonaise.setActionCommand("Mayonaise ");

        classic.addActionListener(new ButtonListener());
        lcc.addActionListener(new ButtonListener());
        bbq.addActionListener(new ButtonListener());
        butter.addActionListener(new ButtonListener());
        Mayonaise.addActionListener(new ButtonListener());

        group5.add(classic);
        group5.add(lcc);
        group5.add(bbq);
        group5.add(butter);
        group5.add(Mayonaise);

        subpanel5.add(classic);
        subpanel5.add(lcc);
        subpanel5.add(bbq);
        subpanel5.add(butter);
        subpanel5.add(Mayonaise);

        subpanel6.setBorder(BorderFactory.createTitledBorder("Toppings"));

        lox = new JCheckBox("Lox");
        novalox = new JCheckBox("Nova Lox");

        lox.addActionListener(new ButtonListener());
        novalox.addActionListener(new ButtonListener());

        subpanel6.add(lox);
        subpanel6.add(novalox);

        panel2b.add(subpanel4);
        panel2b.add(subpanel5);
        panel2b.add(subpanel6);

        //panel2c

        subpanel7 = new JPanel();

        subpanel7.setLayout(new GridLayout());
        subpanel7.setBorder(BorderFactory.createTitledBorder("Pastries"));

        list = new JList(pastrylist);

        list.addListSelectionListener(new ListListener());

        subpanel7.add(list);
        panel2c.add(subpanel7);

        parent.add(panel1, BorderLayout.WEST);
        parent.add(panel2a, BorderLayout.CENTER);
        layout = (BorderLayout)parent.getLayout();

        product1.doClick();
        membern.doClick();
    }

    private class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            if(product1.isSelected())
            {
                parent.remove(layout.getLayoutComponent(BorderLayout.CENTER));
                parent.add(panel2a, BorderLayout.CENTER);
                parent.revalidate();
                parent.repaint();
                if(group1.getSelection() == null)
                {
                    small.doClick();
                }
                if(group2.getSelection() == null)
                {
                    regular.doClick();
                }
            }
            else if(product2.isSelected())
            {
                parent.remove(layout.getLayoutComponent(BorderLayout.CENTER));
                parent.add(panel2b, BorderLayout.CENTER);
                parent.revalidate();
                parent.repaint();
                if(group4.getSelection() == null)
                {
                    Egg.doClick();
                }
            }
            else if(product3.isSelected())
            {
                parent.remove(layout.getLayoutComponent(BorderLayout.CENTER));
                parent.add(panel2c, BorderLayout.CENTER);
                parent.revalidate();
                parent.repaint();
            }
            if(arg0.getActionCommand() == "Enter Item")
            {
                if(!quantity.getText().isEmpty())
                {
                    try
                    {
                        q = Integer.parseInt(quantity.getText());
                    }
                    catch(NumberFormatException e)
                    {
                        q = 1;
                    }
                }
                else
                {
                    q = 1;
                }

                if(product1.isSelected())
                {
                    coffee.getSelectedButton(group1, group2);
                    if(sugar.isSelected())
                    {
                        coffee.getString().add("Extra Sugar");
                        sugar.doClick();
                    }
                    if(milk.isSelected())
                    {
                        coffee.getString().add("Extra Milk");
                        milk.doClick();
                    }
                    coffee.pricing();
                    for(int x = 0; x < q; x++)
                    {
                        order.getOrder(coffee.getPrice(), coffee.getString());
                    }
                    q = 1;
                    coffee.clear();
                    small.doClick();
                    regular.doClick();
                }
                else if(product2.isSelected())
                {
                    Burger.getSelectedButton(group4, group5);
                    if(lox.isSelected())
                    {
                        Burger.getString().add("Lox Topping");
                        lox.doClick();
                    }
                    if(novalox.isSelected())
                    {
                        Burger.getString().add("Nova Lox Topping");
                        novalox.doClick();
                    }
                    Burger.pricing();
                    for(int x = 0; x < q; x++)
                    {
                        order.getOrder(Burger.getPrice(), Burger.getString());
                    }
                    q = 1;
                    Burger.clear();
                    Egg.doClick();
                    group5.clearSelection();
                }
                else if(product3.isSelected())
                {
                    pastry.getSelectedButton(indices, pastrylist);
                    pastry.pricing();
                    for(int x = 0; x < q; x++)
                    {
                        order.getOrder(pastry.getPrice(), pastry.getString());
                    }
                    q = 1;
                    pastry.clear();
                }

                printOrder(order.getOrder(), order.getOrderprice());
                quantity.setText("");

            }
            else if(arg0.getActionCommand() == "New Order")
            {
                order.clear();
                product1.doClick();
                small.doClick();
                regular.doClick();
                Egg.doClick();
                group5.clearSelection();
                text.setText("");
            }
            else if(arg0.getActionCommand() == "Total")
            {
                text.append(String.format("%n%100s%2s%.2f", "Subtotal: ","$", (float)order.calculateSubtotal()/100));
                text.append(String.format("%n%102s%2s%.2f", "Total: ","$", (float)order.calculateTotal(isMember)/100));
            }
            if(arg0.getActionCommand() == "Delete Previous")
            {
                int i = order.getOrder().size()-1;
                int k = order.getOrderprice().size()-1;
                boolean yes = false;
                int y = i;
                int x = k;

                while(!yes && !(i == -1 && k == -1))
                {
                    if(order.getOrder().get(y).equals("Coffee")||order.getOrder().get(y).equals("Burger")||order.getOrder().get(y).equals("Pastry"))
                    {
                        order.getOrder().remove(y);
                        yes = true;
                    }
                    else
                    {
                        order.getOrder().remove(y);
                        order.getOrderprice().remove(x);
                        y--;
                        x--;
                    }
                }
                printOrder(order.getOrder(), order.getOrderprice());

            }
            if(membern.isSelected())
            {
                isMember = false;
            }
            else if(membery.isSelected())
            {
                isMember = true;
            }
        }
    }

    private class ListListener implements ListSelectionListener
    {
        public void valueChanged(ListSelectionEvent arg0)
        {
            JList source;
            if (!arg0.getValueIsAdjusting())
            {
                source = (JList)arg0.getSource();
                indices = source.getSelectedIndices();
            }
        }
    }

    private void printOrder(ArrayList<String> list, ArrayList<Integer> list2)
    {
        int y = 0;
        text.setText("");
        for(int x = 0; x < list.size(); x++)
        {
            if(list.get(x).equals("Coffee")||list.get(x).equals("Burger")||list.get(x).equals("Pastry"))
            {
                text.append(list.get(x)+"\n");
            }
            else
            {
                text.append("           "+list.get(x)+"\n");
                text.append(String.format("%107s%.2f\n","$", (float)list2.get(y)/100));
                y++;
            }
        }
    }

    public static void main(String[] args)
    {
        BurgerApp app = new BurgerApp();
    }

    //Getters and Setters
    public JTextArea getText() {
        return text;
    }

    public void setText(JTextArea text) {
        this.text = text;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean isMember) {
        this.isMember = isMember;
    }
}