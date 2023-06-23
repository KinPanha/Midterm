package kh.edu.rupp.ite.onlineshop.ui.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.ite.onlineshop.api.model.Profile;
import kh.edu.rupp.ite.onlineshop.api.service.ApiService;
import kh.edu.rupp.ite.onlineshop.databinding.FragmentProfileBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         binding = FragmentProfileBinding.inflate(inflater,container, false);
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
                    showProfile(response.body());
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
    private void showProfile(Profile profile){

        binding.name.setText(profile.getFirst_name()+profile.getLast_name());
        binding.email.setText(profile.getEmail());
        Picasso.get().load(profile.getImage_url()).into(binding.image);
        binding.emails.setText(profile.getEmail());
        binding.phonenumber.setText(profile.getPhone_number());
        binding.address.setText(profile.getAddress());
        binding.date.setText(profile.getBirthday());
        binding.gender.setText(profile.getGender());

    }
}
