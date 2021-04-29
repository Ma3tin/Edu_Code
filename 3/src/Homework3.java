import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Homework3 {
    public static void main(String[] args) {
        ArrayList<int[]> codes = new ArrayList<>();
        Coords cr = new Coords();
        int steps = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("sourceThree.txt"));
            String line = br.readLine();
            for (int i = 0; line != null; i++) {
                int[][] position = new int[1][1];
                line = line.substring(1, 8);
                String[] chara = line.split(",");
                int[] numbers = new int[3];
                chara[1] = chara[1].substring(1);
                chara[2] = chara[2].substring(1);
                for (int j = 0; j < chara.length; j++) {
                    if (chara[j].charAt(0) == 'F') numbers[j] = 0;
                    if (chara[j].charAt(0) == 'O') numbers[j] = 1;
                    if (chara[j].charAt(0) == 'S') {
                        numbers[j] = 2;
                        cr.setRows(i);
                        cr.setColumn(j);
                    }
                    if (chara[j].charAt(0) == '#') numbers[j] = 3;
                }
                codes.add(numbers);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (codes.get(cr.getRows())[cr.getColumn()] != 0) {
            boolean left = (cr.getColumn() > 0) ? codes.get(cr.getRows())[cr.getColumn() - 1] <= 1 : false;
            boolean right = (cr.getColumn() < 2) ? codes.get(cr.getRows())[cr.getColumn() + 1] <= 1 : false;
            boolean down = (cr.getRows() < codes.size() - 1) ? codes.get(cr.getRows() + 1)[cr.getColumn()] <= 1 : false;

            if (!left && !right && !down) throw new RuntimeException("no way");

            if (down) {
                steps++;
                cr.setRows(cr.getRows() + 1);
            }else if (left) {
                steps++;
                cr.setColumn(cr.getColumn() - 1);
            } else if (right) {
                steps++;
                cr.setColumn(cr.getColumn() + 1);
            }
        }
        System.out.println(steps);

    }
}
