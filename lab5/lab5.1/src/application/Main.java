package application;
	
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private static TableView tableview = new TableView();
    private static VBox vEditBox = new VBox(10);
    private static HBox hButtonBox = new HBox(10);

    @Override
    public void start(Stage primaryStage) throws Exception{
        DBUtil.dbConnect();
        final ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll("animals","classesofanimals");
        comboBox.valueProperty().addListener((ChangeListener<String>) (ov, t, t1) -> {
            try {
                drawWindow(t1);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        comboBox.getSelectionModel().select(0);

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(comboBox, tableview, vEditBox, hButtonBox);
        primaryStage.setTitle("DB project.");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnCloseRequest(we -> {
            try {
                DBUtil.dbDisconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private static void drawWindow(String tableName) throws SQLException, ClassNotFoundException {
        hButtonBox.getChildren().clear();
        Button bAdd = new Button("Добавить");
        Button bDelete = new Button("Удалить");
        hButtonBox.getChildren().addAll(bAdd,bDelete);

        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        ResultSet rs = DBUtil.getDataFromTable(tableName);

        List nameCol = new ArrayList();

        //TABLE COLUMN ADDED DYNAMICALLY
        tableview.getColumns().clear();
        vEditBox.getChildren().clear();
        for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
            HBox HField = new HBox();
            final int j = i;
            String nameColumn = rs.getMetaData().getColumnName(i+1);
            TableColumn col = new TableColumn(nameColumn);
            col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));
            tableview.getColumns().addAll(col);
            if(i!=0){
                nameCol.add(nameColumn);
                Button bEdit = new Button("Изменить");
                HField.getChildren().addAll(new Label(nameColumn+": "),new TextField(),bEdit);
            } else{
                HField.getChildren().addAll(new Label(nameColumn+": "),new TextField());
            }
            vEditBox.getChildren().add(HField);
        }
        ObservableList<Node> children = vEditBox.getChildren();
        bAdd.setOnAction(e -> {
            List listNewData = new ArrayList();
            for (int i = 0; i < children.size(); i++) {
                Node child = children.get(i);
                HBox currField = (HBox) child;
                TextField tf = (TextField) currField.getChildren().get(1);
                if(i!=0){
                    listNewData.add("'"+tf.getText()+"'");
                }
            }
            DBUtil.addRow(tableName, nameCol, listNewData);
            try {
                drawWindow(tableName);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        //Data added to ObservableList
        while(rs.next()){
            //Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                row.add(rs.getString(i));
            }
            data.add(row);
        }
        tableview.setItems(data);

        tableview.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(tableview.getSelectionModel().getSelectedItem() != null)
            {
                String id = String.valueOf(0);
                List<String> val = (List<String>) newValue;
                for (int i = 0; i < children.size(); i++) {
                    Node child = children.get(i);
                    HBox currField = (HBox) child;
                    TextField tf = (TextField) currField.getChildren().get(1);
                    if(i==0){
                        id = tf.getText();
                    }
                    else {
                        Button bEdite = (Button) currField.getChildren().get(2);
                        String finalId = id;
                        int finalI = i;
                        bEdite.setOnAction(e -> {
                            DBUtil.updateRow(tableName, String.valueOf(nameCol.get(finalI-1)),tf.getText(), finalId);
                            try {
                                drawWindow(tableName);
                            } catch (SQLException | ClassNotFoundException ex) {
                                ex.printStackTrace();
                            }
                        });
                    }
                    tf.setText(val.get(i));
                }
                bDelete.setOnAction(e -> {
                    DBUtil.deleteRowById(tableName, Integer.parseInt(val.get(0)));
                    try {
                        drawWindow(tableName);
                    } catch (SQLException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                });
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
