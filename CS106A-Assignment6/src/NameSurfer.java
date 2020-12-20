/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.Program;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

public class NameSurfer extends Program implements NameSurferConstants {

    /* Method: init() */

    private NameSurferDatabase database;
    private JTextField name_field;
    private NameSurferGraph graph;

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */

    public void init() {

        //initializing the database reader
        try {
            database = new NameSurferDatabase(NAMES_DATA_FILE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // You fill this in, along with any helper methods
        graph = new NameSurferGraph();
        add(graph);
        graph.update();


        JLabel name_label = new JLabel("Name:");
        add(name_label, NORTH);
        name_field = new JTextField(15);
        name_field.addActionListener(this);
        name_field.setActionCommand("Go");
        add(name_field, NORTH);
        JButton button_Graph = new JButton("Graph");
        add(button_Graph, NORTH);
        JButton button_clear = new JButton("Clear");
        add(button_clear, NORTH);
        addActionListeners();
    }

    /* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        // You fill this in //
        String command = e.getActionCommand();
        if (command.equals("Graph") || command.equals("Go")) {
            String nameinput = name_field.getText();
            if (database.findEntry(nameinput) != null) {
                graph.addEntry(database.findEntry(nameinput));
                graph.update();
            }
        } else if (command.equals("Clear")) {
            graph.clear();
        }
    }
}


