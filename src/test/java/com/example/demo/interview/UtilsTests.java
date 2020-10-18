package com.example.demo.interview;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

import static com.example.demo.interview.Utils.*;
import static java.util.stream.Collectors.toList;

@RunWith(JUnit4.class)
public class UtilsTests {
    //Question1 test
    @Test
    public void sortByNameTest() {
        //对比双方lastName和ext都是null的情况
        Extension extension1 = new Extension();
        extension1.setFirstName("小明");
        extension1.setLastName(null);
        extension1.setExt(null);
        Extension extension2 = new Extension();
        extension2.setFirstName("小明");
        extension2.setLastName(null);
        extension2.setExt("xxx");
        List<Extension> extensions = Stream.of(extension1, extension2).collect(toList());
        List<Extension> rightResult = Stream.of(extension2, extension1).collect(toList());
        List<Extension> extensionsSorted = sortByName(extensions);
        Assert.assertEquals("验证lastName为null的情况是否正常排序", rightResult, extensionsSorted);
        //对比双方lastName和ext都是空字符串的情况
        Extension extension3 = new Extension();
        extension3.setFirstName("小红");
        extension3.setLastName("");
        extension3.setExt("");
        Extension extension4 = new Extension();
        extension4.setFirstName("小红");
        extension4.setLastName("");
        extension4.setExt("xxx");
        List<Extension> extensions2 = Stream.of(extension3, extension4).collect(toList());
        List<Extension> rightResult2 = Stream.of(extension3, extension4).collect(toList());
        List<Extension> extensionsSorted2 = sortByName(extensions2);
        Assert.assertEquals("验证ext为空字符串的情况是否正常排序", rightResult2, extensionsSorted2);
        //正常用例
        Extension extension5 = new Extension();
        extension5.setFirstName("小黄");
        extension5.setLastName("黄");
        extension5.setExt("xxx");
        Extension extension6 = new Extension();
        extension6.setFirstName("小蓝");
        extension6.setLastName("李");
        extension6.setExt("001");
        Extension extension7 = new Extension();
        extension7.setFirstName("小蓝");
        extension7.setLastName("张");
        extension7.setExt("002");
        List<Extension> extensions3 = Stream.of(extension5, extension6,extension7).collect(toList());
        List<Extension> rightResult3 = Stream.of(extension7,extension6, extension5).collect(toList());
        List<Extension> extensionsSorted3 = sortByName(extensions3);
        Assert.assertEquals("验证普通情况下是否正常排序", rightResult3, extensionsSorted3);
    }

    //Question2 test
    @Test
    public void sortByExtTypeTest() {
        List<Extension> extensions = new ArrayList<>();
        Extension extension1 = new Extension();
        extension1.setExtType("AO");
        Extension extension2 = new Extension();
        extension2.setExtType("Dept");
        Extension extension3 = new Extension();
        extension3.setExtType("User");
        Extension extension4 = new Extension();
        extension4.setExtType("Other");
        Extension extension5 = new Extension();
        extension5.setExtType("TMO");
        extensions.add(extension1);
        extensions.add(extension2);
        extensions.add(extension3);
        extensions.add(extension4);
        extensions.add(extension5);
        List<Extension> rightResult = Stream.of(extension3, extension2, extension1, extension5, extension4).collect(toList());
        List<Extension> extensionsSorted = sortByExtType(extensions);
        Assert.assertEquals("验证extType是否按照User > Dept > AO > TMO > Other 顺序排序", rightResult, extensionsSorted);
    }

    public static List<SaleItem> initData() {
        SaleItem saleItem1 = new SaleItem();
        saleItem1.setMonth(1);
        saleItem1.setDate(new Date());
        saleItem1.setTransationId("1");
        saleItem1.setSaleNumbers(10.0);
        SaleItem saleItem2 = new SaleItem();
        saleItem2.setMonth(2);
        saleItem2.setDate(new Date());
        saleItem2.setTransationId("2");
        saleItem2.setSaleNumbers(20.0);
        SaleItem saleItem5 = new SaleItem();
        saleItem5.setMonth(5);
        saleItem5.setDate(new Date());
        saleItem5.setTransationId("5");
        saleItem5.setSaleNumbers(50.0);
        SaleItem saleItem6 = new SaleItem();
        saleItem6.setMonth(6);
        saleItem6.setDate(new Date());
        saleItem6.setTransationId("6");
        saleItem6.setSaleNumbers(40.0);
        SaleItem saleItem11 = new SaleItem();
        saleItem11.setMonth(11);
        saleItem11.setDate(new Date());
        saleItem11.setTransationId("11");
        saleItem11.setSaleNumbers(30.0);
        return Stream.of(saleItem1, saleItem2, saleItem5,saleItem6, saleItem11).collect(toList());
    }

    //Question3 test
    @Test
    public void sumByQuarterTest() {
        List<SaleItem> saleItems = initData();
        List<QuarterSalesItem> quarterSalesItems = sumByQuarter(saleItems);
        QuarterSalesItem season1 = new QuarterSalesItem();
        season1.setQuarter(1);
        season1.setValue(30.0);
        QuarterSalesItem season2 = new QuarterSalesItem();
        season2.setQuarter(2);
        season2.setValue(90.0);
        QuarterSalesItem season3 = new QuarterSalesItem();
        season3.setQuarter(3);
        season3.setValue(0);
        QuarterSalesItem season4 = new QuarterSalesItem();
        season4.setQuarter(4);
        season4.setValue(30.0);
        Assert.assertNotNull("验证季度销售列表是否为null",quarterSalesItems);
        Assert.assertEquals("验证季度销售列表季度数量是否正确",4,quarterSalesItems.size());
        Assert.assertTrue("验证第一季度销售数据总和是否正确",compareBean(season1,quarterSalesItems.get(0) ));
        Assert.assertTrue("验证第二季度销售数据总和是否正确",compareBean(season2,quarterSalesItems.get(1) ));
        Assert.assertTrue("验证第三季度销售数据总和是否正确",compareBean(season3,quarterSalesItems.get(2) ));
        Assert.assertTrue("验证第四季度销售数据总和是否正确",compareBean(season4,quarterSalesItems.get(3) ));
    }

    //Question4 test
    @Test
    public void maxByQuarterTest() {
        List<SaleItem> saleItems = initData();
        List<QuarterSalesItem> quarterSalesItems = maxByQuarter(saleItems);
        QuarterSalesItem season1 = new QuarterSalesItem();
        season1.setQuarter(1);
        season1.setValue(20.0);
        QuarterSalesItem season2 = new QuarterSalesItem();
        season2.setQuarter(2);
        season2.setValue(50.0);
        QuarterSalesItem season3 = new QuarterSalesItem();
        season3.setQuarter(3);
        season3.setValue(0);
        QuarterSalesItem season4 = new QuarterSalesItem();
        season4.setQuarter(4);
        season4.setValue(30.0);
        Assert.assertNotNull("验证季度销售列表是否为null",quarterSalesItems);
        Assert.assertEquals("验证季度销售列表季度数量是否正确",4,quarterSalesItems.size());
        Assert.assertTrue("验证第一季度单月最大销售额是否正确",compareBean(season1,quarterSalesItems.get(0) ));
        Assert.assertTrue("验证第二季度单月最大销售额是否正确",compareBean(season2,quarterSalesItems.get(1) ));
        Assert.assertTrue("验证第三季度单月最大销售额是否正确",compareBean(season3,quarterSalesItems.get(2) ));
        Assert.assertTrue("验证第四季度单月最大销售额是否正确",compareBean(season4,quarterSalesItems.get(3) ));
    }

    //Question5 test
    @Test
    public void getUnUsedKeysTest() {
        //已使用集合为空的情况
        int[] unusedKeys = getUnUsedKeys(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[]{});
        //未使用集合为空的情况
        int[] unusedKeys2 = getUnUsedKeys(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[]{6, 9, 1, 2, 3, 4, 5, 7, 8, 0});
        //正常情况
        int[] unusedKeys3 = getUnUsedKeys(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[]{6, 9, 1, 2,});
        Assert.assertArrayEquals("验证已使用集合为空的情况", new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, unusedKeys);
        Assert.assertArrayEquals("验证未使用集合为空的情况", new int[]{}, unusedKeys2);
        Assert.assertArrayEquals("验证普通情况", new int[]{0, 3, 4, 5, 7, 8}, unusedKeys3);

    }

    /**
     * descripion: 校验两对象属性值差异
     * param: [requestBean, fitBean]
     * return: java.lang.Boolean
     */
    public static Boolean compareBean(Object requestBean, Object fitBean) {
        boolean check = true;
        try {
            // 请求对象的Field
            Class requestClazz = requestBean.getClass();
            Field[] requestFields = requestClazz.getDeclaredFields();
            // 本地对象的Field
            Class fitClazz = fitBean.getClass();
            Field[] fitFields = fitClazz.getDeclaredFields();

            Map<String,String> map = new HashMap<>();
            for (Field field : fitFields) {
                map.put(field.getName(), field.getName());
            }
            // serialVersionUID 不做比较
            for (Field field : requestFields) {
                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }
                // 请求对象有Field 但 本地对象没有的Field 不做比较
                if (!map.containsKey(field.getName())) {
                    continue;
                }
                PropertyDescriptor requestPd = new PropertyDescriptor(field.getName(), requestClazz);
                PropertyDescriptor fitPd = new PropertyDescriptor(field.getName(), fitClazz);
                // 获取Field的get方法
                Method getRequestMethod = requestPd.getReadMethod();
                Method getFitMethod = fitPd.getReadMethod();
                // 获取Field属性值
                Object requestField = getRequestMethod.invoke(requestBean);
                Object fitField = getFitMethod.invoke(fitBean);
                // 返回异常信息
                if (!Objects.equals(requestField, fitField)) {
                    check = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
}
