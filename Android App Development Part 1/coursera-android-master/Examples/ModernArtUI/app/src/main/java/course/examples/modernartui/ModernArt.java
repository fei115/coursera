package course.examples.modernartui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class ModernArt extends ActionBarActivity {

    private DialogFragment mDialog;
    private static final int ALERTTAG = 0;
    static private final String URL = "http://www.MOMA.org";
    static private final String CHOOSER_TEXT = "Load " + URL + " with:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_art);
        final SeekBar seekBar = (SeekBar) findViewById(R.id.seekbar1);
        final TextView R1 = (TextView) findViewById(R.id.R1);
        final TextView R2 = (TextView) findViewById(R.id.R2);
        final TextView R3 = (TextView) findViewById(R.id.R3);
        final TextView R4 = (TextView) findViewById(R.id.R4);
        seekBar.setMax(20);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int oldProgress= 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int change = progress - oldProgress;
                oldProgress = progress;
                ColorDrawable cd1 = (ColorDrawable) R1.getBackground();
                ColorDrawable cd2 = (ColorDrawable) R2.getBackground();
                ColorDrawable cd3 = (ColorDrawable) R3.getBackground();
                ColorDrawable cd4 = (ColorDrawable) R4.getBackground();
                int cc1 = cd1.getColor();
                int cc2 = cd2.getColor();
                int cc3 = cd3.getColor();
                int cc4 = cd4.getColor();

                R1.setBackgroundColor(cc1 - change * 5);
                R2.setBackgroundColor(cc2 - change * 6);
                R3.setBackgroundColor(cc3 - change * 7);
                R4.setBackgroundColor(cc4 - change * 8);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modern_art, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showDialogFragment(ALERTTAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void showDialogFragment(int dialogID) {
        mDialog = AlertDialogFragment.newInstance();
        mDialog.show(getFragmentManager(), "Alert");
    }

    // Class that creates the AlertDialog
    public static class AlertDialogFragment extends DialogFragment {

        public static AlertDialogFragment newInstance() {
            return new AlertDialogFragment();
        }

        // Build AlertDialog using AlertDialog.Builder
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.description)

                            // User cannot dismiss dialog by hitting back button
                    .setCancelable(false)

                            // Set up No Button
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {

                                }
                            })

                            // Set up Yes Button
                    .setPositiveButton("Visit MOMA",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        final DialogInterface dialog, int id) {

                                    Intent baseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));


                                    Intent chooserIntent = Intent.createChooser(baseIntent,CHOOSER_TEXT);

                                    startActivity(chooserIntent);
                                }
                            }).create();
        }
    }

}
