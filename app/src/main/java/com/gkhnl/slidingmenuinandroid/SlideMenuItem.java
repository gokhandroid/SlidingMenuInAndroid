package com.gkhnl.slidingmenuinandroid;

/**
 * Created by gkhnnl on 11/06/15.
 */
public class SlideMenuItem {

    private String title;
    private int icon;

    public SlideMenuItem() {
    }

    public SlideMenuItem(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
