package com.login.address;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    private List<AddressModel> AddressList;
    static int id=0;
    static class ViewHolder extends RecyclerView.ViewHolder {
        View AddressView;
        TextView Name;
        TextView Phone;
        TextView Address;
        TextView type;
        TextView sqlite_id;
        TextView default_address_view;
        public ViewHolder(View view) {
            super(view);
            AddressView = view;
            Name = view.findViewById(R.id.Name);
            Phone = view.findViewById(R.id.Phone);
            Address=view.findViewById(R.id.address);
            type=view.findViewById(R.id.type);
            sqlite_id=view.findViewById(R.id.sqlite_id);
            default_address_view=view.findViewById(R.id.default_address_view);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.address_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.AddressView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Intent intent=new Intent(view.getContext(),updata_address.class);
                intent.putExtra("position",position);
                view.getContext().startActivity(intent);
            }
        });
        holder.AddressView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DatabaseHelper dbHelper=new DatabaseHelper(v.getContext());
                final SQLiteDatabase db = dbHelper.getWritableDatabase();
                int position = holder.getAdapterPosition();
                AddressModel addressModel = AddressList.get(position);
                db.execSQL("delete from Address where id='"+addressModel.getSqlite_id()+"'");
                AddressList.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
                Toast.makeText(v.getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                /*Cursor cursor=db.rawQuery("select * from Address limit '"+holder.getAdapterPosition()+"',1",null);
                while (cursor.moveToNext()){
                    id=cursor.getInt(cursor.getColumnIndex("id"));
                }
                AddressList.remove(holder.getAdapterPosition());
                db.execSQL("delete from Address where id='"+id+"'");
                notifyDataSetChanged();
                Toast.makeText(v.getContext(), "删除成功", Toast.LENGTH_SHORT).show();*/
                return true;
            }
        });
//        holder..setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int position = holder.getAdapterPosition();
//                Fruit fruit = AddressList.get(position);
//                Toast.makeText(view.getContext(), "你点击了图片"+ fruit.getName(), Toast.LENGTH_SHORT).show();
//            }
//        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AddressModel addressModel = AddressList.get(position);
        holder.Name.setText(addressModel.getName());
        holder.Phone.setText(addressModel.getPhone());
        holder.Address.setText(addressModel.getAddress());
        holder.type.setText(addressModel.getType());
        holder.sqlite_id.setText(addressModel.getSqlite_id());
        if(position==0){
            holder.default_address_view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return AddressList.size();
    }

    public AddressAdapter(List<AddressModel> fruitList) {
        AddressList = fruitList;
    }

}
