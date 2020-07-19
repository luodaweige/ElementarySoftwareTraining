package com.crazybubble.element;

import com.crazybubble.manager.ElementManager;
import com.crazybubble.manager.GameElement;
import com.crazybubble.manager.GameLoad;
import org.w3c.dom.ls.LSException;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Magic Gunner
 * @description �����
 */
public class Player extends ElementObj {
    //������ͣ�0�������A��1�������B
    private int playerType;

    //���̼���
    private boolean left = false;
    private boolean up = false;
    private boolean right = false;
    private boolean down = false;

    //�и�ͼƬ����
    private int imgX = 0;
    private int imgY = 0;
    //����ͼƬˢ��ʱ��
    private int imgTime = 0;

    //���﷽��
    private String fx = "";
    //����״̬
    private boolean attackType = false;
    //Ѫ��
    private int hp = 5;
    //�ƶ��ٶ�
    private int speed = 10;
    //������ͷ���������
    private int bubbleNum = 0;
    //���ͷ���������
    private int bubbleTotal = 3;
    //��������
    private int bubblePower = 1;
    //�޵�״̬
    private boolean isSuper = false;
    //��ͣ״̬
    private boolean isStop = false;
    //�ܶ�״̬
    private boolean isRun = false;
    //����״̬
    private boolean isReverse = false;

    //��̬�������������ļ���ȡ
    private static int HP = 5;
    private static int SPEED = 10;
    private static int BUBBLETOTAL = 10;
    private static int BUBBLEPOWER = 1;

    @Override
    public void showElement(Graphics g) {
        g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(),
                this.getX() + this.getW(),
                this.getY() + this.getH(),
                24 + (imgX * 100), 42 + (imgY * 100),
                72 + (imgX * 100), 99 + (imgY * 100), null);
    }

    @Override
    public ElementObj createElement(String str) {
        String[] split = str.split(",");
        for (String s :
                split) {
            String[] split1 = s.split(":");
            switch (split1[0]) {
                case "x":
                    this.setX(Integer.parseInt(split1[1]));
                    break;
                case "y":
                    this.setY(Integer.parseInt(split1[1]));
                    break;
                case "w":
                    this.setW(Integer.parseInt(split1[1]));
                    break;
                case "h":
                    this.setH(Integer.parseInt(split1[1]));
                    break;
                case "type":
                    this.setPlayerType(Integer.parseInt(split1[1]));
                    break;
            }
        }
        ImageIcon icon = GameLoad.imgMap.get("player");


        this.setIcon(icon);

        return this;
    }

    protected void addBubble() {
        if (bubbleNum <= bubbleTotal)
            if (attackType) {
                ElementObj obj = GameLoad.getObj("bubble");
                Bubble element = (Bubble) obj.createElement(this.toStr());
                element.bubbleCrash();
                if (!element.isCrash()) {
                    ElementManager.getManager().addElement(element, GameElement.BUBBLE);
                    ++bubbleNum;
                    this.attackType = false;
                }

            }
//        try {
//            //�����ļ���������
//            Class<?> forName = Class.forName("com.crazybubble.element");
//            ElementObj element = PlayFile.class.newInstance().createElement("");
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * @return
     * @description ����ǰ�������Ϣת��Ϊ�ַ���
     */
    @Override
    public String toStr() {
        int x = this.getX();
        int y = this.getY();
        int w = this.getW();
        int h = this.getH();
        int playerType = this.playerType;
        return "x:" + x + ",y:" + y + ",w:" + w + ",h:" + this.getH() + ",playerType:" + playerType;
    }

    /**
     * @param time
     * @description �ܶ�ʱ����ͼƬ
     */
    @Override
    protected void updateImage(long time) {
        if (time - imgTime > 3 && this.isRun) {
            imgTime = (int) time;
            switch (this.fx) {
                case "up":
                    imgY = 3;
                    break;
                case "down":
                    imgY = 0;
                    break;
                case "left":
                    imgY = 1;
                    break;
                case "right":
                    imgY = 2;
                    break;
            }
            imgX++;
            if (imgX > 3) {
                imgX = 0;
            }
        }
    }

    protected void move() {
        if (this.isStop)
            return;
        if (this.isReverse) {
            this.setSpeed(-1 * SPEED);
        } else {
            this.setSpeed(SPEED);
        }
        if (this.left && this.getX() > 0)
            this.setX(this.getX() - this.speed);
        if (this.up && this.getY() > 0)
            this.setY(this.getY() - this.speed);
        if (this.right && this.getX() < 800 - this.getW())
            this.setX(this.getX() + this.speed);
        if (this.down && this.getY() < 800 - this.getH())
            this.setY(this.getY() + speed);
    }

    /**
     * @description ģ�巽������װ���в���
     */
    @Override
    public final void model(long time) {
        updateImage(time);
        move();
        addBubble();
    }

    /**
     * @param bindType ��������� true������ false�����ɿ�
     * @param key      ���������̵�codeֵ
     * @description ���̼���
     */
    public void keyClick(boolean bindType, int key) {
        if (this.isStop)
            return;
        if (bindType) {
            switch (key) {
//                case 65:
                case 37:
                    this.left = true;
                    this.right = false;
                    this.up = false;
                    this.down = false;
                    this.fx = "left";
                    break;
//                case 87:
                case 38:
                    this.up = true;
                    this.down = false;
                    this.left = false;
                    this.right = false;
                    this.fx = "up";
                    break;
//                case 68:
                case 39:
                    this.right = true;
                    this.left = false;
                    this.up = false;
                    this.down = false;
                    this.fx = "right";
                    break;
//                case 83:
                case 40:
                    this.down = true;
                    this.up = false;
                    this.left = false;
                    this.right = false;
                    this.fx = "down";
                    break;
                //��������״̬
                case 32:
//                case 108:
                    this.attackType = true;
                    break;
            }
            this.isRun = true;
        } else {
            switch (key) {
                case 37:
                    this.left = false;
                    break;
                case 38:
                    this.up = false;
                    break;
                case 39:
                    this.right = false;
                    break;
                case 40:
                    this.down = false;
                    break;
                //�رչ���״̬
                case 32:
                    this.attackType = false;
                    break;
            }
            this.isRun = false;
        }
    }

    @Override
    public void destroy() {
        ElementManager em = ElementManager.getManager();
        em.addElement(this, GameElement.DIE);
    }

    @Override
    public void crashMethod(ElementObj obj) {
        //���֮����ײ
        if (Player.class.equals(obj.getClass())) {
            //��Ҫȡ���ƶ�
        }
        //��Һ͵���֮����ײ
        else if (Prop.class.equals(obj.getClass())) {
            Prop prop = (Prop) obj;
            this.propCrash(prop.getPropType(), prop.getLastTime());
        }
        //��Һ�����֮����ײ
        else if (Bubble.class.equals(obj.getClass())) {
            Bubble bubble = (Bubble) obj;
            this.bubbleCrash(bubble);
        }
        //��Һ͵�ͼ֮����ײ
        else if (MapObj.class.equals(obj.getClass())) {
            MapObj mapObj = (MapObj) obj;
            this.mapCrash();
        }
    }


    /**
     * @description ��Һ͵���֮����ײ
     */
    public void propCrash(String propType, int lastTime) {
        //���ط���ֵҲ�����������ļ����ã���ʱ��д�ɶ�ֵ
        switch (propType) {
            case "superpower":
//                this.propSuperPower(this.playerType);
//                this.propTheWorld(2);
                this.propMirror(lastTime);
                break;
            case "bubbleadd":
                this.propBubbleAdd(this.playerType);
                break;
            case "runnningshoes":
                this.propRunningShoes(lastTime);
                break;
            case "crazydiamond":
                this.propCrazyDiamond();
                break;
            case "theworld":
                this.propTheWorld(5);
                break;
        }
        System.out.println(propType);
    }

    /**
     * @description ��Һ�����֮����ײ
     */
    public void bubbleCrash(Bubble bubble) {
        //����������������������ϱ�ը�����һ�Ҫ�ж�������ͣ�����ʱ��д
//        bubble.setCrash(true);
//        this.setHp(this.getHp() - 1);
    }

    /**
     * @description ��Һ͵�ͼ֮����ײ
     */
    public void mapCrash() {
        //��Ҫȡ���ƶ�
        if (this.left && this.getX() > 0)
            this.setX(this.getX() + this.speed);
        if (this.up && this.getY() > 0)
            this.setY(this.getY() + this.speed);
        if (this.right && this.getX() < 800 - this.getW())
            this.setX(this.getX() - this.speed);
        if (this.down && this.getY() < 800 - this.getH())
            this.setY(this.getY() - speed);
    }

    /**
     * @param lastTime
     * @description BubbleAdd������������Ŀ
     */
    public void propBubbleAdd(int lastTime) {
        if (playerType == this.getPlayerType()) {
            this.setBubbleTotal(BUBBLETOTAL + 1);
            Player my = this;
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    my.setBubbleTotal(BUBBLETOTAL);
                }
            };
            timer.schedule(task, lastTime * 1000);
        }
    }

    /**
     * @param lastTime
     * @description SuperPower����ɫҩˮ���������ݹ�����
     */
    public void propSuperPower(int lastTime) {
        if (playerType == this.getPlayerType()) {
            this.setBubblePower(BUBBLEPOWER + 1);
            Player my = this;
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    my.setBubblePower(BUBBLEPOWER);
                }
            };
            timer.schedule(task, lastTime * 1000);

        }
    }

    /**
     * @param lastTime
     * @description Mirror����һ�ú������������ЧӦ������5s
     */
    public void propMirror(int lastTime) {
        if (playerType == this.getPlayerType()) {
            //��������
            this.setReverse(true);
            Player my = this;
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    my.setReverse(false);
                }
            };
            timer.schedule(task, lastTime * 1000);
        }
    }

    public void propRunningShoes(int lastTime) {
        if (playerType == this.getPlayerType()) {
            this.setSpeed(SPEED * 2);
            Player my = this;
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    my.setSpeed(SPEED);
                }
            };
            timer.schedule(task, lastTime * 1000);
        }
    }

    public void propTheWorld(int lastTime) {
        if (playerType == this.getPlayerType()) {
            //զ��³��
            //����ʱͣ
            ElementManager em = ElementManager.getManager();
            List<ElementObj> playerList = em.getElementsByKey(GameElement.PLAYER);
            for (ElementObj obj :
                    playerList) {
                Player player = (Player) obj;
                if (player.playerType != this.getPlayerType()) {
                    player.propWhiteAlbum(lastTime);
                }
            }

        }
    }

    public void propCrazyDiamond() {
        if (playerType == this.getPlayerType()) {
            this.setHp(HP + 5);
        }
    }

    public void propWhiteAlbum(int lastTime) {
        if (playerType == this.getPlayerType()) {
            //����Լ�ֹͣ
            this.setStop(true);
            Player my = this;
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    my.setStop(false);
                }
            };
            timer.schedule(task, lastTime * 1000);
        }
    }

    public void propGodStatus(int lastTime) {
        if (playerType == this.getPlayerType()) {
            //�޵�
            this.setSuper(true);
            Player my = this;
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    my.setSuper(false);
                }
            };
            timer.schedule(task, lastTime * 1000);
        }
    }

    public int getPlayerType() {
        return playerType;
    }

    public void setBubbleNum(int playerType) {
        if (playerType == this.playerType)
            this.setBubbleNum(this.getBubbleNum() - 1);
    }

    public void setPlayerType(int playerType) {
        this.playerType = playerType;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public int getImgX() {
        return imgX;
    }

    public void setImgX(int imgX) {
        this.imgX = imgX;
    }

    public int getImgY() {
        return imgY;
    }

    public void setImgY(int imgY) {
        this.imgY = imgY;
    }

    public int getImgTime() {
        return imgTime;
    }

    public void setImgTime(int imgTime) {
        this.imgTime = imgTime;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public boolean isAttackType() {
        return attackType;
    }

    public void setAttackType(boolean attackType) {
        this.attackType = attackType;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBubbleNum() {
        return bubbleNum;
    }

    public int getBubbleTotal() {
        return bubbleTotal;
    }

    public void setBubbleTotal(int bubbleTotal) {
        this.bubbleTotal = bubbleTotal;
    }

    public int getBubblePower() {
        return bubblePower;
    }

    public void setBubblePower(int bubblePower) {
        this.bubblePower = bubblePower;
    }

    public boolean isSuper() {
        return isSuper;
    }

    public void setSuper(boolean aSuper) {
        isSuper = aSuper;
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    public boolean isRun() {
        return isRun;
    }

    public void setRun(boolean run) {
        isRun = run;
    }

    public boolean isReverse() {
        return isReverse;
    }

    public void setReverse(boolean reverse) {
        isReverse = reverse;
    }
}
