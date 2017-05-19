package textpackage.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import textpackage.RemindTextSidePanel;
import textpackage.TestPane;

public class TestModeListener implements ActionListener {
   private TestPane pane;
   private RemindTextSidePanel panel;
   public final static int NOTE =0;
   public final static int EXAM = 1;
   private int textMode ;
   
   public TestModeListener(TestPane pane, RemindTextSidePanel panel){
      textMode = NOTE;
      this.pane = pane;
      this.panel = panel;
   }
   public void actionPerformed(ActionEvent e){
	   if(textMode == NOTE){
        textMode = EXAM;
         panel.changUserPanel(EXAM);
	   }
      else if(textMode == EXAM){
         textMode = NOTE;
         panel.changUserPanel(NOTE);
      }
      pane.ChangeExamMode(textMode);
   }
}