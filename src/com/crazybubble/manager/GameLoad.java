package com.crazybubble.manager;

import com.crazybubble.element.ElementObj;
import com.crazybubble.element.MapObj;
import com.crazybubble.manager.ElementManager;
import com.crazybubble.manager.GameElement;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author Magic Gunner
 * @˵�� �����������ڶ�ȡ�����ļ��Ĺ��ߣ�����ṩ����static����
 */
public class GameLoad {
    private static ElementManager em = ElementManager.getManager();
    //��ͼ�ֵ�
    public static Map<String, ImageIcon> imgMap = new HashMap<>();
    //�û���ȡ�ļ�����
    private static Properties pro = new Properties();
    //Ԫ���ֵ�
    private static Map<String, Class<?>> objMap = new HashMap<>();

    /**
     * @param mapID �ļ����
     * @˵�� �����ͼID�ɼ��ط��������ļ������Զ����ɵ�ͼ�ļ����Ƽ����ļ�
     */
    public static void MapLoad(int mapID) {
        String mapName = "com/tedu/text/" + mapID + ".map";
        ClassLoader classLoader = GameLoad.class.getClassLoader();
        InputStream maps = classLoader.getResourceAsStream(mapName);
        if (maps == null) {
            System.out.println("error");
            return;
        }
        try {
            pro.load(maps);
            //����ֱ�Ӷ�̬�Ļ�ȡ���е�key����key�Ϳ��Ի�ȡvalue
            Enumeration<?> names = pro.propertyNames();
            while (names.hasMoreElements()) {
                String key = names.nextElement().toString();
                pro.getProperty(key);
                String[] arrs = pro.getProperty(key).split(";");
                for (int i = 0; i < arrs.length; i++) {
                    ElementObj element = new MapObj().createElement(key + "," + arrs[i]);
                    em.addElement(element, GameElement.MAPS);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @˵�� ����ͼƬ����
     * ���Դ���������Ϊ��ͬ�Ĺٿ��܁`��һ����ͼƬ��Դ
     */
    public static void ImgLoad() {
        String texturl = "com/tedu/text/GameData.pro";
        ClassLoader classLoader = GameLoad.class.getClassLoader();
        InputStream texts = classLoader.getResourceAsStream(texturl);
        //imgMap���ڴ������
        pro.clear();
        try {
            pro.load(texts);
            Set<Object> set = pro.keySet();
            for (Object o :
                    set) {
                String url = pro.getProperty(o.toString());
                imgMap.put(o.toString(), new ImageIcon((url)));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ��չ��ʹ�������ļ�����ʵ��������ͨ���̶���key
     */
    public static void ObjLoad() {
        String texturl = "com/tedu/text/obj.pro";
        ClassLoader classLoader = GameLoad.class.getClassLoader();
        InputStream texts = classLoader.getResourceAsStream(texturl);
        //imgMap���ڴ������
        pro.clear();
        try {
            pro.load(texts);
            Set<Object> set = pro.keySet();
            for (Object o :
                    set) {
                String classUrl = pro.getProperty(o.toString());
                Class<?> forName = Class.forName(classUrl);

                objMap.put(o.toString(), forName);

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void PlayLoad() {
        //�������ļ����ؽ�map
        ObjLoad();
        //Ӧ�ÿ��Դ������ļ����ȡstring
        String playStr = "100,100,up";

        ElementObj obj = getObj("play");
        ElementObj play = obj.createElement(playStr);

//        Class<?> class1 = objMap.get("play");
//        ElementObj obj = null;
//        try {
//            //�������ͺ�new Play()�ȼ�
//            Object newInstance = class1.newInstance();
//            if (newInstance instanceof ElementObj) {
//                obj = (ElementObj) newInstance;
//            }
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        ElementObj play = obj.createElement(playStr);
        //������ʹ���ʹ���֮�����϶ȣ�����ֱ��ͨ���ӿڻ������Ϳ��Ի�ȡ��ʵ�����
        em.addElement(play, GameElement.PLAY);
    }

    public static ElementObj getObj(String str) {
        try {
            Class<?> class1 = objMap.get(str);
            //�������ͺ�new Play()�ȼ�
            Object newInstance = class1.newInstance();
            if (newInstance instanceof ElementObj) {
                return (ElementObj) newInstance;
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
