package pnugirls.evergreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomListActivity extends AppCompatActivity {

    private 
    private Button btnadd;
    private CustomListAdapter adapter;
    public ArrayList<CustomListItem> clitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        clitems = new ArrayList<CustomListItem>();
        btnadd = (Button)findViewById(R.id.btnadd);

        for(int i=0; i<9; i++) {

            CustomListItem clitems1 = new CustomListItem();
            clitems1.setIcon(R.drawable.background_login);
            clitems1.setName("Salary");
            clitems1.setContents("hohohohohohohoohhohohohohoh");

            clitems.add(clitems1);
        }

        adapter=new CustomListAdapter(this, clitems);

        final ListView listview =(ListView)findViewById(R.id.clistview);
        listview.setAdapter(adapter);

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Delete"+clitems.get(i).getName(),Toast.LENGTH_SHORT).show();
                clitems.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(getApplicationContext(), AddPopupWindow.class);
               startActivityForResult(intent,0);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){

        super.onActivityResult(requestCode, resultCode, intent);
        switch(requestCode){
            case 0:
                if(resultCode == RESULT_OK){
                    ArrayList<CustomListItem> it= intent.getParcelableExtra("Select");
                    for(int i=0; i<it.size();i++){
                        clitems.add(it.get(i));
                        adapter.notifyDataSetChanged();
                    }
                }

        }

    }
    public void AddCustomArray(CustomListItem item){
        clitems.add(item);
    }
}
