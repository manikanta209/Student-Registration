package com.example.studentregistration;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentregistration.database.MainData;
import com.example.studentregistration.database.RoomDB;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;

    //Create constructor
    public MainAdapter(Activity context, List<MainData> dataList){
        this.context=context;
        this.dataList=dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Initialize View
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Initialize main data
        MainData data=dataList.get(position);

        //Initialize database
        database =RoomDB.getInstance(context);

        //Set text on text view
        holder.textView.setText(data.getUserName());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize main data
                MainData mainData=dataList.get(holder.getAdapterPosition());
                //Get ID
                int sID=mainData.getAutoID();
                //Get name
                String sUsername=mainData.getUserName();

                //Create dialog
                Dialog dialog=new Dialog(context);
                //Set Content view
                dialog.setContentView(R.layout.dialog_update);
                //Initialize width
                int width= WindowManager.LayoutParams.MATCH_PARENT;
                int height=WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width,height);
                dialog.show();

                //Initialize and assign Variable
                EditText editText=dialog.findViewById(R.id.edUsername);
                Button btnUpdate=dialog.findViewById(R.id.btnUpdate);

                //SetText  for edit text
                editText.setText(sUsername);

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                        //Get updated text from editext
                        String username=editText.getText().toString().trim();
                        //Update text in database
                        database.mainDao().update(sID,username);

                        //Notify when data is updated
                        dataList.clear();
                        dataList.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();
                    }
                });

            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize main data
                MainData d=dataList.get(holder.getAdapterPosition());
                //Delete text from database
                database.mainDao().delete(d);
                //Notify when deleted
                int position=holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,dataList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView btnEdit,btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Assign variable
            textView=itemView.findViewById(R.id.textView);
            btnEdit=itemView.findViewById(R.id.btnEdit);
            btnDelete=itemView.findViewById(R.id.btnDelete);
        }
    }
}
