package pnugirls.evergreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddPopupWindow extends Activity {
    private Button btnclose, btnadd;
    public int cnt=0;
    public ArrayList<CustomListItem> clitems;
    public CustomListItem selitems;
    public CustomListActivity customListActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_popup_window);

        FirebaseDatabase database = FirebaseDatabase.getInstance(Config.FIREBASE_URL);
        DatabaseReference databaseReference = database.getReference("Vegi");

        customListActivity = new CustomListActivity();

        btnadd=(Button)findViewById(R.id.btn_add_popup);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cnt>0) {
                    Intent intent = getIntent();
                    intent.putExtra("Select_Name", selitems.getName());
                    intent.putExtra("Select_Content", selitems.getContents());
                    intent.putExtra("Select_Icon", selitems.getIcon());
                    setResult(RESULT_OK, intent);
                    cnt=0;
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please select item",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnclose= (Button)findViewById(R.id.btn_close_popup);
        btnclose.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 finish();
             }
        });

        clitems = new ArrayList<CustomListItem>();
        selitems = new CustomListItem();
        final CustomListAdapter adapter=new CustomListAdapter(this, clitems);

        final ListView listview =(ListView)findViewById(R.id.poplistview);
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listview.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot messageData : dataSnapshot.getChildren()){
                    CustomListItem clitems1 = new CustomListItem();
                    clitems1.setIcon(R.drawable.background_login);
                    clitems1.setName(messageData.getKey().toString());
                    clitems1.setContents(messageData.getValue().toString());
                    clitems.add(clitems1);
                }

                adapter.notifyDataSetChanged();
                listview.setSelection(adapter.getCount()-1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    selitems=clitems.get(i);

                    cnt++;
                }
        });


    }

}
