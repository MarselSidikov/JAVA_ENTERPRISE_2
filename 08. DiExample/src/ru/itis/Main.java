package ru.itis;

public class Main {

    public static void main(String[] args) {
	    Terminal terminal = ApplicationContext
                .getContext().getComponent(Terminal.class, "terminal");
	    terminal.giveMoney(10);
    }
}
