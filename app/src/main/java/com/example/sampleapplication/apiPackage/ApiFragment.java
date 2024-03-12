package com.example.sampleapplication.apiPackage;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.sampleapplication.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFragment extends Fragment {

    Button btnGetData, btnPostData, btnUpdateData, btnDeleteData, btnSubmit;
    EditText editTextUserId, editTextTitle, editTextBody;
    private TextView textView;
    private ApiService apiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_api, container, false);

        textView = view.findViewById(R.id.textResult);
        editTextUserId = view.findViewById(R.id.editTextUserId);
        editTextTitle = view.findViewById(R.id.edittextTitle);
        editTextBody = view.findViewById(R.id.editTextBody);
        btnPostData = view.findViewById(R.id.btnPostData);
        btnGetData = view.findViewById(R.id.btnGetData);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnDeleteData = view.findViewById(R.id.btnDeleteData);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.mockApiLink))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);


        btnGetData.setOnClickListener(v -> getPosts());
        btnPostData.setOnClickListener(v -> postData());

        btnDeleteData.setOnClickListener(v -> deleteData());
        return view;
    }

    public void getPosts() {

        textView.setText("");
        Call<List<ApiDataClass>> call = apiService.getPosts();

        call.enqueue(new Callback<List<ApiDataClass>>() {
            @Override
            public void onResponse(@NonNull Call<List<ApiDataClass>> call, @NonNull Response<List<ApiDataClass>> response) {
                if (!response.isSuccessful()) {
                    textView.setText(getString(R.string.code) + response.code());
                    return;
                }
                List<ApiDataClass> posts = response.body();
                assert posts != null;
                for (ApiDataClass post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Body: " + post.getText() + "\n\n";

                    textView.append(content);

                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ApiDataClass>> call, @NonNull Throwable t) {
                textView.setText(t.getMessage());

            }
        });

    }


    public void putData(String userId, String title, String text) {

        editTextUserId.setVisibility(View.GONE);
        editTextTitle.setVisibility(View.GONE);
        editTextBody.setVisibility(View.GONE);
        btnSubmit.setVisibility(View.GONE);

        Call<ApiDataClass> call = apiService.createPost(userId, title, text);
        call.enqueue(new Callback<ApiDataClass>() {
            @Override
            public void onResponse(@NonNull Call<ApiDataClass> call, @NonNull Response<ApiDataClass> response) {
                if (response.isSuccessful()) {
                    editTextUserId.setVisibility(View.GONE);
                    editTextTitle.setVisibility(View.GONE);
                    editTextBody.setVisibility(View.GONE);
                    btnSubmit.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "Data posted successfully", Toast.LENGTH_SHORT).show();

                    ApiDataClass postResponse = response.body();
                    String content = "";
                    content += "Code: " + response.code() + "\n";
                    assert postResponse != null;
                    content += "ID: " + postResponse.getId() + "\n";
                    content += "User ID: " + postResponse.getUserId() + "\n";
                    content += "Title: " + postResponse.getTitle() + "\n";
                    content += "Body: " + postResponse.getText() + "\n\n";
                    textView.setText(content);

                    //getPosts();
                } else {
                    Toast.makeText(getActivity(), "Failed to post data.", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "Error:" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiDataClass> call, @NonNull Throwable t) {
                Log.d("TAG", "Error in getting response " + t.getMessage());
                Toast.makeText(getActivity(), "Failed to post data. Error: ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void postData() {

        editTextTitle.setVisibility(View.VISIBLE);
        editTextBody.setVisibility(View.VISIBLE);
        editTextUserId.setVisibility(View.VISIBLE);
        btnSubmit.setVisibility(View.VISIBLE);
        btnSubmit.setOnClickListener(v -> submitData());
    }

    public void submitData() {
        String userId = editTextUserId.getText().toString();
        String title = editTextTitle.getText().toString();
        String text = editTextBody.getText().toString();
        putData(userId, title, text);
    }

    public void deleteData() {
        editTextUserId.setVisibility(View.VISIBLE);
        btnSubmit.setVisibility(View.VISIBLE);
        String id = editTextUserId.getText().toString();
        btnSubmit.setOnClickListener(v -> deletePost(id));
    }

    private void deletePost(String id) {
        btnSubmit.setVisibility(View.GONE);
        editTextUserId.setVisibility(View.GONE);
        Call<Void> call = apiService.deletePost(id);
        call.enqueue(new Callback<Void>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                textView.setText(getString(R.string.code) + response.code());
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }


}





