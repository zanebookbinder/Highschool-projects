import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.StreamSupport;
import java.lang.Object;

import static java.lang.System.arraycopy;

//ADD SAVING AND LOADING METHODS IN THIS CALSS


public class MyPlayerApp extends Player implements java.io.Serializable{
    public int[] column = new int[10], row = new int[10];
    public Chip[] gameBoard = new Chip[100];
    public int counter;
    public boolean hi = true;
    public Board methodboard, one, Bpossboard;
    public ArrayList<Board> wboards = new ArrayList<Board>();
    public ArrayList<Board> lboards = new ArrayList<Board>();
    public  ArrayList<Board> possboard = new ArrayList<Board>();
    public SaveLoad sl;


    public MyPlayerApp() throws IOException {
        one = new Board(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, true, 9);
        lboards.add(one);
        methodboard = new Board(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, 0);
        //getboardsandmoves();
    }

    public void getboardsandmoves() throws IOException {
        for (int a = 1; a < 11; a++) {
            for (int b = 0; b < 11; b++) {
                if(a>=b) {
                    for (int c = 0; c < 11; c++) {
                        if(b>=c) {
                            for (int d = 0; d < 11; d++) {
                                if(c>=d) {
                                    for (int e = 0; e < 11; e++) {
                                        if(d>=e) {
                                            for (int f = 0; f < 11; f++) {
                                                if(e>=f) {
                                                    for (int g = 0; g < 11; g++) {
                                                        if(f>=g) {
                                                            for (int h = 0; h < 11; h++) {
                                                                if(g>=h) {
                                                                    for (int i = 0; i < 11; i++) {
                                                                        if(h>=i) {
                                                                            for (int j = 0; j < 11; j++) {
                                                                                if(i>=j) {

                                                                                        Bpossboard = new Board(a, b, c, d, e, f, g, h, i, j, false, false, 0);
                                                                                        checkifLosing(Bpossboard);
                                                                                        if (Bpossboard.win == true) {
                                                                                            wboards.add(Bpossboard);
                                                                                        }
                                                                                        if (Bpossboard.loss == true) {
                                                                                            lboards.add(Bpossboard);
                                                                                        }
                                                                                            possboard.add(Bpossboard);
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public int move(Chip[] chip) {
        gameBoard = chip;
        countCols();

        for (int t = 0; t < possboard.size(); t++) {
            if(Arrays.equals(column, possboard.get(t).col)){
                    return (possboard.get(t).wchip);
            }
        }
        return 9;
    }

    public void countCols() {
        for (int i = 0; i < 10; i++) {
            for (int a = 0; a < 10; a++) {
                if (gameBoard[i * 10 + a].isAlive) {
                    counter++;
                }
            }
            column[i] = counter;
            counter = 0;
        }
    }

    public void checkifLosing(Board board) {

            for (int y = 0; y < 10; y++) {
                methodboard.col[y] = board.col[y];
            }

            for (int u = 0; u < 10; u++) {

                    if (methodboard.col[u] > 0) {

                        for (int i = methodboard.col[u]; i > 0; i--) {
                            methodboard.col[u] -= i;
                                for (int t = u+1; t < 10; t++) {
                                    if (methodboard.col[t] > methodboard.col[u]) {
                                        methodboard.col[t] = methodboard.col[u];
                                    }
                                }

                                for (int y = 0; y < lboards.size(); y++) {

                                    if(Arrays.equals(lboards.get(y).col,methodboard.col)){
                                            Bpossboard.win = true;
                                            Bpossboard.wchip = (10 * u) + (9 - methodboard.col[u]);
                                            return;
                                    }
                                }

                                for (int t = 0; t < 10; t++) {
                                    methodboard.col[t] = board.col[t];
                                }
                        }
                    }
            }

             Bpossboard.loss = true;
                for(int t = 9; t>-1; t--){
                    if(hi && Bpossboard.col[t]!=0){
                        Bpossboard.wchip = (10 * t) + (10 - Bpossboard.col[t]);
                        return;
                    }
                }
    }
}//class 207