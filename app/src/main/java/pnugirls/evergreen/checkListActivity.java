package pnugirls.evergreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by doohee on 2017-07-30.
 */

public class checkListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist1);

        Button b2 =(Button)findViewById(R.id.btnNExt);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(),checkListActivity2.class);
                startActivity(intent);
            }
        });
    } //end onCreate()


} //end MainActivity