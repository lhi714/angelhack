package pnugirls.evergreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout btn_makejuice = (LinearLayout) findViewById(R.id.btn_makejuice);
        btn_makejuice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(),checkList1Activity.class);
                startActivity(intent);
            }
        });
    } //end onCreate()

} //end MainActivity
