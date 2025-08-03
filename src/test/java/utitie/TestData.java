package utitie;

import utilities.DataUtitlie;

import java.io.IOException;

public class TestData {
    public static final String username = DataUtitlie.getJsonData("ValidLogin", "username");
    public static final String password = DataUtitlie.getJsonData("ValidLogin", "password");
    public final static String Firstname = DataUtitlie.getJsonData("Valid_Checkout_data", "firstName");
    public final static String Lastname = DataUtitlie.getJsonData("Valid_Checkout_data", "LastName");
    public final static String postalCode = DataUtitlie.getJsonData("Valid_Checkout_data", "Postsl");
    public final static String invalidname = DataUtitlie.getJsonData("InValidLogin","invaliduser");
    public final static String invalidpass = DataUtitlie.getJsonData("InValidLogin","invalidpassword");
    public final static String emptyname = DataUtitlie.getJsonData("InValidLogin","emptyuser");
    public final static String emptypass = DataUtitlie.getJsonData("InValidLogin","emptypassword");
    public final static String empty_first = DataUtitlie.getJsonData("InValid_Checkout_data", "first_Name");
    public final static String empty_last = DataUtitlie.getJsonData("InValid_Checkout_data", "Last_Name");
    public final static String empty_postal  = DataUtitlie.getJsonData("InValid_Checkout_data", "Postsl_num");
    public final static String Home_URL;
    public final static String Exp_URL;
    public final static String Expected;
    public final static String Checkout;
    public final static String overview;
    public final static String finish;
    public final static String Cart;
    public final static String Logout;
    public final static String Product_Details_URL;
    public final static String EmFirst;
    public final static String EmLast;
    public final static String EmPostal;
    public final static String Invalid_page;
    public final static String InExP_product_Details;
    public final static String Cancle;
    public final static String Cart_URL;
    static {
        try {
            Cart_URL = DataUtitlie.getPropertyValue("enviroments","Cart_Link");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Cancle = DataUtitlie.getPropertyValue("enviroments","Checkout");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            InExP_product_Details = DataUtitlie.getPropertyValue("enviroments","Product_Details_URL");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Invalid_page = DataUtitlie.getPropertyValue("enviroments","Finish");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            EmPostal = DataUtitlie.getPropertyValue("enviroments","Empty_Postal");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Product_Details_URL = DataUtitlie.getPropertyValue("enviroments","Valid_Product_Details_URL");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Logout = DataUtitlie.getPropertyValue("enviroments","LOGIN_URL");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Home_URL = DataUtitlie.getPropertyValue("enviroments","Home_URL");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Exp_URL = DataUtitlie.getPropertyValue("enviroments","Home_URL");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Expected = DataUtitlie.getPropertyValue("enviroments","Valid_Product_Details_URL");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Checkout = DataUtitlie.getPropertyValue("enviroments","Checkout");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            overview = DataUtitlie.getPropertyValue("enviroments","Overview");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            finish = DataUtitlie.getPropertyValue("enviroments","Finish");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Cart = DataUtitlie.getPropertyValue("enviroments","Cart_Link");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            EmFirst = DataUtitlie.getPropertyValue("enviroments","Empty_first");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            EmLast = DataUtitlie.getPropertyValue("enviroments","Empty_Last");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
