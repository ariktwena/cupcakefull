package facadeLayer;

import dataLayer.BottomMapper;
import dataLayer.TopMapper;
import modelLayer.Bottom;
import modelLayer.Top;

import java.util.ArrayList;
import java.util.HashMap;

public class BottomFacade {

    private static HashMap<Integer, Bottom> bottomMap;
    private static ArrayList<Bottom> bottomList;

    public static ArrayList<Bottom> getBottomList() {
        if (bottomList == null)
            setBottomList();
        return bottomList;
    }

    public static void setBottomList(){
        if (bottomList == null){
            bottomList = BottomMapper.getBottomList();
        }
    }

    public static void setBottomMap() {
        if (bottomMap == null) {
            bottomMap = new HashMap<>();
            setBottomList();
            for (Bottom b:bottomList) {
                bottomMap.put(b.getBottomId(), b);
            }
        }
    }

    public static Bottom getBottomByKey(int key){
        if (bottomMap == null)
            setBottomMap();
        return bottomMap.get(key);
    }

    public static HashMap<Integer, Bottom> getBottomMap() {
        if (bottomMap == null)
            setBottomMap();
        return bottomMap;
    }
}
