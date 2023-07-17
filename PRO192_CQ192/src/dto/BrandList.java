package dto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import tool.Inputter;
import ui.Menu;

public class BrandList extends HashMap<String, Brand>{

    public BrandList() {
    }
    
    public void loadFromFile(String fileName)
    {
        String brand = "";
        try ( FileReader f = new FileReader(fileName);  BufferedReader br = new BufferedReader(f)) {
            while ((brand = br.readLine()) != null) {
                String[] b = brand.split(", ");
                String ID = b[0];
                String name = b[1];
                String[] b1 = b[2].split(": ");
                String sound = b1[0];
                double price = Double.parseDouble(b1[1]);
                this.put(ID, new Brand(ID, name, sound, price));
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
    
    public boolean searchID(String bID)
    {
        for (String key : this.keySet())
        {
            if (bID.equals(key)) return true;
        }
        return false;
    }
    
    public Brand getUserChoice()
    {
        Menu<Brand> mnu = new Menu();
        return (Brand) mnu.ref_getChoice((ArrayList) this.values());
    }
    
    public void addBrand()
    {
        String newID, newName, newsound;
        double newPrice;
        try {
            newID = Inputter.inpStringPattern("Enter a brand's code: ", "B.++","ERROR: Invalid code! (B______)");
            if (searchID(newID)) {
                System.out.println("The code is duplicated!");
                return;
            }
            newName = Inputter.inpNonblankString("Enter brand's name: ");
            newsound = Inputter.inpNonblankString("Enter brand's sound: ");
            newPrice = Inputter.inpDouble("Enter brand's price: ");
            this.put(newID, new Brand(newID, newName, newsound, newPrice));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void updateBrand()
    {
        String updateID;
        double newPrice;
        updateID = Inputter.inpStringPattern("Enter a brand's code for updating: ", "B.++","ERROR: Invalid code! (B______)");
        if (searchID(updateID)){
            newPrice = Inputter.inpDouble("Old price: " + this.get(updateID).getPrice()+ "New price: ");
            this.get(updateID).setPrice(newPrice);
            System.out.println("The brand: "+ updateID+" has been updated!");        
        }else 
        {
            System.out.println("The brand's ID does not exist!");
        }      
    }
    
    public void listBrand()
    {
        for (Brand e: this.values())
        {
            System.out.println(e.toString());
        }
    }
    
    public void searchByID()
    {
        String anID;
        anID = Inputter.inpStringPattern("Enter a brand's code for searching: ", "B.++","ERROR: Invalid code! (B______)");
        for (String key : this.keySet())
        {
            if (anID.equals(key)) {
                System.out.println(this.get(anID).toString());
                return;
            }
        }
        System.out.println("The brand's ID does not exist!");
    }
}
