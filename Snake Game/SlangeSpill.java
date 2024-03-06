import java.awt.*;
import java.awt.event.*;

import javax.print.event.PrintEvent;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.AncestorListener;
import javax.swing.plaf.ColorUIResource;

import org.w3c.dom.css.Rect;
import java.util.ArrayList;
import java.util.Random;

public class SlangeSpill implements ActionListener{
	
    private int x_verdi = 10;
    private int y_verdi = 10;

    private int x;
    private int y;

    private int str =5;

    private int antallTatt, antallPaaBrett = 0;
    protected Slange slange;
    public Slange koordinater;
    protected ArrayList<Koordinater> slangeKor;

    protected Thread traad; 
    protected boolean bevegelse;

	protected static final int width = 75;
	protected static final int height = 75;
	protected static final int dimension = 10;

    //protected int lengde, bredde;
    protected  JLabel lengde;
    protected  JLabel liv;
    protected JFrame vindu;
    protected JPanel panel, brett;
    protected JLabel[][] ruter= new JLabel[10][10];


    public SlangeSpill() {
		
        try { 
            UIManager.setLookAndFeel(
            UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {
                 System.exit(1); 
            }
        
            traad = new Thread(new Teller());
            traad.start();

        vindu = new JFrame("Slangespill");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel  = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.add(pacmanTekst(), BorderLayout.NORTH);

        panel.add(knapper(), BorderLayout.SOUTH);

       

        brett = tegnBrett(); 
        panel.add(brett);

        slange = new Slange(this);


        //vindu.addActionListener(new actionPerformed());
        vindu.addKeyListener(new TrykketKnapp());
        vindu.setFocusable(true);
        vindu.add(panel);
        vindu.pack();
		vindu.setTitle("Snake");
		vindu.setSize(width * dimension + 2, height * dimension + dimension + 4);
		vindu.setVisible(true);
		
	}

    class TrykketKnapp implements KeyListener{
        //protected Rettning rettning;
        @Override
        public void keyPressed(KeyEvent e) {
            int nokkel = e.getKeyCode();
            
            
            if(nokkel == KeyEvent.VK_LEFT) {
                slange.settRettning(Retning.VEST);
            }
            if(nokkel == KeyEvent.VK_UP) {
                slange.settRettning(Retning.NORD);
            }
            if(nokkel == KeyEvent.VK_DOWN) {
                slange.settRettning(Retning.SOR);
            }
            if(nokkel == KeyEvent.VK_RIGHT) {
                slange.settRettning(Retning.OST);
            }
        }

        @Override
        public void keyTyped(KeyEvent e) { }

        @Override
        public void keyReleased(KeyEvent e) { }
    }

    class Teller implements Runnable{
        @Override
        public void run(){
            while(true){
                try {
                    Thread.sleep(300);
                    
                } catch (Exception e) {
                    return;
                } 
                slange.flytt(); // selv kollisjon
            }
        }
    }

	public JPanel tegnBrett(){// metode for slangebrettet
        JPanel brett = new JPanel(); // oppretter et brett 
        brett.setLayout(new GridLayout(dimension,dimension)); // fikser dimesjonene
        for (int rad = 0; rad < dimension; rad++) { // angir storrelse paa brett
            for (int kol = 0; kol < dimension; kol++) {
                 JLabel rute = new JLabel(" ");
                 Border kanter = BorderFactory.createLineBorder(Color.BLACK, 1); // setter paa kanter
                 rute.setBorder(kanter);
                 ruter[rad][kol] = rute;
                 rute.setOpaque(true);
                 rute.setHorizontalAlignment(JLabel.CENTER);
                 rute.setVerticalAlignment(JLabel.CENTER);
                 brett.add(rute); 

             }
         }return brett;
    }

    public void setTeller(int teller) {
        lengde.setText(slange.slangeArray.size() + " lengde");
    }

    protected JPanel pacmanTekst() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK);

        JLabel tittel = new JLabel("Slangespill");
        tittel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 25));
        tittel.setForeground(Color.PINK);
        panel.add(tittel, BorderLayout.LINE_START);

        liv = new JLabel("Liv: * * * ", SwingConstants.CENTER);
        liv.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        liv.setForeground(Color.PINK); 
        panel.add(liv, BorderLayout.CENTER);
        
        lengde = new JLabel("0 lengde");
        lengde.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        lengde.setForeground(Color.PINK); 
        panel.add(lengde, BorderLayout.LINE_END);

        return panel;
    }

    protected JPanel knapper() {
        JPanel panel = new JPanel();
       // panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK);

        JButton OPP  = new JButton("Opp");
        OPP.setBounds(50, 5,100, 40);
        panel.add(OPP);
        OPP.addActionListener(this);
        
        JButton VENSTRE  = new JButton("Venstre");
        VENSTRE.setBounds(50, 10,100, 40);
        panel.add(VENSTRE);
        VENSTRE.addActionListener(this);

        JButton HOYRE  = new JButton("Hoyre");
        HOYRE.setBounds(50, 10,100, 40);
        panel.add(HOYRE);
        HOYRE.addActionListener(this);

        JButton NED  = new JButton("Ned");
        NED.setBounds(50, 10,100, 40);
        panel.add(NED);
        NED.addActionListener(this);

        return panel;
    }


    public Koordinater tegnSlange(int slangeRad, int slangeBredde) {
        ruter[slangeRad][slangeBredde].setBackground(new Color (142,251,142));
        ruter[slangeRad][slangeBredde].setOpaque(true);

        Koordinater k = new Koordinater(slangeRad-1, slangeBredde-1);
        k.setBackground(Color.GREEN);
        k.setOpaque(true);

        return k;
  
    }


    public void tegnSkatt(int rad, int kol) {
        ruter[rad][kol].setText("$");
        ruter[rad][kol].setFont(new Font(Font.MONOSPACED, Font.BOLD,  30));
        ruter[rad][kol].setForeground(new Color (219,112,147));
        ruter[rad][kol].setOpaque(true);
    }


    public void gyldigPos(int slangeRad, int slangeBredde){
        if(slangeRad < 0 || slangeRad >= dimension){
            System.out.println("GAME OVER");
            System.exit(-1);
        } 
        if(slangeBredde < 0 || slangeBredde >= dimension){
            System.out.println("GAME OVER");
            System.exit(-1);
        } 
        if(ruter[slangeRad][slangeBredde].getBackground().equals(new Color (142,251,142))){
            System.out.println("GAME OVER");
            System.exit(-1);

        }
    }


    public void slangeHarBesokt(int slangeRad,  int slangeBredde, int haleRad, int haleBredde){
        Koordinater k = new Koordinater(slangeRad, slangeBredde); 
        slange.slangeArray.add(0, k);
        k.setColor(Color.GREEN);


        if (ruter[slangeRad][slangeBredde].getText().equals("$"))
        //tegnSkatt(slangeRad, slangeBredde);
        {
            antallTatt++;
            ruter[slangeRad][slangeBredde].setText("");
            slange.plasserSkattEn();

            Koordinater kor = new Koordinater(haleRad, haleBredde); 
            slange.leggTilSlange(kor);
            setTeller(antallTatt);

        } else {
            hvitRute(haleRad, haleBredde);

        }
        
    }
        

    public void hvitRute(int slangeRad,  int slangeBredde){ 
        ruter[slangeRad][slangeBredde].setBackground(Color.WHITE);
        ruter[slangeRad][slangeBredde].setOpaque(false);
    }



    public JLabel hent(int x, int y){
        return ruter[x][y];
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Opp")){
            slange.settRettning(Retning.NORD);
        }
        if(e.getActionCommand().equals("Hoyre")){
            slange.settRettning(Retning.OST);
        }
        if(e.getActionCommand().equals("Venstre")){
            slange.settRettning(Retning.VEST);
        }
        if(e.getActionCommand().equals("Ned")){
            slange.settRettning(Retning.SOR);
        }
        
    }


    }
        





