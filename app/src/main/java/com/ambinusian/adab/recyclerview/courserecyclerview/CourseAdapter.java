package com.ambinusian.adab.recyclerview.courserecyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.ambinusian.adab.R;
import com.ambinusian.adab.ui.student.LiveSessionActivity;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseHolder> {

    private Context context;
    private ArrayList<CourseModel> list;

    public CourseAdapter(Context context, ArrayList<CourseModel> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_courses,parent,false);
        return new CourseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHolder holder, int position) {
        CourseModel item = list.get(position);
        holder.classTime.setText(item.getClassTime());
        holder.classTitle.setText(item.getClassTitle());
        holder.courses.setText(item.getCourse());
        holder.classMeeting.setText(item.getClassMeeting());

        //Chip
        holder.courseCode.setText(item.getCourseCode());
        holder.classCode.setText(item.getClassCode());
        holder.classType.setText(item.getClassType());

        int classIcon = item.getClassIcon();
        //set classIcon
        switch (classIcon){
            case 0:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_00_cube));
                break;
            case 1:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_01_laptopidea));
                break;
            case 2:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_02_cloudnet));
                break;
            case 3:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_03_lawprotect));
                break;
            case 4:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_04_picture));
                break;
            case 5:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_05_gearmail));
                break;
            case 6:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_06_boxmoney));
                break;
            case 7:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_07_slidepie));
                break;
            case 8:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_08_slidepencil));
                break;
            case 9:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_09_globe));
                break;
            case 10:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_10_awardmedal));
                break;
            case 11:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_10_awardmedal));
                break;
            case 12:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_12_privacychat));
                break;
            case 13:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_13_hammer));
                break;
            case 14:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_14_communication));
                break;
            case 15:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_15_databaseprotection));
                break;
            case 16:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_16_circuit));
                break;
            case 17:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_17_laptopprotection));
                break;
            case 18:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_18_trustperson));
                break;
            case 19:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_19_websecurity));
                break;
            case 20:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_20_laptoplocation));
                break;
            case 21:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_21_cloudmail));
                break;
            case 22:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_22_webplayer));
                break;
            case 23:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_23_cloudsetting));
                break;
            case 24:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_24_webdata));
                break;
            case 25:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_25_vectordrawing));
                break;
            case 26:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_26_geometrydrawing));
                break;
            case 27:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_27_document));
                break;
            case 28:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_28_computersetting));
                break;
            case 29:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_29_computerdrawing));
                break;
            case 30:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_30_boxlight));
                break;
            case 31:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_31_stationerybox));
                break;
            case 32:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_32_layer));
                break;
            case 33:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_33_stationery));
                break;
            case 34:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_34_searchdraw));
                break;
            case 35:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_35_compass));
                break;
            case 36:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_36_notepad));
                break;
            case 37:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_37_palettepaint));
                break;
            case 38:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_38_vectorgraphic));
                break;
            case 39:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_39_colorpalette));
                break;
            case 40:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_40_paintbrush));
                break;
            case 41:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_41_webconnect));
                break;
            case 42:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_42_organization));
                break;
            case 43:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_43_ideathinking));
                break;
            case 44:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_44_plantgrow));
                break;
            case 45:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_45_money));
                break;
            case 46:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_46_presentation));
                break;
            case 47:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_47_broadcast));
                break;
            case 48:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_48_gearweb));
                break;
            case 49:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_49_graph));
                break;
            case 50:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_50_plantmoney));
                break;
            case 51:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_51_chartgrow));
                break;
            case 52:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_52_print));
                break;
            case 53:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_53_phonecard));
                break;
            case 54:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_54_webcode));
                break;
            case 55:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_55_pencilruler));
                break;
            case 56:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_56_pencilnote));
                break;
            case 57:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_57_slidegrow));
                break;
            case 58:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_58_pencilbook));
                break;
            case 59:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_59_pencilpaper));
                break;
            case 60:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_60_globe));
                break;
            case 61:
                holder.classIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_class_61_shopping));
                break;
        }

        //set onclick listener
        holder.mainLinearLayout.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), LiveSessionActivity.class);
            int classId = item.getClassId();

            //set all data to bundle
            Bundle bundle = new Bundle();
            bundle.putInt("class_id", classId);

            //set bundle to the intent
            intent.putExtras(bundle);

            //go to LiveSessionActivity
            holder.itemView.getContext().startActivity(intent);
        });
    }
}
