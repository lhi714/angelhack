package pnugirls.evergreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user on 2017-07-29.
 */

public class CustomListAdapter extends BaseAdapter {
    public Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<CustomListItem> clitems;


    public CustomListAdapter(Context context, ArrayList<CustomListItem> clitems){
        this.context=context;
        this.clitems=clitems;

        this.layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return clitems.size();
    }

    @Override
    public Object getItem(int i) {
        return clitems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        System.out.println("getView");
        final int pos = i;
        if(view==null){
            view=layoutInflater.inflate(R.layout.customlist_item, viewGroup, false);
        }

        ImageView imageView =(ImageView)view.findViewById(R.id.clitemImage);
        imageView.setImageResource(clitems.get(pos).getIcon());

        TextView textName =(TextView)view.findViewById(R.id.clitemName);
        textName.setText(clitems.get(pos).getName());

        TextView textContents =(TextView)view.findViewById(R.id.clitemText);
        textContents.setText(clitems.get(pos).getContents());

        ImageButton buttonView=(ImageButton)view.findViewById(R.id.cancelImage);
        buttonView.setImageResource(R.drawable.trashbin);
        buttonView.setFocusable(false);



        return view;
    }


}
