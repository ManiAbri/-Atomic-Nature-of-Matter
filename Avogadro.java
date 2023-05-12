import java.io.File ;
import java.io.FileNotFoundException ;
import java.io.IOException ;

import java.util.Optional ;
import java.util.Scanner ;

public class Avogadro {


    public static void main(String[] args) {

//        File file = new File("displacements-run_1.txt");
//        Scanner scn = null;
//        try {
//            scn = new Scanner(file);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }

        Scanner scn = new Scanner(System.in) ;

        double a = 0 ,
                sum = 0 ;
        int n = 0 ;

        while (scn.hasNextLine()) {
            String b = scn.nextLine() ;

            //System.out.println(b) ;

            if (!b.equals("")) {
                if (b.charAt(0) == ' ')
                    b = b.substring(1) ;

                a = Double.parseDouble(b)* ( 0.175*1E-6) ;

                sum += (a * a) ;
                n++ ;
            }
//            a = b * ( 0.175*10E-6) ;
//                sum += (a * a) ;
//                n++ ;

        }
        double variance = sum / (2 * n) ;

        double t = 0.5 ;

        double D = variance / (2*t) ;

        double viscos = 9.135 * 1E-4 ;

        double T = 297 ;

        double ro = 0.5 * 1E-6 ;

        double k = (6 * Math.PI * viscos * ro * D ) / T ;

        double R = 8.31446 ;

        double Na = R / k ;



        //System.out.println(variance) ;

      //System.out.println("bultzman : " + k) ;
      //System.out.println("avogadro : " + Na) ;

        System Std = null ;

        Std.out.println("boltzman= " +String.format("%.4e",k));

        Std.out.println("Avogadro= " +String.format("%.4e",Na));


    }

}


