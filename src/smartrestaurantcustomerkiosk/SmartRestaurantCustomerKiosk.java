/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartrestaurantcustomerkiosk;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author csc190
 */
public class SmartRestaurantCustomerKiosk extends Application {
    
    GridPane grid = new GridPane();
    Text name = new Text();
    Text description = new Text();
    Text price = new Text();
    ImageView picture = new ImageView();
    ArrayList<FoodInfo> foods = new ArrayList();
    int page = 0;
    
    
    private String spaceOutString(String str){
        int chars = 0;
        boolean replace = false;
        StringBuilder output = new StringBuilder(str);
        for (int i = 0; i<str.length(); i++){
            chars++;
            if ((!replace) && (chars>22)){
                replace = true;
            }
            if ((replace) && (str.charAt(i)==' ')){
                output.setCharAt(i, '\n');
                replace = false;
                chars = 0;
            }
        }
        return output.toString();
    }
    
    private void initializeMenu(){
        try {
            
            InputStream in = getClass().getResourceAsStream("files/config.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            
        
            String line;
            int counter = 0;
            String[] lastfewlines = new String[4];
            
            while ((line = reader.readLine()) != null) {
                lastfewlines[counter] = line;
                if (counter==3){
                    counter=0;
                    foods.add(new FoodInfo(lastfewlines[0],lastfewlines[1],lastfewlines[2],lastfewlines[3]));
                }
                else{
                    counter++;
                }
            }
            in.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void redrawMenu(){
        name.setText(foods.get(page).fname);
        description.setText(spaceOutString(foods.get(page).fdesc));
        price.setText(foods.get(page).fprice);
        picture.setImage(new Image(getClass().getResourceAsStream(foods.get(page).picname)));
        picture.setFitHeight(425);
        picture.setFitWidth(425);
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("SmartRestaurant Table 21");
        
        initializeMenu();
        
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        name.setText(foods.get(0).fname);
        name.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(name, 1, 0);
        
        description.setText(spaceOutString(foods.get(0).fdesc));
        description.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(description, 2, 1, 2, 5);
        
        price.setText(foods.get(0).fprice);
        price.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(price, 2, 2);
        
        picture.setImage(new Image(getClass().getResourceAsStream(foods.get(0).picname)));
        picture.setFitHeight(425);
        picture.setFitWidth(425);
        grid.add(picture, 1, 1);
        
        Button nextBtn = new Button("Next");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(nextBtn);
        grid.add(hbBtn, 2, 4);
        
        nextBtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent e) {
                if (page<foods.size()-1){
                    page++;
                }
                else{
                    page=0;
                }
                redrawMenu();
            }
        });
        
        
        Button prevBtn = new Button("Prev");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn2.getChildren().add(prevBtn);
        grid.add(hbBtn2, 1, 4);

        prevBtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent e) {
                if (page>0){
                    page--;
                }
                else{
                    page=foods.size()-1;
                }
                redrawMenu();
            }
        });
        
        
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
