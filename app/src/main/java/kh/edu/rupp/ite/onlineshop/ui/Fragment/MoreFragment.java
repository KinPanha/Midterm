package kh.edu.rupp.ite.onlineshop.ui.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.ite.onlineshop.api.model.Profile;
import kh.edu.rupp.ite.onlineshop.api.service.ApiService;
import kh.edu.rupp.ite.onlineshop.databinding.FragmentMoreBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoreFragment extends Fragment {
    private FragmentMoreBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         binding = FragmentMoreBinding.inflate(inflater,container, false);
         return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadProfileFromService();
    }
    private void loadProfileFromService(){
        Retrofit http = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/kimsongsao/ferupp/main/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService  = http.create(ApiService.class);
        Call<Profile> task = apiService.loadProfileList();
        task.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if (response.isSuccessful()){
                    showMore(response.body());
                } else {
                    Toast.makeText(getContext(), "404 not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Toast.makeText(getContext(), "Load Product failed!", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void showMore(Profile profile){
        binding.name.setText(profile.getFirst_name()+profile.getLast_name());
        binding.email.setText(profile.getEmail());
        Picasso.get().load(profile.getImage_url()).into(binding.image);
    }
}
