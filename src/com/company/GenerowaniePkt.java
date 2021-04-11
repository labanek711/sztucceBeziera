package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;


public class GenerowaniePkt {

    File input = new File("pktLyzka.txt");
    File output = new File("wynikiLyzka.txt");
    Scanner scanner = new Scanner(input);
    BufferedWriter writter = new BufferedWriter(new FileWriter(output));
    PunktyKontrolne[][] inputPoints = new PunktyKontrolne[4][4];

    public GenerowaniePkt() throws IOException {
    }

    public static double berstein(int m, int i, double v) {
        return dwumianNewtona(m, i) * Math.pow(v, i) * Math.pow((1 - v), (m - i));
    }

    public static double dwumianNewtona(int n, int k) {
        return (double) silnia(n) / (double) (silnia(k) * silnia(n - k));
    }

    public static int silnia(int i) {
        if (i == 0)
            return 1;
        else
            return i * silnia(i - 1);
    }

    public void GenerowaniePkt() throws IOException {
        double współrzędneX, współrzędneY, współrzędneZ;
        współrzędneX = 0.0;
        współrzędneY = 0.0;
        współrzędneZ = 0.0;

        int iloscplaszczyzn = scanner.nextInt();
        writter.write("x, y, z" + System.lineSeparator());
        for (int z = 0; z < iloscplaszczyzn; z++) {
            for (int k = 0; k < 4; k++) {
                for (int d = 0; d < 4; d++) {
                    double tempx = Double.parseDouble(scanner.next());
                    double tempy = Double.parseDouble(scanner.next());
                    double tempz = Double.parseDouble(scanner.next());
                    inputPoints[k][d] = new PunktyKontrolne(tempx, tempy, tempz);
                }
            }
            for (double v = 0.0; v <= 1.0; v += 0.01) {
                for (double w = 0.0; w <= 1.0; w += 0.01) {
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            współrzędneX += inputPoints[i][j].x * berstein(3, i, w) * berstein(3, j, v);
                            współrzędneY += inputPoints[i][j].y * berstein(3, i, w) * berstein(3, j, v);
                            współrzędneZ += inputPoints[i][j].z * berstein(3, i, w) * berstein(3, j, v);
                        }
                    }
                    writter.write("" + (new BigDecimal(współrzędneX)).setScale(2, RoundingMode.HALF_EVEN) + ','
                            + (new BigDecimal(współrzędneY)).setScale(2, RoundingMode.HALF_EVEN) + ','
                            + (new BigDecimal(współrzędneZ)).setScale(2, RoundingMode.HALF_EVEN) + '\n');
                    współrzędneX = 0.0;
                    współrzędneY = 0.0;
                    współrzędneZ = 0.0;
                }
            }
        }
        writter.close();


    }
}
