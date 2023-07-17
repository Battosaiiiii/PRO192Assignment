package ui;

import java.util.ArrayList;
import tool.Inputter;

public class Menu <E>{
    
    public void display(ArrayList<E> options)
    {
        for (int i=0; i<options.size();i++)
        {
            System.out.println((i+1) +"- " + options.get(i));
        }
    }
    
    public int int_getChoice(ArrayList<E> options)
    {
        display(options);
        int reponse = Inputter.inpInt("Choose 1.." + options.size()+": ", options.size());
        return reponse;
    }
    
    public E ref_getChoice(ArrayList<E> options)
    {
        int response;
        do {            
            response = int_getChoice(options);
        } while (response<0 || response>options.size());
        return options.get(response-1);
    }
    
}
