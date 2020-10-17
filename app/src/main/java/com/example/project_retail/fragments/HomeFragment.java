package com.example.project_retail.fragments;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.project_retail.assynctasks.Insert_product_assynctask;
import com.example.project_retail.models.ProductsModel;
import com.example.project_retail.responses.Products_Response;
import com.example.project_retail.adapters.Products_rv_adapter;
import com.example.project_retail.R;
import com.example.project_retail.room.RoomFactory;
import com.example.project_retail.webservices.Retrofit_Factory;
import com.example.project_retail.webservices.WebServices;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
Products_rv_adapter products_rv_adapter;
List<ProductsModel>productlist=new ArrayList<>();
WebServices webServices;
ProgressDialog progressDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=view.findViewById(R.id.products_rv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpprogressdialog();
        progressDialog.show();
        setUpRecyclerView();
        getProducts();

    }

    private void setUpprogressdialog() {
        progressDialog=new ProgressDialog(requireContext());
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
    }

    private void getProducts() {
        webServices= Retrofit_Factory.getRetrofit().create(WebServices.class);
        Call<Products_Response> getproduct=webServices.getproducts();
getproduct.enqueue(new Callback<Products_Response>() {
    @Override
    public void onResponse(Call<Products_Response> call, Response<Products_Response> response) {
        progressDialog.dismiss();
        productlist.clear();
        productlist.addAll(response.body().getProductlist());
        products_rv_adapter.notifyDataSetChanged();


    }

    @Override
    public void onFailure(Call<Products_Response> call, Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(requireContext(), "Error!!!", Toast.LENGTH_SHORT).show();
        Log.d("retail-omar", t.toString());

    }
});
    }


    private void setUpRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(requireContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(12), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        products_rv_adapter=new Products_rv_adapter(productlist, requireContext(), new Products_rv_adapter.OnProductClickListener() {
            @Override
            public void onProductClick(View view, int position) {
                ProductsModel clickedProduct = productlist.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("clickedProduct", clickedProduct);
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_product_detailsFragment, bundle);

            }
        }, new Products_rv_adapter.OnAddProductClickListener() {
            @Override
            public void onAddProductClick(View view, int position) {
                ProductsModel clickedProduct = productlist.get(position);
                clickedProduct.setQuantity(1);
                new Insert_product_assynctask(RoomFactory.createRoomObject(requireContext()).getProductDao()).execute(clickedProduct);
Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_cartFragment);
            }
        });
        recyclerView.setAdapter(products_rv_adapter);
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
