package com.ambinusian.adab.recyclerview.nextorlatestclass;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ambinusian.adab.R;


import java.util.ArrayList;

public class NextOrLatestClassAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<NextOrLatestClassModel> lists;

    public NextOrLatestClassAdapter (Context context, ArrayList<NextOrLatestClassModel> lists){
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 0){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_next_or_latest_class,parent,false);
            return new NextOrLatestClassHolder(v);
        }
        else
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_next_or_latest_class_date,parent,false);
            return new NextOrLatestClassDateHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NextOrLatestClassModel item = lists.get(position);

        if(holder.getItemViewType() == 0){
            ((NextOrLatestClassHolder)holder).classTopic.setText(item.getClassTopic());
            ((NextOrLatestClassHolder)holder).session.setText(item.getSession());
            ((NextOrLatestClassHolder)holder).room.setText(item.getRoom());

            //set classIcon
            int classIcon = item.getClassIcon();
            switch (classIcon) {
                case 0:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_00_cube));
                    break;
                case 1:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_01_laptopidea));
                    break;
                case 2:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_02_cloudnet));
                    break;
                case 3:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_03_lawprotect));
                    break;
                case 4:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_04_picture));
                    break;
                case 5:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_05_gearmail));
                    break;
                case 6:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_06_boxmoney));
                    break;
                case 7:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_07_slidepie));
                    break;
                case 8:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_08_slidepencil));
                    break;
                case 9:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_09_globe));
                    break;
                case 10:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_10_awardmedal));
                    break;
                case 11:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_10_awardmedal));
                    break;
                case 12:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_12_privacychat));
                    break;
                case 13:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_13_hammer));
                    break;
                case 14:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_14_communication));
                    break;
                case 15:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_15_databaseprotection));
                    break;
                case 16:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_16_circuit));
                    break;
                case 17:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_17_laptopprotection));
                    break;
                case 18:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_18_trustperson));
                    break;
                case 19:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_19_websecurity));
                    break;
                case 20:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_20_laptoplocation));
                    break;
                case 21:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_21_cloudmail));
                    break;
                case 22:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_22_webplayer));
                    break;
                case 23:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_23_cloudsetting));
                    break;
                case 24:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_24_webdata));
                    break;
                case 25:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_25_vectordrawing));
                    break;
                case 26:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_26_geometrydrawing));
                    break;
                case 27:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_27_document));
                    break;
                case 28:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_28_computersetting));
                    break;
                case 29:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_29_computerdrawing));
                    break;
                case 30:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_30_boxlight));
                    break;
                case 31:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_31_stationerybox));
                    break;
                case 32:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_32_layer));
                    break;
                case 33:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_33_stationery));
                    break;
                case 34:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_34_searchdraw));
                    break;
                case 35:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_35_compass));
                    break;
                case 36:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_36_notepad));
                    break;
                case 37:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_37_palettepaint));
                    break;
                case 38:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_38_vectorgraphic));
                    break;
                case 39:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_39_colorpalette));
                    break;
                case 40:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_40_paintbrush));
                    break;
                case 41:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_41_webconnect));
                    break;
                case 42:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_42_organization));
                    break;
                case 43:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_43_ideathinking));
                    break;
                case 44:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_44_plantgrow));
                    break;
                case 45:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_45_money));
                    break;
                case 46:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_46_presentation));
                    break;
                case 47:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_47_broadcast));
                    break;
                case 48:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_48_gearweb));
                    break;
                case 49:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_49_graph));
                    break;
                case 50:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_50_plantmoney));
                    break;
                case 51:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_51_chartgrow));
                    break;
                case 52:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_52_print));
                    break;
                case 53:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_53_phonecard));
                    break;
                case 54:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_54_webcode));
                    break;
                case 55:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_55_pencilruler));
                    break;
                case 56:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_56_pencilnote));
                    break;
                case 57:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_57_slidegrow));
                    break;
                case 58:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_58_pencilbook));
                    break;
                case 59:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_59_pencilpaper));
                    break;
                case 60:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_60_globe));
                    break;
                case 61:
                    ((NextOrLatestClassHolder)holder).classIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_class_61_shopping));
                    break;
            }
        }
        else
        {
            Log.d("date", ((NextOrLatestClassDateHolder)holder).day.getText().toString());
            ((NextOrLatestClassDateHolder)holder).day.setText(item.getDate());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(lists.get(position).getDate().isEmpty()){
            return 0;
        }
        else
        {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class NextOrLatestClassDateHolder extends RecyclerView.ViewHolder {
        TextView day;
        public NextOrLatestClassDateHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.tv_next_or_latest_class_date);
        }
    }

    public class NextOrLatestClassHolder extends RecyclerView.ViewHolder {
        public ImageView classIcon;
        public TextView classTopic, session, room;
        public NextOrLatestClassHolder(@NonNull View itemView) {
            super(itemView);
            classIcon = itemView.findViewById(R.id.next_or_latest_class_icon);
            classTopic = itemView.findViewById(R.id.tv_next_or_latest_class_topic);
            session = itemView.findViewById(R.id.tv_next_or_latest_class_session);
            room = itemView.findViewById(R.id.tv_next_or_latest_class_room);
        }
    }

}
