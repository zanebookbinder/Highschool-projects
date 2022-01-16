import java.awt.*;

public class Wario {
    public int xpos, ypos;
    public Rectangle rec;
    public int xrec, yrec;
    public Image ImWario;
    public boolean Max = true;

    public Wario(Image Wario, int pxpos, int pypos){
        ImWario = Wario;
        xpos = pxpos;
        ypos = pypos;

        xrec = 100;
        yrec = 100;


        rec = new Rectangle(xpos,ypos,xrec,yrec);
    }
}
