import java.awt.*;
import java.awt.event.*;
import javax.print.event.PrintEvent;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.JFrame;
import org.w3c.dom.css.Rect;
import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		SlangeSpill newGame = new SlangeSpill();
        Slange slange = new Slange(newGame);
	}

}