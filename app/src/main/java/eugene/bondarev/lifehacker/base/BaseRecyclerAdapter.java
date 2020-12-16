package eugene.bondarev.lifehacker.base;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T extends RecyclerView.ViewHolder, O> extends RecyclerView.Adapter<T> {

    private IRecyclerClickListener<O> mRecyclerClickListener;
    private RecyclerEndListener mRecyclerEndListener;
    protected ArrayList<O> mCollection = new ArrayList<>();

    public static final int LIMIT = 25;
    public static final int LIMIT_SEARCH = 50;
    public static final int FIRST_PAGE = 0;

    private int mCurrentPage = 1;
    private boolean isNeedUpdate = true;
    private Object mRecentlyDeletedItem;
    private int mRecentlyDeletedItemPosition;

    public void addAll(@NonNull List<O> objects) {
        mCollection.addAll(objects);
        notifyDataSetChanged();
    }

    public void addAllWithoutNotify(List<O> objects) {
        mCollection.addAll(objects);
    }

    public void clear() {
        mCurrentPage = 1;
        mCollection.clear();
        notifyDataSetChanged();
    }

    public ArrayList<O> getCollection() {
        return mCollection;
    }

    public void add(O o) {
        int index = mCollection.indexOf(o);
        if (index != -1){
            mCollection.set(index, o);
            notifyItemChanged(index);
        }else {
            mCollection.add(o);
            notifyItemInserted(mCollection.size() - 1);
        }
    }

    public O get(int position) {
        return mCollection.get(position);
    }

    public void setCurrentPage(int mCurrentPage) {
        this.mCurrentPage = mCurrentPage;
    }

    public void clearWithoutNotify() {
        mCurrentPage = 1;
        mCollection.clear();
    }

    public void setUpdatable(boolean isUpdatable) {
        isNeedUpdate = isUpdatable;
    }

    public void setmRecyclerClickListener(IRecyclerClickListener<O> mRecyclerClickListener) {
        this.mRecyclerClickListener = mRecyclerClickListener;
    }

    public void setmRecyclerEndListener(RecyclerEndListener mRecyclerEndListener) {
        this.mRecyclerEndListener = mRecyclerEndListener;
    }

    public void deleteItem(int position) {
        mRecentlyDeletedItem = mCollection.get(position);
        mRecentlyDeletedItemPosition = position;
        mCollection.remove(position);
        notifyItemRemoved(position);
    }

    public void deleteItem(O item) {
        mRecentlyDeletedItemPosition = mCollection.indexOf(item);
        mCollection.remove(item);
        notifyItemRemoved(mRecentlyDeletedItemPosition);
    }

    public Object getmRecentlyDeletedItem() {
        return mRecentlyDeletedItem;
    }

    public int getmRecentlyDeletedItemPosition() {
        return mRecentlyDeletedItemPosition;
    }

    @Override
    public void onBindViewHolder(@NonNull final T holder, int position) {
        if (mRecyclerClickListener != null) {
            holder.itemView.setOnClickListener(view -> onItemClick(holder));

            holder.itemView.setOnLongClickListener(view -> onItemLongClick(holder));
        }

        if (mCollection.size() - position <= 3 && isNeedUpdate && mCollection.size() >= LIMIT) {
            if (mRecyclerEndListener != null) {
                mCurrentPage++;
                mRecyclerEndListener.onRecyclerEnd(mCurrentPage);

            }
        }

    }

    private void onItemClick(@NonNull T holder) {
        if (holder.getAdapterPosition() != -1)
            mRecyclerClickListener.onItemClick(holder.getAdapterPosition(), mCollection.get(holder.getAdapterPosition()));
    }

    private boolean onItemLongClick(@NonNull T holder) {
        return holder.getAdapterPosition() != -1 && mRecyclerClickListener.onItemLongClick(holder.getAdapterPosition(), mCollection.get(holder.getAdapterPosition()));

    }

    @Override
    public int getItemCount() {
        return mCollection.size();
    }

    public interface RecyclerEndListener {
        void onRecyclerEnd(int nextPage);
    }
}