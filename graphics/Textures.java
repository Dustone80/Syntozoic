package graphics;
import java.awt.image.BufferedImage;
import util.ResourceLoader;
public class Textures
{
    BufferedImage image;
    public Textures(String imageName)
    {
        image = ResourceLoader.loadImage(imageName);
    }
    public BufferedImage cut(int x,int y,int w,int h)
    {
        return image.getSubimage(x, y, w, h);
    }    
}
