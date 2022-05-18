import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class map {
    private double[] Wall;
    private List<cell> cells = new ArrayList<>();

    public map(double[] Wall,List<cell> cells) {
        this.Wall = Wall;
        this.cells =cells;
    }

    public double[] getWall() {
        return Wall;
    }

    public List<cell> getCells() {
        return cells;
    }

    public void addcell(cell c) {
        cells.add(c);
    }

    public void move(cell c) {
        double dis = (double)1/15;
        for (int i = 0; i < cells.size(); ++i) {
            if (cells.get(i).getidentity() != c.getidentity()) {
                double dis_i = c.move(cells.get(cells.get(i).getidentity()));
                if (dis_i < dis) dis = dis_i;
            }
        }
        if (c.getColor() .equals( Color.RED)) {
            double[] p = c.getPosition();
            p[1] = p[1] + (double)1/15;
            c.setPosition(p);
        }
        if (c.getColor() .equals( Color.GREEN)) {
            double[] p = c.getPosition();
            p[1] = p[1] - (double)1/15;
            c.setPosition(p);
        }
        if (c.getColor() .equals( Color.BLUE)) {
            double[] p = c.getPosition();
            p[0] = p[0] - (double)1/15;
            c.setPosition(p);
        }
        if (c.getColor() .equals(Color.YELLOW)) {
            double[] p = c.getPosition();
            p[0] = p[0] + (double)1/15;
            c.setPosition(p);
        }
    }

    public void move(){
        for(int i = 0; i < cells.size(); ++i) {
            if(cells.get(i).insidewall()) {
                move(cells.get(i));
            }
        }
    }

}
