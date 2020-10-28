package menu;

import exec.ExecEnum;

import static exec.ExecEnum.*;


public class MenuFactory {

    public static Menu getMenuInstance() {
        return new Menu.MenuBuilder()
                .withItem(new Menu.MenuItem(name(ADD), ADD.getExec()))
                .withItem(new Menu.MenuItem(name(SHOW), SHOW.getExec()))
                .withItem(new Menu.MenuItem(name(SHOW_SORTED_UNIQUE), SHOW_SORTED_UNIQUE.getExec()))
                .withItem(new Menu.MenuItem(name(SAVE_TO_FILE), SAVE_TO_FILE.getExec()))
                .withItem(new Menu.MenuItem(name(READ_FROM_FILE), READ_FROM_FILE.getExec()))
                .withItem(new Menu.MenuItem(name(CLEAR_DATA), CLEAR_DATA.getExec()))
                .withItem(new Menu.MenuItem(name(EXIT), EXIT.getExec()))
                .build();
    }

    private static String name(ExecEnum e) {
        String s = e.name().trim().replace("_", " ");
        return s.substring(0, 1).concat(s.substring(1).toLowerCase());

    }
}