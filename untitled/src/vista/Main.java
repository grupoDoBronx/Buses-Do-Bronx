package vista;

import Ventanas.MenuPrincipal;

public class Main {
    public static void main(String[] args) {
        MenuPrincipal menu = MenuPrincipal.getInstance();

        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }
}