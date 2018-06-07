package lifegame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JPanel;



public class ButtonView extends JPanel
	implements BoardListener, MouseListener, MouseMotionListener {/*BoardView はJPanelを拡張する*/

	BoardModel boardinfo;
	JButton btn1,btn2,btn3,btn4;

	public ButtonView(BoardModel model, JButton button1, JButton button2, JButton button3,JButton button4) {
	//public BoardView(BoardModel model) {
		boardinfo = model;
		btn1=button1;
		btn2=button2;
		btn3=button3;
		btn4=button4;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	@Override
	public void paint(Graphics g) {
		//BoardModel boardinfo = new BoardModel(GlobalVar.side, GlobalVar.ver);
		//boardinfo.changeCellState(4,4);
		super.paint(g);/*JPanelの描画処理拝啓塗りつぶし*/

		//((Object) btn1).setWidth(this.getWidth()/3,100));

		btn1.setPreferredSize(new Dimension((this.getWidth())/4, 100));
		btn2.setPreferredSize(new Dimension((this.getWidth())/4, 100));
		btn3.setPreferredSize(new Dimension((this.getWidth())/4, 100));
		btn4.setPreferredSize(new Dimension((this.getWidth())/4, 100));

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mousePressed(MouseEvent e) {


	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void updated(BoardModel m) {
		// TODO 自動生成されたメソッド・スタブ

	}

}