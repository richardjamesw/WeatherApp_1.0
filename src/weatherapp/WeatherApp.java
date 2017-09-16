/*
Rick Wallace
WeatherApp
Desc: Main class displays GUI
*/

package weatherapp;
//
///**
// *
// * @author Rick
// */

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;

public class WeatherApp {
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        //setup jframe for GUI
        JFrame frame = new JFrame("NOAA Weather Receiver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //create panel
        WeatherAppPanel panel = new WeatherAppPanel();
        
        //add panel to frame
        frame.getContentPane().add(panel);
        
        //adjust size
        frame.pack();
        
        //location
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        //get current screen
        int xPos = (dim.width / 2) - (frame.getWidth() / 2);
        int yPos = (dim.height / 2) - (frame.getHeight() / 2);
        //frame.setLocationRelativeTo(null);
        frame.setLocation(xPos, yPos);
        
        //show
        frame.setResizable(true);
        frame.setVisible(true);
    }
}
