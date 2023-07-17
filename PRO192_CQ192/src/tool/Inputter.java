package tool;

import java.util.Scanner;

public class Inputter {
    
    public static int intInt(String mess)
    {
        int aNumber = 0;
        boolean count = false;
        do {            
            Scanner sc = new Scanner(System.in);
            try {
                System.out.print("   - " + mess);
                aNumber = Integer.parseInt(sc.nextLine());
                if (aNumber<=0) throw new Exception();
                count = false;
            } catch (Exception e) {
                System.out.println("   - ERROR: Invalid number (number>0)!");
                count = true;
            }
        } while (count);
        return aNumber;
    }
    
    public static int inpInt(String mess, int max)
    {
        int aNumber = 0;
        boolean count = false;
        do {            
            Scanner sc = new Scanner(System.in);
            try {
                System.out.print("   - " + mess);
                aNumber = Integer.parseInt(sc.nextLine());
                if (aNumber<=0 ||  aNumber>max) throw new Exception();
                count = false;
            } catch (Exception e) {
                System.out.println("   - ERROR: Invalid number (0<number<" + max + ")!");
                count = true;
            }
        } while (count);
        return aNumber;
    }
    
    //Get a positive double number
    public static double inpDouble(String mess)
    {
        double aNumber = 0;
        boolean count = false;
        do {            
            Scanner sc = new Scanner(System.in);
            try {
                System.out.print("   - " + mess);
                aNumber = Double.parseDouble(sc.nextLine());
                if (aNumber<=0) throw new Exception();
                count = false;
            } catch (Exception e) {
                System.out.println("   - ERROR: Invalid number (number>0)!");
                count = true;
            }
        } while (count);
        return aNumber;
    }
    
    //Get a positive number in range 0 to max
    public static double inpDouble(String mess, double max)
    {
        double aNumber = 0;
        boolean count = false;
        do {            
            Scanner sc = new Scanner(System.in);
            try {
                System.out.print("   - " + mess);
                aNumber = Double.parseDouble(sc.nextLine());
                if (aNumber<=0 ||  aNumber>max) throw new Exception();
                count = false;
            } catch (Exception e) {
                System.out.println("   - ERROR: Invalid number (0<number<" + max + ")!");
                count = true;
            }
        } while (count);
        return aNumber;
    }
    
    //Get a string
    public static String inpString(String mess)
    {
        String aString = null;
        boolean count = false;
        do {            
            Scanner sc = new Scanner(System.in);
            try {
                System.out.print("   - " + mess);
                aString = sc.nextLine();
                count = false;
            } catch (Exception e) {
                System.out.println("   - ERROR: Invalid input!");
                count = true;
            }
        } while (count);
        return aString;
    }
    
    //Get a non_blank string
    public static String inpNonblankString(String mess)
    {
        String aString = null;
        boolean count = false;
        do {            
            Scanner sc = new Scanner(System.in);
            try {
                System.out.print("   - " + mess);
                aString = sc.nextLine().trim();
                if (aString.isEmpty()) throw new Exception();
                count = false;
            } catch (Exception e) {
                System.out.println("   - ERROR: Invalid input!");
                count = true;
            }
        } while (count);
        return aString;
    }
    
    //Get a string with pattern
    public static String inpStringPattern(String mess, String pattern, String error)
    {
        String aString = null;
        boolean count = false;
        do {            
            Scanner sc = new Scanner(System.in);
            try {
                System.out.print("   - " + mess);
                aString = sc.nextLine().trim().toUpperCase();
                if (!aString.matches(pattern)) throw new Exception();
                count = false;
            } catch (Exception e) {
                System.out.println("   - " + error);
                count = true;
            }
        } while (count);
        return aString;
    }
    
}
