package com.furkanekiz.rickandmorty.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.furkanekiz.rickandmorty.R;
import com.furkanekiz.rickandmorty.databinding.CharacterCardBinding;
import com.furkanekiz.rickandmorty.model.character.CharacterResult;
import com.furkanekiz.rickandmorty.view.DetailFragment;
import com.furkanekiz.rickandmorty.view.HomeActivty;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {
    List<CharacterResult> characterResultList;
    int itemLayoutId;

    public CharacterAdapter(List<CharacterResult> itemList, int itemLayoutId) {
        this.characterResultList = itemList;
        this.itemLayoutId = itemLayoutId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CharacterCardBinding characterCardBinding = CharacterCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(characterCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CharacterResult characterResult = characterResultList.get(position);
        holder.bindData(characterResult);
    }

    @Override
    public int getItemCount() {
        return characterResultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CharacterCardBinding characterCardBinding;



        public ViewHolder(@NonNull CharacterCardBinding characterCardBinding) {
            super(characterCardBinding.getRoot());
            this.characterCardBinding=characterCardBinding;

        }



        public void bindData(CharacterResult item) {
            if (item.getGender().equalsIgnoreCase("Female")){
                characterCardBinding.rLayoutFemale.setVisibility(View.VISIBLE);
                characterCardBinding.rLayoutMale.setVisibility(View.GONE);
                characterCardBinding.rLayoutUnKnow.setVisibility(View.GONE);
                characterCardBinding.txtCharacterItemNameFemale.setText(item.getName());
                Glide.with(HomeActivty.context)
                        .load(item.getImage())
                        .into(characterCardBinding.imgViewCharacterItemLogoFemale);
            }else if(item.getGender().equalsIgnoreCase("Male")) {
                characterCardBinding.rLayoutMale.setVisibility(View.VISIBLE);
                characterCardBinding.rLayoutFemale.setVisibility(View.GONE);
                characterCardBinding.rLayoutUnKnow.setVisibility(View.GONE);
                characterCardBinding.txtCharacterItemNameMale.setText(item.getName());
                Glide.with(HomeActivty.context)
                        .load(item.getImage())
                        .into(characterCardBinding.imgViewCharacterItemLogoMale);
            }else {
                characterCardBinding.rLayoutUnKnow.setVisibility(View.VISIBLE);
                characterCardBinding.rLayoutFemale.setVisibility(View.GONE);
                characterCardBinding.rLayoutMale.setVisibility(View.GONE);
                characterCardBinding.txtCharacterItemNameUnKnow.setText(item.getName());
                Glide.with(HomeActivty.context)
                        .load(item.getImage())
                        .into(characterCardBinding.imgViewCharacterItemLogoUnKnow);
            }
            itemView.setOnClickListener(view -> {
                Bundle bundle = new Bundle();
                bundle.putParcelable("characterItemDetail", item);
                loadFragment(new DetailFragment(), bundle);
            });

        }
    }

    public void loadFragment(Fragment fragment, Bundle bundle) {
        FragmentTransaction transaction = HomeActivty.fragmentManager.beginTransaction().setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out);
        fragment.setArguments(bundle);
        transaction.add(R.id.fragMain, fragment, fragment.getClass().getSimpleName());
        transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commit();

    }
}
