import java.awt.*;

public class box {
    public int xpos, ypos;
    public Rectangle rec;
    public int xrec, yrec;
    public int num;
    public int resistance = 100000;


    public box(int txpos, int typos){
        xpos = txpos;
        ypos = typos;
        num = (int)(Math.random() * 100);



        xrec = 200;
        yrec = 200;

        rec = new Rectangle(xpos,ypos,xrec,yrec);
    }
}
