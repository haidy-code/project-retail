package com.example.project_retail.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.project_retail.R;
import com.example.project_retail.adapters.Cart_rv_adapter;
import com.example.project_retail.adapters.Products_rv_adapter;
import com.example.project_retail.assynctasks.Delete_product_assynctask;
import com.example.project_retail.assynctasks.Get_product_assynctask;
import com.example.project_retail.assynctasks.Update_product_assynctask;
import com.example.project_retail.models.ProductsModel;
import com.example.project_retail.room.RoomFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class CartFragment extends Fragment {
    List<ProductsModel>getproducts=new ArrayList<>();
    RecyclerView recyclerView;
    Cart_rv_adapter cart_rv_adapter;
    Button clearBtn;
    Button proceedToCheckOut;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView=view.findViewById(R.id.cart_rv);
        clearBtn = view.findViewById(R.id.clear_btn);
        proceedToCheckOut = view.findViewById(R.id.checkout_btn);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getAllProductsFromDb();
        setuprecyclerview();
        setupcicklisteners();
        
    }

    private void setupcicklisteners() {
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
getproducts.clear();
cart_rv_adapter.notifyDataSetChanged();
new Delete_product_assynctask(RoomFactory.createRoomObject(requireContext()).getProductDao()).execute();
            }
        });
        proceedToCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_cartFragment_to_signupFragment);
            }
        });
    }

    private void getAllProductsFromDb() {

        try {
            getproducts.addAll(new Get_product_assynctask(RoomFactory.createRoomObject(requireContext()).getProductDao()).execute().get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private void setuprecyclerview() {
        cart_rv_adapter=new Cart_rv_adapter(getproducts, requireContext(), new Cart_rv_adapter.OnIncButtonClicked() {
            @Override
            public void onincbuttonclicked(View view, int positioin) {
                getproducts.get(positioin).setQuantity(getproducts.get(positioin).getQuantity()+1);
                new Update_product_assynctask(RoomFactory.createRoomObject(requireContext()).getProductDao()).execute(getproducts.get(positioin));
                cart_rv_adapter.notifyDataSetChanged();
            }
        }, new Cart_rv_adapter.OnDecButtonClicked() {
            @Override
            public void ondecbuttonclicked(View view, int positioin) {
                getproducts.get(positioin).setQuantity(getproducts.get(positioin).getQuantity()-1);
                new Update_product_assynctask(RoomFactory.createRoomObject(requireContext()).getProductDao()).execute(getproducts.get(positioin));
                cart_rv_adapter.notifyDataSetChanged();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL));
                recyclerView.setAdapter(cart_rv_adapter);
                cart_rv_adapter.notifyDataSetChanged();
    }
}