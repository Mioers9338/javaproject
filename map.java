import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class map {
    private double[] Wall;
    private List<cell> cells = new ArrayList<>();

    public map(double[] Wall, List<cell> cells) {
        this.Wall = Wall;
        this.cells = cells;
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
        double dis = (double) 1 / 15;
        for (int i = 0; i < cells.size(); ++i) {
            if (cells.get(i).getidentity() != c.getidentity()) {
                double dis_i = c.move(cells.get(cells.get(i).getidentity()));
                if (dis_i < 0) dis_i = 0;
                if (dis_i < dis) dis = dis_i;
            }
        }
        if (c.getColor().equals(Color.RED)) {
            double[] p = c.getPosition();
            if (p[1] + c.getRadius() > Wall[1] - dis) {
                p[1] = Wall[1] - c.getRadius();
            } else {
                p[1] = p[1] + dis;
            }
            c.setPosition(p);
        }
        if (c.getColor().equals(Color.GREEN)) {
            double[] p = c.getPosition();
            if (p[1] - c.getRadius() < dis) {
                p[1] = c.getRadius();
            } else {
                p[1] = p[1] - dis;
            }
            c.setPosition(p);
        }
        if (c.getColor().equals(Color.BLUE)) {
            double[] p = c.getPosition();
            if (p[0] - c.getRadius() < dis) {
                p[0] = c.getRadius();
            } else {
                p[0] = p[0] - dis;
            }
            c.setPosition(p);
        }
        if (c.getColor().equals(Color.YELLOW)) {
            double[] p = c.getPosition();
            if (p[0] + c.getRadius() > Wall[1] - dis) {
                p[1] = Wall[1] - c.getRadius();
            } else {
                p[0] = p[0] + dis;
            }
            c.setPosition(p);
        }
    }

    public void move() {
        for (int i = 0; i < cells.size(); ++i) {
            if (cells.get(i).insidewall()) {
                move(cells.get(i));
            }
        }
    }

    public void ChangeColor() {
        for (int i = 0; i < cells.size(); ++i) {
            int[] ColorNumber = new int[4]; //???????????????????????????????????????
            for (int j = 0; i < cells.size(); ++i) { //??????????????????
                if (cells.get(i).getidentity() != cells.get(j).getidentity()) { //?????????????????????cell
                    if (cells.get(i).iswithin(cells.get(j))) { //??????????????????
                        ColorNumber[colorNumber(cells.get(j).getLastColor())]++; //??????????????????
                    }
                    cells.get(i).ChangeColor(ColorNumber); //????????????
                }
            }
        }
        for (int i = 0; i < cells.size(); ++i) {
            cells.get(i).setLastColor(cells.get(i).getColor());
        }
    }

    private int colorNumber(Color color) {
        if (color.equals(Color.RED)) return 0;
        if (color.equals(Color.GREEN)) return 1;
        if (color.equals(Color.BLUE)) return 2;
        if (color.equals(Color.YELLOW)) return 3;
        return -1;
    }


}
