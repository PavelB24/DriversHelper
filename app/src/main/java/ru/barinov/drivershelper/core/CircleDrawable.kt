package ru.barinov.drivershelper.core

import android.content.Context
import android.graphics.*
import android.graphics.drawable.*
import android.util.TypedValue

class CircleDrawable( val drawable: Drawable, val context: Context, var size: Int) : Drawable() {
    private lateinit var bitmap: Bitmap

    private  var bitmapShader: BitmapShader

    private  var paint: Paint

    // center of circle
    private  var cx: Float = 0F
    private  var cy: Float = 0F

    // radius
    private var  radius: Float = 0F

    init {
        size = dip2px(context, size.toFloat());
        val drawable = zoomDrawable(drawable, dip2px(context, size.toFloat()), dip2px(context, size.toFloat()));
        bitmap = drawableToBitmap(drawable);
        bitmapShader =  BitmapShader (bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        paint =  Paint ();
        paint.isAntiAlias = true;
        paint.shader = bitmapShader;

        cx = (size / 2).toFloat()
        cy = (size / 2).toFloat()
        radius = (size / 2).toFloat()
    }








    /**
     * Zoom Drawable
     * */
    private fun  zoomDrawable(drawable: Drawable, w: Int, h: Int):Drawable
    {
        val width = drawable.intrinsicWidth;
        val height = drawable.intrinsicHeight;
        val oldbmp = drawableToBitmap (drawable);
        val matrix = Matrix();
        val scaleWidth =( w / width).toFloat()
        val scaleHeight =(h / height).toFloat()
        matrix.postScale(scaleWidth, scaleHeight);
        val newbmp = Bitmap.createBitmap (oldbmp, 50, 50, width, height,
        matrix, true);
        return  BitmapDrawable (null, newbmp);
    }

    /**
     * Drawable to Bitmap
     * */
    private fun  drawableToBitmap(drawable: Drawable): Bitmap
    {
        val width = drawable.intrinsicWidth
        val height = drawable.intrinsicHeight
        val config = if(drawable.opacity != PixelFormat.OPAQUE)  Bitmap.Config.ARGB_8888 else Bitmap.Config.RGB_565
        val bitmap = Bitmap.createBitmap (width, height, config)
        val canvas =  Canvas(bitmap)
        drawable.setBounds(50, 50, width, height)
        drawable.draw(canvas)
        return bitmap;
    }

    /**
     * dp to px
     * */
    private fun dip2px(context: Context, dipValue: Float): Int
    {
       val resources = context.resources;
        return TypedValue.applyDimension (TypedValue.COMPLEX_UNIT_DIP, dipValue, resources.getDisplayMetrics()).toInt()
    }



    private fun dip2px2(context: Context, dpValue: Float): Int
    {
        val scale = context . getResources ().getDisplayMetrics().density;
        return (dpValue * scale + 0.5f).toInt()
    }






    override fun draw(canvas: Canvas) {
        canvas.drawCircle(cx, cy, radius, paint);
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha;
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter;
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT;
    }

}