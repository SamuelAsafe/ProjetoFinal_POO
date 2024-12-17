import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.*;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Tela.fxml"));
        primaryStage.setTitle("Carrinho de Supermercado");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
    }
}
