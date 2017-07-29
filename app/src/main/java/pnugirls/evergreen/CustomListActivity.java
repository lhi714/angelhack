package pnugirls.evergreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;

public class CustomListActivity extends AppCompatActivity {

    private Button btnadd;
    private ArrayList<Integer> cint;
    private ArrayList<CustomListItem> clitems;
    public int canclecnt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        clitems = new ArrayList<CustomListItem>();
        cint = new ArrayList<Integer>();
        btnadd = (Button)findViewById(R.id.btnadd);

        for(int i=0; i<9; i++) {

            CustomListItem clitems1 = new CustomListItem();
            clitems1.setIcon(R.drawable.background_login);
            clitems1.setName("Salary");
            clitems1.setContents("hohohohohohohoohhohohohohoh");

            clitems.add(clitems1);
        }

        final CustomListAdapter adapter=new CustomListAdapter(this, clitems);

        final ListView listview =(ListView)findViewById(R.id.clistview);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                        if(adapter.
                            cint.add(index);
                            canclecnt++;
                        }
                        if(canclecnt!=0) btnadd.setText("Delete");
                    }
                }
        );

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnadd.getText()=="Add"){}
                else{
                    if(cint.size()>0){
                        for(int i=0; i<cint.size(); i++) {
                            clitems.remove(cint.get(i));
                            listview.clearChoices();
                            adapter.notifyDataSetChanged();
                        }
                        cint.clear();
                    }
                }
            }
        });


    }
}
