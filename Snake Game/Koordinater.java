import javax.swing.JPanel;

import java.awt.*;
import javax.swing.*;

public class Koordinater extends JPanel {

    int x_verdi;
    int y_verdi;

    public Koordinater(int x_verdi, int y_verdi){
         this.x_verdi = x_verdi;
         this.y_verdi = y_verdi;

        // this.resetColor();
    }

    public void resetColor () {
        // sett farge til graa ved reset
        this.setColor(Color.gray);
    }

    public void setColor (Color color) {
        // sett en ny bakgrunns farge til JPanel
        this.setBackground(color);
        this.repaint();
    }

    public int getX_verdi() {
        return this.x_verdi;
    }

    public void setNyverdier(int x_verdi, int y_verdi) {
        this.x_verdi = x_verdi;
        this.y_verdi = y_verdi;
    }

    public int getY_verdi() {
        return this.y_verdi;
    }

    public void setY_verdi(int y_verdi) {
        this.y_verdi = y_verdi;
    }
    


}
