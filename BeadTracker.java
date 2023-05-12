public class BeadTracker {

    private static double radius_aroundTheBlob ;
    private static int min_pixel ;
    private static int tau ;

    public BeadTracker(int minimumPixel, int tauNumber, double delta) {

        min_pixel = minimumPixel ;
        tau = tauNumber ;
        radius_aroundTheBlob = delta ;

    }

//    public String addressUpdate(int i, String addressSubString){
//        String s = "";
//        if ((i+"").length() + s.length() < 3 && i != 0) {
//            while ((i+"").length() + s.length() < 3)
//                s += '0' + "";
//            s += i;
//        } else if (i == 0) {
//            s = i + "00";
//        } else s = i + "";
//        s += ".jpg";
//        addressSubString += s;
//        return addressSubString;
//    }

    public static void main(String[] args) {
        //String addressOfImage = "C:\\Users\\AvajangServiceCo\\IdeaProjects\\avogadro_1\\run_res\\frame00";

        int minimum_pixel = Integer.parseInt(args[0]) ,
                tau_2 = Integer.parseInt(args[1]) ;
        double delta = Double.parseDouble(args[2]) ;



        BeadTracker beadTracker = new BeadTracker(minimum_pixel, tau_2,delta) ;
        //delta 30

        for (int i = 0 ; i < 199 ; i++) {

            Picture pic = new Picture(args[i + 3]) ;

            //System.out.println(beadTracker.addressUpdate(i, addressOfImage)) ;

            BeadFinder beadFinder = new BeadFinder(pic, tau) ;

            Blob[] blobs1 = beadFinder.getBeads(min_pixel) ;

            // update address

            pic = new Picture(args[i + 4]);

            //System.out.println(beadTracker.addressUpdate(i+1, addressOfImage)) ;

            beadFinder = new BeadFinder(pic, tau) ;

            Blob[] blobs2 = beadFinder.getBeads(min_pixel) ;

            double minDistance = 0 ;

            for (int i2 = 0 ; i2 < blobs1.length ; i2++) {
                minDistance = blobs1[i2].distanceTo(blobs2[0]) ;

                boolean b = false ;

                for (int j = 0 ; j < blobs2.length ; j++) {
                    if (blobs1[i2].distanceTo(blobs2[j]) < radius_aroundTheBlob){
                        b = true ;
                        if (blobs1[i2].distanceTo(blobs2[j]) < minDistance)
                            minDistance = blobs1[i2].distanceTo(blobs2[j]) ;
                    }
                }

                if (b) {
                    System.out.printf("%.4f", minDistance) ;
                    System.out.println() ;
                }
            }
            System.out.println();

        }


    }
}




