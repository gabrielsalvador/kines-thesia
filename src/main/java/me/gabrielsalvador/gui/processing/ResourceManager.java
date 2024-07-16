package me.gabrielsalvador.gui.processing;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PImage;

public class ResourceManager {
    
    HashMap<String, PImage> icons = new HashMap<>();
    {
        icons.put(Config.SELECTTOOL_CURSOR_ARROW_ICON, null);
        icons.put(Config.SELECTTOOL_CURSOR_ADD_ICON, null);
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
//        PApplet app = App.getInstance();
//        for (Map.Entry<String, PImage> entry : icons.entrySet()) {
//            try{
//                PImage img = app.loadImage(Config.ICON_FOLDER_PATH + "/" + entry.getKey());
//                img.resize(32, 32);
//                entry.setValue(img);
//            }catch(Exception e){
//                System.out.println("Error loading icon: " + entry.getKey());
//            }
//        }
    }


    public PImage[] loadIconStates(String name) {
        ArrayList<PImage> icons = new ArrayList<>();
        Main app = Main.getInstance();

        for(String state : new String[]{"active", "hover", "normal"}){
            PImage img = app.loadImage(Config.ICON_FOLDER_PATH + name + "-" + state + ".png");
            if(img != null){
                icons.add(img);
            }
        }

        return icons.toArray(new PImage[0]);

    }
}
