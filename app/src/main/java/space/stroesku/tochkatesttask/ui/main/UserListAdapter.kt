package space.stroesku.tochkatesttask.ui.main

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_user.view.*

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import space.stroesku.tochkatesttask.databinding.ItemUserBinding

class UserListAdapter(private val interaction: Interaction? = null)  :
        RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private val diffUtilCallBack = DiffUtilCallBack()
    private val differ = AsyncListDiffer(this,diffUtilCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view, interaction)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<User>) {
        differ.submitList(list)
    }

     class UserViewHolder
    constructor(
        private val binding: ItemUserBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(binding.root) {

         fun bind(item: User) {
             binding.apply {
                 Glide.with(itemView)
                     .load(item.avatarUrl)
                     .transition(DrawableTransitionOptions.withCrossFade())
                     .centerCrop()
                     .circleCrop() //???
                     .into(imageUser)
                 itemView.userName.text = item.login
                 itemView.setOnClickListener {
                     interaction?.onItemSelected(adapterPosition, item)
                 }
             }
         }
     }

    class DiffUtilCallBack: DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: User)
    }
}
