package com.crazybubble.element;

import com.crazybubble.manager.GameLoad;

import javax.swing.*;
import java.awt.*;

/**
 * @author Magic Gunner
 * @description ����Ԫ�صĻ���
 */
public abstract class ElementObj {
    private int x;
    private int y;
    private int w;
    private int h;

    //ͼƬ�����Ͻ�
    private int sx, sy;
    //ͼƬ�����½�
    private int dx, dy;

    private ImageIcon icon;
    //����״̬
    private boolean live = true;
    //��ʱ��
    private static long time = 0;


    /**
     * @description �޲ι���
     */
    public ElementObj() {

    }


    /**
     * @param x    ���Ͻ�x����
     * @param y    ���Ͻ�y����
     * @param w    w���
     * @param h    h�߶�
     * @param icon ͼƬ
     * @description ���ι���
     */
    public ElementObj(int x, int y, int w, int h, ImageIcon icon) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.icon = icon;
    }


    /**
     * @param g ���� ���ڽ��л滭
     * @description ���󷽷�����ʾԪ��
     */
    public abstract void showElement(Graphics g);


    /**
     * @param bindType ��������� true������ false�����ɿ�
     * @param key      ���������̵�codeֵ
     * @description ���̼���
     * @��չ �������Ƿ���Է�Ϊ����������һ�����հ���һ�������ɿ�����չ
     */
    public void keyClick(boolean bindType, int key) {
    }

    /**
     * @param str
     * @return
     * @description ���󷽷���ʹ���ַ�������Ԫ��
     */
    public abstract ElementObj createElement(String str);


    /**
     * @description ģ�壬˳��ִ��
     */
    public void model(long time) {
    }

    /**
     * @description �ƶ�������protected ֻ�����������д
     */
    protected void move() {
    }


    /**
     * @description ����ͼƬ
     */
    protected void updateImage(long time) {
        //�������п���
        // long ... a �����������飬�����������������N��long���͵�����
    }


    /**
     * @description Ԫ������
     */
    public void destroy() {
        //�������� ������̳е�
        //����Ҳ��һ������
    }

    /**
     * @return ����������Ԫ�ص���ײ���ζ���ʵʱ���أ�
     * @description ��ͼƬ����ɾ��Σ�������ײ���
     */
    public Rectangle getRectangle() {
        //���Խ�������ݽ��д���
        return new Rectangle(x, y, w, h);
    }

    /**
     * @param obj ��ײ��Ԫ��
     * @return boolean ����true˵������ײ������false˵��û����ײ
     * @description ��ײ����
     */
    public boolean crash(ElementObj obj) {
        return this.getRectangle().intersects(obj.getRectangle());
    }

    /**
     * @return
     * @description ת��Ϊ�ַ���
     */
    protected String toStr() {
        return null;
    }

    /**
     * @return
     * @description ��ײ֮��ִ�еķ���
     */
    public void crashMethod(ElementObj obj) {

    }

    //ֻҪ��VO���ҪΪ��������get��set����

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}
