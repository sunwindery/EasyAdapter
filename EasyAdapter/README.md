本项目主要包含两个类：EasyAdapter和EasyRecyclerAdapter。
他们分别继承自BeseAdapter和Recycler.Adapter。均既适用于相同item的情况，又适用于多种item的情况（比如聊天界面）。使用过程迅速且简单。

1.EasyAdapter:
	EasyAdapter继承自BeseAdapter，实现了通用方法，并封装了ViewHolder内部类。内部实现了两种常用的性能优化方法，使用者不必再重复这些通用代码。
	
	代码示例：
	    EasyAdapter<Bean> myAdapter = new EasyAdapter<Bean>(list, R.layout.left_layout) {
            @Override
            public void setDataForView(ViewHolder holder, int viewType, Bean item, int position) {
                ((TextView) holder.getViewById(R.id.left_text)).setText(item.getContent() + position);
            }
        };
2.EasyRecyclerAdapter
	EasyRecyclerAdapter继承自RecyclerView.Adapter。与RecyclerView配合使用。同样实现了通用方法，封装了ViewHolder类，使代码更加简洁。
	
	代码示例：
		EasyRecyclerAdapter<Bean> adapter = new EasyRecyclerAdapter<Bean>(list,R.layout.left_layout) {
            @Override
            public void setDataForView(ViewHolder holder, int viewType, Bean itemData, int position) {
                ((TextView)holder.getViewById(R.id.left_text)).setText(itemData.getContent()+position);
            }
        };