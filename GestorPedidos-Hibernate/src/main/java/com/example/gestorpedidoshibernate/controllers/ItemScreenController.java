package com.example.gestorpedidoshibernate.controllers;

import com.example.gestorpedidoshibernate.App;
import com.example.gestorpedidoshibernate.Session;
import com.example.gestorpedidoshibernate.domain.item.Item;
import com.example.gestorpedidoshibernate.domain.item.ItemDAOImplement;
import com.example.gestorpedidoshibernate.domain.product.Product;
import com.example.gestorpedidoshibernate.domain.product.ProductDAO;
import com.example.gestorpedidoshibernate.domain.product.ProductDAOImplement;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ItemScreenController implements Initializable {
    @javafx.fxml.FXML
    private TableView tableItem;
    @javafx.fxml.FXML
    private TableColumn<Item, String> columnProductName;
    @javafx.fxml.FXML
    private TableColumn<Item, Double> columnProductPrice;
    @javafx.fxml.FXML
    private FlowPane back;
    @javafx.fxml.FXML
    private Button btnBack;
    @javafx.fxml.FXML
    private TableColumn<Item, Integer> columnProductAmmount;
    @javafx.fxml.FXML
    private Button btnLogOut;
    @javafx.fxml.FXML
    private ComboBox<Product> comboGame;
    @javafx.fxml.FXML
    private Spinner<Integer> spinnerAmmountGame;
    @javafx.fxml.FXML
    private Button btnAddGame;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        columnProductName.setCellValueFactory((fila) -> {
            return new SimpleStringProperty(fila.getValue().getProducto().getProductName());
        });

        columnProductPrice.setCellValueFactory((fila) -> {
            return new SimpleObjectProperty<>(fila.getValue().getProducto().getProductPrice());
        });

        columnProductAmmount.setCellValueFactory((fila) -> {
            return new SimpleObjectProperty<>(fila.getValue().getProducto().getAmmountDisponible());

        });

        tableItem.getItems().addAll(Session.getCurrentOrder().getItems());  //Rallenamos la tabla con los pedidos de cada usuario


        //Rellenamos los combos
        var item = new ItemDAOImplement().getAllItems();
         var dao = new ProductDAOImplement();



        comboGame.setConverter(new javafx.util.StringConverter<Product>() {  //Asi se mapea los objetos Producst en los combos
             @Override
             public String toString(Product p) {

                 if (p != null) {

                     return p.getProductName();
                 } else {

                     return null;
                 }
             }

             @Override
             public Product fromString(String s) {
                 return null;
             }
         });



        comboGame.getItems().addAll(dao.getAllProducts());
        List<Product> lista = new ArrayList<>();
        lista.addAll(dao.getAllProducts());

        for (Product i : lista
             ) {

            System.out.println(i);

        }

        //Spinner de cantidad
        spinnerAmmountGame.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1 ,1));



    }

    @javafx.fxml.FXML

    /**
     * Metodo para volver atras hacia Main-Screen
     */
    public void back(ActionEvent actionEvent) {

        try {
            Session.setCurrentOrder(null); //Ponemos el pedido elegido antes en null

            App.changeScene("main-screen.fxml", "Order View");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void logOut(ActionEvent actionEvent) {

        try {
            App.changeScene("login-screen.fxml", "Login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @javafx.fxml.FXML
    public void addGame(ActionEvent actionEvent) {




    }
}