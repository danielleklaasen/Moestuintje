package com.danielleklaasen.moestuintje.data;

import com.danielleklaasen.moestuintje.R;
import com.danielleklaasen.moestuintje.model.CultivatedPlantItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CultivatedPlantDataProvider {

    // 2 data collections
    public static List<CultivatedPlantItem> cultivatedPlantItemList; // listItems are instances of dataItem class
    public static Map<String, CultivatedPlantItem> cultivatedPlantItemMap; // key, associated value

    static {
        // initialize both objects: List + Map
        cultivatedPlantItemList = new ArrayList<>();
        cultivatedPlantItemMap = new HashMap<>();

        addItem(new CultivatedPlantItem("2e255541-eb29-4e58-92cd-cc1357a4c9c4", "06-11-2017"));
    }

    // creating instance of DataItem object and pass/reference it on to list + map
    private static void addItem(CultivatedPlantItem item){
        cultivatedPlantItemList.add(item);
        cultivatedPlantItemMap.put(item.getItemId(), item);
    }

}
