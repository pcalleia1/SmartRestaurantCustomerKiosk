/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartrestaurantcustomerkiosk;

/**
 *
 * @author csc190
 */
public class FoodInfo {
    String fname;
    String fdesc;
    String fprice;
    String picname;
    
    public FoodInfo(String iname, String idesc, String iprice, String ipic){
        fname = iname;
        fdesc = idesc;
        fprice = iprice;
        picname = "files/"+ipic;
    }
}
