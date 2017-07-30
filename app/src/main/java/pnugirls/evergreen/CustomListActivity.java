package pnugirls.evergreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomListActivity extends AppCompatActivity {


    private Button btnadd, btnorder;
    private CustomListAdapter adapter;
    public ArrayList<CustomListItem> clitems;
    public int[] exm={0,0,1,0,0,0,0,1,1,0,1,0,1,0};
    public boolean calledAlready = false;
    ChooseIngredient chooseIngredient;
    private String[] Indigrent={"apple","canola","celery","kale","broccoli",
        "tomato","carrot","pineapple","pomegranate" ,"banana",
        "japanese plum","cucumber","houttuynia cordata","water parsely","lemon",
        "aloe","raspberry","persimmon","plum","parsely",
        "crown daisy","chive","tangerine","cabbage","cornus fruit"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_custom_list);

        FirebaseDatabase database = FirebaseDatabase.getInstance(Config.FIREBASE_URL);
        DatabaseReference databaseReference = database.getReference("Vegi");

        chooseIngredient=new ChooseIngredient();
        final ArrayList<Integer> result=chooseIngredient.GetIngrediChoose(exm);

        clitems = new ArrayList<CustomListItem>();
        btnadd = (Button)findViewById(R.id.btnadd);
        btnorder = (Button)findViewById(R.id.btnorder);



        adapter=new CustomListAdapter(this, clitems);
        final ListView listview =(ListView)findViewById(R.id.clistview);
        listview.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(int i=0; i<4; i++) {
                    for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                        if (Indigrent[result.get(i)]==messageData.getKey().toString()) {
                            CustomListItem clitems1 = new CustomListItem();
                            clitems1.setIcon(R.drawable.ic_launcher_round);
                            clitems1.setName(messageData.getKey().toString());
                            clitems1.setContents(messageData.getValue().toString());
                            clitems.add(clitems1);
                        }
                    }
                }
                    adapter.notifyDataSetChanged();
                    listview.setSelection(adapter.getCount() - 1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Delete "+clitems.get(i).getName(),Toast.LENGTH_SHORT).show();
                clitems.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

       btnadd.setOnClickListener(new View.OnClickListener() {
            @Override//
            public void onClick(View view) {
               Intent intent=new Intent(getApplicationContext(), AddPopupWindow.class);
               startActivityForResult(intent,0);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    CustomListItem clitems1 = new CustomListItem();
                    clitems1.setIcon(intent.getIntExtra("Select_Icon", 0));
                    clitems1.setName(intent.getStringExtra("Select_Name"));
                    clitems1.setContents(intent.getStringExtra("Select_Content"));
                    clitems.add(clitems1);
                    adapter.notifyDataSetChanged();
                }
               break;
        }
    }

}
