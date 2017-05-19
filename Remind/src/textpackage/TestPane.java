package textpackage;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TestPane extends JPanel {
   private Box box ;
   private List<TextPane> textTable;
   final static int NOTE =0;
   final static int EXAM = 1;
//   private int textMode;
   private String[][]str;
   public TestPane(){
      textTable = new ArrayList<TextPane>();
   }
   
   public TestPane(String[][] str){
      this();
      this.str = str;

      box = Box.createVerticalBox();
      this.setLayout(new GridLayout());
      addTable();
      this.add(box);
   }
   
   public void addTable(){
      for(int i=0;i< str.length;i++){
         TextPane pane = new TextPane(str[i]);
         textTable.add(pane);
         box.add(pane);
      }
   }
   //textpane 빈칸으로 고치기...
   public void ChangeExamMode(int textMode){
         for(int i=0;i<textTable.size();i++)
            if(textMode == NOTE)   textTable.get(i).addString(str[i]);
            else               textTable.get(i).initTextArea();
         
   }

   public Box getBox() {
      return box;
   }

   public void setBox(Box box) {
      this.box = box;
   }
   //get 해서 그 안의 text 값 리턴을 받는다 = 컨트롤러에서  //채점 버튼 누를 시에
   public List<TextPane> getTextTable() {
      return textTable;
   }

   public void setTextTable(List<TextPane> textTable) {
      this.textTable = textTable;
   }
}