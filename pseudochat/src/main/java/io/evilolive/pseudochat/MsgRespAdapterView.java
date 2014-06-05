package io.evilolive.pseudochat;

import android.content.Context;
import android.database.DataSetObserver;
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
//                addAndMeasureChild(newBottomChild);
                bottomEdge += newBottomChild.getMeasuredHeight();
                position++;
            }
        }

//        positionItems();
    }
}
