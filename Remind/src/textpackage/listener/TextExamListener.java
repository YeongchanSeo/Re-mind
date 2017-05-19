package textpackage.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import main.TextModeController;
import textpackage.RemindTextSidePanel;
import textpackage.TextPane;
import textpackage.TextTestResultPanel;

public class TextExamListener implements ActionListener {

	private RemindTextSidePanel panel;
	private List<TextPane> 		textlist;
	private TextModeController 	controller;

	public TextExamListener(List<TextPane> textlist,
			RemindTextSidePanel panel) {
		this.panel 		= panel;
		this.textlist 	= textlist;
		this.controller = panel.getController();
	}

	public void actionPerformed(ActionEvent e) {
			
		System.out.println("1");
		String[][] str = new String[textlist.size()][3];
		Iterator<TextPane> it = textlist.iterator();
		int i = 0;
		while (it.hasNext()) {
			TextPane text = it.next();
			str[i++] = text.getText();
			
			System.out.println(str[i-1][0]);
			System.out.println(str[i-1][1]);
			System.out.println(str[i-1][2]);
		}

		int correct = controller.check(str);
		/* 점수 입력하는 창 에다 리턴된 수 대입한다 */
		
		panel.changeUserPanel(correct);
		panel.setTextModeTestButtonUsable(true);
	}
}
