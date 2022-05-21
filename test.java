import edu.princeton.cs.algs4.StdDraw;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner input=new Scanner(new File("src/sample1.txt"));
        //读数据
        double[] wall={input.nextInt(),input.nextInt()};
        //txt第一行 wall大小

        List<cell> cells=new ArrayList<>();
        map map=new map(wall,cells);

        int num=input.nextInt();
        //txt第二行 cell数量
        for(int i = 0;i < num; i++){
            int id=i;
            double[]p={input.nextDouble(), input.nextDouble()};
            double r=input.nextDouble();
            double perception= input.nextDouble();
            String color= input.next();
//            System.out.println(color);
            Color c = null;
            if(color.equals("r")) {
                c = Color.red;
            }else if (color.equals("g")){
                c = Color.green;
            }else if (color.equals("b")){
                c = Color.blue;
            }else if (color.equals("y")) {
                c = Color.yellow;
            }
            cell cell=new cell(id,r,p,perception,c, map.getWall());
            map.addcell(cell);
        }
        //txt第三行开始 根据后面几行的数据创建cell

        double index= 1000/map.getWall()[1];
        //分辨率系数

        StdDraw.setCanvasSize((int) (map.getWall()[0]*index),1000);
        StdDraw.setPenRadius(0.005);

        StdDraw.setXscale(0, map.getWall()[0]);
        StdDraw.setYscale(0, map.getWall()[1]);
        //坐标
        double dt = (double) 1/15;
        for (double t = 0.0; true; t = t + dt) {
            for(int i = 0;i < num; i++){
                StdDraw.setPenColor(map.getCells().get(i).getColor());
                StdDraw.filledCircle(map.getCells().get(i).getPosition()[0],map.getCells().get(i).getPosition()[1],map.getCells().get(i).getRadius());
            }
            StdDraw.show(66);
            map.move();
            map.ChangeColor();
            StdDraw.clear();
//            System.out.println(map.getCells().get(1).getPosition()[1]);
        }
        }
    }

