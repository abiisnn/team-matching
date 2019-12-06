package mx.ipn.escom.team_matching;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

public class form_basic_data extends AppCompatActivity {
    Button nextButton;
    TextView groupId;
    TextView username;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_basic_data);

        nextButton = findViewById(R.id.basicNextButton);
        groupId = findViewById(R.id.basic_input_group_id);
        username = findViewById(R.id.basic_input_username);
        name = findViewById(R.id.basic_input_name);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(form_basic_data.this, form_skills.class);
                i.putExtra("groupid", StringUtils.stripAccents(groupId.getText().toString().replaceAll("\\s+","")));
                i.putExtra("username", StringUtils.stripAccents(username.getText().toString().replaceAll("\\s+","")));
                i.putExtra("name", StringUtils.stripAccents(name.getText().toString().replaceAll("\\s+","")));
                startActivity(i);
            }
        });
    }
}
