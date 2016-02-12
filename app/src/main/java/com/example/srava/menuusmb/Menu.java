package com.example.srava.menuusmb;

import java.util.List;

/**
 * Created by modenaq on 12/02/2016.
 */
public class Menu {
    private List<JourMenu> jourMenu;

    public List<JourMenu> getJourMenu() {
        return jourMenu;
    }

    public void setJourMenu(List<JourMenu> jourMenu) {
        this.jourMenu = jourMenu;
    }

    public Menu() {
        setJourMenu(null);
    }

    public Menu(List<JourMenu> jourMenu) {
        this.jourMenu = jourMenu;
    }

    public void addJourMenu(JourMenu pJourMenu) {
        jourMenu.add(pJourMenu);
    }

    public String toString(){
        String result="";
        for(JourMenu jour:jourMenu)
            result=result+" "+jour.toString();
        return result;
    }
}
