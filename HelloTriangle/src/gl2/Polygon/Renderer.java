package gl2.Polygon;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.GLU;

/**
 *
 * @author Wsl_F
 */
public class Renderer implements GLEventListener {

    private float rotateT = 0.0f;
    private static final GLU glu = new GLU();

    public void display(GLAutoDrawable gLDrawable) {
        final GL2 gl = gLDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(-1.5f, 0.0f, -5.0f);

        // draw few polygons
        for (float i = -100; i <= 100; i += 5) {
            gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2f(i / 10 - 0.1f, -0.1f);
            gl.glVertex2f(i / 10 - 0.1f, +0.1f);
            if (i<0) {
                gl.glVertex2f(i / 10 + 0.0f, +0.2f);
            } else {
                gl.glVertex2f(i / 10 + 0.0f, +0.0f);
            }
            gl.glVertex2f(i / 10 + 0.1f, +0.1f);
            gl.glVertex2f(i / 10 + 0.1f, -0.1f);
            gl.glEnd();
        }
        gl.glLoadIdentity();

        gl.glFlush();
        rotateT += 0.2f;
    }

    public void init(GLAutoDrawable gLDrawable) {
        final GL2 gl = gLDrawable.getGL().getGL2();
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
    }

    public void reshape(GLAutoDrawable gLDrawable, int x,
            int y, int width, int height) {
        final GL2 gl = gLDrawable.getGL().getGL2();
        if (height <= 0) {
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(50.0f, h, 1.0, 1000.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void dispose(GLAutoDrawable arg0) {

    }

}
