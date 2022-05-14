import java.awt.*;

public class cell {
    private int Identity;
    private double Radius;
    private double[] Position;
    private Color color;
    private double Perception;
    private double[] Wall;

    public cell(int id, double r, double[] p, Color c, double perception, double[] Wall) {
        this.Identity = id;
        this.Radius = r;
        this.Position = p;
        this.color = c;
        this.Perception = perception;
        this.Wall = Wall;
    }

    public boolean iswithin(cell b) {
        boolean within = false;
        double size = Perception + b.getRadius();
        double dis_x = Position[0] - b.getPosition()[0];
        double dis_y = Position[1] - b.getPosition()[1];
        if (size >= dis_x && size >= dis_y) {
            within = true;
        }
        return within;
    }

    public boolean insidewall() {
        boolean within = false;
        if (Position[0] - Radius >= 0 && Position[1] - Radius >= 0 && Position[0] - Radius <= Wall[0] && Position[1] - Radius <= Wall[1]) {
            within = true;
        }
        return within;
    }

    public boolean overlap(cell b) {
        boolean within = true;
        double size = Radius + b.getRadius();
        double dis_x = Position[0] - b.getPosition()[0];
        double dis_y = Position[1] - b.getPosition()[1];
        double dis = Math.sqrt(dis_x * dis_x + dis_y * dis_y);
        if (size <= dis) {
            within = false;
        }
        return within;
    }

    public double move_Red(cell b) {
        boolean hit = false;
        if (b.getPosition()[1] <= Position[1] + 1 / 15 && b.getPosition()[1] >= Position[1]) {
            if (Math.abs(b.getPosition()[0] - Position[0]) < (b.getRadius() + Radius)) {
                hit = true;
            }
        } else {
            double size = Radius + b.getRadius();
            double dis_x = Position[0] - b.getPosition()[0];
            double dis_y = Position[1] + 1 / 15 - b.getPosition()[1];
            double dis = Math.sqrt(dis_x * dis_x + dis_y * dis_y);
            if (size <= dis) hit = true;
        }
        if (!hit) {
            return 1 / 15;
        } else {
            double size = Radius + b.getRadius();
            double dis_x = Position[0] - b.getPosition()[0];
            double dis_y = Position[1] - b.getPosition()[1];
            return -dis_y - Math.sqrt(size * size - dis_x * dis_x);
        }

    }

    public double move_Green(cell b) {
        boolean hit = false;
        if (b.getPosition()[1] >= Position[1] - 1 / 15 && b.getPosition()[1] <= Position[1]) {
            if (Math.abs(b.getPosition()[0] - Position[0]) < (b.getRadius() + Radius)) {
                hit = true;
            }
        } else {
            double size = Radius + b.getRadius();
            double dis_x = Position[0] - b.getPosition()[0];
            double dis_y = Position[1] - 1 / 15 - b.getPosition()[1];
            double dis = Math.sqrt(dis_x * dis_x + dis_y * dis_y);
            if (size <= dis) hit = true;
        }
        if (!hit) {
            return 1 / 15;
        } else {
            double size = Radius + b.getRadius();
            double dis_x = Position[0] - b.getPosition()[0];
            double dis_y = Position[1] - b.getPosition()[1];
            return dis_y + Math.sqrt(size * size - dis_x * dis_x);
        }

    }

    public double move_Blue(cell b) {
        boolean hit = false;
        if (b.getPosition()[0] >= Position[0] - 1 / 15 && b.getPosition()[0] <= Position[0]) {
            if (Math.abs(b.getPosition()[1] - Position[1]) < (b.getRadius() + Radius)) {
                hit = true;
            }
        } else {
            double size = Radius + b.getRadius();
            double dis_x = Position[0] - 1 / 15 - b.getPosition()[0];
            double dis_y = Position[1] - b.getPosition()[1];
            double dis = Math.sqrt(dis_x * dis_x + dis_y * dis_y);
            if (size <= dis) hit = true;
        }
        if (!hit) {
            return 1 / 15;
        } else {
            double size = Radius + b.getRadius();
            double dis_x = Position[0] - b.getPosition()[0];
            double dis_y = Position[1] - b.getPosition()[1];
            return dis_x + Math.sqrt(size * size - dis_y * dis_y);
        }

    }

    public double move_Yellow(cell b) {
        boolean hit = false;
        if (b.getPosition()[0] <= Position[0] + 1 / 15 && b.getPosition()[0] >= Position[0]) {
            if (Math.abs(b.getPosition()[1] - Position[1]) < (b.getRadius() + Radius)) {
                hit = true;
            }
        } else {
            double size = Radius + b.getRadius();
            double dis_x = Position[0] + 1 / 15 - b.getPosition()[0];
            double dis_y = Position[1] - b.getPosition()[1];
            double dis = Math.sqrt(dis_x * dis_x + dis_y * dis_y);
            if (size <= dis) hit = true;
        }
        if (!hit) {
            return 1 / 15;
        } else {
            double size = Radius + b.getRadius();
            double dis_x = Position[0] - b.getPosition()[0];
            double dis_y = Position[1] - b.getPosition()[1];
            return -dis_x - Math.sqrt(size * size - dis_y * dis_y);
        }

    }

    public double move(cell c) {
        double dis = 0;
        if (color == Color.red) {
            dis = move_Red(c);
        }
        if (color == Color.blue) {
            dis = move_Blue(c);
        }
        if (color == Color.green) {
            dis = move_Green(c);
        }
        if (color == Color.YELLOW) {
            dis = move_Yellow(c);
        }
        return dis;
    }

    public int getidentity() {
        return Identity;
    }

    public double getRadius() {
        return Radius;
    }

    public double[] getPosition() {
        return Position;
    }

    public Color getColor() {
        return color;
    }

    public double getPerception() {
        return Perception;
    }

    public void setRadius(double r) {
        Radius = r;
    }

    public void setPosition(double p[]) {
        Position = p;
    }

    public void setColor(Color c) {
        color = c;
    }

    public void setPerception(double p) {
        Perception = p;
    }

}
