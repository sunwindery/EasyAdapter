package cn.liuws.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 通用的Adapter。
 *
 * @param <T> 传入List的元素类型
 * @author LiuWs
 */
public abstract class EasyAdapter<T> extends BaseAdapter {
    protected List<T> mList;
    /**
     * 对应的layout的xml，如R.layout.XXX
     */
    protected final int[] mItemLayoutIds;

    /**
     * @param mList          list数据
     * @param mItemLayoutIds 对应的layout的xml，如:R.layout.XXX。
     */
    public EasyAdapter(List<T> mList, int... mItemLayoutIds) {
        this.mList = mList;
        this.mItemLayoutIds = mItemLayoutIds;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder ViewHolder = getViewHolder(position, convertView, parent);
        setDataForView(ViewHolder, getItemViewType(position), mList.get(position), position);
        return ViewHolder.getItemView();

    }

    /**
     * 设置convertView中子View的内容。<br>
     *
     * @param holder   ViewHolder，可以通过getViewById（R.id.XXX）的方式获取子View
     * @param viewType 参见{@link #getItemViewType(int, List)}
     * @param itemData List数据中对应项，用于给convertView中各个子View赋值
     * @param position the index of item.
     */
    public abstract void setDataForView(ViewHolder holder, int viewType, T itemData, int position);

    /**
     * 获取ViewHolder
     *
     * @param position
     * @param convertView
     * @param parent
     * @return ViewHolder
     */
    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (null == convertView) {
            holder = onCreateViewHolder(position, parent);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return holder;
    }

    private ViewHolder onCreateViewHolder(int position, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        View view = LayoutInflater.from(parent.getContext()).inflate(mItemLayoutIds[itemViewType], parent, false);
        return new ViewHolder(view);
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

    @Override
    public int getViewTypeCount() {
        return mItemLayoutIds.length;
    }

    /**
     * 通用的ViewHolder
     *
     * @author LiuWs
     */
    public static class ViewHolder {
        private final SparseArray<View> mViews;  //用于convertView存储布局中的控件。
        private final View mItemView;

        /**
         * 构造方法
         *
         * @param mItemView
         */
        public ViewHolder(View mItemView) {
            mViews = new SparseArray<View>();
            this.mItemView = mItemView;
            //setTag
            mItemView.setTag(this);
        }

        /**
         * 通过控件的Id获取对应的控件
         *
         * @param viewId 控件的Id。eg:R.id.XXX
         * @return view itemView中对应id的子View
         */
        public View getViewById(int viewId) {

            View view = mViews.get(viewId);
            if (null == view) {
                view = mItemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return view;
        }

        /**
         * 返回ItemView布局View
         *
         * @return itemView
         */
        public View getItemView() {
            return mItemView;
        }

    }
}

