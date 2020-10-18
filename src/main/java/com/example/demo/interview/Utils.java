package com.example.demo.interview;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Utils {
    //Question1
    public static List<Extension> sortByName(List<Extension> extensions) {
        extensions.sort(Comparator.comparing(Extension::getFirstName)
                .thenComparing(Extension::getLastName, Comparator.nullsFirst(String::compareTo))
                .thenComparing(Extension::getExt, Comparator.nullsLast(String::compareTo)));
        return extensions;
    }

    //Question2
    public static List<Extension> sortByExtType(List<Extension> extensions) {
        Map<String, Integer> map = new HashMap<>();
        map.put("User", 1);
        map.put("Dept", 2);
        map.put("AO", 3);
        map.put("TMO", 4);
        map.put("Other", 5);
        extensions.sort(Comparator.comparing(o -> map.get(o.getExtType())));
        return extensions;
    }

    public static List<QuarterSalesItem> funcByQuarter(List<SaleItem> saleItems, CustomFunc func) {
        QuarterSalesItem season1 = new QuarterSalesItem();
        season1.setQuarter(1);
        QuarterSalesItem season2 = new QuarterSalesItem();
        season2.setQuarter(2);
        QuarterSalesItem season3 = new QuarterSalesItem();
        season3.setQuarter(3);
        QuarterSalesItem season4 = new QuarterSalesItem();
        season4.setQuarter(4);
        List<QuarterSalesItem> quarterSalesItemList = Stream.of(season1, season2, season3, season4).collect(toList());
        for (SaleItem saleItem : saleItems) {
            int offset = saleItem.getMonth() % 3 == 0 ? 0 : 1;
            int index = saleItem.getMonth() / 3 + offset -1;
            QuarterSalesItem quarterSalesItem = quarterSalesItemList.get(index);
            //计算过程抽象为接口
            func.execute(quarterSalesItem, saleItem);
        }
        return quarterSalesItemList;
    }

    //抽象计算类
    public interface CustomFunc {
        public void execute(QuarterSalesItem quarterSalesItem, SaleItem saleItem);
    }

    //Question3
    public static List<QuarterSalesItem> sumByQuarter(List<SaleItem> saleItems) {
        return funcByQuarter(saleItems, (quarterSalesItem, saleItem) ->
                //求和计算方法实现
                quarterSalesItem.setValue(quarterSalesItem.getValue() + saleItem.getSaleNumbers()));
    }

    //Question4
    public static List<QuarterSalesItem> maxByQuarter(List<SaleItem> saleItems) {
        return funcByQuarter(saleItems, (quarterSalesItem, saleItem) -> {
            if (quarterSalesItem.getValue() >= saleItem.getSaleNumbers()) {
                quarterSalesItem.setValue(quarterSalesItem.getValue());
            } else {
                quarterSalesItem.setValue(saleItem.getSaleNumbers());
            }
        });
    }

    //Question5

    /**
     * We have all Keys: 0-9;
     * usedKeys is an array to store all used keys like :[2,3,4];
     * We want to get all unused keys, in this example it would be: [0,1,5,6,7,8,9,]
     */

    public static int[] getUnUsedKeys(int[] allKeys, int[] usedKeys) {
        int[] unusedKeys = new int[allKeys.length - usedKeys.length];
        int[] all = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        for (int usedKey : usedKeys) {
            all[usedKey] = 0;
        }
        for (int i = 0, j = 0; i < all.length; i++) {
            if (all[i] == 1) {
                unusedKeys[j++] = i;
            }
        }
        return unusedKeys;
    }
}
