package mx.ipn.escom.team_matching;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.Toast;

public class form_personality extends AppCompatActivity {
    ImageButton nextButton;
    WebView web16Personatilies;
    Boolean hasResult = false;
    String personality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_personality);

        nextButton = findViewById(R.id.personality_next_button);
        web16Personatilies = findViewById(R.id.web16Personalites);

        web16Personatilies.getSettings().setJavaScriptEnabled(true);
        web16Personatilies.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                System.out.println("when you click on any interlink on webview that time you got url :-" + url);
                StringBuilder sb = new StringBuilder();
                //https://www.16personalities.com/ru/lichnost-enfj
                int start = 44;
                for(int i=url.length()-4; i<url.length(); i++){
                    sb.append(url.charAt(i));
                }

                hasResult = true;
                personality = sb.toString().toUpperCase();

                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        web16Personatilies.loadUrl("https://www.16personalities.com/ru/test-lichnosti");


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getIntent());
                i.setComponent(new ComponentName(form_personality.this, form_send.class));
                if(hasResult){
                    Toast.makeText(form_personality.this, "Personalidad: " + personality, Toast.LENGTH_LONG).show();
                    i.putExtra("personality", personality);
                }else{
                    Toast.makeText(form_personality.this, "MODO TEST: Generando Resultado Aleatorio\nESTP", Toast.LENGTH_LONG).show();
                    i.putExtra("personality", "ESTP");
                }
                startActivity(i);
            }
        });
    }
}
