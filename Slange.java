import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

import javax.swing.JLabel;

import java.awt.*;

public class Slange {

    protected ArrayList<Koordinater> slangeArray;
    public Koordinater koordinater;

    public static final int LEFT  = 1;
    public static final int UP    = 2;
    public static final int RIGHT = 3;
    public static final int DOWN  = 4;

    protected int w = SlangeSpill.width;
	protected int h = SlangeSpill.height;

    protected int rad;
	protected int kol;

	protected int d = SlangeSpill.dimension;
    protected String[][] brett = new String[10][10];

    protected Thread traad; 
    protected Retning retning = Retning.SOR;
    public SlangeSpill slangeSpill;
    protected static int slangeRad = 0;
    protected static int slangeBredde = 1;
    protected int lengde = 1;


     public Slange(SlangeSpill slangeSpill){ 
        slangeArray = new ArrayList<Koordinater>();
        slangeArray.add(new Koordinater(slangeRad, slangeBredde));
        this.slangeSpill = slangeSpill;

        plasserSkattstart();

     }

    public void flytt(){

        Koordinater hale = slangeArray.remove(slangeArray.size() - 1);
        slangeSpill.slangeHarBesokt(slangeRad, slangeBredde, hale.getX_verdi(), hale.getY_verdi());


        int midSlangeRad = slangeRad;
        int midslangeBredde = slangeBredde;
    

        if(Retning.NORD == retning) slangeRad--;
        if(Retning.SOR == retning) slangeRad++;
        if(Retning.OST == retning) slangeBredde++;
        if(Retning.VEST == retning) slangeBredde--;

        slangeSpill.gyldigPos(slangeRad, slangeBredde);

            
            Koordinater head = slangeSpill.tegnSlange(slangeRad,slangeBredde); //etter hvit saa 
            
            slangeArray.get(0).setNyverdier(slangeRad, slangeBredde);
            
   }


    public void plasserSkattstart(){
        for( int i = 0; i < 5; i ++){
            plasserSkatt();
        }

    }

    public void plasserSkattEn(){
        for( int i = 0; i < 1; i ++){
            plasserSkatt();
        }

    }

    public void plasserSkatt(){
        Random skattRandom = new Random();
        rad = skattRandom.nextInt(10);
        kol = skattRandom.nextInt(10);
        brett[rad][kol] = "$";

        slangeSpill.tegnSkatt(rad,kol);
        
    }

    public void leggTilSlange(Koordinater k){
        slangeArray.add(k);
        k.setColor(Color.GREEN);
    }


    public int hentHodeRad(){
        return slangeRad;
    }

    public int hentHodeKol(){
        return slangeBredde;
    }


    public void settRettning(Retning retning){
            //System.out.println(retning);
            this.retning = retning;
    }

}