import java.awt.*;

public class Board implements java.io.Serializable{

    public int a,b,c,d,e,f,g,h,i,j;
    public int[] col;

    public Rectangle myRect;
    public boolean win, loss;
    public boolean isAlive;
    public int wchip;
//values for all 10 columns, win or loss, best move

    public Board(int aa, int bb, int cc, int dd, int ee, int ff, int gg, int hh, int ii, int jj, boolean w, boolean l, int chipNum){

        col=new int[10];
        col[0] = aa;
        col[1] = bb;
        col[2] = cc;
        col[3] = dd;
        col[4] = ee;
        col[5] = ff;
        col[6] = gg;
        col[7] = hh;
        col[8] = ii;
        col[9] = jj;
        win = w;
        loss = l;
        wchip = chipNum;
    }

    public String printBoard() {
        String out = "";

        for (int x = 0; x < col.length; x++) {
            out += Integer.toString(col[x]) + ", ";
        }
        out +=  ("L   " + loss + "    W     "  + win);
        out += ("   WChip:    " + wchip);

        return out;
    }

    public String printSmallBoard() {
        String out = "";

        for (int x = 0; x < col.length; x++) {
            out += Integer.toString(col[x]) + ", ";
        }

        return out;
    }

}

