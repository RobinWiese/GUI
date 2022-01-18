package nervigesGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortierenGUIGruppe extends JFrame implements ActionListener {

    private JPanel pMain;
    private JButton bSortieren;
    private JPanel pDiagramm;
    private JLabel tAusgabe;
    private JButton bReset;

    private int[] array = {7,5,8,3,1,9,6,2,0,4};
    int durchlaeufe = 0;
    int verschiebungen = 0;


    public SortierenGUIGruppe(){

        bSortieren.addActionListener(this);

        //pDiagramm.setPreferredSize(new Dimension(xSize, (int) Math.round(ySize * 0.9)));

        bSortieren.setText("sortiern");
        bReset.setText("reset");

        setTitle("SortierenGUI");
        add(pMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }


    @Override
    public  void actionPerformed(ActionEvent e) {
        Object b = e.getSource();

        if(b == bSortieren){
            InsertionSort();
            durchlaeufe++;
        }else if(b == bReset){
            durchlaeufe = 0;
            verschiebungen = 0;
            array[0] = 7;
            array[1] = 5;
            array[2] = 8;
            array[3] = 3;
            array[4] = 1;
            array[5] = 9;
            array[6] = 6;
            array[7] = 2;
            array[8] = 0;
            array[9] = 4;
            tAusgabe.setText("Durchläufe: " + durchlaeufe + "  |  verschiebungen: " + verschiebungen);
            paint();
        }
    }

    public void paint(){
        Graphics g = pDiagramm.getGraphics();
        g.clearRect(0,0, pDiagramm.getWidth(), pDiagramm.getHeight());

        int x = (int) (pDiagramm.getWidth());
        int y = (int) (pDiagramm.getHeight() - 75);
        int left = 0;
        int right = 0;

        for(int i = 0; i < array.length; i++){
            g.setColor(Color.gray);
            right = right + x / array.length;
            int height = (int) ((((array[i] * 1.0) / 9) * y) * -1) - 10;
            int width = right - left;
            g.fillRect(left, y + 40, width, height);
            g.setColor(Color.black);
            g.setFont(g.getFont().deriveFont(10f));
            g.drawString("" + array[i], left + ((width)/2), y + height + 30);
            left = right + (100/array.length);
            setVisible(true);
        }


    }

    public static void swap(int[] a, int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    public void mySleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void InsertionSort(){
        //paint();

        for (int i = 1; i < array.length; i++) {
            int j = i;

            while (j >= 0 && array[j] < array[j-1]) {
                swap(array, j, j-1);
                verschiebungen++;
                tAusgabe.setText("Durchläufe: " + durchlaeufe + "  |  verschiebungen: " + verschiebungen);
                paint();
                mySleep();
            }
        }
    }

    public static void main(String[] args) {
        new SortierenGUIGruppe();
    }
}
