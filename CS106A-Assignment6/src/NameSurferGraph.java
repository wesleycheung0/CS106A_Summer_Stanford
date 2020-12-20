/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;


public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    private ArrayList<NameSurferEntry> nameSurferEntries;
    private final ArrayList<Color> color;

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        nameSurferEntries = new ArrayList<>();
        color = new ArrayList<>();
        color.add(Color.BLACK);
        color.add(Color.RED);
        color.add(Color.blue);
        color.add(Color.MAGENTA);
        graphBackgroundGrid();
        addComponentListener(this);
        // You fill in the rest //

    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        // You fill this in //
        nameSurferEntries.clear();
        removeAll();
        graphBackgroundGrid();
    }


    /* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        // You fill this in //
        //Cant add entry. Dont know Why
        nameSurferEntries.add(entry);

    }

    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        graphBackgroundGrid();
        displayEntries();
    }

    /**
     * this method loops through all the entries that should be displayed in the graph
     */
    private void displayEntries() {
        for (int i = 0; i < nameSurferEntries.size(); i++) {
            createGraph(nameSurferEntries.get(i));
        }
    }

    /**
     * this method creates the graph for a specific entry
     */
    private void createGraph(NameSurferEntry entry) {

        //Init
        int location_x_pro = 0;
        double location_y_pro = 0;
        double pixelBetweenMargin = (getHeight() - (2 * GRAPH_MARGIN_SIZE)) / 1000.0;
        Color graphColor = color.get(nameSurferEntries.indexOf(entry) % 4);

        for (int i = 0; i < 10; i++) {
            int location_x_pre = (getWidth() / NDECADES) * i;
            double location_y_pre = pixelBetweenMargin * entry.getRank(i) + GRAPH_MARGIN_SIZE; // Width-margins / 1000 (ranking) * the entry ranking
            location_x_pro = (getWidth() / NDECADES) * (i + 1);
            location_y_pro = pixelBetweenMargin * entry.getRank(i + 1) + GRAPH_MARGIN_SIZE;
            String rank = (entry.getRank(i) == 1000) ? "*" : String.valueOf(entry.getRank(i));
            String label_name = entry.getName() + " " + rank;

            //Adding Line
            GLine line = new GLine(location_x_pre, location_y_pre, location_x_pro, location_y_pro);
            line.setColor(graphColor);
            add(line);

            // Adding Label
            GLabel label = new GLabel(label_name, location_x_pre, location_y_pre);
            label.setColor(graphColor);
            add(label);
        }
        String rank = (entry.getRank(10) == 1000) ? "*" : String.valueOf(entry.getRank(10));
        String label_name = entry.getName() + " " + rank;
        GLabel label = new GLabel(label_name, location_x_pro, location_y_pro);
        label.setColor(graphColor);
        add(label);

    }

//    TODO: Clear button not working

    private void graphBackgroundGrid() {
        /* makes the grid of lines */

        /* draws horizontal lines*/
        GLine upperLine = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
        add(upperLine);
        GLine lowerline = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
        add(lowerline);

        /* draws vertical lines */
        for (int i = 0; i <= NDECADES; i++) {
            GLine decadeLine = new GLine((((getWidth() / NDECADES) * i)), (0), ((getWidth() / NDECADES) * i), getHeight());
            add(decadeLine);
        }

        /* draws decade labels */
        add(new GLabel("1900", (getWidth() / NDECADES) * 0 + DECADE_LABEL_MARGIN_SIZE, getHeight()));
        add(new GLabel("1910", (getWidth() / NDECADES) * 1 + DECADE_LABEL_MARGIN_SIZE, getHeight()));
        add(new GLabel("1920", (getWidth() / NDECADES) * 2 + DECADE_LABEL_MARGIN_SIZE, getHeight()));
        add(new GLabel("1930", (getWidth() / NDECADES) * 3 + DECADE_LABEL_MARGIN_SIZE, getHeight()));
        add(new GLabel("1940", (getWidth() / NDECADES) * 4 + DECADE_LABEL_MARGIN_SIZE, getHeight()));
        add(new GLabel("1950", (getWidth() / NDECADES) * 5 + DECADE_LABEL_MARGIN_SIZE, getHeight()));
        add(new GLabel("1960", (getWidth() / NDECADES) * 6 + DECADE_LABEL_MARGIN_SIZE, getHeight()));
        add(new GLabel("1970", (getWidth() / NDECADES) * 7 + DECADE_LABEL_MARGIN_SIZE, getHeight()));
        add(new GLabel("1980", (getWidth() / NDECADES) * 8 + DECADE_LABEL_MARGIN_SIZE, getHeight()));
        add(new GLabel("1990", (getWidth() / NDECADES) * 9 + DECADE_LABEL_MARGIN_SIZE, getHeight()));
        add(new GLabel("2000", (getWidth() / NDECADES) * 10 + DECADE_LABEL_MARGIN_SIZE, getHeight()));
    }


    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        removeAll();
        update();
    }

    public void componentShown(ComponentEvent e) {
    }

}

