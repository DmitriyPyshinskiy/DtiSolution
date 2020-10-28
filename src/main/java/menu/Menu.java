package menu;

import exec.Exec;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<MenuItem> items = new ArrayList<>();

    public List<MenuItem> getItems() {
        return items;
    }

    public String showMenu() {
        StringBuilder listMenu = new StringBuilder("Menu:\n");
        for(int i = 0; i < this.items.size(); i++) {
            listMenu.append(i+1).append(".").append(this.items.get(i).name).append("\n");
        }
        return listMenu.toString();
    }

    public static class MenuItem {
        private final String name;
        private final Exec exec;

        public MenuItem(String name, Exec exec) {
            this.name = name;
            this.exec = exec;
        }

        public String getName() {
            return name;
        }

        public Exec getExec() {
            return exec;
        }
    }

    public static class MenuBuilder {
        private final Menu menu = new Menu();

        public MenuBuilder withItem(MenuItem item) {
            menu.items.add(item);
            return this;
        }

        public Menu build() {
            return menu;
        }
    }
}