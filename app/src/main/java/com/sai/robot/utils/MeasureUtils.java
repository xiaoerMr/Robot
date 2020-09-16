package com.sai.robot.utils;

import android.view.View;

class MeasureUtils {

//        View.resolveSize()
//        View.getDefaultSize()

    public static int getMeasurement(int measureSpec, int contentSize) {
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(contentSize);

        int resultSize = 0;
        switch (specMode) {
            case View.MeasureSpec.UNSPECIFIED:
                resultSize = contentSize;
                break;
            case View.MeasureSpec.AT_MOST:
                resultSize = Math.min(contentSize, specSize);
                break;
            case View.MeasureSpec.EXACTLY:
                resultSize = specSize;
                break;
        }
        return resultSize;
    }
}
