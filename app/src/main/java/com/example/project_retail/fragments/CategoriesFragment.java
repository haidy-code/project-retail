package com.example.project_retail.fragments;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.project_retail.R;
import com.example.project_retail.adapters.Category_rv_adapter;
import com.example.project_retail.adapters.Products_rv_adapter;
import com.example.project_retail.models.CategoryModel;
import com.example.project_retail.models.ProductsModel;
import com.example.project_retail.responses.Category_Response;
import com.example.project_retail.webservices.Retrofit_Factory;
import com.example.project_retail.webservices.WebServices;

import java.util.ArrayList;
import java.util.List;


public class CategoriesFragment extends Fragment {
    RecyclerView recyclerView;
    Category_rv_adapter categories_rv_adapter;
    List<CategoryModel> categorytlist=new ArrayList<>();
    WebServices webServices;
    ProgressDialog progressDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_categories, container, false);
        recyclerView=view.findViewById(R.id.category_rv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupprogressdialog();
        setuprecyclerview();
        getcategories();
    }

    private void getcategories() {
        webServices= Retrofit_Factory.getRetrofit().create(WebServices.class);
       Call<Category_Response> getcategory = webServices.getcategories();
       getcategory.enqueue(new Callback<Category_Response>() {
           @Override
           public void onResponse(Call<Category_Response> call, Response<Category_Response> response) {
               progressDialog.dismiss();
               categorytlist.clear();
               categorytlist.addAll(response.body().getCategorylist());
               categories_rv_adapter.notifyDataSetChanged();

           }

           @Override
           public void onFailure(Call<Category_Response> call, Throwable t) {
               progressDialog.dismiss();
               Toast.makeText(requireContext(), "Error!!!", Toast.LENGTH_SHORT).show();
           }
       });
    }

    private void setupprogressdialog() {
        progressDialog=new ProgressDialog(requireContext());
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    private void setuprecyclerview() {

        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(requireContext(),2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(16), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        categories_rv_adapter=new Category_rv_adapter(categorytlist,requireContext());
recyclerView.setAdapter(categories_rv_adapter);
    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}