/*
 * Copyright (c) 2016. André Mion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.emreatak.android.RadRoid.activities;

import android.os.Bundle;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.andremion.music.MusicCoverView;
import com.emreatak.android.RadRoid.R;
import com.emreatak.android.RadRoid.music.MusicContent;
import com.emreatak.android.RadRoid.view.TransitionAdapter;

import co.mobiwise.library.RadioListener;
import co.mobiwise.library.RadioManager;

public class DetailActivity extends PlayerActivity implements RadioListener {

    private MusicCoverView mCoverView;
    private ImageView forward,rewind;

    private final String[] RADIO_URL = {"http://dinle.romantikses.com:5959/listen.pls", "http://46.20.4.61/listen.pls"};

    RadioManager mRadioManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_detail);

        mRadioManager = RadioManager.with(getApplicationContext());
        mRadioManager.registerListener(this);
        mRadioManager.setLogging(true);

        forward = (ImageView) findViewById(R.id.forward);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicContent.radioposition++;

                mRadioManager.stopRadio();
                pause();

                play();
                mRadioManager.startRadio(RADIO_URL[MusicContent.radioposition]);
            }
        });

        /*
        //TODO radiopositionu kontrol et!!!
         */


        rewind = (ImageView) findViewById(R.id.rewind);
        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicContent.radioposition--;

                mRadioManager.stopRadio();
                pause();

                play();
                mRadioManager.startRadio(RADIO_URL[MusicContent.radioposition]);
            }
        });

        mCoverView = (MusicCoverView) findViewById(R.id.cover);
        mCoverView.setCallbacks(new MusicCoverView.Callbacks() {
            @Override
            public void onMorphEnd(MusicCoverView coverView) {
                // Nothing to do
            }

            @Override
            public void onRotateEnd(MusicCoverView coverView) {
                supportFinishAfterTransition();
            }
        });

        getWindow().getSharedElementEnterTransition().addListener(new TransitionAdapter() {
            @Override
            public void onTransitionEnd(Transition transition) {


                if (!mRadioManager.isPlaying()){

                    play();
                    mCoverView.start();
                    mRadioManager.startRadio(RADIO_URL[MusicContent.radioposition]);
                }
                else
                    mRadioManager.stopRadio();

            }
        });
    }


    @Override
    public void onBackPressed() {
        onFabClick(null);
    }

    public void onFabClick(View view) {


        if (!mRadioManager.isPlaying()){
            mRadioManager.startRadio(RADIO_URL[MusicContent.radioposition]);
        }
        else
        {
            pause();
            mCoverView.stop();
            mRadioManager.stopRadio();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mRadioManager.connect();
    }

    public void onRadioLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //TODO Do UI works here.
                //mTextViewControl.setText("RADIO STATE : LOADING...");
            }
        });
    }

    @Override
    public void onRadioConnected() {

    }

    @Override
    public void onRadioStarted() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //TODO Do UI works here.
                //mTextViewControl.setText("RADIO STATE : PLAYING...");
            }
        });
    }

    @Override
    public void onRadioStopped() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //TODO Do UI works here
                //mTextViewControl.setText("RADIO STATE : STOPPED.");
            }
        });
    }

    @Override
    public void onMetaDataReceived(String s, String s1) {
        //TODO Check metadata values. Singer name, song name or whatever you have.


    }

    public void onError() {

    }


}
