package cn.liuws.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @param <T> 传入List的元素类型
 * Created by LiuWs on 2016/2/12.
 */
public abstract class EasyRecyclerAdapter<T> extends RecyclerView.Adapter<EasyRecyclerAdapter.ViewHolder> {
    protected List<T> mList;
    /**
     * 对应的layout的xml，如R.layout.XXX
     */
    protected final int[] mItemLayoutIds;
    private LayoutInflater layoutInflater;

    /**
     * 构造方法
     *
     * @param mList          list数据
     * @param mItemLayoutIds 对应的layout的xml，如:R.layout.XXX。
     */
    public EasyRecyclerAdapter(List<T> mList, int... mItemLayoutIds) {
        this.mList = mList;
        this.mItemLayoutIds = mItemLayoutIds;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        View view = layoutInflater.inflate(mItemLayoutIds[viewType], parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        setDataForView((ViewHolder) holder, getItemViewType(position), mList.get(position), position);
    }

    /**
     * 设置convertView中子View的内容。<br>
     *
     * @param holder   ViewHolder，可以通过getViewById（R.id.XXX）的方式获取子View
     * @param viewType 参见{@link #getItemViewType(int, List)}
     * @param itemData List数据中对应项，用于给itemView中各个子View赋值
     * @param position the index of item.
     */
    public abstract void setDataForView(ViewHolder holder, int viewType, T itemData, int position);

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * itemView使用多种布局时，需覆盖（override）该方法。<br>
     * 多种itemView对应布局（R.layout.XXX)数组mItemLayoutIds[]，该方法返回布局数组下标值。
     * {@link #getItemViewType(int)}有相同效果
     *
     * @param position
     * @param mList
     * @return index of mItemLayoutIds[]
     */
    public int getItemViewType(int position, List<T> mList) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return getItemViewType(position, mList);
    }

    /**
     * Created by LiuWs on 2016/2/12.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected final SparseArray<View> mView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = new SparseArray<View>();
        }

        /**
         * 通过控件的Id获取对应的控件
         *
         * @param viewId 控件的Id。eg:R.id.XXX
         * @return view itemView中对应id的子View
         */
        public View getViewById(int viewId) {
            View view = mView.get(viewId);
            if (null == view) {
                view = itemView.findViewById(viewId);
                mView.put(viewId, view);
            }
            return view;
        }

        /**
         * 返回ItemView布局View
         *
         * @return itemView
         */
        public View getItemView() {
            return itemView;
        }
    }
}
