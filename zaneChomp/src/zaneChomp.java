import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class zaneChomp extends Frame implements ActionListener {

    public Rectangle mouserec;
    public int CELL_WIDTH = 25, NUM_ROWS = 10, NUM_COLLOMS = 10, xpos = 0, ypos = 0, chipNum = 0,  counter = 0;
    public int[] column = new int[10];
    public Chip[] piec = new Chip[100];
    public boolean youLose = false;
    public Button start, suicide, zaney;
    public Panel p = new Panel();
    public String loser;
    public  ArrayList<Board> loader = new ArrayList<Board>();
    //public MyPlayerApp zane;

    public static void main(String[] args) throws IOException {
        zaneChomp c = new zaneChomp();
        c.resize(400, 400);
        c.show();
    }

    public zaneChomp() throws IOException {
        init();
    }

    public void init() throws IOException {
        try {
            URL desktopURL = new URL("C:\\windows\\desktop\\");
        } catch (Exception e) {

        }

        //zane = new MyPlayerApp();

        zaney = new Button("Perfect Move");
        zaney.addActionListener(this);
        p.add(zaney);

        start = new Button("NewGame");
        start.addActionListener(this);
        p.add(start);


        suicide = new Button("End Game");
        suicide.addActionListener(this);
        p.add(suicide);


        setLayout(new BorderLayout());
        add("South", p);
        for (int i = 0; i < NUM_COLLOMS; i++) {

            for (int z = 0; z < NUM_ROWS; z++) {
                xpos = i * 25;
                ypos = z * 25 + 20;
                piec[chipNum] = new Chip(xpos + 18, ypos + 18, chipNum);
                if (chipNum < 100) {
                    chipNum++;
                }
            }
        }
            //savearray(zane);
            loadarray();
    }

    public void loadarray() {
        String fileName = "AllBoards.txt";
        String dir = "/Users/zanebookbinder/Desktop/zaneChomp";

        FileInputStream fis;
        try {
            fis = new FileInputStream(dir + "/"+ fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            loader = (ArrayList<Board>) ois.readObject();
            fis.close();

        } catch (IOException e) {
            System.out.println("IOException");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
    }

    public void savearray(MyPlayerApp zane) {
        String fileName = "";
        String dir = "/windows/desktop/2018-2019/zaneChomp";

        FileDialog fdS = new FileDialog(this, "SAVE", FileDialog.SAVE);
        fdS.show();
        fileName = fdS.getFile();
        dir = fdS.getDirectory();

        try {
            FileOutputStream fos = new FileOutputStream(dir + fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(zane);
            oos.flush();
            fos.close();

        } catch (IOException e) {
            System.out.println("did not save");

        }
    }

    public boolean mouseDown(Event evt, int x, int y) {
        mouserec = new Rectangle(x, y, 2, 2);
        for (int z = 0; z < 100; z++) {
            if (mouserec.intersects(piec[z].myRect)) {
                move(z);
                repaint();
            }
        }
        return (true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("NewGame")) {
            youLose = false;
            for (int z = 0; z < 100; z++) {
                piec[z].isAlive = true;
            }
            repaint();
        }

        if (e.getActionCommand().equals("Perfect Move")) {

            int num = movem(loader);
            move(num);
            repaint();
        }

        if (e.getActionCommand().equals("End Game")) {
            move(9);
            repaint();
        }
    }

    public int movem(ArrayList<Board> list) {

        countCols();

        for (int t = 0; t < list.size(); t++) {
            if(Arrays.equals(column, list.get(t).col)){
                return (list.get(t).wchip);
            }
        }
        return 9;
    }

    public void move(int z) {
            notLegal(piec[z], z);
            for (int q = 0; q < 100; q++) {
                if ((piec[z].xpos <= piec[q].xpos) && (piec[z].ypos >= piec[q].ypos)) {
                    piec[q].isAlive = false;
                }
            }
            if (youLose == true) {
                System.out.println(loser + "  is the loser");
            }
    }

    public void notLegal(Chip chip, int chipNum) {
        if (chip.isAlive == false) {
            youLose = true;
        }
        if (chipNum == 9) {
            youLose = true;
        }
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void paint(Graphics g)  // was boardPaint
    {//System.out.println("paint");
        if (youLose) {
            g.setColor(Color.black);
            g.fillRect(0, 0, 600, 600);
            g.setColor(Color.red);
            g.setFont(new Font("Times Roman", Font.BOLD, 40));
            g.drawString("YOU LOSE", 100, 150);
        }
        if (youLose == false) {
            chipNum = 0;
            // paint the framework
            g.setColor(Color.lightGray);
            g.fillRect(15, 15 + 20, CELL_WIDTH * NUM_ROWS, CELL_WIDTH * NUM_ROWS);
            g.setColor(Color.black);
            g.drawRect(10, 10 + 20, CELL_WIDTH * NUM_ROWS + 10, CELL_WIDTH * NUM_ROWS + 10);
            for (int c = 0; c < NUM_ROWS; c++)
                for (int r = 0; r < NUM_ROWS; r++)
                    g.drawRect(15 + CELL_WIDTH * c, 15 + 20 + CELL_WIDTH * r, CELL_WIDTH, CELL_WIDTH);

            for (int i = 0; i < 100; i++) {

                g.setColor(Color.red);

                if (piec[i].isAlive) {
                    g.fillOval(piec[i].xpos, piec[i].ypos, CELL_WIDTH - 6, CELL_WIDTH - 6);
                    g.setColor(Color.blue);
                    g.drawOval(piec[i].xpos, piec[i].ypos, CELL_WIDTH - 6, CELL_WIDTH - 6);
                    g.setColor(Color.red);

                    g.setColor(Color.blue);
                    if (piec[9].isAlive)
                        g.fillOval(piec[9].xpos, piec[9].ypos, CELL_WIDTH - 6, CELL_WIDTH - 6);
                }
            }
        }//close for (youlose==fales)
    }

    public void countCols() {
        for (int i = 0; i < 10; i++) {
            for (int a = 0; a < 10; a++) {
                if (piec[i * 10 + a].isAlive) {
                    counter++;
                }
            }
            column[i] = counter;
            counter = 0;
        }
    }
}


