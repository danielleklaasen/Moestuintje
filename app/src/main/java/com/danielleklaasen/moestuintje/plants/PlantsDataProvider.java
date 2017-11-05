package com.danielleklaasen.moestuintje.plants;

import com.danielleklaasen.moestuintje.R;
import com.danielleklaasen.moestuintje.model.PlantItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.danielleklaasen.moestuintje.R.drawable.zucchini;

public class PlantsDataProvider {
    // 2 data collections
    public static List<PlantItem> plantItemList; // listItems are instances of dataItem class
    public static Map<String, PlantItem> plantItemMap; // key, associated value

    static {
        // initialize both objects: List + Map
        plantItemList = new ArrayList<>();
        plantItemMap = new HashMap<>();

        addItem(new PlantItem(null, "Zucchini", R.drawable.zucchini));
        addItem(new PlantItem(null, "Tomato", R.drawable.tomato));
        addItem(new PlantItem(null, "Garlic", R.drawable.garlic));
        addItem(new PlantItem(null, "Strawberry", R.drawable.strawberry));
        addItem(new PlantItem(null, "Raspberry", R.drawable.raspberry));
        addItem(new PlantItem(null, "Potato", R.drawable.potato));
    }

    // creating instance of DataItem object and pass/reference it on to list + map
    private static void addItem(PlantItem item){
        plantItemList.add(item);
        plantItemMap.put(item.getItemId(), item);
    }
}
