/*
 * Copyright (C) 2015 The Android Open Source Project
 * modified
 * SPDX-License-Identifier: Apache-2.0 AND GPL-3.0-only
 */

package com.best.deskclock.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.Settings;

import com.best.deskclock.R;
import com.best.deskclock.Utils;
import com.best.deskclock.data.DataModel.AlarmVolumeButtonBehavior;
import com.best.deskclock.data.DataModel.CitySort;
import com.best.deskclock.data.DataModel.ClockStyle;

import java.util.TimeZone;

/**
 * All settings data is accessed via this model.
 */
final class SettingsModel {

    private final Context mContext;

    private final SharedPreferences mPrefs;

    /**
     * The model from which time data are fetched.
     */
    private final TimeModel mTimeModel;

    /**
     * The uri of the default ringtone to use for timers until the user explicitly chooses one.
     */
    private Uri mDefaultTimerRingtoneUri;

    /**
     * The uri of the default ringtone to use for alarms until the user explicitly chooses one.
     */
    private Uri mDefaultAlarmSettingsRingtoneUri;

    SettingsModel(Context context, SharedPreferences prefs, TimeModel timeModel) {
        mContext = context;
        mPrefs = prefs;
        mTimeModel = timeModel;

        // Set the user's default display seconds preference if one has not yet been chosen.
        SettingsDAO.setDefaultDisplayClockSeconds(mContext, prefs);
    }

    int getGlobalIntentId() {
        return SettingsDAO.getGlobalIntentId(mPrefs);
    }

    void updateGlobalIntentId() {
        SettingsDAO.updateGlobalIntentId(mPrefs);
    }

    CitySort getCitySort() {
        return SettingsDAO.getCitySort(mPrefs);
    }

    void toggleCitySort() {
        SettingsDAO.toggleCitySort(mPrefs);
    }

    TimeZone getHomeTimeZone() {
        return SettingsDAO.getHomeTimeZone(mContext, mPrefs, TimeZone.getDefault());
    }

    ClockStyle getClockStyle() {
        return SettingsDAO.getClockStyle(mContext, mPrefs);
    }

    String getTheme() {
        return SettingsDAO.getTheme(mPrefs);
    }

    String getAccentColor() {
        return SettingsDAO.getAccentColor(mPrefs);
    }

    String getDarkMode() {
        return SettingsDAO.getDarkMode(mPrefs);
    }

    public boolean isCardBackgroundDisplayed() {
        return SettingsDAO.isCardBackgroundDisplayed(mPrefs);
    }

    public boolean isCardBackgroundBorderDisplayed() {
        return SettingsDAO.isCardBackgroundBorderDisplayed(mPrefs);
    }

    public boolean isVibrationsEnabled() {
        return SettingsDAO.isVibrationsEnabled(mPrefs);
    }

    boolean getDisplayClockSeconds() {
        return SettingsDAO.getDisplayClockSeconds(mPrefs);
    }

    void setDisplayClockSeconds(boolean shouldDisplaySeconds) {
        SettingsDAO.setDisplayClockSeconds(mPrefs, shouldDisplaySeconds);
    }

    ClockStyle getScreensaverClockStyle() {
        return SettingsDAO.getScreensaverClockStyle(mContext, mPrefs);
    }

    public boolean getScreensaverClockDynamicColors() {
        return SettingsDAO.getScreensaverClockDynamicColors(mPrefs);
    }

    public int getPickerClockColor() {
        return SettingsDAO.getPickerClockColor(mPrefs);
    }

    public int getPickerDateColor() {
        return SettingsDAO.getPickerDateColor(mPrefs);
    }

    public int getPickerNextAlarmColor() {
        return SettingsDAO.getPickerNextAlarmColor(mPrefs);
    }

    public int getScreensaverBrightness() {
        return SettingsDAO.getScreensaverBrightness(mPrefs);
    }

    boolean getDisplayScreensaverClockSeconds() {
        return SettingsDAO.getDisplayScreensaverClockSeconds(mPrefs);
    }

    boolean getScreensaverBoldDigitalClock() {
        return SettingsDAO.getScreensaverBoldDigitalClock(mPrefs);
    }

    boolean getScreensaverItalicDigitalClock() {
        return SettingsDAO.getScreensaverItalicDigitalClock(mPrefs);
    }

    boolean getScreensaverBoldDate() {
        return SettingsDAO.getScreensaverBoldDate(mPrefs);
    }

    boolean getScreensaverItalicDate() {
        return SettingsDAO.getScreensaverItalicDate(mPrefs);
    }

    boolean getScreensaverBoldNextAlarm() {
        return SettingsDAO.getScreensaverBoldNextAlarm(mPrefs);
    }

    boolean getScreensaverItalicNextAlarm() {
        return SettingsDAO.getScreensaverItalicNextAlarm(mPrefs);
    }

    boolean getShowHomeClock() {
        if (!SettingsDAO.getAutoShowHomeClock(mPrefs)) {
            return false;
        }

        // Show the home clock if the current time and home time differ.
        // (By using UTC offset for this comparison the various DST rules are considered)
        final TimeZone defaultTZ = TimeZone.getDefault();
        final TimeZone homeTimeZone = SettingsDAO.getHomeTimeZone(mContext, mPrefs, defaultTZ);
        final long now = System.currentTimeMillis();
        return homeTimeZone.getOffset(now) != defaultTZ.getOffset(now);
    }

    Uri getDefaultTimerRingtoneUri() {
        if (mDefaultTimerRingtoneUri == null) {
            mDefaultTimerRingtoneUri = Utils.getResourceUri(mContext, R.raw.timer_expire);
        }

        return mDefaultTimerRingtoneUri;
    }

    Uri getTimerRingtoneUri() {
        return SettingsDAO.getTimerRingtoneUri(mPrefs, getDefaultTimerRingtoneUri());
    }

    void setTimerRingtoneUri(Uri uri) {
        SettingsDAO.setTimerRingtoneUri(mPrefs, uri);
    }

    boolean getTimerVibrate() {
        return SettingsDAO.getTimerVibrate(mPrefs);
    }

    void setTimerVibrate(boolean enabled) {
        SettingsDAO.setTimerVibrate(mPrefs, enabled);
    }

    int getDefaultTimeToAddToTimer() {
        return SettingsDAO.getDefaultTimeToAddToTimer(mPrefs);
    }

    boolean shouldTimerDisplayRemainOn() {
        return SettingsDAO.shouldTimerDisplayRemainOn(mPrefs);
    }

    AlarmVolumeButtonBehavior getAlarmVolumeButtonBehavior() {
        return SettingsDAO.getAlarmVolumeButtonBehavior(mPrefs);
    }

    AlarmVolumeButtonBehavior getAlarmPowerButtonBehavior() {
        return SettingsDAO.getAlarmPowerButtonBehavior(mPrefs);
    }

    int getAlarmTimeout() {
        return SettingsDAO.getAlarmTimeout(mPrefs);
    }

    int getSnoozeLength() {
        return SettingsDAO.getSnoozeLength(mPrefs);
    }

    int getFlipAction() {
        return SettingsDAO.getFlipAction(mPrefs);
    }

    int getShakeAction() {
        return SettingsDAO.getShakeAction(mPrefs);
    }

    int getAlarmNotificationReminderTime() {
        return SettingsDAO.getAlarmNotificationReminderTime(mPrefs);
    }

    boolean areAlarmVibrationsEnabledByDefault() {
        return SettingsDAO.areAlarmVibrationsEnabledByDefault(mPrefs);
    }

    String getMaterialTimePickerStyle() {
        return SettingsDAO.getMaterialTimePickerStyle(mPrefs);
    }

    Uri getDefaultAlarmRingtoneUriFromSettings() {
        if (mDefaultAlarmSettingsRingtoneUri == null) {
            mDefaultAlarmSettingsRingtoneUri = Settings.System.DEFAULT_ALARM_ALERT_URI;
        }
        return mDefaultAlarmSettingsRingtoneUri;
    }

    Uri getAlarmRingtoneUriFromSettings() {
        return SettingsDAO.getAlarmRingtoneUriFromSettings(mPrefs, getDefaultAlarmRingtoneUriFromSettings());
    }

    void setAlarmRingtoneUriFromSettings(Uri uri) {
        SettingsDAO.setAlarmRingtoneUriFromSettings(mPrefs, uri);
    }

    void setSelectedAlarmRingtoneUri(Uri uri) {
        SettingsDAO.setSelectedAlarmRingtoneUri(mPrefs, uri);
    }

    long getAlarmCrescendoDuration() {
        return SettingsDAO.getAlarmCrescendoDuration(mPrefs);
    }

    long getTimerCrescendoDuration() {
        return SettingsDAO.getTimerCrescendoDuration(mPrefs);
    }

    boolean isSwipeActionEnabled() {
        return SettingsDAO.isSwipeActionEnabled(mPrefs);
    }

    Weekdays.Order getWeekdayOrder() {
        return SettingsDAO.getWeekdayOrder(mPrefs);
    }

    boolean isRestoreBackupFinished() {
        return SettingsDAO.isRestoreBackupFinished(mPrefs);
    }

    void setRestoreBackupFinished(boolean finished) {
        SettingsDAO.setRestoreBackupFinished(mPrefs, finished);
    }

    TimeZones getTimeZones() {
        return SettingsDAO.getTimeZones(mContext, mTimeModel.currentTimeMillis());
    }

}
