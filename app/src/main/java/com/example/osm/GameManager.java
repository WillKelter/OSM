package com.example.osm;



import java.util.ArrayList;
import java.util.Random;


public class GameManager {
    public Random random = new Random();
    public static final int MAX_EN = 20;
    private static MainCircle mainCircle;
    private static ArrayList<EnemyCircle> enemies;
    private static CanvasView canvasView;
    private static int width;
    private static int height;



    public GameManager(CanvasView canvasView, int w, int h) {
        GameManager.canvasView = canvasView;
        width = w;
        height = h;
        initMainCircle();
        initEnemyCircles();

    }

    private void initEnemyCircles() {
        enemies = new ArrayList<>();
        for (int i = 0; i < MAX_EN; i++) {
            EnemyCircle circle;
            Circle mainCircleArea = mainCircle.getArea();
            do {
                circle = EnemyCircle.getRandom();
            } while (circle.inIntersect(mainCircleArea));
            enemies.add(circle);
        }
        calcAndSetColor();
    }

    private void calcAndSetColor() {
        for (EnemyCircle enemy : enemies) {
            enemy.enOrFoodCol(mainCircle);
        }                
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }


    private void initMainCircle() {
        mainCircle = new MainCircle(width / 2, height / 2);
    }

    public static void onDraw() {
        canvasView.drawCircle(mainCircle);
        for (EnemyCircle circle : enemies) {
            canvasView.drawCircle(circle);
        }
    }

    public void onTouchEvent(int x, int y) {
        mainCircle.moveMainCircleWhenTouchAt(x, y);
        checkCollision();
        mainCircle.checkBound();
        moveCircles();
    }

    private void checkCollision() {
        Circle fordel = null;
        for (EnemyCircle enemy : enemies) {
            if (mainCircle.inIntersect(enemy)){
                if(enemy.isSmaller(mainCircle)){
                    mainCircle.grow(enemy);
                    fordel = enemy;
                    calcAndSetColor();
                    break;
                } else{
                gameEnd("You're loser"); return;}
            }
        }
        if (fordel != null){enemies.remove(fordel);}
        if (enemies.isEmpty()){gameEnd("You're Winner");}
    }

    private void gameEnd(String s) {
        canvasView.showMessage(s);
        mainCircle.initRadius();
        initEnemyCircles();
        canvasView.redraw();
    }

    private void moveCircles() {
        for (EnemyCircle enemy : enemies) {
            enemy.moveOneStep();
        }
    }
}
