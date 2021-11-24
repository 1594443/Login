package com.example.login.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class CodeUtil {
    private static final char[] CHARS = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };
    private static CodeUtil bmpCode;

    public static CodeUtil getInstance() {
        if (bmpCode == null)
            bmpCode = new CodeUtil();
        return bmpCode;
    }

    //验证码的个数
    private static final int DEFAULT_CODE_LENGTH = 4;
    //默认字体的大小
    private static final int DEFAULT_FONT_SIZE = 25;
    //线条的个数
    private static final int DEFAULT_LINE_NUMBER = 4;
    //padding值
    private static final int BASE_PADDING_LEFT = 10, RANGE_PADDING_LEFT = 15;
    private static final int BASE_PADDING_TOP = 15, RANGE_PADDING_TOP = 20;
    //验证码的默认宽高
    private static final int DEFAULT_WIDTH = 100, DEFAULT_HEIGHT = 40;

    private int width = DEFAULT_WIDTH, height = DEFAULT_HEIGHT;

    private int base_padding_left = BASE_PADDING_LEFT, range_padding_lift = RANGE_PADDING_LEFT;
    private int base_padding_top = BASE_PADDING_TOP, range_padding_top = RANGE_PADDING_TOP;

    private int codeLength = DEFAULT_CODE_LENGTH, line_number = DEFAULT_LINE_NUMBER, font_size = DEFAULT_FONT_SIZE;

    private String code;
    private int padding_left, padding_top;
    private Random random = new Random();

    //验证图片
    public Bitmap createBitmap() {
        padding_left = 0;
        padding_top = 0;
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        code = createCode();

        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        //是否抗锯齿
        paint.setAntiAlias(true);
        paint.setTextSize(font_size);
        //画验证码
        for (int i = 0; i < code.length(); i++) {
            randomTextStyle(paint);
            randomPadding();
            canvas.drawText(code.charAt(i) + "", padding_left, padding_top, paint);
        }
        //画线条
        for (int i = 0; i < line_number; i++) {
            drawLine(canvas, paint);
        }
        //保存
        canvas.save();
        canvas.restore();
        return bitmap;
    }

    public String getCode() {
        return code;
    }

    //生成验证码
    private String createCode() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            buffer.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return buffer.toString();
    }

    //画干扰线
    private void drawLine(Canvas canvas, Paint paint) {
        int color = randomColor();
        int startX = random.nextInt(width);
        int startY = random.nextInt(height);
        int stopX = random.nextInt(width);
        int stopY = random.nextInt(height);
        paint.setStrokeWidth(1);
        paint.setColor(color);
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }

    //生成随机颜色
    private int randomColor() {
        return randomColor(1);
    }

    private int randomColor(int rate) {
        int red = random.nextInt(256) / rate;
        int green = random.nextInt(256) / rate;
        int blue = random.nextInt(256) / rate;
        return Color.rgb(red, green, blue);
    }

    //随机生成文字样式，颜色，粗细，倾斜度
    private void randomTextStyle(Paint paint) {
        int color = randomColor();
        paint.setColor(color);
        paint.setFakeBoldText(random.nextBoolean());  //true为粗体，false为非粗体
        float skewX = random.nextInt(11) / 10;
        skewX = random.nextBoolean() ? skewX : -skewX;  //
        paint.setTextSkewX(skewX); //float类型参数，负数表示右斜，整数左斜
    }

    //随机生成padding值
    private void randomPadding() {

        padding_left += base_padding_left + random.nextInt(range_padding_lift);
        padding_top = base_padding_top + random.nextInt(range_padding_top);
    }
}
