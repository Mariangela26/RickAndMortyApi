package com.example.rickandmortyapi.clases;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.rickandmortyapi.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements View.OnClickListener{

    private List<ListElement> mData;
    private LayoutInflater mInflater;
    private Context context;
    ImageView iconImage;
    private View.OnClickListener listener;


    public ListAdapter(List<ListElement> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_element, null);
        view.setOnClickListener(this);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        holder.binData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    public void setItems(List<ListElement> items){
        mData = items;
    }

    @Override
    public void onClick(View view) {

        if(listener!=null){
            listener.onClick( view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, species, status, gender;
        Bitmap urlImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImage);
            name = itemView.findViewById(R.id.txNameCard);
            species = itemView.findViewById(R.id.txSpeciesCard);
            status = itemView.findViewById(R.id.txStatusCard);
            gender = itemView.findViewById(R.id.txGenderCard);
        }

        void binData(final ListElement item){
            //setImage(item.getImage());
            name.setText(item.getName());
            species.setText(item.getSpecies());
            status.setText(item.getStatus());
            gender.setText(item.getGender());
        }
    }

    /*private void setImage(String urlImage) {

        ImageRequest imageRequest = new ImageRequest(urlImage, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                iconImage.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context.getApplicationContext(),  "error al cargar la imagen", Toast.LENGTH_LONG).show();
            }
        });
    }*/
}
