package com.example.blogfirebase.ui.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blogfirebase.core.BaseViewHolder
import com.example.blogfirebase.data.model.Post
import com.example.blogfirebase.databinding.PostItemViewBinding

class HomeScreenAdapter (private val postList: List<Post>): RecyclerView.Adapter<BaseViewHolder<*>>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> { //donde se crea cada uno de los post
       val itemBinding = PostItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false) // inflamos el XML (item)
        return HomeScreenViewHolder(itemBinding, parent.context)  //pasamos el viewHolder que creamos abajo (esto se hace despues de la inner class)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is HomeScreenViewHolder -> holder.bind(postList[position])
        }
    }

    override fun getItemCount(): Int = postList.size   //retorna la cantidad de post o items que tenemos el la lista.


    private inner class HomeScreenViewHolder( //creamos el ViewHolder que va aponer los datos
        val binding: PostItemViewBinding,     //Le pasamos el postItemBinding (creada automaticamente por ViewBinding)
        val context: Context                  //y el context
    ): BaseViewHolder<Post>(binding.root){   //Extendemos del BaseViewHolder. Este nos pide que pase un tipo de dato para bindear info. <Post>,
        override fun bind(item: Post) {     // aca le ponemos los datos a cada elemento

            Glide.with(context).load(item.post_image).centerCrop().into(binding.postImage)  //cargamos la foto de post
            Glide.with(context).load(item.profile_picture).centerCrop().into(binding.profilePicture)  //cargamos la de perfil
            binding.profileName.text = item.profile_name
            binding.postTimestamp.text =  "Hace 1 horas"

        }

    }
}
