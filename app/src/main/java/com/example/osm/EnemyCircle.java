package com.example.osm;

import android.graphics.Color;

import java.util.Random;

public class EnemyCircle extends Circle {

    public static final int TO_RADIUS = 150;
    public static final int FROM_RADIUS = 10;
    public static final int EN_COLOR = Color.RED;
    public static final int FOOD_COL = Color.GREEN;
    public static final int SPEED = 10;
    private int dx;
    private int dy;


    public EnemyCircle(int x, int y, int radius, int dx, int dy) {
        super(x, y, radius);
        this.dx = dx;
        this.dy = dy;


    }

    public static EnemyCircle getRandom() {
        Random random = new Random();
        int x = random.nextInt(GameManager.getWidth());
        int y = random.nextInt(GameManager.getHeight());
        int dx = 1 + random.nextInt(SPEED);
        int dy = 1 + random.nextInt(SPEED);
        int radius = FROM_RADIUS + random.nextInt(TO_RADIUS - FROM_RADIUS);
        EnemyCircle enemyCircle = new EnemyCircle(x, y, radius, dx, dy);
        enemyCircle.setColor(EN_COLOR);
        return enemyCircle;
    }

    protected void enOrFoodCol(MainCircle mainCircle) {
        if (isSmaller(mainCircle)){
            this.setColor(FOOD_COL);
        } else {this.setColor(EN_COLOR);}
    }

    protected boolean isSmaller(Circle circle) {
        return radius < circle.radius;
    }

    public void moveOneStep() {
        x += dx;
        y += dy;
        checkBounds();
    }

    private void checkBounds() {
    if (x > GameManager.getWidth() || x < 0) {dx = -dx;}
    if (y > GameManager.getHeight() || y < 0) {dy = -dy;}
    }

}
