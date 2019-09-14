package com.github.islamkhsh.movie_app.ui.fragments.fav

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.movie_app.R
import kotlin.math.max
import kotlin.math.min


class SwipeController(val context: Context, val deleteAction: (Int) -> Unit) : Callback() {

    private var swipeBack = false

    private var buttonShowedState = ButtonsState.GONE

    private var buttonInstance: RectF? = null

    private var currentItemViewHolder: RecyclerView.ViewHolder? = null

    private var buttonWidth = 0f

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        buttonWidth = viewHolder.itemView.height.toFloat()
        return makeMovementFlags(0, START)
    }

    override fun onMove(
        recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ) = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        if (swipeBack) {
            swipeBack = buttonShowedState != ButtonsState.GONE
            return 0
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        var dX1 = dX
        if (actionState == ACTION_STATE_SWIPE) {
            if (buttonShowedState != ButtonsState.GONE) {

                dX1 = if (context.resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL)
                    max(dX1, buttonWidth)
                else
                    min(dX1, -buttonWidth)

                super.onChildDraw(c, recyclerView, viewHolder, dX1, dY, actionState, isCurrentlyActive)
            } else
                setTouchListener(c, recyclerView, viewHolder, dX1, dY, actionState, isCurrentlyActive)

        }

        if (buttonShowedState == ButtonsState.GONE)
            super.onChildDraw(c, recyclerView, viewHolder, dX1, dY, actionState, isCurrentlyActive)

        currentItemViewHolder = viewHolder
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchListener(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        recyclerView.setOnTouchListener { _, event ->
            swipeBack = event.action == MotionEvent.ACTION_CANCEL || event.action == MotionEvent.ACTION_UP
            if (swipeBack) {

                if (context.resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL
                    && dX > buttonWidth
                )
                    buttonShowedState = ButtonsState.END_VISIBLE
                else if (dX < -buttonWidth)
                    buttonShowedState = ButtonsState.END_VISIBLE

                if (buttonShowedState != ButtonsState.GONE) {
                    setTouchDownListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    setItemsClickable(recyclerView, false)
                }
            }
            false
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchDownListener(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        recyclerView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                setTouchUpListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
            false
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchUpListener(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        recyclerView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                super@SwipeController.onChildDraw(c, recyclerView, viewHolder, 0f, dY, actionState, isCurrentlyActive)
                recyclerView.setOnTouchListener { _, _ -> false }
                setItemsClickable(recyclerView, true)
                swipeBack = false

                if (buttonInstance != null && buttonInstance!!.contains(event.x, event.y)) {

                    if (buttonShowedState == ButtonsState.END_VISIBLE)
                        deleteAction(viewHolder.adapterPosition)
                }
                buttonShowedState = ButtonsState.GONE
                currentItemViewHolder = null
            }
            false
        }
    }

    private fun setItemsClickable(recyclerView: RecyclerView, isClickable: Boolean) {

        for (i in 0 until recyclerView.childCount) {
            recyclerView.getChildAt(i).isClickable = isClickable
        }
    }

    private fun drawButtons(c: Canvas, viewHolder: RecyclerView.ViewHolder) {

        val buttonWidthWithoutPadding = buttonWidth - context.resources.getDimensionPixelOffset(R.dimen.space_12)
        val corners = context.resources.getDimensionPixelOffset(R.dimen.cardView_corner_radius)

        val itemView = viewHolder.itemView
        val p = Paint()


        val button =
            if (context.resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL) {

                RectF(
                    itemView.left.toFloat(),
                    itemView.top.toFloat(),
                    itemView.left + buttonWidthWithoutPadding,
                    itemView.bottom.toFloat()
                )

            } else RectF(
                itemView.right - buttonWidthWithoutPadding,
                itemView.top.toFloat(),
                itemView.right.toFloat(),
                itemView.bottom.toFloat()
            )

        p.color = ContextCompat.getColor(context, R.color.colorAccent)
        c.drawRoundRect(button, corners.toFloat(), corners.toFloat(), p)

        drawImage(button, c, R.drawable.ic_delete)

        buttonInstance = null
        if (buttonShowedState == ButtonsState.END_VISIBLE)
            buttonInstance = button

    }

    private fun drawImage(button: RectF, c: Canvas, @DrawableRes imgSrc: Int) {

        val drawable = ContextCompat.getDrawable(context,imgSrc)

        val bitmap = Bitmap.createBitmap(
            drawable!!.intrinsicWidth,
            drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )

//        bitmap = bitmap.scale((button.width() * 0.4).toInt(),
//            (button.width() * 0.4).toInt() * bitmap.height/bitmap.width)

        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        c.drawBitmap(
            bitmap, button.left + ((button.width() - bitmap.width) / 2),
            button.top + ((button.height() - bitmap.height) / 2), null
        )
    }

    private fun drawText(text: String, c: Canvas, button: RectF, p: Paint) {
        val textSize = 60f
        p.color = Color.WHITE
        p.isAntiAlias = true
        p.textSize = textSize

        val textWidth = p.measureText(text)
        c.drawText(text, button.centerX() - textWidth / 2, button.centerY() + textSize / 2, p)
    }

    fun onDraw(c: Canvas) {
        if (currentItemViewHolder != null)
            drawButtons(c, currentItemViewHolder!!)
    }


    internal enum class ButtonsState { GONE, END_VISIBLE }
}
