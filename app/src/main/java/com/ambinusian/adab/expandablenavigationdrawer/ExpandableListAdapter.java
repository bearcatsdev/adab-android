package com.ambinusian.adab.expandablenavigationdrawer;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ambinusian.adab.R;
import com.ambinusian.adab.preferences.UserPreferences;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    Context context;
    List<MenuModel> groupList;
    HashMap<MenuModel,List<MenuModel>> childList;
    UserPreferences userPreferences;
    float defaultMenuTextSize = -1;
    Typeface defaultMenuTextTypeface = null;

    public ExpandableListAdapter(Context context, List<MenuModel> groupList, HashMap<MenuModel, List<MenuModel>> childList) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
        userPreferences = new UserPreferences(context);
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        if(childList.get(groupList.get(i)) == null)
            return 0;
        else
            return childList.get(groupList.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return groupList.get(i);
    }

    @Override
    public MenuModel getChild(int i, int i1) {
        return childList.get(groupList.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        int menuIcon = groupList.get(i).menuIcon;
        String menuName = groupList.get(i).menuName;

        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.expandable_menu_group_item, null);
        }

        ImageView mMenuIcon = (ImageView) view.findViewById(R.id.group_menu_icon);
        ImageView indicatorIcon = (ImageView)
                view.findViewById(R.id.indicator);
        TextView mMenuName = (TextView) view.findViewById(R.id.group_menu_name);

        if(menuIcon == 0){
            mMenuIcon.setImageResource(R.drawable.round_view_stream_24px);
        }
        else
        if(menuIcon == 1){
            mMenuIcon.setImageResource(R.drawable.round_book_24px);
        }
        else
        if(menuIcon == 2){
            mMenuIcon.setImageResource(R.drawable.round_today_24px);
        }
        else
        if(menuIcon == 3){
            mMenuIcon.setImageResource(R.drawable.round_forum_24px);
        }
        else
        if(menuIcon == 4){
            mMenuIcon.setImageResource(R.drawable.round_help_24px);
        }
        else
        if(menuIcon == 5){
            mMenuIcon.setImageResource(R.drawable.round_settings_24px);
        }
        mMenuName.setText(menuName);
        getDefaultMenuTextSize(mMenuName);
        mMenuName.setTextSize(TypedValue.COMPLEX_UNIT_PX,defaultMenuTextSize*userPreferences.getTextSize());
        getDefaultMenuTextTypeface(mMenuName);
        mMenuName.setTypeface(userPreferences.getTextTypeface());


        if(i != 1){
            indicatorIcon.setVisibility(View.GONE);
        }

        if(b)
            indicatorIcon.setImageDrawable(view.getContext().getResources().getDrawable(R.drawable.ic_round_keyboard_arrow_up_24px));
        else
            indicatorIcon.setImageDrawable(view.getContext().getResources().getDrawable(R.drawable.ic_round_keyboard_arrow_down_24px));

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        int menuIcon = childList.get(groupList.get(i)).get(i1).menuIcon;
        String menuName = childList.get(groupList.get(i)).get(i1).menuName;

        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.expandable_menu_child_item, null);
        }

        ImageView mMenuIcon = view.findViewById(R.id.child_menu_icon);
        TextView mMenuName = view.findViewById(R.id.child_menu_name);
        getDefaultMenuTextSize(mMenuName);
        mMenuName.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultMenuTextSize*userPreferences.getTextSize());

        if(menuIcon == 6)
        mMenuIcon.setImageResource(R.drawable.ic_outline_book_24px);
        mMenuName.setText(menuName);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public void getDefaultMenuTextSize(TextView menu){
        if(defaultMenuTextSize == -1){
            defaultMenuTextSize = menu.getTextSize();
        }
    }

    public void getDefaultMenuTextTypeface(TextView menu){
        if(defaultMenuTextTypeface == null){
            defaultMenuTextTypeface = menu.getTypeface();
        }
    }
}
