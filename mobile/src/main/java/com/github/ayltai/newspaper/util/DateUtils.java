package com.github.ayltai.newspaper.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.github.ayltai.newspaper.R;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public final class DateUtils {
    //region Constants

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * DateUtils.SECOND_MILLIS;
    private static final int HOUR_MILLIS   = 60 * DateUtils.MINUTE_MILLIS;
    private static final int DAY_MILLIS    = 24 * DateUtils.HOUR_MILLIS;

    //endregion

    private DateUtils() {
    }

    @SuppressFBWarnings("ICAST_INT_CAST_TO_FLOAT_PASSED_TO_ROUND")
    @SuppressWarnings("checkstyle:magicnumber")
    @Nullable
    public static String toApproximateTime(@NonNull final Context context, final long time) {
        final long now = System.currentTimeMillis();

        if (time > now || time <= 0) return null;

        final long diff = now - time;

        if (diff < DateUtils.MINUTE_MILLIS) return context.getString(R.string.time_ago_now);
        if (diff < 2 * DateUtils.MINUTE_MILLIS) return context.getString(R.string.time_ago_minute);
        if (diff < 50 * DateUtils.MINUTE_MILLIS) return String.format(context.getString(R.string.time_ago_minutes), String.valueOf(Math.round(diff / (double)DateUtils.MINUTE_MILLIS)));
        if (diff < 90 * DateUtils.MINUTE_MILLIS) return context.getString(R.string.time_ago_hour);
        if (diff < 24 * DateUtils.HOUR_MILLIS) return String.format(context.getString(R.string.time_ago_hours), String.valueOf(Math.round(diff / (double)DateUtils.HOUR_MILLIS)));
        if (diff < 48 * DateUtils.HOUR_MILLIS) return context.getString(R.string.time_ago_day);

        return String.format(context.getString(R.string.time_ago_days), String.valueOf(Math.round(diff / (double)DateUtils.DAY_MILLIS)));
    }
}
