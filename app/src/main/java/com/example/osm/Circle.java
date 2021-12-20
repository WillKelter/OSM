package com.example.osm;

public class Circle {
    protected int x;
    protected int y;
    protected int radius;
    private  int color;

    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int c) {
        this.color = c;
    }

    public Circle getArea() {
        return new Circle(x, y, radius*2);
    }

    public boolean inIntersect(Circle circle) {
        return radius + circle.radius >= Math.sqrt(Math.pow(x - circle.x, 2) + Math.pow(y- circle.y, 2));
    }

}
