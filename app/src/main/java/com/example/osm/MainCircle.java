package com.example.osm;

import android.graphics.Color;

public class MainCircle extends Circle{
    public static final int INIT_RADIUS = 50;
    public static final int MAIN_SPEED = 30;
    public static final int MAIN_COLOR = Color.BLUE;

    public MainCircle(int x, int y) {
        super(x, y, INIT_RADIUS);
        setColor(MAIN_COLOR);
    }


    public void moveMainCircleWhenTouchAt(int x1, int y1) {
        int dx = (x1 - x) * MAIN_SPEED / GameManager.getWidth();
        int dy = (y1 - y) * MAIN_SPEED / GameManager.getHeight();
        x += dx;
        y += dy;
        }

    public void initRadius() {
        radius = INIT_RADIUS;
        x = GameManager.getWidth() / 2;
        y = GameManager.getHeight() / 2;
    }

    public void grow(Circle circle) {
        radius =(int) Math.sqrt(Math.pow(radius, 2) + Math.pow(circle.radius, 2));
    }


    public void checkBound() {
        if (x > GameManager.getWidth()) {x = GameManager.getWidth();}
        if (y > GameManager.getHeight()) {y = GameManager.getHeight();}
    }
}
