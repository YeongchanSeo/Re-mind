package textpackage.dialog;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.colorchooser.*;
 
/* ColorChooserDemo.java requires no other files. */
public class ColorChooserDemo extends JDialog
                              implements ChangeListener {
 
    protected JColorChooser tcc;
//    protected JLabel banner;
 
 
    public ColorChooserDemo() {
 
        tcc = new JColorChooser();
//        tcc.getSelectionModel().addChangeListener(this);
        tcc.setBorder(BorderFactory.createTitledBorder(
                                             "Choose Text Color"));
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("확인");
        okButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		System.out.println(tcc.getColor());
        		dispose();
        	}
        });
        
        JButton xxButton = new JButton("취소");
        xxButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Closing dialog");
                dispose();
              }
            });
        
        buttonPanel.add(okButton);
        buttonPanel.add(xxButton);
        add(buttonPanel, BorderLayout.PAGE_END);
        add(tcc, BorderLayout.CENTER);
    }
    //색변화!!!!
    public void stateChanged(ChangeEvent e) {
        Color newColor = tcc.getColor();
//        banner.setForeground(newColor);
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("ColorChooserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        JDialog newContentPane = new ColorChooserDemo();
        //Display the window.
        newContentPane.pack();
        newContentPane.setVisible(true);
    }
 
    public static void main(String[] args) {
    	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
		catch(Exception e){}
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}