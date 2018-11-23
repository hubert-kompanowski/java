package drawing;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.control.Label;

import javafx.scene.control.Button;


import java.util.Random;
import java.util.Vector;

import static java.lang.Math.abs;


public class app extends Application {

    private Stage primaryStage;
    private int numberOfShapes = 0;

    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;

    private Vector<MyShape> shapes = new Vector<>();

    private static final int WIDTH = 500, HEIGHT = 500, MAX_SHAPES=25; // Size of our example


    private Color GetRandomColor(){
        Random generator = new Random();
        Vector<Color> v = new Vector<>();
        v.add(Color.RED);
        v.add(Color.GREEN);
        v.add(Color.BLUE);
        v.add(Color.BLACK);
        v.add(Color.YELLOW);
        v.add(Color.GRAY);
        v.add(Color.PINK);
        return v.get(abs(generator.nextInt()%7));
    }




    @Override
    public void start(Stage stage){
        primaryStage = stage;


        VBox siatkaPionowa = new VBox(6);
        siatkaPionowa.setPadding(new Insets(6));
        Label label = new Label();
        label.setText("Click what do you want to draw. Up to 25 shapes");
        siatkaPionowa.getChildren().add(label);



        HBox buttons1 = new HBox(2);
        Button c = new Button("Circle");

        c.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(numberOfShapes >= MAX_SHAPES){
                    System.out.println("too many shapes ");
                } else {
                    shapes.add(new MyCircle());
                    numberOfShapes++;
                    System.out.println(numberOfShapes);
                }
            }
        });
        c.setMinHeight(150);
        c.setMinWidth(250);
        Button t = new Button("Triangle");
        t.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(numberOfShapes >= MAX_SHAPES){
                    System.out.println("too many shapes ");
                } else {
                    shapes.add(new MyTriangle());
                    numberOfShapes++;
                    System.out.println(numberOfShapes);
                }
            }
        });
        t.setMinHeight(150);
        t.setMinWidth(250);
        buttons1.getChildren().addAll(c,t);
        siatkaPionowa.getChildren().add(buttons1);



        HBox buttons2 = new HBox(2);
        Button s = new Button("Square");
        s.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(numberOfShapes >= MAX_SHAPES){
                    System.out.println("too many shapes ");
                } else {
                    shapes.add(new MySquare());
                    numberOfShapes++;
                    System.out.println(numberOfShapes);
                }

            }
        });
        s.setMinHeight(150);
        s.setMinWidth(250);
        Button r = new Button("Rectangle");
        r.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(numberOfShapes >= MAX_SHAPES){
                    System.out.println("too many shapes ");
                } else {
                    shapes.add(new MyRectangle());
                    numberOfShapes++;
                    System.out.println(numberOfShapes);
                }
            }
        });
        r.setMinHeight(150);
        r.setMinWidth(250);
        buttons2.getChildren().addAll(s,r);
        siatkaPionowa.getChildren().add(buttons2);




        Label label2 = new Label();
        label2.setText("To draw your shapes click the button below::");
        siatkaPionowa.getChildren().add(label2);


        HBox buttonsHBox = new HBox(5);
        Button draw = new Button("Draw");
        draw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Group g = new Group();

                Vector<Pair<Integer, Integer>> pos = new Vector<>();

                for(int x = 62; x <= 438; x+=94){
                    for(int y = 62; y <= 438; y+=94){
                        pos.add(new Pair<>(x,y));
                    }
                }

                int x= 0;
                for(MyShape sha : shapes){
                    Shape new_shape =  sha.draw(pos.get(x).getValue(),pos.get(x).getKey(), 30, GetRandomColor());
                    new_shape.setCursor(Cursor.HAND);
                    new_shape.setOnMousePressed(circleOnMousePressedEventHandler);
                    new_shape.setOnMouseDragged(circleOnMouseDraggedEventHandler);
                    g.getChildren().add(new_shape);
                    x++;
                }
                Scene scene2 =  new Scene(g, WIDTH, HEIGHT);
                primaryStage.setScene(scene2);


            }
        });

        Button exit = new Button("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });



        buttonsHBox.getChildren().addAll(draw,  exit);
        siatkaPionowa.getChildren().add(buttonsHBox);

        Scene scene =  new Scene(siatkaPionowa, WIDTH, HEIGHT);

        primaryStage.setTitle("Drawing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String [] argv){
        launch(argv);
    }


    private EventHandler<MouseEvent> circleOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                    orgTranslateX = ((Shape)(t.getSource())).getTranslateX();
                    orgTranslateY = ((Shape)(t.getSource())).getTranslateY();
                }
            };

    private EventHandler<MouseEvent> circleOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;

                    ((Shape)(t.getSource())).setTranslateX(newTranslateX);
                    ((Shape)(t.getSource())).setTranslateY(newTranslateY);
                }
            };
}




//        siatkaPionowa.getChildren().add(g);
//
//        Button wroc = new Button("Wróć");
//        wroc.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.print("wrocic do menu");
//            }
//        });
//
