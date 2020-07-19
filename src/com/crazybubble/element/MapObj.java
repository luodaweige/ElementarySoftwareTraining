package com.crazybubble.element;

import com.crazybubble.element.ElementObj;

import javax.swing.*;
import java.awt.*;

/**
 * @author Magic Gunner
 * @description ��ͼԪ��
 */
public class MapObj extends ElementObj {
    //��ͼԪ������ֵ
    private int hp = 1;
    //��ͼԪ������
    private String mapType = "";
    //��ͼԪ������ֵ
    private int mapHp;

    @Override
    public void showElement(Graphics g) {
        g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
    }


    @Override
    public ElementObj createElement(String str) {
        //���ط����Ե��������ļ�
        String[] arr = str.split(",");
        ImageIcon icon = null;
        switch (arr[0]) {
            case "GRASS":
                icon = new ImageIcon("image/image/wall/grass.png");
                break;
            case "BRICK":
                icon = new ImageIcon("image/image/wall/brick.png");
                break;
            case "RIVER":
                icon = new ImageIcon("image/image/wall/river.png");
                break;
            case "IRON":
                icon = new ImageIcon("image/image/wall/iron.png");
                this.hp = 4;
                this.mapType = "IRON";
                break;
        }
        this.setX(Integer.parseInt(arr[1]));
        this.setY(Integer.parseInt(arr[2]));
        this.setW(icon.getIconWidth());
        this.setH(icon.getIconHeight());
        this.setIcon(icon);

        return this;
    }

    /**
     * @description ���õ�ͼԪ�صĴ���״̬
     */
    @Override
    public void setLive(boolean live) {
//        if ("IRON".equals(this.name)) {
//            this.hp--;
//            if (this.hp >= 0) {
//                super.setLive(live);
//                return;
//            }
//        }
        super.setLive(live);
    }
}
