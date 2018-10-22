package ru.itis;

public class Main {

    public static void main(String[] args) {
        // human -> объектная переменная
        // указывает на объект типа Human

        // две объектных переменных и один объект
        Human human = new Human("Марсель", "Сидиков", 24);
        Human human1 = human;
        human1.setFirstName("Ляйсан");
        System.out.println(human.getFirstName() + " " +
                human.getLastName() + " " +
                human.getAge());
    }
}
