// Lab6Skeleton.java
//


//TODO: Add proper import java.*s in this block////////////////

///////////////////////////////////////////////////////////////

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;

public class Lab6 extends JFrame implements ActionListener{

    JButton cutButton;
    JButton copyButton;
    JButton pasteButton;
    JButton openButton;
    JButton saveButton;

    JScrollPane scroller;
    JTextArea text;

    // Following is the constructor
    public Lab6() {
        super("CSCI 241 Lab6");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        initialize();
    }
    
    //Initialize the GUI: Follow instructions for Stage 2
    private void initialize() {
        // Task 3: add a panel for the toolbar
        JPanel toolbar = new JPanel();
        add(toolbar, BorderLayout.NORTH);

        addButton(toolbar, cutButton, "Cut");
        addButton(toolbar, copyButton, "Copy");
        addButton(toolbar, pasteButton, "Paste");
        addButton(toolbar, openButton, "Open");
        addButton(toolbar, saveButton, "Save");

        // Task 7: add a text area within a scrolling pane by following instructions for Stage 4
        text = new JTextArea();
        scroller = new JScrollPane(text);
        add(scroller, BorderLayout.CENTER);
    }

    // Add a button to a panel: Follow instructions for Stage 3
    private void addButton(JPanel panel, JButton button, String label) {
        //Task 5: Follow instructions for Stage 3
        button = new JButton(label);
        panel.add(button);
        button.addActionListener(this);
    }
    
    // Event-handler for button-press: Follow instructions for Stage 3
    public void actionPerformed(ActionEvent e) {
        // Task 6: Follow instructions for Stage 3
        System.out.println(e.getActionCommand() + " pressed");
        // Task 8: Follow instructions for Stage 5
        if (e.getActionCommand() == "Cut") text.cut();
        else if (e.getActionCommand() == "Copy") text.copy(); //--> Follow instructions for Stage 5

        else if (e.getActionCommand() == "Paste") text.paste();  //--> Follow instructions for Stage 5

        // Task 10 : Add actionPerformend for "Open"
        else if (e.getActionCommand() == "Open") readFile();// ---> Follow instructions for Stage 6

        // Task 13 : Add actionPerformed for "Save"
        else if (e.getActionCommand() == "Save") writeFile();// ---> Follow instructions for Stage 7
    }
    
    // Read a file in Stage 6
    private void readFile() {
        //Task 11 --> Follow instructions for Stage 6
        JFileChooser chooser = new JFileChooser();
        int option = chooser.showOpenDialog(this);
        if(option == JFileChooser.APPROVE_OPTION){
            try{
                String filename = chooser.getName(chooser.getSelectedFile());
                text.setText(new String(Files.readAllBytes(Paths.get(filename))));
            }catch(IOException e){
                System.out.println("Cannot read the file " + e);
            }
        }
    }

    // Write to a file in Stage 7
    private void writeFile() {
        //Task 14 --> Follow instructions for Stage 7
        JFileChooser chooser = new JFileChooser();
        int option = chooser.showSaveDialog(this);
        if(option == JFileChooser.APPROVE_OPTION){
            try{
                String filename = chooser.getName(chooser.getSelectedFile());
                Files.write(Paths.get(filename), text.getText().getBytes());
            }catch(IOException e){
                System.out.println("Cannot write to file " + e);
            }
        }
    }

    public static void main(String[] args) {
        Lab6 frame = new Lab6();
    }
}
