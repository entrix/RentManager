package com.volkoval.rent;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ApplicationWindow extends Application {

    private double computedResult = 0.0;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Учет коммунальных платежей");
        BorderPane componentLayout = new BorderPane();
        componentLayout.setPadding(new Insets(20,20,20,20));

        final FlowPane choicePane = new FlowPane();
        choicePane.setHgap(100);
        Label choiceLbl = new Label("Fruits");
        ChoiceBox fruits = new ChoiceBox(FXCollections.observableArrayList("Asparagus", "Beans", "Broccoli", "Cabbage"
                , "Carrot", "Celery", "Cucumber", "Leek", "Mushroom"
                , "Pepper", "Radish", "Shallot", "Spinach", "Swede"
                , "Turnip"));

        fruits.getSelectionModel().select(0);
        choicePane.getChildren().add(choiceLbl);
        choicePane.getChildren().add(fruits);

        componentLayout.setTop(choicePane);

//        final FlowPane listPane = new FlowPane();
//        listPane.setHgap(100);
//        Label listLbl = new Label("Vegetables");
//        ListView vegetables = new ListView(FXCollections.observableArrayList("Apple", "Apricot", "Banana"
//                ,"Cherry", "Date", "Kiwi", "Orange", "Pear", "Strawberry"));
//        listPane.getChildren().add(listLbl);
//        listPane.getChildren().add(vegetables);
//        listPane.setVisible(true);
//
//        componentLayout.setCenter(listPane);

        addCenterComponent(componentLayout);

        Button btn = new Button();
        btn.setText("Рассчитать");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        Scene appScene = new Scene(componentLayout,600,400);
        componentLayout.setStyle("-fx-background-color: #e6e6e6;");
        primaryStage.setScene(appScene);
        primaryStage.show();
    }

    public void addCenterComponent(BorderPane pane) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setMinHeight(200.0);
        grid.setPrefHeight(200.0);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setStyle("-fx-text-alignment: justify;");

        Text scenetitle = new Text("Окно Расчета");
        scenetitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        scenetitle.setStyle("-fx-text-alignment: right;");
        grid.add(scenetitle, 0, 0, 2, 1);

        Label kiloName = new Label("количество килоВатт:");
        kiloName.setStyle("-fx-font-family: arial; -fx-font-size: 14;");
        grid.add(kiloName, 0, 1);

        final TextField kiloNameField = new TextField();
        kiloNameField.setMaxWidth(80.0);
        grid.add(kiloNameField, 1, 1);

        Label kiloPrice = new Label("стоимость одного килоВатта:");
        kiloPrice.setStyle("-fx-font-family: arial; -fx-font-size: 14;");
        grid.add(kiloPrice, 0, 2);

        final TextField kiloPriceField = new TextField();
        kiloPriceField.setMaxWidth(80.0);
        grid.add(kiloPriceField, 1, 2);

        final Label result = new Label();
        result.setStyle("-fx-font-family: arial; -fx-font-size: 16; -fx-text-fill: rgb(255,0,0);");
        result.setVisible(false);
        grid.add(result, 0, 4, 2, 1);

        final Button btn2 = new Button();
        btn2.setText("Скрыть");
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                result.setVisible(false);
                btn2.setVisible(false);
            }
        });
        btn2.setVisible(false);
        grid.add(btn2, 4, 4);

        Button btn = new Button();
        btn.setText("Рассчитать");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                double count      = Double.valueOf(String.valueOf(kiloNameField.getCharacters()));
                double priceOfOne = Double.valueOf(String.valueOf(kiloPriceField.getCharacters()));

                result.setText("Результат: " + String.valueOf(count * priceOfOne) + " руб.");
                result.setVisible(true);
                btn2.setVisible(true);
            }
        });
        grid.add(btn, 4, 2);

        pane.setCenter(grid);
    }
}