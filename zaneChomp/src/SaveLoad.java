//Dan Adair's serializable demo  changed by chris hales

import java.awt .*;
import java.awt.event .*;
import java.io .*;
import java.applet.Applet;
import java.util .*;

    public class SaveLoad extends Frame implements ActionListener // this extends frame
    {

        public SaveLoad sl;
        //public Board[] loader; // an array of junkFile
        public ArrayList<Board> loader = new ArrayList<Board>();
        //public Board[] saver;
        public ArrayList<Board> saver = new ArrayList<Board>();
        public ArrayList<Board> allboards = new ArrayList<Board>();

        public Button save, load;
        public Canvas c;

        public int counter = 0;

        // this is the main wich is run when you run an application


        // this is the consturctor for saveload and calls initialize

        public SaveLoad() throws IOException {
            initialize();
            resize(400, 300);
            show();
        }

        public void initialize() throws IOException {
            MyPlayerApp z = new MyPlayerApp();
            System.out.println(z.possboard.get(4).printBoard());
            Collections.copy(allboards, z.possboard);

            Button save = new Button("save array");
            save.addActionListener(this);
            Button load = new Button("load array");
            load.addActionListener(this);

            setLayout(new BorderLayout());
            add("Center", save);
            add("North", load);

        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("save array")) {
                // call save array and send it saver
                savearray(allboards);
                //for (int x = 0; x < 15; x++) {
                  //  System.out.println("rx=" + saver[x].rx);
               // }
            }
            if (e.getActionCommand().equals("load array")) {
                loadarray();
            }
        }


        public void loadarray() {
            String fileName = "";
            String dir = "C:/windows/desktop";
            FileDialog fdL = new FileDialog(this, "load me", FileDialog.LOAD);
            fdL.show();
            fileName = fdL.getFile();
            dir = fdL.getDirectory();

            FileInputStream fis;
            try {
                fis = new FileInputStream(dir + fileName);
                ObjectInputStream ois = new ObjectInputStream(fis);
                ArrayList<Board> b = (ArrayList<Board>) ois.readObject();
                //loader = new ArrayList<Board>();
                loader = b;
                fis.close();

            } catch (IOException e) {
                System.out.println("IOException");
                System.out.println(e);
            } catch (ClassNotFoundException e) {
                System.out.println("Class not found");
            }


        }

        public void savearray(ArrayList<Board> s) {
            String fileName = "boards.all";
            String dir = "C:/windows/desktop";


            FileDialog fdS = new FileDialog(this, "SAVE", FileDialog.SAVE);
            fdS.show();
            //fileName = fdS.getFile();
            dir = fdS.getDirectory();


            try {
                FileOutputStream fos = new FileOutputStream(dir + fileName);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(s);
                oos.flush();

                fos.close();
            } catch (IOException e) {
                System.out.println("did not save");

            }

        }


    }


