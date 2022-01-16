import sun.awt.image.ToolkitImage;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class Pathfinder implements Runnable {

	final int WIDTH = 600;
	final int HEIGHT = 600;

	JFrame frame;
	Canvas canvas;
	BufferStrategy bufferStrategy;

	public Image grid;
	public Image Wario;
	public Image Wario2;
    public Image Wario3;
    public Image Wario4;


    private box boxes[][];
    public String finalpath;
    public String finalpath2;
    public String finalpath3;
    public String finalpath4;


    public boolean j = true;
    public boolean y = true;
    public boolean r = true;
    public boolean t = true;
    public boolean aaaa = true;
    public boolean bbbb = true;
    public boolean cccc = true;
    public boolean dddd = true;
    public boolean eeee = true;
    private boolean zzzz = true;

    public int bestr = 10000;
    public int bestr2 = 10000;
    public int bestr3 = 10000;
    public int bestr4 = 10000;

    public int count = 0;
    public int count2 = 0;
    private int sint[];
    private int sint2[];
    private int sint3[];
    private int sint4[];
    private int xxpos = 0;
    private int yypos = 0;
    private int xxxpos = 400;
    private int yyypos = 0;
    private int xxpos2 = 400;
    private int yypos2 = 400;
    private int xxxpos2 = 0;
    private int yyypos2 = 400;

	public static void main(String[] args) {
		Pathfinder ex = new Pathfinder();
		new Thread(ex).start();
	}

	public Pathfinder() {
		frame = new JFrame("Basic Game");

		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		canvas = new Canvas();
		canvas.setBounds(0, 0, WIDTH, HEIGHT);
		canvas.setIgnoreRepaint(true);

		panel.add(canvas);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);

		grid = Toolkit.getDefaultToolkit().getImage("3x3.png");

        Wario = Toolkit.getDefaultToolkit().getImage("Wario.png");
        Wario2 = Toolkit.getDefaultToolkit().getImage("Wario.png");
        Wario3 = Toolkit.getDefaultToolkit().getImage("Wario.png");
        Wario4 = Toolkit.getDefaultToolkit().getImage("Wario.png");


        canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();

		boxes = new box[3][3];
		sint = new int[10];
		sint2 = new int[10];
        sint3 = new int[10];
        sint4 = new int[10];


        for (int y = 0; y<3; y++){
			for(int q = 0; q<3; q++){
				boxes[y][q] = new box(y*200, q*200);
			}
		}

		canvas.requestFocus();
	}// BasicGameApp()


	//thread
	public void run() {

		while (true) {
			// paint the graphics
			render();

			//sleep
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
			}
		}
	}

	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		
        g.drawImage(grid, 0,0, 600, 600, null);

        //Draws Wario at 0,0 until it needs to move
        if(zzzz == true) {
            g.drawImage(Wario, xxpos, yypos, 200, 200, null);
            g.drawImage(Wario2, xxxpos, yyypos, 200, 200, null);
            g.drawImage(Wario3, xxpos2, yypos2, 200, 200, null);
            g.drawImage(Wario4, xxxpos2, yyypos2, 200, 200, null);

        }

        //draws numbers in 9 boxes
        for(int e = 0; e<3;e++) {
            for(int p = 0; p<3; p++) {
                g.setColor(new Color(255-(2*boxes[e][p].num), 255-(2*boxes[e][p].num), 255-(2*boxes[e][p].num)));
                g.fillRect(boxes[e][p].xpos, boxes[e][p].ypos, 200,200);
                g.setColor(Color.BLUE);
                g.drawString(Integer.toString(boxes[e][p].num), (e+1)*200-30, ((p)*200)+30);
            }
        }

        //Calls initial method at starting points
		if (j==true) {
			getValue(0, 0,0, "");
            System.out.println(bestr);
            System.out.println("FINAL PATH IS " + finalpath);
            j=false;
		}

		if(y== true){
            getValue2(2,0,0,"");
            System.out.println(bestr2);
            System.out.println("Final path 2 is " + finalpath2);
            y = false;
        }

        if(r== true){
            getValue3(2,2,0,"");
            System.out.println(bestr3);
            System.out.println("Final path 3 is " + finalpath3);
            r = false;
        }

        if(t== true){
            getValue4(0,2,0,"");
            System.out.println(bestr4);
            System.out.println("Final path 4 is " + finalpath4);
            t = false;
        }






		//Turns finalpath string into an array of integers
        if(aaaa == true) {
            for (int e = 0; e < finalpath.length(); e++) {
                sint[e] = Integer.parseInt(finalpath.substring(e, e + 1));
            }
            for(int f = 0; f<finalpath2.length(); f++){
                sint2[f] = Integer.parseInt(finalpath2.substring(f, f+1));
            }
            for(int f = 0; f<finalpath3.length(); f++){
                sint3[f] = Integer.parseInt(finalpath3.substring(f, f+1));
            }
            for(int f = 0; f<finalpath4.length(); f++){
                sint4[f] = Integer.parseInt(finalpath4.substring(f, f+1));
            }
            count ++;   //count = 1
            aaaa= false;
        }

        //Assigns letters to array for convenience
        int c = sint[2];
        int d = sint[3];
        int e = sint[4];
        int f = sint[5];
        int gg = sint[6];
        int h = sint[7];
        int i = sint[8];
        int jj = sint[9];

        int c2 = sint2[2];
        int d2 = sint2[3];
        int e2 = sint2[4];
        int f2 = sint2[5];
        int gg2 = sint2[6];
        int h2 = sint2[7];
        int i2 = sint2[8];
        int jj2 = sint2[9];

        int c3 = sint3[2];
        int d3 = sint3[3];
        int e3 = sint3[4];
        int f3 = sint3[5];
        int gg3 = sint3[6];
        int h3 = sint3[7];
        int i3 = sint3[8];
        int jj3 = sint3[9];

        int c4 = sint4[2];
        int d4 = sint4[3];
        int e4 = sint4[4];
        int f4 = sint4[5];
        int gg4 = sint4[6];
        int h4 = sint4[7];
        int i4 = sint4[8];
        int jj4 = sint4[9];

        if(count ==1){
            count2++;
            g.drawImage(Wario, 0,0,200,200,null);
            g.drawImage(Wario2, 400,0,200,200,null);
            g.drawImage(Wario3, 400,400,200,200,null);
            g.drawImage(Wario4, 0,400,200,200,null);

        }

        //Holds Wario at 0,0 until count2 reaches 200
        if(bbbb == true && count2 > 150){
            count++;    //count = 2
            bbbb = false;
            zzzz =false;     //stop drawing Wario at 0,0
            count2 = 0;
        }

        if(count == 2) {
            //Move Wario right if neccesary
            if(xxpos<boxes[c][d].xpos){
                xxpos +=5;
            }
            if(xxxpos>boxes[c2][d2].xpos){
                xxxpos -=5;
            }
            if(xxpos2>boxes[c3][d3].xpos){
                xxpos2 -=5;
            }
            if(xxxpos2<boxes[c4][d4].xpos){
                xxxpos2 +=5;
            }
            //Move Wario down if neccesary
            if(yypos<boxes[c][d].ypos){
                yypos +=5;
            }
            if(yyypos<boxes[c2][d2].ypos){
                yyypos +=5;
            }
            if(yypos2>boxes[c3][d3].ypos){
                yypos2 -=5;
            }
            if(yyypos2>boxes[c4][d4].ypos){
                yyypos2 -=5;
            }
            g.drawImage(Wario, xxpos, yypos, 200, 200, null);
            g.drawImage(Wario2, xxxpos, yyypos, 200, 200, null);
            g.drawImage(Wario3, xxpos2, yypos2, 200, 200, null);
            g.drawImage(Wario4, xxxpos2, yyypos2, 200, 200, null);
            count2++;
        }

        //Holds Wario at 2nd position until further notice
        if(cccc == true && count2 > 200){
            count++;   //count = 3
            cccc = false;
            count2 = 0;
        }

        if(count == 3) {
            //Moves right if needed
            if(xxpos<boxes[e][f].xpos){
                xxpos +=5;
            }
            if(xxxpos>boxes[e2][f2].xpos){
                xxxpos -=5;
            }
            if(xxpos2>boxes[e3][f3].xpos){
                xxpos2 -=5;
            }
            if(xxxpos2<boxes[e4][f4].xpos){
                xxxpos2 +=5;
            }
            //Move Wario down if neccesary
            if(yypos<boxes[e][f].ypos){
                yypos +=5;
            }
            if(yyypos<boxes[e2][f2].ypos){
                yyypos +=5;
            }
            if(yypos2>boxes[e3][f3].ypos){
                yypos2 -=5;
            }
            if(yyypos2>boxes[e4][f4].ypos){
                yyypos2 -=5;
            }
            g.drawImage(Wario, xxpos, yypos, 200, 200, null);
            g.drawImage(Wario2, xxxpos, yyypos, 200, 200, null);
            g.drawImage(Wario3, xxpos2, yypos2, 200, 200, null);
            g.drawImage(Wario4, xxxpos2, yyypos2, 200, 200, null);
            count2++;
        }

        //Holds Wario at 3rd position until further notice
        if(dddd == true && count2 > 200){
            count++;   //count = 4
            dddd = false;
            count2 = 0;
        }

        //if more than 3 moves are needed
        if(finalpath.length()>6) {
            if (count == 4) {
                //Moves Wario right if needed
                if(xxpos<boxes[gg][h].xpos){
                    xxpos +=5;
                }
                if(xxxpos>boxes[gg2][h2].xpos){
                    xxxpos -=5;
                }
                if(xxpos2>boxes[gg3][h3].xpos){
                    xxpos2 -=5;
                }
                if(xxxpos2<boxes[gg4][h4].xpos){
                    xxxpos2 +=5;
                }
                //Move Wario down if neccesary
                if(yypos<boxes[gg][h].ypos){
                    yypos +=5;
                }
                if(yyypos<boxes[gg2][h2].ypos){
                    yyypos +=5;
                }
                if(yypos2>boxes[gg3][h3].ypos){
                    yypos2 -=5;
                }
                if(yyypos2>boxes[gg4][h4].ypos){
                    yyypos2 -=5;
                }
                g.drawImage(Wario, xxpos, yypos, 200, 200, null);
                g.drawImage(Wario2, xxxpos, yyypos, 200, 200, null);
                g.drawImage(Wario3, xxpos2, yypos2, 200, 200, null);
                g.drawImage(Wario4, xxxpos2, yyypos2, 200, 200, null);
                count2++;
            }
        }

        //Holds Wario at 4th position until further notice
        if(eeee == true && count2 >200){
            count++;  //count = 5
            eeee = false;
            count2 = 0;
        }

        //if 5 moves are needed
        if(finalpath.length()>8) {
            if (count == 5) {
                //moves Wario right
                if(xxpos<boxes[i][jj].xpos){
                    xxpos +=5;
                }
                if(xxxpos>boxes[i2][jj2].xpos){
                    xxxpos -=5;
                }
                if(xxpos2>boxes[i3][jj3].xpos){
                    xxpos2 -=5;
                }
                if(xxxpos2<boxes[i4][jj4].xpos){
                    xxxpos2 +=5;
                }
                //Move Wario down if neccesary
                if(yypos<boxes[i][jj].ypos){
                    yypos +=5;
                }
                if(yyypos<boxes[i2][jj2].ypos){
                    yyypos +=5;
                }
                if(yypos2>boxes[i3][jj3].ypos){
                    yypos2 -=5;
                }
                if(yyypos2>boxes[i4][jj4].ypos){
                    yyypos2 -=5;
                }
                g.drawImage(Wario, xxpos, yypos, 200, 200, null);
                g.drawImage(Wario2, xxxpos, yyypos, 200, 200, null);
                g.drawImage(Wario3, xxpos2, yypos2, 200, 200, null);
                g.drawImage(Wario4, xxxpos2, yyypos2, 200, 200, null);
            }
        }

        if(xxpos == 400 && yypos == 400){
            g.drawImage(Wario, xxpos, yypos, 200,200,null);
        }

        if(xxxpos == 0 && yyypos == 400){
            g.drawImage(Wario2, xxxpos, yyypos, 200,200,null);
        }

        if(xxpos2 == 0 && yypos2 == 0){
            g.drawImage(Wario3, xxpos2, yypos2, 200,200,null);
        }

        if(xxxpos2 == 400 && yyypos2 == 0){
            g.drawImage(Wario4, xxxpos2, yyypos2, 200,200,null);
        }

        System.out.println("this is Wario3's xpos        " + yypos2);
        System.out.println("this is Wario4's xpos   " + yyypos2);

        g.dispose();
		bufferStrategy.show();
	}

	public void getValue(int v, int b, int RESISTANCE, String path){

	    if(v<=2 && b<=2){

	    	//Totals up boxes for different paths
            int resistance = RESISTANCE+boxes[v][b].num;
            path = path + Integer.toString(v) + Integer.toString(b);

	        if (v==2 && b==2) {

                //Makes best path
                if(resistance< bestr){
                    bestr = resistance;
                    finalpath = path;
                }
                else{
                }
            }
                //Gets Values for squares diagonal, right, and below
                    getValue(v + 1, b + 1, resistance, path);
                    getValue(v, b + 1, resistance, path);
                    getValue(v + 1, b, resistance, path);
	    }
	}

    public void getValue2(int x, int y, int RESISTANCE2, String path2){

        if(x>=0 && y<=2){

            //Totals up boxes for different paths
            int resistance2 = RESISTANCE2+boxes[x][y].num;
            path2 = path2 + Integer.toString(x) + Integer.toString(y);

            if (x==0 && y==2) {

                //Makes best path
                if(resistance2< bestr2){
                    bestr2 = resistance2;
                    finalpath2 = path2;
                }
                else{
                }
            }


                //Gets Values for squares diagonal, right, and below
                if (RESISTANCE2 < bestr2) {
                   // System.out.println(x + "      " + y);
                    getValue2(x - 1, y + 1, resistance2, path2);
                }
                if (RESISTANCE2 < bestr2) {
                   // System.out.println(x + "      " + y);
                    getValue2(x, y + 1, resistance2, path2);
                }
                if (RESISTANCE2 < bestr2) {
                   // System.out.println(x + "      " + y);
                    getValue2(x - 1, y, resistance2, path2);
                }

        }
    }

    public void getValue3(int j, int k, int RESISTANCE3, String path3){

        if(j>=0 && k>=0){

            //Totals up boxes for different paths
            int resistance3 = RESISTANCE3+boxes[j][k].num;
            path3 = path3 + Integer.toString(j) + Integer.toString(k);

            if (j==0 && k==0) {

                //Makes best path
                if(resistance3< bestr3){
                    bestr3 = resistance3;
                    finalpath3 = path3;
                }
                else{
                }
            }


            //Gets Values for squares diagonal, right, and below
            if (RESISTANCE3 < bestr3) {
                // System.out.println(x + "      " + y);
                getValue3(j - 1, k - 1, resistance3, path3);
            }
            if (RESISTANCE3 < bestr3) {
                // System.out.println(x + "      " + y);
                getValue3(j-1, k, resistance3, path3);
            }
            if (RESISTANCE3 < bestr3) {
                // System.out.println(x + "      " + y);
                getValue3(j, k-1, resistance3, path3);
            }

        }
    }

    public void getValue4(int o, int p, int RESISTANCE4, String path4){

        if(o<=2 && p>=0){

            //Totals up boxes for different paths
            int resistance4 = RESISTANCE4+boxes[o][p].num;
            path4 = path4 + Integer.toString(o) + Integer.toString(p);

            if (o==2 && p==0) {

                //Makes best path
                if(resistance4< bestr4){
                    bestr4 = resistance4;
                    finalpath4 = path4;
                }
                else{
                }
            }


            //Gets Values for squares diagonal, right, and below
            if (RESISTANCE4 < bestr4) {
                // System.out.println(x + "      " + y);
                getValue4(o+1, p - 1, resistance4, path4);
            }
            if (RESISTANCE4 < bestr4) {
                // System.out.println(x + "      " + y);
                getValue4(o+1, p, resistance4, path4);
            }
            if (RESISTANCE4 < bestr4) {
                // System.out.println(x + "      " + y);
                getValue4(o, p-1, resistance4, path4);
            }

        }
    }
}
