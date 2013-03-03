package com.dmitrys.games.snakes.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import com.dmitrys.games.snakes.R;

/**
 * User: Administrator
 * Date: 03.03.13
 * Time: 15:45
 */
public class SplashScreen extends BaseActivity {

    private static final int STOPSPLASH = 0;
    private static final long SPLASHTIME = 10000; //Время показа Splash картинки 10 секунд
    private ImageView splash;
    private Handler splashHandler;

    private Thread splashThread;

    //creates a ViewSwitcher object, to switch between Views
    private ViewSwitcher viewSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);

        final SplashScreen splashScreen = this;

//        splashThread = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    synchronized (this) {
//                        wait(SPLASHTIME);
//                    }
//                } catch (InterruptedException e) {
//                }
//
//                finish();
//
//                Intent intent = new Intent();
//                intent.setClass(splashScreen, MainMenu.class);
//                startActivity(intent);
//                stop();
//            }
//        };

//        splashHandler = new Handler() { //создаем новый хэндлер
//            public void handleMessage(Message msg) {
//                switch (msg.what) {
//                    case STOPSPLASH:
//                        //убираем Splash картинку - меняем видимость
//                        // splash.setVisibility(View.GONE);
//                        finish();
//
//                        // Run next activity
//                        Intent intent = new Intent();
//                        intent.setClass(SplashScreen.this, MainMenu.class);
//                        startActivity(intent);
//                        break;
//                }
//                super.handleMessage(msg);
//            }
//        };
//
//        splash = (ImageView) findViewById(R.id.splash_image_view);
//        splashHandler.sendEmptyMessageDelayed(STOPSPLASH, SPLASHTIME);

        new LoadViewTask().execute();
    }

    private class LoadViewTask extends AsyncTask<Void, Integer, Void> {

        //A TextView object and a ProgressBar object
        private TextView tv_progress;
        private ProgressBar pb_progressBar;

        //Before running code in the separate thread
        @Override
        protected void onPreExecute() {
            //Initialize the ViewSwitcher object
            viewSwitcher = new ViewSwitcher(SplashScreen.this);
            /* Initialize the loading screen with data from the 'loadingscreen.xml' layout xml file.
             * Add the initialized View to the viewSwitcher.*/
            viewSwitcher.addView(ViewSwitcher.inflate(SplashScreen.this, R.layout.splash, null));

            //Initialize the TextView and ProgressBar instances - IMPORTANT: call findViewById() from viewSwitcher.
            tv_progress = (TextView) viewSwitcher.findViewById(R.id.progress);
            pb_progressBar = (ProgressBar) viewSwitcher.findViewById(R.id.progressbar);
            //Sets the maximum value of the progress bar to 100
            pb_progressBar.setMax(100);

            //Set ViewSwitcher instance as the current View.
            setContentView(viewSwitcher);
        }

        @Override
        protected Void doInBackground(Void... params) {
            /* This is just a code that delays the thread execution 4 times,
             * during 850 milliseconds and updates the current progress. This
             * is where the code that is going to be executed on a background
             * thread must be placed.
             */
            try {
                //Get the current thread's token
                synchronized (this) {
                    //Initialize an integer (that will act as a counter) to zero
                    int counter = 0;
                    //While the counter is smaller than four
                    while(counter <= 4) {
                        //Wait 850 milliseconds
                        this.wait(850);
                        //Increment the counter
                        counter++;
                        //Set the current progress.
                        //This value is going to be passed to the onProgressUpdate() method.
                        publishProgress(counter*25);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        //Update the TextView and the progress at progress bar
        @Override
        protected void onProgressUpdate(Integer... values) {
            //Update the progress at the UI if progress value is smaller than 100
            if (values[0] <= 100) {
                tv_progress.setText("Progress: " + Integer.toString(values[0]) + "%");
                pb_progressBar.setProgress(values[0]);
            }
        }

        //After executing the code in the thread
        @Override
        protected void onPostExecute(Void result) {
            /* Initialize the application's main interface from the 'main.xml' layout xml file.
             * Add the initialized View to the viewSwitcher.*/
//            viewSwitcher.addView(ViewSwitcher.inflate(SplashScreen.this, R.layout.main_menu, null));
            //Switch the Views
//            viewSwitcher.showNext();
            Intent intent = new Intent(SplashScreen.this, MainMenu.class);
            startActivity(intent);
            finish();
        }
    }

    //Override the default back key behavior
    @Override
    public void onBackPressed() {
        //Emulate the progressDialog.setCancelable(false) behavior
        //If the first view is being shown
        if (viewSwitcher.getDisplayedChild() == 0) {
            //Do nothing
            return;
        } else {
            //Finishes the current Activity
            super.onBackPressed();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent evt) {
//        if(evt.getAction() == MotionEvent.ACTION_DOWN) {
//            synchronized (splashThread) {
//                splashThread.notifyAll();
//            }
//        }
        return true;
    }
}
