package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class buttonHandler implements ActionListener {

	BoardModel boardinfo;
	BoardView viewupdate;

	public buttonHandler(BoardModel model, BoardView view) {
		boardinfo = model;
		viewupdate = view;
	}

	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();

		switch(cmd) {
		case"button1":
			boardinfo.next();
			viewupdate.repaint();
			break;
		case"button2":
			boardinfo.undo();
			viewupdate.repaint();
			break;
		case"button3":
			boardinfo.newgame();
			viewupdate.repaint();
			break;
		case"button4":
			/*Timer t = new Timer();
			t.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					boardinfo.next();
					viewupdate.repaint();
				}

			}, 0,500);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
			t.cancel();
			*/
			Timer timer = new Timer(false);
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					boardinfo.next();
					viewupdate.repaint();
					if(boardinfo.diff_prev_current()==true)timer.cancel();
				}
			};
			timer.schedule(task,0,500);

			break;
		//	System.out.println("button4");
			//while(boardinfo.diff_prev_current()==false) {
				//System.out.println("kitay4");
				//try {
					//Thread.sleep(500);/*0.5秒スリープ*/
					//boardinfo.next();
					//viewupdate.repaint();
				//} catch (InterruptedException e1) {
					// TODO 自動生成された catch ブロック
					//e1.printStackTrace();
			//	}
				/*boardinfo.next();
				viewupdate.repaint();*/
			//}
			//System.out.println("kitay4owari");
			//break;
		}


	}

}
