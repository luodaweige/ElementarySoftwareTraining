package com.crazybubble.element;

import com.crazybubble.controller.GameThread;
import com.crazybubble.manager.ElementManager;
import com.crazybubble.manager.GameElement;
import com.crazybubble.manager.GameLoad;

import javax.swing.*;
import javax.xml.stream.FactoryConfigurationError;
import java.awt.*;
import java.util.IllegalFormatCodePointException;
import java.util.List;

public class Bubble extends ElementObj {
    //�и�ͼƬ����
    private int imgX = 0;
    private int imgY = 0;
    //����ͼƬˢ��ʱ��
    private int imgTime = 0;
    //�������ݱ�ըʱ��
    private int bubbleExploreTime = 0;
    //�ͷ����ݵ��������
    private int playerType;
    //�Ƿ��ͻ
    private boolean isCrash = false;
    //static�����������ݱ��
    private static int number = 0;
    //���ݱ�ţ������ж����������Ƿ�Ϊͬһ��
    private int ID;

    @Override
    public void showElement(Graphics g) {
        g.drawImage(this.getIcon().getImage(), getX(), getY(),
                this.getX() + this.getW(),
                this.getY() + this.getH(),
                0 + imgX, 8 + imgY,
                31 + imgX, 45 + imgY, null);
    }

    @Override
    public ElementObj createElement(String str) {
        String[] split = str.split(",");
        for (String str1 : split) {
            String[] split2 = str1.split(":");
            switch (split2[0]) {
                case "x":
                    this.setX(Integer.parseInt(split2[1]));
                    break;
                case "y":
                    this.setY(Integer.parseInt(split2[1]));
                    break;
                case "w":
                    this.setW(Integer.parseInt(split2[1]));
                    break;
                case "h":
                    this.setH(Integer.parseInt(split2[1]));
                    break;
                case "playerType":
                    this.playerType = Integer.parseInt(split2[1]);
                    break;
            }

        }
        ImageIcon icon = GameLoad.imgMap.get("bubble");
        this.setIcon(icon);
        this.setID(ID + 1);
        return this;
    }

    public Bubble(int x, int y, int w, int h, ImageIcon icon) {
        super(x, y, w, h, icon);
    }

    public Bubble() {
    }

    @Override
    protected void updateImage(long time) {
        if (time - imgTime > 3) {
            imgTime = (int) time;
            imgX += 33;
            if (imgX >= 99) {
                imgX = 0;
            }
        }
    }

    @Override
    public void model(long time) {
        bubbleCrash();
        updateImage(time);
        destroy();
    }

    /**
     * @description ���ݱ�ը��ʧ
     */
    @Override
    public void destroy() {
        if (this.isCrash) {
            this.setBubbleLive(false);
        } else {
            if (bubbleExploreTime < 80) {
                bubbleExploreTime++;
            } else {
                this.setBubbleLive(false);
                ElementManager.getManager().getElementsByKey(GameElement.BUBBLE);
            }
        }
    }

    /**
     * @description ��ײ������������״̬
     */
    public void setBubbleLive(boolean live) {
        if (!live) {
            ElementManager em = ElementManager.getManager();
            List<ElementObj> playerList = em.getElementsByKey(GameElement.PLAYER);
            for (ElementObj obj :
                    playerList) {
                Player player = (Player) obj;
                player.setBubbleNum(this.playerType);
            }
            this.setLive(false);
        }
    }

    /**
     * @description ���������ײ
     */
    public void bubbleCrash() {
        ElementManager em = ElementManager.getManager();
        List<ElementObj> bubbleList = em.getElementsByKey(GameElement.BUBBLE);
        for (ElementObj obj :
                bubbleList) {
            Bubble bubble = (Bubble) obj;
            if (this.getID() != bubble.getID()) {
                if (crash(bubble)) {
                    this.isCrash = true;
                }

            }
        }
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = number + 1;
        number += 1;
    }


    public boolean isCrash() {
        return isCrash;
    }

    public void setCrash(boolean crash) {
        isCrash = crash;
    }

}
