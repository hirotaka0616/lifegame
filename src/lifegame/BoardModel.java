package lifegame;

import java.util.ArrayList;

public class BoardModel{

	private int cols;
	private int rows;
	private ArrayList<BoardListener> listeners;
	//private ArrayList<Boolean> prev_copys;

	/*ArrayListは要素数を指定しない配列のようなもの*/

	private boolean[][] cells;/*一番左上[0][0]，その右隣[0][1]*/
	/*publicとprivateの違いはクラスの外からもアクセスできるかできないか*/
	private boolean[][] prev_cells;/*1つ前のセルだけ記録*/
	private boolean[][] prev_copy;/*初期から現在までのセルの状態を記録*/
	//private ArrayList<boolean[][]> prev_copy = new ArrayList<boolean[][]>();

	//prev_copy.add(cells);
	//prev_copy.get(prev_copy.size() -1);

	public boolean isAlive(int x, int y) {/*指定されたセルが生きているか*/
		if(cells[y][x]==true) {
			return true;
		}else {
			return false;
		}
	}


	public boolean isUndoable() {/*undoができるかどうかの判定*/
		if(GlobalVar.undo_call_cnt>32 || GlobalVar.current_cnt==0) {
			return false;
		}else {
			return true;
		}
	}


	public void newgame() {/*盤面の初期化*/
		for(int i=0; i<GlobalVar.side; i++) {
			for(int j=0; j<GlobalVar.ver; j++) {
				cells[i][j]=false;
			}
		}
		fireUpdate();
	}

	public void undo() {/*前の状態に巻き戻し*/
		GlobalVar.current_cnt--;
		int cp=GlobalVar.current_cnt;
		System.out.println("cp"+cp);
		GlobalVar.undo_call_cnt++;
		for(int i=0; i<GlobalVar.ver; i++) {
			for(int j=0; j<GlobalVar.side; j++) {
				cells[i][j]=prev_copy[i+cp*10][j];

			}
		}
		//cells = prev_copy.get(prev_copy.size()-cp);

		fireUpdate();
	}

	public void auto() {
		next();
	}

	public boolean diff_prev_current() {
		/*前の盤面と現在の盤面が違えばtrue，そうでなければfalse*/
		for(int i=0; i<GlobalVar.ver; i++) {
			for(int j=0; j<GlobalVar.side; j++) {
				if(prev_cells[i][j]!=cells[i][j]) break;
				if(i==GlobalVar.ver-1 && j == GlobalVar.side-1) {
					return false;/*前の盤面同じ*/
				}
			}
		}
		return true;/*前の盤面と違う*/
	}

	public void prev_copy(){/*前の盤面の情報をprev_copy[]に保存*/
		GlobalVar.current_cnt++;
		int cp=GlobalVar.current_cnt;
		System.out.println("cp"+cp);
		//for(int i=cp*10; i<(cp+1)*10; i++) {
		for(int i=0; i<GlobalVar.ver; i++) {
			for(int j=0; j<GlobalVar.side; j++) {
				//prev_copys.add(cells[i][j]);
				prev_copy[i+cp*10][j]=cells[i][j];
			}
		}
		//prev_copy.add(cells);
	}


	public void next() {/*盤面を1世代更新*/
		int living_cells_num;
		GlobalVar.undo_call_cnt=0;
		for(int i=0; i<GlobalVar.ver; i++) {
			for(int j=0; j<GlobalVar.side; j++) {
				prev_cells[i][j]=cells[i][j];
			}
		}
		//System.out.print("ugoke\n");
		for(int y=0; y<GlobalVar.ver; y++) {
			//System.out.print("oi\n");
			for(int x=0; x<GlobalVar.side; x++) {
				//System.out.print("nextx\n");
				living_cells_num = get_servival_cell_count(x,y,prev_cells);
				if(prev_cells[y][x]==true && servival_condition(x,y,living_cells_num)==false){
					//System.out.print("dead\n");
					changeCellState(x,y);
				}else if(prev_cells[y][x]==false && birth_condition(x,y,living_cells_num)==true) {
					//System.out.print("birth\n");
					changeCellState(x,y);
				}
			}
		}
		prev_copy();
		fireUpdate();
	}

	private boolean birth_condition(int x, int y, int living_cells_num) {/*セル誕生条件*/
		if(living_cells_num == 3) {
		//	System.out.print("birth true\n");
			return true;
		}else {
			//System.out.print("birth false\n");
			return false;
		}
	}

	private boolean servival_condition(int x, int y, int living_cells_num) {/*セル生存条件*/
		if(living_cells_num == 2 || living_cells_num == 3) {
			//System.out.print("servival true\n");
			return true;
		}else {
			//System.out.print("servival false\n");
			return false;
		}
	}

	private int get_servival_cell_count(int x, int y, boolean [][] prev_cell) {
		/*周りの生きているセルの数を取得*/
		int servival_cell_count=0;
		for(int i=y-1; i<=y+1; i++) {
			for(int j=x-1; j<=x+1; j++) {
				if(i<0 || i>9 || j<0 || j>9 || (i==y && j==x)) {/*セルなし*/
				}else if(prev_cell[i][j]==true) {/*生きているセルがある場合カウント増やす*/
					servival_cell_count++;
				}
			}
		}
		//System.out.println("cell["+y +"]["+x+"]count ="+ servival_cell_count);
		return servival_cell_count;
	}

	public BoardModel(int c, int r) { /*コンストラクタ*/
		cols = c;
		rows = r;
		cells = new boolean [rows][cols];
		prev_cells = new boolean [rows][cols];
		prev_copy = new boolean[rows*10000][cols];
		listeners = new ArrayList<BoardListener>();/*BoardListenerを格納するためのオブジェクトを作成しフィールドに代入する*/
		//prev_copys = new ArrayList<Boolean>();
	}

	public void addListener(BoardListener listener) {
		/*盤面の状態が更新されたときメソッドを呼び出したいオブジェクトの登録を受け付ける*/
		listeners.add(listener);
		//fireUpdate();/*自分で初期状態の確認のため追加*/
	}

	//private void fireUpdate() {
	public void fireUpdate() {
		/*更新の伝達，listenersに追加さてた各オブジェクトに対しupdateメソッドの呼び出し*/
		for (BoardListener listener: listeners) {
			listener.updated(this);
		}
	}

	public int getCols() {
		return cols;
	}

	public int getRows() {
		return rows;
	}

	public void printForDebug() {
		//System.out.println(cells[0][0]);
		for(int y = 0; y<10; y++) {
			for(int x = 0; x<10; x++) {
				if(cells[y][x] == false) {
					System.out.print(".");
				}else if(cells[y][x] == true) {
					System.out.print("*");
				}
			}
			System.out.println("↓");
		}
		System.out.println("↓");
	}

	public void changeCellState(int x, int y) {
		/*(x,y)で指定されたセルの状態を変更する*/
		if(cells[y][x] == true) {
			cells[y][x] = false;
		}else if(cells[y][x] == false) {
			cells[y][x] = true;
		}
		fireUpdate();
	}


}
