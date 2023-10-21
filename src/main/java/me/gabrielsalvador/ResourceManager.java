package me.gabrielsalvador;
import java.util.HashMap;
import java.util.Map;

import me.gabrielsalvador.core.Sinesthesia;
import processing.core.PApplet;
import processing.core.PImage;

public class ResourceManager {
    
    HashMap<String, PImage> icons = new HashMap<>();
    {
        icons.put(Config.SELECT_CURSOR_ARROW_ICON, null);
        icons.put(Config.SELECT_CURSOR_ADD_ICON, null);
    }
    
    


    private static ResourceManager instance = null;

    public static ResourceManager getInstance(){
        if(instance == null){
            instance = new ResourceManager();
        }
        return instance;
    }
    

    private ResourceManager(){
        loadIcons();
    }

    private void loadIcons(){
        PApplet app = Sinesthesia.getInstance();
        for (Map.Entry<String, PImage> entry : icons.entrySet()) {
            try{
                PImage img = app.loadImage(Config.ICON_FOLDER_PATH + "/" + entry.getKey());
                img.resize(32, 32);
                entry.setValue(img);
            }catch(Exception e){
                System.out.println("Error loading icon: " + entry.getKey());
            }
        }
    }


    public PImage getIcon(String name) {
        return  icons.get(name);
        
    }
}
