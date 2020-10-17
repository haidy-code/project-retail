package com.example.project_retail.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project_retail.assynctasks.Insert_product_assynctask;
import com.example.project_retail.models.ProductsModel;
import com.example.project_retail.R;
import com.example.project_retail.room.RoomFactory;


public class Product_detailsFragment extends Fragment {
    private ImageView productIv;
    private TextView titleTv;
    private TextView detailsTv;
    private TextView descriptionTv;
    private TextView priceTv;
    private TextView quantityTv;
    private Button addToCartBtn;
    ProductsModel productsModel;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        productIv = view.findViewById(R.id.product_details_iv);
        titleTv = view.findViewById(R.id.product_details_title_tv);
        detailsTv = view.findViewById(R.id.product_details_details_tv);
        descriptionTv = view.findViewById(R.id.product_details_description_tv);
        priceTv = view.findViewById(R.id.details_price_tv);
        quantityTv = view.findViewById(R.id.details_quantity_tv);
        addToCartBtn = view.findViewById(R.id.addtocart_btn);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getClickedProductFromHomeFragment();
        setUpClickListener();
    }

    private void setUpClickListener() {

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO : after implementing room we will insert product into the cart
                productsModel.setQuantity(1);
                new Insert_product_assynctask(RoomFactory.createRoomObject(requireContext()).getProductDao()).execute(productsModel);
                Navigation.findNavController(view).navigate(R.id.action_product_detailsFragment_to_cartFragment);
            }
        });

    }

    private void getClickedProductFromHomeFragment() {

        Bundle arguments = getArguments();

        if (arguments != null) {
            productsModel = (ProductsModel) arguments.getSerializable("clickedProduct");
            Glide.with(requireContext()).load(productsModel.getPhoto()).into(productIv);
            titleTv.setText(productsModel.getTitle());
            descriptionTv.setText(productsModel.getDescription());
            detailsTv.setText(productsModel.getDetails());
            priceTv.setText(productsModel.getFinalPriceText());
        }

    }
}