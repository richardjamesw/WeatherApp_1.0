/*
Rick Wallace
WeatherAppPanel
Desc: Build GUI and handle lists and buttons
 */
package weatherapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import static java.lang.Math.abs;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.time.LocalDate;
import java.util.Arrays;

public final class WeatherAppPanel extends JPanel {
    //instance variabels
    //ListSelectionListener listListener;

    //GUI Components
    JPanel panel, centerPanel, northPanel, southPanel, eastPanel, westPanel;
    JLabel headingL, tempL, askState_lb;
    //station labels
    JLabel stationL, monTempL, newTempValL, similarTempL, similarTempValL;
    public JButton getStateB = new JButton("Select State");
    public JButton getStationB = new JButton("Select Station");
    private final JList listStates, listStations;
    JScrollPane sb1 = new JScrollPane();   //states
    JScrollPane sb2 = new JScrollPane();   //stations
    public int x, y;
    public int index;
    String[] statesArr;
    String[] stationsArr;
    int[] dataArr = {};
    ButtonListener listener;
    public WeatherHashTable<String, State> statesMap;
    WeatherHashTable<String, Station> stationsMap;

    State[] stateArrayGetID;
    State[] stationArrayGetInfo;
    Station[] stations;

    LocalDate enddate = LocalDate.now();
    LocalDate startdate = enddate.minusMonths(1);

    public WeatherAppPanel() throws IOException {
        // create the page heading w/font
        headingL = new JLabel("NOAA Weather Receiver");
        headingL.setFont(new Font("Arial", Font.BOLD, 24));
        headingL.setForeground(Color.white);

        // create main panel, color, layout and dimensions
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.white);
        panel.setPreferredSize(new Dimension(900, 400));
        //panel.setLayout(new BorderLayout());

        // create the center panel, color
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.gray);
        centerPanel.setLayout(new GridLayout(11, 2));

        // create the north panel with color and headingL
        northPanel = new JPanel();
        northPanel.setBackground(Color.gray);
        northPanel.add(headingL);

        // create the south panel with color
        southPanel = new JPanel();
        southPanel.setBackground(Color.gray);

        // create the east panel with color
        eastPanel = new JPanel();
        eastPanel.setBackground(Color.gray);

        // create the west panel
        westPanel = new JPanel();
        westPanel.setBackground(Color.gray);
        //add label to panel
//        askState_lb = new JLabel("Please select a U.S. State:");
//        westPanel.add(askState_lb);

        //get states
        statesMap = new WeatherHashTable<String, State>();
        statesMap = HttpDownloader.downloadStates();
        //statesMap.sort();

        //create String array from map
        stateArrayGetID = (State[]) statesMap.toStateArray();
        fillStateArray(stateArrayGetID);

        //setup state list
        listStates = new JList(statesArr);
        listStates.setVisibleRowCount(20);
        listStates.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sb1.setViewportView(listStates);
        //sb2.setPreferredSize(d);
        westPanel.add(sb1);

        //create listener
        listener = new ButtonListener();
        //statesbutton
        //getStateB.setContentAreaFilled(false);
        getStateB.setForeground(Color.black);
        getStateB.setBackground(Color.LIGHT_GRAY);
        getStateB.setBorder(BorderFactory.createLineBorder(Color.white));
        getStateB.setVerticalAlignment(JButton.TOP);
        getStateB.addActionListener(listener);
        southPanel.add(getStateB);

        //setup station list
        listStations = new JList();
        listStations.setVisibleRowCount(20);
        listStations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sb2.setViewportView(listStations);
        //sb2.setPreferredSize(d);
        westPanel.add(sb2);

        //stationsbutton
        //getStationB.setContentAreaFilled(false);
        getStationB.setForeground(Color.black);
        getStationB.setBackground(Color.LIGHT_GRAY);
        getStationB.setBorder(BorderFactory.createLineBorder(Color.white));
        getStationB.setVerticalAlignment(JButton.TOP);
        getStationB.addActionListener(listener);
        southPanel.add(getStationB);

        //setup temperature section
        tempL = new JLabel("Temperatures");
        tempL.setFont(new Font("Helvetica", Font.BOLD, 21));
        tempL.setForeground(Color.white);
        centerPanel.add(tempL);

        //setup station and info
        monTempL = new JLabel("Monthly Average Temperature: ");
        monTempL.setFont(new Font("Arial", Font.BOLD, 16));
        monTempL.setForeground(Color.white);
        centerPanel.add(monTempL);

        //new temperature reading
        newTempValL = new JLabel("___");
        newTempValL.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
        newTempValL.setForeground(Color.red);
        centerPanel.add(newTempValL);

        //similarity metric temp label
        similarTempL = new JLabel("Similar Station: ");
        similarTempL.setFont(new Font("Arial", Font.BOLD, 16));
        similarTempL.setForeground(Color.white);
        centerPanel.add(similarTempL);

        //similarity metric temp
        similarTempValL = new JLabel("___");
        similarTempValL.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
        similarTempValL.setForeground(Color.red);
        centerPanel.add(similarTempValL);

        // place all panels in the Border Layout positions
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(southPanel, BorderLayout.SOUTH);
        panel.add(westPanel, BorderLayout.WEST);
        panel.add(eastPanel, BorderLayout.EAST);

        // add the main panel
        add(panel);

    }//end constructor

//    //@Overrides
//    private class ListSelectionHandler implements ListSelectionListener{
//        @Override
//        public void valueChanged(ListSelectionEvent event){
//            case:
//            
//        }
//    }
    public void fillStateArray(State[] state) {
        statesArr = new String[state.length];
        for (int i = 0; i < state.length; i++) {
            System.out.println("Test: " + state[i].getName());
            statesArr[i] = state[i].getName();
        }

        Arrays.sort(statesArr);
    }

    public void fillStationArray(Station[] stn) {
        stationsArr = new String[stn.length];
        for (int i = 0; i < stn.length; i++) {
            stationsArr[i] = stn[i].getName();
        }
        Arrays.sort(stationsArr);
    }

    public Station similarStation(Station s) {
        double h = 200, dist = 0;
        Station hStn = null;
        
        for (int p=0; p < stations.length; p++) {
            if (!stations[p].getName().equals(s.getName())) {//avoid identical station
                dist = abs(stations[p].getAveTemp() - s.getAveTemp());
                if(dist < h){
                    h = dist;
                    hStn = stations[p];
                }
            }
        }

        return hStn;

    }

    private class ButtonListener implements ActionListener {

        Station newStation, simStation;
        private String stateName, newStateID, stationName, newStationID;
        private int index, index2;

        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                // check for which radio button was clicked
                if (event.getSource() == getStateB) {
                    index = listStates.getSelectedIndex();
                    stateName = statesArr[index];

                    //use state name to get its ID
                    newStateID = ((State) statesMap.findVal(stateName)).getID();

                    //get stations map in the state selected
                    stationsMap = new WeatherHashTable();
                    try {
                        stationsMap = HttpDownloader.downloadStations(newStateID);
                    } catch (Exception ex) {
                        //Logger.getLogger(WeatherAppPanel.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Error downloading stations in Panel - " + ex);
                    }

                    //create String array from map of stations
                    stations = (Station[]) stationsMap.toStationArray();
                    fillStationArray(stations);
                    //fill list with station names
                    listStations.setListData(stationsArr);

                    //StationButton 
                } else if (event.getSource() == getStationB) {
                    newTempValL.setText("__");
                    similarTempValL.setText("__");
                    index2 = listStations.getSelectedIndex();
                    stationName = stationsArr[index2];

                    //use station name to get its temp
                    newStation = (Station) stationsMap.findVal(stationName);
                    //set its monthly average temp
                    newTempValL.setText(Double.toString(newStation.getAveTemp())+ " *C");

                    //set similar metric values
                    simStation = similarStation(newStation);
                    if (simStation != null) {
                        similarTempL.setText(simStation.getName() + " has a similar Monthly Ave. Temp.:");
                        similarTempValL.setText(Double.toString(simStation.getAveTemp()) + " *C");
                    } else {
                        similarTempL.setText("No other stations available.");
                    }

                }
            } catch (Exception e) {
                System.err.println("Select a List Option - " + e);
            }
        }//end action performed
    }//end button listener

}//end WeatherAppPanel

