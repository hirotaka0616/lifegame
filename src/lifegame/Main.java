package lifegame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Main implements Runnable {
//public class Main {

	public static void main(String[] args){
		SwingUtilities.invokeLater(new Main());
		//System.out.println("Hello,world!2");
	}

	public void run () {
		//BoardModel model = new BoardModel(GlobalVar.side_len, GlobalVar.ver_len);
		BoardModel model = new BoardModel(GlobalVar.side, GlobalVar.ver);
		model.addListener(new ModelPrinter());
		model.prev_copy();
		/*model.changeCellState(1, 1);
		model.prev_copy();
		model.changeCellState(2, 2);
		model.prev_copy();
		model.changeCellState(0, 3);
		model.prev_copy();
		model.changeCellState(1, 3);
		model.prev_copy();
		model.changeCellState(2, 3);
		model.prev_copy();
		model.changeCellState(4, 4);
		model.prev_copy();
		model.changeCellState(4, 4);
		model.prev_copy();*/
/*
		for(int i=0; i<12; ++i) {
			System.out.print("call next\n");
			model.next();
		}
		while(model.isUndoable()) {
			System.out.println("call undo\n");
			model.undo();
		}
*/

		JFrame frame = new JFrame();
		frame.setTitle("Lifegame");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel base = new JPanel();
		frame.setContentPane(base);
		base.setPreferredSize(new Dimension(400,300));/*希望のサイズ指定*/
		frame.setMinimumSize(new Dimension(300,200));/*最小サイズの指定*/

		base.setLayout(new BorderLayout());/*baseに配置するGUI部品のルール設定*/

		//BoardView view = new BoardView(model);
		//base.add(view, BorderLayout.CENTER);/*baseの中央にviewを配置する*/



		JPanel buttonPanel = new JPanel(); /*ボタン用パネル作成*/
		base.add(buttonPanel, BorderLayout.SOUTH);/*baseの下端に配置する*/
		buttonPanel.setLayout(new FlowLayout());/*java.awt.FlowLayoutを設定*/
		//buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		JButton button1 = new JButton("Next");
		JButton button2 = new JButton("Undo");
		JButton button3 = new JButton("New Game");
		JButton button4 = new JButton("Auto");
/*		button1.setActionCommand("button1");
		button2.setActionCommand("butston2");
		button3.setActionCommand("button3");
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);
*/


	//	BoardView view = new BoardView(model, button1,button2,button3);
	//	base.add(view, BorderLayout.CENTER);/*baseの中央にviewを配置する*/

		/*button1.setPreferredSize(new Dimension(100, 50));
		button2.setPreferredSize(new Dimension(100, 50));
		button3.setPreferredSize(new Dimension(100, 50));
*/
		button1.setActionCommand("button1");
		button2.setActionCommand("button2");
		button3.setActionCommand("button3");
		button4.setActionCommand("button4");
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);
		buttonPanel.add(button4);

		BoardView view = new BoardView(model, button1,button2,button3,button4);
		base.add(view, BorderLayout.CENTER);/*baseの中央にviewを配置する*/

		//ButtonView buttonview = new ButtonView(model,button1,button2,button3);
		//base.add(buttonview, BorderLayout.SOUTH);



		frame.pack();/*絵引導に乗せたものの配置を確定*/
		frame.setVisible(true);/*ウィンドウに表示する*/

		button1.addActionListener(new buttonHandler(model, view));
		button2.addActionListener(new buttonHandler(model,view));
		button3.addActionListener(new buttonHandler(model,view));
		button4.addActionListener(new buttonHandler(model,view));

	}

}
