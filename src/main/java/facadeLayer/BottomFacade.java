package facadeLayer;

import dataLayer.BottomMapper;
import dataLayer.TopMapper;
import modelLayer.Bottom;
import modelLayer.CupCakeException;
import modelLayer.Top;

import java.util.ArrayList;
import java.util.HashMap;

public class BottomFacade {

    private static HashMap<Integer, Bottom> bottomMap;
    private static ArrayList<Bottom> bottomList;

    public static ArrayList<Bottom> getBottomList() throws CupCakeException {
        if (bottomList == null)
            setBottomList();
        return bottomList;
    }

    public static void setBottomList() throws CupCakeException {
        if (bottomList == null) {
            bottomList = BottomMapper.getBottomList();
        }
    }

    public static void setBottomMap() throws CupCakeException {
        if (bottomMap == null) {
            bottomMap = new HashMap<>();
            setBottomList();
            for (Bottom b:bottomList) {
                bottomMap.put(b.getBottomId(), b);
            }
        }
    }

    public static Bottom getBottomByKey(int key) throws CupCakeException {
        if (bottomMap == null)
            setBottomMap();
        return bottomMap.get(key);
    }

    public static HashMap<Integer, Bottom> getBottomMap() throws CupCakeException {
        if (bottomMap == null)
            setBottomMap();
        return bottomMap;
    }
}
