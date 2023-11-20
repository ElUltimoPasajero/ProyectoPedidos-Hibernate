package com.example.gestorpedidoshibernate.controllers;

import com.example.gestorpedidoshibernate.App;
import com.example.gestorpedidoshibernate.Session;
import com.example.gestorpedidoshibernate.domain.order.Order;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    @javafx.fxml.FXML
    private TableColumn<Order, String> columnCode;
    @javafx.fxml.FXML
    private TableColumn<Order, String> columnDate;
    @javafx.fxml.FXML
    private TableColumn<Order, Integer> columnAmmmount;
    @javafx.fxml.FXML
    private TableView<Order> table;
    @javafx.fxml.FXML
    private Button btnExit;
    @javafx.fxml.FXML
    private Button btnAddOrder;
    @javafx.fxml.FXML
    private Button btnDeleteOrder;


    @javafx.fxml.FXML
    public void exit(ActionEvent actionEvent) {
        Session.setCurrentUser(null);
        try {
            App.changeScene("login-screen.fxml", "Login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnAmmmount.setCellValueFactory((fila) -> {
            return new SimpleObjectProperty<>(fila.getValue().getAmmount());
        });
        columnDate.setCellValueFactory((fila) -> {
            return new SimpleStringProperty(fila.getValue().getDate());
        });
        columnCode.setCellValueFactory((fila) -> {
            return new SimpleStringProperty(fila.getValue().getCode());
        });


        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Order>() {
            @Override
            public void changed(ObservableValue<? extends Order> observableValue, Order order, Order t1) {
                Session.setCurrentOrder(t1);
                try {
                    App.changeScene("item-screen.fxml", t1.getCode());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Cantidad de Items: " + Session.getCurrentOrder().getItems().size());
            }
        });

        table.getItems().addAll(Session.getCurrentUser().getOrders());


    }

    @javafx.fxml.FXML
    public void addOrder(ActionEvent actionEvent) {

        var o = new Order();

        o.setUser(Session.getCurrentUser());
        Session.setCurrentOrder(o);

        try {
            App.changeScene("item-screen.fxml", "New Order");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



    @javafx.fxml.FXML
    public void deleteOrder(ActionEvent actionEvent) {
    }
}