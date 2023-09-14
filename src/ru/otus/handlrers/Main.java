package ru.otus.handlrers;

public class Main {
    public static void main(String[] args) {
        Button button = new Button(new ButtonClickHandler());
        button.click();
        button.click();

        Button tvButton = new Button(new EventHandler() {
            private boolean on = false;
            @Override
            public void execute() {
                if (on) {
                    System.out.println("Телевизор выключен");
                    on = false;
                } else {
                    System.out.println("Телевизор включен");
                    on = true;
                }
            }
        });
        tvButton.click();
        tvButton.click();
    }
}

interface EventHandler {
    void execute();
}

class ButtonClickHandler implements EventHandler {
    @Override
    public void execute() {
        System.out.println("Click");
    }
}

class Button {
    EventHandler handler;
    Button(EventHandler action) {
        this.handler = action;
    }
    public void click() {
        handler.execute();
    }
}