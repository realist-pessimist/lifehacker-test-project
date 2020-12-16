package eugene.bondarev.lifehacker.base;

public interface IRecyclerClickListener<T> {

    void onItemClick(int position, T o);
    boolean onItemLongClick(int position, T o);
}
