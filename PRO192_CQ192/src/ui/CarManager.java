package ui;

import dto.BrandList;
import dto.CarList;
import java.util.ArrayList;
import tool.Inputter;

public class CarManager {
    public static void main(String[] args) {
        ArrayList<String> ops = new ArrayList<>();
        ops.add("Brand Management");
        ops.add("Car Managerment");
        ops.add("Quit");
        
        String brandIn = "brands-in.txt";
        String brandOut = "brands-out.txt";
        String carIn = "cars-in.txt";
        String carOut = "cars-out.txt";
        
        BrandList myBrandList = new BrandList();
        myBrandList.loadFromFile(brandIn);
        
        CarList myCarList = new CarList(myBrandList);
        myCarList.loadFromFile(carIn);
        
        Menu<String> myMenu = new Menu();
        int userChoice;
        do {           
            System.out.println("-------------------------------------------------------");
            System.out.println("                  CAR MANAGING PROGRAM");
            System.out.println("-------------------------------------------------------");
            userChoice = myMenu.int_getChoice(ops);
            boolean saveFlag = false;
            switch (userChoice){
                case 1:
                        ArrayList<String> ops1 = new ArrayList<>();
                        ops1.add("List all brands");
                        ops1.add("Add a new brand");
                        ops1.add("Search a brand based on its ID");
                        ops1.add("Update a brand");
                        ops1.add("Save brands to the file, named brands-out.txt");
                        ops1.add("Quit");
                        int userChoiceBrand;
                        do {
                            System.out.println("-------BRAND MANAGEMENT-------");
                            userChoiceBrand = myMenu.int_getChoice(ops1);
                            switch (userChoiceBrand){
                                case 1:
                                    myBrandList.listBrand();
                                    break;
                                case 2:
                                    myBrandList.addBrand();
                                    saveFlag = true;
                                    break;
                                case 3:
                                    myBrandList.searchByID();
                                    break;
                                case 4:
                                    myBrandList.updateBrand();
                                    saveFlag = true;
                                    break;
                                case 5:
                                    myBrandList.saveToFile(brandOut);
                                    break;
                                case 6:
                                    if (saveFlag)
                                    {
                                        String save = Inputter.inpStringPattern("Save to the file? (y/n): ", "[yYnN]", "Yes/No? (y/n)");
                                        if (save.equals("Y"))
                                        {
                                            myBrandList.saveToFile(brandOut);
                                        }
                                    }
                                    break;
                                    
                            }
                    } while (userChoiceBrand>0 && userChoiceBrand<ops1.size());
                    break;
                case 2:                        
                        ArrayList<String> ops2 = new ArrayList<>();
                        ops2.add("List all cars in the ascending order of brand names");
                        ops2.add("List cars based on a part of an input brand name");
                        ops2.add("Add a new car");
                        ops2.add("Remove a car based on its ID");
                        ops2.add("Update a car based on its ID");
                        ops2.add("Save cars to the file, named cars-out.txt");
                        ops2.add("Quit");
                        int userChoiceCar;
                        do {                        
                            userChoiceCar = myMenu.int_getChoice(ops2);
                            switch (userChoiceCar){
                                case 1:
                                    myCarList.listCar();
                                    break;
                                case 2:
                                    myCarList.printByBrandName();
                                    break;
                                case 3:
                                    myCarList.addCar();
                                    saveFlag = true;
                                    break;
                                case 4:
                                    saveFlag = true;
                                    myCarList.removeCar();
                                    break;
                                case 5:
                                    saveFlag = true;
                                    myCarList.updateCar();
                                    break;
                                case 6:
                                    myCarList.saveToFile(carOut);
                                    break;
                                case 7:
                                    if (saveFlag)
                                    {
                                        String save = Inputter.inpStringPattern("Save to the file? (y/n): ", "[yYnN]", "Yes/No? (y/n)");
                                        if (save.equals("Y"))
                                        {
                                            myBrandList.saveToFile(carOut);
                                        }
                                    }
                                    break;
                                    
                            }
                    } while (userChoiceCar>0 && userChoiceCar<ops2.size());
                    break;
                case 3:
                    System.out.println("GOOD BYE");
                    break;
            }
        } while (userChoice>0 && userChoice<ops.size());
        
    }
    
}
