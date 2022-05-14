import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class map {
    private double[] Wall;
    private List<cell> cells = new ArrayList<>();

    public map(double[] Wall) {
        this.Wall = Wall;
    }

    public void addcell(cell c) {
        cells.add(c);
    }

    public void move(cell c) {
        double dis = 1 / 15;
        for (int i = 0; i < cells.size(); ++i) {
            if (cells.get(i).getidentity() != c.getidentity()) {
                double dis_i = c.move(cells.get(cells.get(i).getidentity()));
                if (dis_i < dis) dis = dis_i;
            }
        }
        if (c.getColor() == Color.RED) {
            double[] p = c.getPosition();
            p[1] = p[1] + 1 / 15;
            c.setPosition(p);
        }
        if (c.getColor() == Color.GREEN) {
            double[] p = c.getPosition();
            p[1] = p[1] - 1 / 15;
            c.setPosition(p);
        }
        if (c.getColor() == Color.BLUE) {
            double[] p = c.getPosition();
            p[0] = p[0] - 1 / 15;
            c.setPosition(p);
        }
        if (c.getColor() == Color.YELLOW) {
            double[] p = c.getPosition();
            p[0] = p[0] + 1 / 15;
            c.setPosition(p);
        }
    }

    public void move() {
        for (int i = 0; i < cells.size(); ++i) {
            move(cells.get(i));
        }
    }

    public int[] perception(cell c) {
        int Redcount = 0;
        int Greencount = 0;
        int Bluecount = 0;
        int Yellowcount = 0;
        for (int i = 0; i < cells.size(); ++i) {
            if (c.iswithin(cells.get(i))) {
                Color color = cells.get(i).getColor();
                if (color == Color.RED) {
                    Redcount = Redcount + 1;
                }
                if (color == Color.GREEN) {
                    Greencount = Greencount + 1;
                }
                if (color == Color.BLUE) {
                    Bluecount = Bluecount + 1;
                }
                if (color == Color.YELLOW) {
                    Yellowcount = Yellowcount + 1;
                }
            }
        }
        int[] a = new int[]{Redcount,Greencount, Bluecount, Yellowcount};
        return a;
    }

    public void changecolor(){
        List<Color> cs = new ArrayList<>();
        for (int i = 0; i < cells.size(); ++i) {
            int[] Ps = perception(cells.get(i));
            int Pall = Ps[0]+Ps[1]+Ps[2]+Ps[3];
            Color color = cells.get(i).getColor();
            if(color==Color.RED){
                if(Ps[0]>=3){
                    if(Ps[0]>0.7*Pall)cs.add(Color.GREEN);
                }else if(Ps[3]>=1){
                    if(Ps[3]<0.1*Pall)cs.add(Color.yellow);
                }else{
                    cs.add(Color.RED);
                }
            }
            if(color==Color.GREEN){
                if(Ps[1]>=3){
                    if(Ps[1]>0.7*Pall)cs.add(Color.BLUE);
                }else if(Ps[0]>=1){
                    if(Ps[0]<0.1*Pall)cs.add(Color.RED);
                }else{
                    cs.add(Color.GREEN);
                }
            }
            if(color==Color.BLUE){
                if(Ps[2]>=3){
                    if(Ps[2]>0.7*Pall)cs.add(Color.YELLOW);
                }else if(Ps[1]>=1){
                    if(Ps[1]<0.1*Pall)cs.add(Color.GREEN);
                }else{
                    cs.add(Color.BLUE);
                }
            }
            if(color==Color.YELLOW){
                if(Ps[3]>=3){
                    if(Ps[3]>0.7*Pall)cs.add(Color.RED);
                }else if(Ps[3]>=1){
                    if(Ps[2]<0.1*Pall)cs.add(Color.BLUE);
                }else{
                    cs.add(Color.YELLOW);
                }
            }
        }
        for (int i = 0; i < cells.size(); ++i) {
            cells.get(i).setColor(cs.get(i));
        }
    }

}
