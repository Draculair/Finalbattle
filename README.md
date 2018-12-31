# 大作业：GUP 少女与战车 最终章
161220102 阮伟琦
## 综述
|开发环境                 | 运行环境  | 构建环境           | GUI框架 | 最后一次更新 | 版本 |
| ------------------------ | -------- | ------------------ | ------- | ------------ | ---- |
| IntelliJ IDEA 2018.2.2 | JDK 1.8  | Apache Maven 3.1 | Javafx  | 2018/12/29   | 1.0  |
## 1.项目介绍
此项目为使用以上所述环境开发的一个简易的小游戏，主题是关于少女与战车的脑内幻想展开。通过鼠标点击来开始游戏，存档，复盘等（*是的我并没有用键盘来实现按键，
因为感觉对于普通用户不是很友好的*）游戏开始后两方阵营的选手们会互相攻击直至一方全部战斗不能，则对方获胜。每个人都有血量，攻击力（浮动的），攻击距离等，会在战场上寻找最近的敌人进行攻击，
当射程不够的时候会主动靠近。实现比较简陋，还请多多包涵
## 2.使用说明
开启程序后，能看到左边有三个按键“开始”，“复盘”，“存档”，单击“开始”后就开始了一盘新的游戏，单击”复盘“会弹出对话框让用户选择需要复盘的文件，
单击“存档”会弹出对话框让用户选择需要保存记录的文件夹并自动保存记录“Record.txt“。
效果如图
<div>
    <img src="images\start.png" width=60%>
</div>
<div>
    <img src="images\save&load.png" width=60%>
</div>
## 3.项目结构
### 1.主要的class
如图
<div>
    <img src="images\1.png" width=30%>
</div>

#### a.character
如图
<div>
    <img src="images\2.png" width=90%>
</div>
其中package stggc中有stggc_teammember和stggc_student，package university中有shimada_alice和shimada_chiyo和university_student。

#### b.replay
如图
<div>
    <img src="images\3.png" width=90%>
</div>
需要解释一下的是我是通过记录选手们每一个回合的走位和作出的攻击动作来记录战况的，所以就有Movement和Action类，然后用Record把它们聚合起来
成为真正的记录。class Replay是用来回放数据用的，counter是纯粹为了实现一个多线程的环境来播放画面，顺便记录一下播了的画面数。serialize
是用来序列化的，不得不说序列化在做这种设计class的io的时候确实是挺方便的。

#### c.battle
如图
<div>
    <img src="images\4.png" width=70%>
</div>
battle里面是表示战场的battlefield以及表示整场战斗的war。比较令我满意的是我使用了unit来表示战场上的一个单元，并在里面放入了human对象。
然后在用一个unit数组来表示整个field。

#### d.其他
其他有colour，gui。gui里面就是Controller和display，display主要就负责画面的更新，比较有趣的是我把这个工作赋予了一个名为referee的生物
，让其来负责监视这个战场。而colour就只是一个enum而已。
### 2.机制使用
#### a.异常处理
```java
try {
    lock.notifyAll();
} catch (Exception e) {
    e.printStackTrace();
}
try {
    lock.wait();
} catch (InterruptedException e) {
    e.printStackTrace();
}
```
#### b.集合类型
```java
private ArrayList<Movement[]> movementRecord;// = new ArrayList<>();
private ArrayList<action[]> actionRecord;// = new ArrayList<>();
private action[] oneround_action = new action[16];
private Movement[] oneround_movement = new Movement[16];
public Record(){
    movementRecord = new ArrayList<>();
    actionRecord = new ArrayList<>();
    movementRecord.ensureCapacity(60);
    actionRecord.ensureCapacity(60);
    for(int i = 0; i < 16; i++)
    {
        oneround_action[i] = new action();
        oneround_movement[i] = new Movement();
    }
}
```
#### c.输入输出
```java
try {
    ObjectOutputStream out =
        new ObjectOutputStream(new FileOutputStream(new File(path+"/Record.txt")));
    System.out.println(human.turns);
    System.out.println(war.record.getactionRecord().size());
    out.writeObject(war.record);
    System.out.println("Record save success");
    out.close();
}   catch (IOException ex){
    ex.printStackTrace();
}
try {
    ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
    temp = (Record) in.readObject();
    System.out.println("Record load success");
}   catch (IOException | ClassNotFoundException e){
    e.printStackTrace();
}
```
## 4.项目工作机制
通过在war里面实现多个human（当然他们各有各的身份），然后让他们作为一个线程开始运行，在战场上寻觅敌人并攻击在射程内的敌人，在移动和攻击等动作时是线程互斥的，
同时只能由一个人来执行动作，每个人依次轮流动，动完了之后轮到referee来更新画面。在复盘的过程中，上面也提到我记录了每个人每回合的移动和攻击动作，
然后就以此来逐个回合的把这些信息显示在屏幕上就可以了。

## 5.不足与反思
### a.显示的问题
我现在显示的时候当选手行动不能的时候会出现先举白旗炮弹还飞过来，这应该是因为我显示的时候是按照先显示选手位置再显示动画的原因。复盘的时候会出现子弹弹道飞到没人的地方，
具体原因还未查明，然后还有就是举白旗的选手就不再显示了，因为我在记录的时候是在线程里面记录的，一旦选手行动不能了，他就不会再在Movement中留下记录了，
这个问题解决起来实在是有难度，迫于时间问题只能将就。
### b.test
我现在的test实在是有点简陋了，仅仅是new了一个war然后判断了一下里面是不是所有人物都活着而已，但是实际上像这样对对象来回改动的代码我思考了一下也并没有什么特别有必要test的地方，
不像那些算法什么的，所以同样因为时间原因，没有再想更多的test用例。
