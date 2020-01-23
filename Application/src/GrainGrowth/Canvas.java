package GrainGrowth;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * 
 * @author marcinkrzyzowski
 */
public final class Canvas extends JComponent{
    
    private Grain[][] grainsArray;
    
    private boolean isBoundary = false;
    
    private int width = Constants.boardWidth;
    
    private int height = Constants.boardHeight;
    
    private boolean shouldShowEdge;
    
    private int maximumX = Constants.boardWidth;
    
    private int maximumY = Constants.boardHeight;

    public void changeShowEdge() {
        shouldShowEdge = !shouldShowEdge;
    }
       
    public Canvas(){
        grainsArray = new Grain[width][height];
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                grainsArray[i][j] = new Grain();
            } 
        }  
        shouldShowEdge = false;
    }
    
    public void setRGBColor(Grain[][] grains) {
        for(int j=0;j<height;j++){
            for(int i=0;i<width;i++){
                    int R=0;
                    int G=0;
                    int B=0;
                    int grain = grains[i][j].getId();
                    if(grain == 0){
                        grains[i][j].setRGB(255, 255, 255);
                    }
                    else if(grain == -1){
                        grains[i][j].setRGB(0, 0, 0);
                    }
                    else if(grain == -2){
                        grains[i][j].setRGB(255,105,180);
                    }
                    else{
                        switch(grain%3){
                            case 0:
                                R=(grain*10+100)%254;
                                G=(grain*5+100)%254;
                                B=(grain*2+100)%254;
                                break;
                            case 1:
                                R=(grain*2+100)%254;
                                G=(grain*10+100)%254;
                                B=(grain*5+100)%254;
                                break;
                            case 2:
                                R=(grain*5+100)%254;
                                G=(grain*2+100)%254;
                                B=(grain*10+100)%254;
                                break;
                            default:
                                break;
                        }
                        grains[i][j].setRGB(R, G, B);
                    }
                    grains[i][j].setX(i);
                    grains[i][j].setY(j);
            }
        }
    }

    public void paint(Graphics g){
        for(int j=0;j<height;j++){
            for(int i=0;i<width;i++){
                if(grainsArray[i][j].getId() == 0 || (grainsArray[i][j].isBoundary() && shouldShowEdge) ){
                    g.setColor(Color.WHITE);
                }else{
                    g.setColor(new Color(grainsArray[i][j].getRColorParameter(),grainsArray[i][j].getGColorParameter(),grainsArray[i][j].getBColorParameter()));
                }
                g.fillRect(Math.round(i),Math.round(j),Math.round(1),Math.round(1));
            } 
        }
    }
        
    public void setGrains(Grain[][] grains){
        setRGBColor(grains);
        this.grainsArray = grains;
    }
    
        public void resizeBoard(int x, int y) {
        this.width = x;
        this.height = y;
        this.maximumX = x;
        this.maximumY = y;
    }
}
