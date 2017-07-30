package pnugirls.evergreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

import static pnugirls.evergreen.R.id.nextButton1;

/**
 * Created by doohee on 2017-07-30.
 */

public class checkList1Activity extends Activity {
    private final int MALE = 1;
    private final int FEMALE = 2;

    private int gender = 1;

    public static Activity checkList1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist1);

        ImageButton nextButton = (ImageButton) findViewById(nextButton1);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(checkList1Activity.this, checkList2Activity.class));
            }
        });

        checkList1 = checkList1Activity.this;
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.male:
                if (checked)
                    gender = MALE;
                    break;
            case R.id.female:
                if (checked)
                    gender = FEMALE;
                    break;
        }
    }
}
