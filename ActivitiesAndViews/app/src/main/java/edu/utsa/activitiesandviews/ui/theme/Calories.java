package edu.utsa.activitiesandviews.ui.theme;

import android.content.res.AssetManager;

import java.io.IOException;
import java.util.Scanner;

public class Calories {


    private int cals;

    public Calories(int cals){
        this.cals = cals;
    }

    public int getcals() {
        return cals;
    }

    private void setId(int id) {
        this.cals = cals;
    }

    private void setupFromCalories(int cals, AssetManager assets){
        Scanner scan;
        String str = "";
        String[] arr = null;

        try {
            scan = new Scanner(assets.open("calories.txt"));
            while (scan.hasNext()) {
                str = scan.nextLine();
                arr = str.split(",");

            }
            scan.close();
            if (arr!= null && arr.length > 0) {
    cals = Integer.parseInt(arr[arr.length -1]);
            }
        }
        catch(IOException e) {
            System.out.println("Error" + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing integer: " + e.getMessage());
        }
    }
}
