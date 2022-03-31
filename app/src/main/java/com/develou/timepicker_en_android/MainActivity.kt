package com.develou.timepicker_en_android

import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalTime

class MainActivity : AppCompatActivity() {
    private lateinit var startTime: TextView
    private lateinit var endTime: TextView
    private lateinit var duration: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        duration = findViewById(R.id.duration)

        setUpStartTime()

        setUpEndTime()

        updateDuration()
    }

    private fun setUpStartTime() {
        startTime = findViewById(R.id.from_time)
        startTime.text = LocalTime.now().toTimeText()
        startTime.setOnClickListener {
            showStartTimePicker()
        }
    }

    private fun showStartTimePicker() {
        val time = startTime.getTime()
        showDialog(time.hour, time.minute) { _, hour, minute ->
            val currentTime = LocalTime.of(hour, minute)
            if (isValidStartTime(currentTime)) {
                startTime.setTime(currentTime)
                updateDuration()
            }
        }
    }

    private fun showDialog(initialHour: Int, initialMinute: Int, observer: OnTimeSetListener) {
        TimePickerFragment.newInstance(initialHour, initialMinute, observer)
            .show(supportFragmentManager, "time-picker")
    }

    private fun isValidStartTime(time: LocalTime): Boolean {
        return time < endTime.getTime()
    }

    private fun updateDuration() {
        val start = startTime.getTime()
        val end = endTime.getTime()

        val hours = start hoursBetween end

        duration.text = "%.1f Horas".format(hours)
    }

    private fun setUpEndTime() {
        endTime = findViewById(R.id.to_time)
        endTime.text = LocalTime.now().plusHours(2).toTimeText()
        endTime.setOnClickListener {
            showEndTimePicker()
        }
    }

    private fun showEndTimePicker() {
        val time = endTime.getTime()
        showDialog(
            time.hour,
            time.minute
        ) { _, hour, minute ->
            val currentTime = LocalTime.of(hour, minute)

            if (isValidEndTime(currentTime)) {
                endTime.setTime(currentTime)
                updateDuration()
            }
        }
    }

    private fun isValidEndTime(time: LocalTime): Boolean {
        return time > startTime.getTime()
    }
}
