package dto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import ui.Menu;
import tool.Inputter;

public class CarList extends HashMap<String, Car>{
    BrandList brandList = new BrandList();
    
    public CarList(BrandList bList) {
        this.brandList = bList;
    }
    
    public void loadFromFile(String fileName)
    {
        String car = "";
        try ( 
            FileReader f = new FileReader(fileName);  
            BufferedReader br = new BufferedReader(f)){
            while ((car= br.readLine()) != null) {
                String[] c = car.split(", ");
                String ID = c[0];
                String bID = c[1];
                String color = c[2];
                String frameID = c[3];
                String engineID = c[4];
                this.put(ID , new Car(ID, brandList.get(bID), color, frameID, engineID));
            }
            f.close();
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void saveToFile(String fileName)
    {
        try (FileWriter fw = new FileWriter(fileName)) {
            for (String key : this.keySet()) {
                fw.write(key + ", " + this.get(key).toString());
                fw.write("\n");
            }
            fw.close();
            System.out.println("Write to file success");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public boolean searchID(String carID)
    {
        for (String key : this.keySet())
        {
            if (key.equals(carID)) return true;
        }
        return false;
    }
    
    public void addCar()
    {
        String newID;
        boolean flag = true;
        newID  = Inputter.inpStringPattern("Enter the car's ID: ","C\\d{1,4}+$", "ERROR: Invalid ID! (Cxxx...)");
        if (!searchID(newID))
        {
            String frameID = "";
            String engineID = "";
            Menu menu = new Menu();
            ArrayList<Brand> brList = new ArrayList<>(brandList.values());
            Brand b = (Brand) menu.ref_getChoice(brList);
            String color = Inputter.inpNonblankString("Enter the car's color: ");
            do {                
                frameID = Inputter.inpStringPattern("Enter the car's frameID: ", "^F\\d{5}$", "ERROR: Invalid frameID! (F00000)");
                for (Car c: this.values())
                {
                    if (c.getFrameID().equals(frameID)) {
                        System.out.println("   - The frameID is duplicated!");
                    }else{
                        flag = false;
                    }
                }
            } while (flag);
            
            flag = true;
            do {                
                engineID = Inputter.inpStringPattern("Enter the car's engineID:" ,"^E\\d{5}$", "ERROR: Invalid engineID! (E00000)");
                for (Car c: this.values())
                {
                    if (c.getEngineID().equals(engineID)) {
                        System.out.println("   - The engineID is duplicated!");
                    }else{
                        flag = false;
                    }
                }
            } while (flag);
            
            this.put(newID, new Car(newID, b, color, frameID, engineID));
            System.out.println("The new car has been added!");
        }else 
        {
            System.out.println("The car's ID is dublicated!");
        }
        
    }
    
    public void printByBrandName()
    {
        String aBrandName = Inputter.inpNonblankString("Enter the brand's name: ");
        int count =0;
        for (Car c: this.values())
        {
            if (c.getBrand().getBrandName().equals(aBrandName))
            {
                System.out.println(c);
                count++;
            }
        }
        
        if (count==0)
        {
            System.out.println("No car is detected!");
        }
    }
    
    public void removeCar()
    {
        String removeID;
        removeID  = Inputter.inpStringPattern("Enter the car's ID for removing: ","C\\d{1,4}+$", "ERROR: Invalid ID! (Cxxx...)");
        if (searchID(removeID))
        {
            this.remove(removeID);
            System.out.println("The car " + removeID + " has been removed!");
        }else 
        {
            System.out.println("The car's ID does not exist!");
        }
    }
    
    public void updateCar()
    {
        String updateID, frameID, engineID;
        boolean flag = true;
        updateID  = Inputter.inpStringPattern("Enter the car's ID for updating: ","C\\d{1,4}+$", "ERROR: Invalid ID! (Cxxx...)");
        if (searchID(updateID))
        {
            Menu menu = new Menu();
            ArrayList<Brand> brList = new ArrayList<>(brandList.values());
            Brand b = (Brand) menu.ref_getChoice(brList);
            String color = Inputter.inpNonblankString("Enter the car's color: ");
            do {                
                frameID = Inputter.inpStringPattern("Enter the car's frameID: ", "^F\\d{5}$", "ERROR: Invalid frameID! (F00000)");
                for (Car c: this.values())
                {
                    if (c.getFrameID().equals(frameID)) {
                        System.out.println("   - The frameID is duplicated!");
                    }else{
                        flag = false;
                    }
                }
            } while (flag);
            
            flag = true;
            do {                
                engineID = Inputter.inpStringPattern("Enter the car's engineID:" ,"^E\\d{5}$", "ERROR: Invalid engineID! (E00000)");
                for (Car c: this.values())
                {
                    if (c.getEngineID().equals(engineID)) {
                        System.out.println("   - The engineID is duplicated!");
                    }else{
                        flag = false;
                    }
                }
            } while (flag);
            this.replace(updateID, new Car(updateID, b, color, frameID, engineID));
            System.out.println("The car " + updateID + " has been updated!");
        }else 
        {
            System.out.println("The car's ID does not exist!");
        }
    }
    
    public void listCar()
    {
        ArrayList<Car> carByBrandName = new ArrayList<>(this.values());
        Collections.sort(carByBrandName);
        for (Car c:carByBrandName)
        {
            System.out.println(c.toString());
        }
    }
    
}
