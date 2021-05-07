package com.sagitest.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer


class MainActivity : AppCompatActivity() {

    //use var coz the value will be given later
    //all 'var has ?= null' at the end to tell system they can be null
    private var chronometer : Chronometer ?= null
    private var pauseOffset : Long ?= null
    private var running : Boolean ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //connecting chronometer var to chronometer component in Layout
        chronometer = findViewById(R.id.meter)

        //SystemClock.elapsedRealtime() is the time the app has been active for after onCreate
        val cb = chronometer?.base
        pauseOffset = SystemClock.elapsedRealtime() - cb!!
    }

    //if we dont use pauseOffset then the internal timer of chronometer will keep running.
    //so what happens is, even if we stop chronometer, i will only be stopped on display.
    //But internally it will still be running.Thats why we change the timer value everytime.

    //usually in jave we use .getBase but here we are using .base coz
    //Kotlin dont want us to specify getter and putter
    //Everytime we use chronometer, we write chronormeter?. instead of chronometer. coz
    //when creating chronometer, we said it can be null. so by putting chronometer?. we are saying
    //do this take only if its not null.


    fun onStart(view: View) {
        if (running != true) {
            chronometer?.base = SystemClock.elapsedRealtime() - pauseOffset!!
            chronometer?.start()
            running = true
        }
    }

    //here we use !! because we always knew pauseOffset will not be null.
    //it considers the varible can be null coz we said it can be null when creating them.

    fun onPause(view: View) {
        if (running == true) {
            chronometer?.stop()

            val cb = chronometer?.base
            pauseOffset = SystemClock.elapsedRealtime() - cb!!

            running = false
        }
    }

    //reseting the values to initial ones.
    fun onReset(view: View) {
        chronometer?.base = SystemClock.elapsedRealtime()
        pauseOffset = 0
    }
}

//can be used in Layout if chronometer is not possible

/*
<TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="0:00:00"
        android:textSize="60sp"
        android:textStyle="bold|italic"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.357" />
 */