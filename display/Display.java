package display;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import IO.Input;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;
import javax.swing.JFrame;
public abstract class Display
{
  
	private static boolean created	= false;
	private static JFrame window;
	private static Canvas content;

	private static BufferedImage buffer;
	private static int[] bufferData;
	private static Graphics	bufferGraphics;
	private static int clearColor;

	private static BufferStrategy bufferStrategy;                                       
    public static void create(int width,int heigth,String title,int _clearColor,int numBuffers)
    {
        if(created)
        {
            return;
        }
        window = new JFrame(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        content = new Canvas();
        Dimension size = new Dimension(width, heigth);
        content.setPreferredSize(size);
        window.getContentPane().add(content);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setVisible(true);
        buffer = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt)buffer.getRaster().getDataBuffer()).getData();
		content.createBufferStrategy(numBuffers);
		bufferStrategy = content.getBufferStrategy();
        bufferGraphics = buffer.getGraphics();
        clearColor =_clearColor;
        created = true;
    }public static void clear() {
		Arrays.fill(bufferData, clearColor);
	}

	public static void swapBuffers() {
		Graphics g = bufferStrategy.getDrawGraphics();
		g.drawImage(buffer, 0, 0, null);
		bufferStrategy.show();
	}

	public static Graphics2D getGraphics() {
		return (Graphics2D) bufferGraphics;
	}

	public static void destroy() {

		if (!created)
			return;

		window.dispose();

	}

	public static void setTitle(String title) {

		window.setTitle(title);

	}
	public static void addInputListener(Input inputListener) {
		window.add(inputListener);
	}
}