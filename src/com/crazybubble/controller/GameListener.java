package com.crazybubble.controller;

import com.crazybubble.element.ElementObj;
import com.crazybubble.manager.ElementManager;
import com.crazybubble.manager.GameElement;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ������ ���ڼ����û��Ĳ���KeyListener
 *
 * @author Magic Gunner
 */
public class GameListener implements KeyListener{
    ElementManager em = new ElementManager().getManager();
    Set<Integer> set = new HashSet<Integer>();

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //������Ҫ����˫����Ϸ�����Ҫ�ഫһ��int PlayerType
    //PlayerType = 0 �������A��PlayerType = 1�������B���Դ�����
    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println("press" + e.getKeyCode());
        int key = e.getKeyCode();
        //�ж��������Ƿ��Ѿ����ڣ������������
        if (set.contains(key)) {
            //�������ֱ�ӽ�������
            return;
        }
        set.add(key);
        List<ElementObj> play = em.getElementsByKey(GameElement.PLAYER);
        for (ElementObj obj :
                play) {
            //������Ҫ��PlayerType����keyClick��
            obj.keyClick(true, e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println("release" + e.getKeyCode());
        int key = e.getKeyCode();
        //�ж��������Ƿ��Ѿ����ڣ������������
        if (!set.contains(key)) {
            return;
        }
        //�Ƴ�����
        set.remove(e.getKeyCode());
        List<ElementObj> play = em.getElementsByKey(GameElement.PLAYER);
        for (ElementObj obj :
                play) {
            obj.keyClick(false, e.getKeyCode());
        }
    }

}
