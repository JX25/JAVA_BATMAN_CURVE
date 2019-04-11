package sample;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;

import java.awt.image.BufferedImage;
import java.util.Random;

public class AsyncTask extends Task
{

    private GraphicsContext graphCont;
    private int pts;
    private Random rand = new Random();
    private final int X = 470;
    private final int Y = 235;

    AsyncTask(GraphicsContext gc, int points)
    {
        graphCont = gc;
        pts = points;
    }

    @Override
    protected Object call() throws Exception
    {
        double i = 0;
        double j = 0;
        BufferedImage bi= new BufferedImage(940, 470, BufferedImage.TYPE_INT_RGB);


        while(i<pts)
        {
            double x = -470 + (940) * rand.nextDouble();
            double y = -235 + (470) * rand.nextDouble();

            double x2 = ((8+8) * (x+470) / (940) - 8);
            double y2 = ((3+4) * (y+235) / (470) - 4);

             if( Equation.calc(x2,-y2) )
             {
                 bi.setRGB( (int)(x+X) , (int)(y+Y) ,0x000000);
                 if(i%1000==0) graphCont.drawImage(SwingFXUtils.toFXImage(bi, null), 0, 0 );
                 j++;
             }
             else
             {
                 bi.setRGB( (int)(x+X) , (int)(y+Y) , 0xffff00);
                 if(i%1000==0) graphCont.drawImage(SwingFXUtils.toFXImage(bi, null),0, 0);
             }
            if(isCancelled()) break;
            if(i%1000==0) updateProgress(i,pts);
            i++;
        }
        return j/i;
    }
}