package lab5.graphics;

import lab5.helpers.Tuple;
import lab5.net.INN;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Document implements ISubject, IDrawing {
    List<IObserver> observers = new ArrayList<>();
    List<Tuple> drawing = new ArrayList<>();
    String drawingClass = "L00001";
    INN net;

    public Document() {
        super();

        this.net = null;
    }

    public Document(INN net) {
        super();

        this.net = net;
    }

    @Override
    public void notifyObservers() {
        for(IObserver obs: this.observers) obs.update();
    }

    @Override
    public void removeObserver(int index) {
        this.observers.remove(index);
    }

    @Override
    public void addObserver(IObserver obs) {
        this.observers.add(obs);
    }

    @Override
    public void predict() {
        try {
            net.predict(scale());
        } catch (NullPointerException ex ) {
            System.out.println("NN is not set");
        }
    }

    @Override
    public void save() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        String now = dtf.format(LocalDateTime.now());
        File f = new File("./lab5/drawings/" + this.drawingClass + "/" + now + ".txt");
        try {
            String data = "";
            int i = 1;
            for(Tuple tup: scale()) {
                data += tup.toString() + " ";
                if (i % 4 == 0) {
                    data += "\n";
                }
                i++;
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            writer.write(data);
            writer.close();
        } catch (IOException exception) {
            System.out.println("Something went wrong " + exception.getMessage());
        }
    }

    @Override
    public void add(Tuple tup) {
        this.drawing.add(tup);
        notifyObservers();
    }

    @Override
    public void clear() {
        this.drawing.clear();
        notifyObservers();
    }

    @Override
    public void drawLine(Graphics2D renderer) {
        for (int i = 1; i < this.drawing.size(); ++i) {
            renderer.drawLine(drawing.get(i-1).getIntX(), drawing.get(i-1).getIntY(), drawing.get(i).getIntX(), drawing.get(i).getIntY());
        }
    }

    @Override
    public ArrayList<Tuple> scale() {
        Tuple<Double, Double> average = calculateAverage();
        ArrayList<Tuple> scaled = new ArrayList<Tuple>();
        double max = Double.NEGATIVE_INFINITY;
        double tempX,tempY;
        for(Tuple tup: this.drawing) {
            tempX = tup.getIntX() + average.getX();
            tempY = tup.getIntY() + average.getY();
            if(max < tempX) {
                max = tempX;
            }
            if(max < tempY) {
                max = tempY;
            }
            scaled.add(new Tuple(tempX, tempY));
        }

        double finalMax = max;
        scaled.forEach(tuple -> { tuple.setX(Double.parseDouble(tuple.getX().toString())/ finalMax); tuple.setY(Double.parseDouble(tuple.getY().toString())/ finalMax);});

        double calculateDistance = 0;
        ArrayList<Double> listOfDistances = new ArrayList<Double>();
        listOfDistances.add(0.0);
        Tuple previous = scaled.get(0);
        for (Tuple element : scaled.subList(1, scaled.size())) {
            calculateDistance += Math.sqrt(Math.pow(Double.parseDouble(element.getX().toString()) - Double.parseDouble(previous.getX().toString()), 2) + Math.pow(Double.parseDouble(element.getY().toString()) - Double.parseDouble(previous.getY().toString()), 2));
            listOfDistances.add(calculateDistance);
            previous = element;
        }

        ArrayList newList = new ArrayList<Tuple>();
        int index = 0;
        int listIndex = 0;
        while(index<20) {
            double ratio = index * calculateDistance / 19;
            while (ratio > listOfDistances.get(listIndex + 1)) {
                ++listIndex;
            }
            if(listIndex != scaled.size()) {
                if (Math.abs(listOfDistances.get(listIndex + 1) - ratio) > Math.abs(listOfDistances.get(index) - ratio) ) newList.add(scaled.get(listIndex));
                else newList.add(scaled.get(index + 1));
            } else if(listIndex == scaled.size()) newList.add(scaled.get(listIndex));

            index++;
        }
        return newList;
    }

    public Tuple calculateAverage() {
        double avgXCord = 0, avgYCord = 0;
        for(int i = 0; i < this.drawing.size(); i++) {
            avgXCord += Double.parseDouble(this.drawing.get(i).getX().toString());
            avgYCord += Double.parseDouble(this.drawing.get(i).getY().toString());
        }

        return new Tuple(avgXCord/this.drawing.size(), avgYCord/this.drawing.size());
    }

    public void setDrawingClass(String drawingClass) {
        this.drawingClass = drawingClass;
    }
}
