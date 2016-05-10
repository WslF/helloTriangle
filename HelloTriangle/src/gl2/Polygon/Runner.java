package gl2.Polygon;

import com.jogamp.opengl.awt.GLCanvas;
import gl2.HelloTetrahedron.JavaRenderer;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author Wsl_F
 */
public class Runner implements Runnable, KeyListener {

    private static Thread displayT = new Thread(new Runner());
    private static boolean bQuit = false;

    public static void main(String[] args) {
        displayT.start();
    }

    public void run() {
        JFrame frame = new JFrame("Jogl 3D Shape/Rotation");
        GLCanvas canvas = new GLCanvas();
        int size = frame.getExtendedState();

        canvas.addGLEventListener(new Renderer());
        frame.add(canvas);
        //frame.setUndecorated(true);
        size |= Frame.MAXIMIZED_BOTH;
        frame.setExtendedState(size);
        canvas.addKeyListener(this);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                bQuit = true;
                System.exit(0);
            }
        });

        frame.setVisible(true);
        canvas.requestFocus();
        while (!bQuit) {
            canvas.display();
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            displayT = null;
            bQuit = true;
            System.exit(0);
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
}
