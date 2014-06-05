package io.evilolive.pseudochat;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;

/**
 * Created by Jonathan Almeida on 11/03/14.
 */
public class MsgRespAdapterView extends AdapterView {
    private Adapter adapter;

    public MsgRespAdapterView(Context context) {
        super(context);
    }

    @Override
    public Adapter getAdapter() {
        return this.adapter;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public View getSelectedView() {
        return null;
    }

    @Override
    public void setSelection(int i) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (adapter == null) {
            return;
        }

        if (getChildCount() == 0) {
            int position = 0;
            int bottomEdge = 0;
            while (bottomEdge < getHeight() && position < adapter.getCount()) {
                View newBottomChild = adapter.getView(position, null, this);
                addAndMeasureChild(newBottomChild);
                bottomEdge += newBottomChild.getMeasuredHeight();
                position++;
            }
        }

        positionItems();
    }

    private void addAndMeasureChild(View child) {
        LayoutParams params = child.getLayoutParams();
        if (params == null) {
            params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        }

        addViewInLayout(child, -1, params, true);

        int itemWidth = getWidth();
        child.measure(MeasureSpec.EXACTLY | itemWidth, MeasureSpec.UNSPECIFIED);
    }

    private void positionItems() {
        int top = 0;

        for(int index = 0; index< getChildCount(); index++) {
            View child = getChildAt(index);

            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            int left = (getWidth() - width) / 2;

            child.layout(left, top, left + top, top + height);
            top += height;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getChildCount() == 0) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchStartY = (int)event.getY();
                mListTopStart = getChildAt(0).getTop();
                break;

            case MotionEvent.ACTION_MOVE:
                int scrolledDistance = (int)event.getY() - mTouchStartY;
                mListTop = mListTopStart + scrolledDistance;
                break;
            default:
                break;
        }
        return true;
    }
}
