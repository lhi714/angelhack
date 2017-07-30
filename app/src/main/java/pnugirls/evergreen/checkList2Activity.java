package pnugirls.evergreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;

/**
 * Created by doohee on 2017-07-30.
 */

public class checkList2Activity extends Activity {
    public static boolean ALG = false, AN = false, FT = false,
            CTP = false, DRR = false, BRTH = false, FOOT = false,
            SK = false, EYE = false, HANG = false, COLD = false, SMOK = false;

    public static Activity checkList2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist2);

        ImageButton nextButton = (ImageButton) findViewById(R.id.nextButton2);
        ImageButton previousButton = (ImageButton) findViewById(R.id.previousButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(checkList2Activity.this, checkList2Activity.class));
            }
        });
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(checkList2Activity.this, checkList1Activity.class));
            }
        });

        checkList1Activity checkList1 = (checkList1Activity) checkList1Activity.checkList1;
        checkList1.finish();

        checkList2 = checkList2Activity.this;
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox1:
                if (checked)
                    ALG = true;
                else
                    ALG = false;
                    break;
            case R.id.checkbox2:
                if (checked)
                    AN = true;
                else
                    AN = false;
                    break;
            case R.id.checkbox3:
                if (checked)
                    FT = true;
                else
                    FT = false;
                break;
            case R.id.checkbox4:
                if (checked)
                    CTP = true;
                else
                    CTP = false;
                break;
            case R.id.checkbox5:
                if (checked)
                    DRR = true;
                else
                    DRR = false;
                break;
            case R.id.checkbox6:
                if (checked)
                    BRTH = true;
                else
                    BRTH = false;
                break;
            case R.id.checkbox7:
                if (checked)
                    FOOT = true;
                else
                    FOOT = false;
                break;
            case R.id.checkbox8:
                if (checked)
                    SK = true;
                else
                    SK = false;
                break;
            case R.id.checkbox9:
                if (checked)
                    EYE = true;
                else
                    EYE = false;
                break;
            case R.id.checkbox10:
                if (checked)
                    HANG = true;
                else
                    HANG = false;
                break;
            case R.id.checkbox11:
                if (checked)
                    COLD = true;
                else
                    COLD = false;
                break;
            case R.id.checkbox12:
                if (checked)
                    SMOK = true;
                else
                    SMOK = false;
                break;
        }
    }
}
