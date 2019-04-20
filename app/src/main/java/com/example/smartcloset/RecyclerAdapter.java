package com.example.smartcloset;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>{
    private ArrayList<Data> listData = new ArrayList<>();
    private Context context;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    private int prePosition = -1;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position){
        holder.onBind(listData.get(position), position);
    }

    @Override
    public int getItemCount(){
        return listData.size();
    }

    void additem(Data data){
        listData.add(data);
    }


    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
        private ImageView imageView;
        private Data data;
        private int position;

        ItemViewHolder(View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            imageView = itemView.findViewById(R.id.imageView);
        }

        void onBind(Data data, int position) {
            this.data = data;
            this.position = position;

            textView1.setText(data.getTitle());
            textView2.setText(data.getContent());
            textView3.setText(data.getinf());
            imageView.setImageResource(data.getResId());

            changeVisibility(selectedItems.get(position));

            itemView.setOnClickListener(this);
            textView1.setOnClickListener(this);
            textView2.setOnClickListener(this);
            textView3.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.linearItem:
                    if(data.getResId() == R.drawable.sad){
                        Toast.makeText(context, "옷 종류 : " + data.getTitle() + "\n재질 : " + data.getContent() + "\n상태 : 위험" , Toast.LENGTH_SHORT).show();
                    } else if(data.getResId() == R.drawable.surprised){
                        Toast.makeText(context, "옷 종류 : " + data.getTitle() + "\n재질 : " + data.getContent() + "\n상태 : 주의" , Toast.LENGTH_SHORT).show();
                    } else if(data.getResId() == R.drawable.happy){
                        Toast.makeText(context, "옷 종류 : " + data.getTitle() + "\n재질 : " + data.getContent() + "\n상태 : 양호" , Toast.LENGTH_SHORT).show();
                    }

                    if(selectedItems.get(position)){
                        selectedItems.delete(position);
                    }else {
                        selectedItems.delete(prePosition);
                        selectedItems.put(position, true);
                    }

                    if(prePosition != -1) notifyItemChanged(prePosition);
                    notifyItemChanged(position);

                    prePosition = position;
                    break;
                case R.id.textView1:
                    Toast.makeText(context, data.getTitle(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.textView2:
                    Toast.makeText(context, data.getContent(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }


        private void changeVisibility(final boolean IsExpected){
            int dpvalue = 100;
            float d = context.getResources().getDisplayMetrics().density;
            int height = (int) (dpvalue * d);

            ValueAnimator va = IsExpected ? ValueAnimator.ofInt(0, height) : ValueAnimator.ofInt(height, 0);
            va.setDuration(600);
            va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int value = (int) animation.getAnimatedValue();

                    textView3.getLayoutParams().height = value;
                    textView3.requestLayout();
                    textView3.setVisibility(IsExpected ? View.VISIBLE : View.GONE);


                }
            });

            va.start();

        }



    }
}
