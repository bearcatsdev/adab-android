package com.ambinusian.adab.expandablenavigationdrawer;

import android.graphics.drawable.Drawable;

public class MenuModel {
    public String menuName;
    public Drawable menuIcon;
    public boolean hasChildren, isGroup;

    public MenuModel(Drawable menuIcon, String menuName, boolean isGroup, boolean hasChildren) {
        this.menuIcon = menuIcon;
        this.menuName = menuName;
        this.isGroup = isGroup;
        this.hasChildren = hasChildren;
    }
}
