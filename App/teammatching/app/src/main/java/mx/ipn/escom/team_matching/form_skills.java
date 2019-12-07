package mx.ipn.escom.team_matching;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class form_skills extends AppCompatActivity {
    Button nextButton;
    LinearLayout linearMain;
    String totalchecks;
    Boolean[] isOn;
    String cadena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_skills);

        nextButton = findViewById(R.id.skills_next_button);
        linearMain = findViewById(R.id.linearMain);
        totalchecks = new String();

        Intent prev = getIntent();
        String sergioUrl = "http://10.100.66.182:8000/getOptions?";
        sergioUrl += prev.getStringExtra("groupid");

        AsyncUrlAccess getData = new AsyncUrlAccess(sergioUrl);
        try {
            cadena = getData.execute(sergioUrl).get().replace("_"," ");
        } catch (ExecutionException e) {
            e.printStackTrace();
            cadena = "op1-op2-op3";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final List<String> checks = Arrays.asList(cadena.split(","));
        //checks.add("Opcion1");
        //checks.add("sup2");
        //checks.add("sup3");
        isOn = new Boolean[checks.size()];
        Arrays.fill(isOn, Boolean.FALSE);

        for(int i = 0; i < checks.size(); i++) {
            CheckBox ch = new CheckBox(this);
            ch.setTextAppearance(this, R.style.checkbox_pink);
            ch.setButtonTintList(ColorStateList.valueOf(getColor(R.color.colorPrimaryDark)));
            ch.setId(i);
            ch.setText(checks.get(i));
            ch.setOnClickListener(getOnClickDoSomething(ch));
            linearMain.addView(ch);
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getIntent());
                i.setComponent(new ComponentName(form_skills.this, form_personality.class));
                for(int j=0; j<checks.size(); j++){
                    if(isOn[j])
                        totalchecks += j + ",";
                }
                i.putExtra("skills",totalchecks.substring(0,totalchecks.length()-1));
                startActivity(i);
            }
        });
    }

    View.OnClickListener getOnClickDoSomething(final Button button) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                isOn[button.getId()] = true;
                //totalchecks += Integer.toString(button.getId()) + ",";
                //System.out.println("*************id******" + button.getId());
                //System.out.println("and text***" + button.getText().toString());
            }
        };
    }
}
