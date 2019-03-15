package facadeLayer;

import dataLayer.BottomMapper;
import dataLayer.TopMapper;
import modelLayer.Bottom;
import modelLayer.Top;

import java.util.ArrayList;
import java.util.HashMap;

public class TopFacade {

    private static HashMap<Integer, Top> topMap;
    private static ArrayList<Top> topList;

    public static void setTopList(){
        if (topList == null){
            topList = TopMapper.getTopList();
        }
    }

    public static ArrayList<Top> getTopList() {
        if (topList == null)
            setTopList();
        return topList;
    }

    public static void setTopMap() {
        if (topMap == null) {
            topMap = new HashMap<>();
            setTopList();
            for (Top t:topList) {
                topMap.put(t.getTopId(), t);
            }
        }
    }

    public static Top getTopByKey(int key){
        if (topMap == null)
            setTopMap();
        return topMap.get(key);
    }

    public static HashMap<Integer, Top> getTopMap() {
        if (topMap == null)
            setTopMap();
        return topMap;
    }

}
