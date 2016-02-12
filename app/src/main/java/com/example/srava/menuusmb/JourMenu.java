package com.example.srava.menuusmb;

import java.sql.Date;
import java.util.List;

/**
 * Created by modenaq on 12/02/2016.
 */
public class JourMenu {
    private String date;
    private List<String> petitDejeuner;
    private List<String> midi;
    private List<String> soir;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getMidi() {
        return midi;
    }

    public void setMidi(List<String> midi) {
        this.midi = midi;
    }

    public List<String> getPetitDejeuner() {
        return petitDejeuner;
    }

    public void setPetitDejeuner(List<String> petitDejeuner) {
        this.petitDejeuner = petitDejeuner;
    }

    public List<String> getSoir() {
        return soir;
    }

    public void setSoir(List<String> soir) {
        this.soir = soir;
    }

    public JourMenu() {
        setDate(null);
        setMidi(null);
        setPetitDejeuner(null);
        setSoir(null);
    }

    public JourMenu(String date, List<String> petitDejeuner, List<String> midi, List<String> soir) {
        this.date = date;
        this.petitDejeuner = petitDejeuner;
        this.midi = midi;
        this.soir = soir;
    }

    public void addPetitDejeuner(String pPetitDejeuner) {
        petitDejeuner.add(pPetitDejeuner);
    }

    public void addMidi(String pMidi) {
        midi.add(pMidi);
    }

    public void addSoir(String pSoir) {
        soir.add(pSoir);
    }

    public void clear(){
        setDate(null);
        setSoir(null);
        setMidi(null);
        setPetitDejeuner(null);
    }

    public String toString(){
        String result=date;
        for(String petitD:petitDejeuner)
            result=result+" "+petitD;
        for(String bMidi:petitDejeuner)
            result=result+" "+bMidi;
        for(String bSoir:petitDejeuner)
            result=result+" "+bSoir;
        return result;
    }
}
