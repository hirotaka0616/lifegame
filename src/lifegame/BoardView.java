package lifegame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class BoardView extends JPanel
	implements BoardListener, MouseListener, MouseMotionListener {/*BoardView はJPanelを拡張する*/
	/*灰色の領域を表示する機能を持つ*/
	//BoardModel boardinfo = new BoardModel(GlobalVar.side, GlobalVar.ver);
	//boardinfo.changeCellState(4,4);

	//BoardModel boardinfo = new BoardModel(GlobalVar.side, GlobalVar.ver);

	int final_side_line = 0;
	int final_ver_line = 0;
	int masu_side_len = 0;
	int masu_ver_len = 0;

	BoardModel boardinfo;
	JButton btn1,btn2,btn3,btn4;
	//JPanel btnPanel;

	public BoardView(BoardModel model, JButton button1, JButton button2, JButton button3, JButton button4) {
	//public BoardView(BoardModel model, JPanel buttonPanel) {
		boardinfo = model;
	//	btnPanel=buttonPanel;
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
		//直線や塗りつぶしの例
		//g.drawLine(20, 30, 50, 30);/*(x1,y1)=(20,30)から(x2,y2)=(50,30)に直線*/
		//g.drawLine(20, 40, 50, 40);
		//g.drawLine(30, 20, 30, 50);
		//g.drawLine(40, 20, 40, 50);
		//g.fillRect(31, 31, 9, 9);/*(x,y)=(31,31)を左上端として，9×9の範囲を塗りつぶし*/
		//for()
		/*int masu_side_len = (this.getHeight()-40)/GlobalVar.side;
		for(int i=0; i<=GlobalVar.side+1; i++) {
			g.drawLine(20, 20+i*masu_side_len, this.getWidth()-20, 20+i*masu_side_len);
		}

		int masu_ver_len = (this.getWidth()-40)/GlobalVar.ver;

		for(int i=0; i<=GlobalVar.ver+1; i++) {
			g.drawLine(20+i*masu_ver_len, 20, 20+i*masu_ver_len, this.getHeight()-20);
		}*/

		/*JButton button1 = new JButton("Next");
		JButton button2 = new JButton("Undo");
		JButton button3 = new JButton("New Game");
		*/
		btn1.setPreferredSize(new Dimension(this.getWidth()/6+20, this.getHeight()/7));
		btn2.setPreferredSize(new Dimension(this.getWidth()/6+20, this.getHeight()/7));
		btn3.setPreferredSize(new Dimension(this.getWidth()/6+20, this.getHeight()/7));
		btn4.setPreferredSize(new Dimension(this.getWidth()/6+20, this.getHeight()/7));
		//System.out.println("yoko=" + btn1.getWidth());
		//System.out.println("tate=" + btn1.getHeight());
		int fontsize=btn1.getHeight()/6+10;

		btn1.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, fontsize));
		btn2.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, fontsize));
		btn3.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, fontsize));
		btn4.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, fontsize));
		/*button1.setActionCommand("button1");
		button2.setActionCommand("button2");
		button3.setActionCommand("button3");
		btnPanel.add(button1);
		btnPanel.add(button2);
		btnPanel.add(button3);

		button1.addActionListener(new buttonHandler(boardinfo, this));
		button2.addActionListener(new buttonHandler(boardinfo,this));
		button3.addActionListener(new buttonHandler(boardinfo,this));
*/
		final_side_line = this.getWidth()-20;
		final_ver_line = this.getHeight()-20;

		g.drawLine(20, 20, final_side_line,20);
		g.drawLine(20, 20, 20, final_ver_line);
		g.drawLine(20, final_ver_line,final_side_line,final_ver_line);
		g.drawLine(final_side_line ,20, final_side_line, final_ver_line);
		masu_side_len = (final_side_line-20)/GlobalVar.side;
		masu_ver_len = (final_ver_line-20)/GlobalVar.ver;

		for(int i=1; i<GlobalVar.side; i++) {
			g.drawLine(20+i*masu_side_len, 20, 20+i*masu_side_len, final_ver_line);
		}

		for(int i=1; i<GlobalVar.ver; i++) {
			g.drawLine(20, 20+i*masu_ver_len, final_side_line, 20+i*masu_ver_len);
		}

		//g.fillRect(20, 20, masu_side_len, masu_ver_len);

	//	boardinfo.fireUpdate();

		System.out.println("kita1");
		int i,j=0;
		for(i=0; i<GlobalVar.ver; i++) {
			for(j=0; j<GlobalVar.side; j++) {
				System.out.println(boardinfo.isAlive(j, i));
				if(boardinfo.isAlive(j, i) == true) {
					System.out.println(j+","+i);
					g.fillRect(20+j*masu_side_len, 20+i*masu_ver_len, masu_side_len, masu_ver_len);
					System.out.println("kita2");
				}
			}
		}


	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mousePressed(MouseEvent e) {
		//(final_side_len-20)/GlobalVar.side
		int getx=e.getX();
		int gety=e.getY();
		int x_count=0;
		int y_count=0;
		if(getx>20 && getx<final_side_line && gety>20 && gety<final_side_line ) {
			System.out.println("sidelen="+masu_side_len);
			while(getx>20+x_count*masu_side_len) {
				++x_count;
			}
			x_count--;
			while(gety>20+y_count*masu_ver_len) {
				++y_count;
			}
			y_count--;

		}
		System.out.println("Pressed: " + e.getX() + ", " + e.getY());
		System.out.println("x_count="+x_count);
		System.out.println("y_count="+y_count);
		boardinfo.changeCellState(x_count, y_count);
		boardinfo.prev_copy();
		this.repaint();

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
