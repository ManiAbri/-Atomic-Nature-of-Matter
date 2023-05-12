import java.awt.* ;
import java.lang.reflect.Array ;
import java.util.ArrayList ;

public class BeadFinder {
    private Picture thePic ;
    private Blob theCurrentBlob ;
    //private int k = 0 ;
    public BeadFinder (Picture picture, double tau) {

        // finds all blobs in the specified picture using luminance threshold tau

        for (int i = 0 ; i < picture.width() ; i++)
            for (int j = 0 ; j < picture.height() ; j++) {

                Color pix_color = picture.get(i,j) ;

                int  red = pix_color.getRed() ,
                   green = pix_color.getGreen() ,
                    blue = pix_color.getBlue() ;

                double luminance = (red * 0.2126 + green * 0.7152 + blue * 0.0722) ;

                if ( !(luminance >= (tau)) )
                    // if (red < tau && green < tau && blue < tau) doesn't work at least with pic 198
                    picture.set(i,j, Color.black) ;

                //else picture.set(i, j, Color.white) ;

                // اینجا noise گیری کردیم و عکس دیگر کلا سیاه یا سفید است

            }

        thePic = picture ;

        // این قسمت هم thePic تعریف شده در اول را مساوی با picture نویز گیری شده جدید قرار می دهیم


        //picture.show();
    }

//    public boolean recursive (int i, int j, Picture pic){
//        if (pic.height() == j || pic.width() == i || i == -1 || j == -1)
//            return false;//********************
//
//
//
//        if (pic.get(i, j).equals(Color.black) || pic.get(i, j).equals(Color.yellow)) // when using == exception comes
//        {
//            //System.out.println("hjkjlklkl");
//            return false;
//        }
//
//        pic.set(i,j,Color.yellow);
//        theCurrentBlob.add(i, j);
//        //System.out.println(i);
//        //System.out.println(j);
//        boolean b = i+1 != pic.width() && j+1 != pic.height() && i-1 != -1 && j-1 != -1;
//        if (b)
//        if (/*pic.get(i, j).equals(Color.yellow) &&*/pic.get(i + 1, j).equals(Color.yellow) && pic.get(i, j + 1).equals(Color.yellow) && pic.get(i - 1, j).equals(Color.yellow) && pic.get(i, j - 1).equals(Color.yellow))
//            // when this comes before if (pic.get(i, j).equals(Color.black) || pic.get(i, j).equals(Color.yellow)) see sout of recursive
//            return true;
//
//        if (recursive(i+1,j,pic)) return true;
//        if (recursive(i,j-1,pic)) return true;
//        if (recursive(i-1,j,pic)) return true;
//        if (recursive(i,j+1,pic)) return true;//when the place of j+1 and j-1 changes we have problem
//        return false;
//    }

    private void recursive (int i, int j, Picture pic) {

        if (pic.height() == j || pic.width() == i || i == -1 || j == -1)
            return ;


        if (pic.get(i, j).equals(Color.black) || pic.get(i, j).equals(Color.yellow)) // when using == exception comes
        {
            return ;
        }

        pic.set(i,j,Color.yellow) ;
        theCurrentBlob.add(i, j) ;

        //System.out.println(i) ;
        //System.out.println(j) ;

        recursive(i+1,j,pic) ;
        recursive(i,j+1,pic) ;
        recursive(i-1,j,pic) ;
        recursive(i,j-1,pic) ;


    }
    public Blob[] getBeads (int min) {
        // returns all beads (blobs with >= minpixels)

        ArrayList<Blob> blobList = new ArrayList<Blob>() ;

        for (int i = 0 ; i < thePic.width() ; i++)
            for (int j = 0 ; j < thePic.height() ; j++)

                if (!thePic.get(i, j).equals(Color.yellow) && !thePic.get(i, j).equals(Color.black)) {
                    theCurrentBlob = new Blob() ;

                    //k++ ;
                    //System.out.println(i) ;
                    //System.out.println(j) ;
                    //System.out.println(recursive(i, j, thePic)) ;

                    recursive(i, j, thePic) ;

                    if (theCurrentBlob.getMass() >= min) {

                        theCurrentBlob.setX_avg(roundTheValue((float)theCurrentBlob.getX_avg())) ;
                        theCurrentBlob.setY_avg(roundTheValue((float)theCurrentBlob.getY_avg())) ;

                        blobList.add(theCurrentBlob) ;

                    }

                }

        Blob[] blobs = blobList.toArray(new Blob[0]) ;
        // یک متد که ArrayList را تبدیل به آرایه میکند  چون خروجی getBeads یک آرایه میخواهد
        // k = 0;
        return blobs;
    }
    public double roundTheValue(double a) {
        double b = a - ((int) a) ;
        String s = b + "" ;
        if (s.length() > 6) {
            if (s.charAt(6) >= '5') {
               if (s.charAt(5) == '9') {
                    s = (b + 0.0001) + "" ;
                    s = s.substring(0, 6) ;        // 0 تا 5
                }
                else {
                   String pNum = (char) (((int) s.charAt(5)) + 1) + "" ;
                    s = s.substring(0, 5) ;
                   s += pNum ;
                }
            }
            else {
                s = s.substring(0, 6) ;
            }
        }
        a = Double.parseDouble(s) + (int) a ;

        return a ;
    }
    public static void main(String[] args) {

            int minPix = Integer.parseInt(args[0]) ,
                   tau = Integer.parseInt(args[1]) ;
                       String picAddress = args[2] ;

//        min, tau, pic

        Picture picture = new Picture(picAddress) ;

        BeadFinder beadFinder = new BeadFinder(picture, tau) ;

        Blob[] beads = beadFinder.getBeads(minPix) ;

        for (int i = 0 ; i < beads.length ; i++)
            System.out.println(beads[i].toString()) ;

        //0,23,1 in boolean
        //System.out.println(beadFinder.roundTheValue(3.69696969));

    }
}

