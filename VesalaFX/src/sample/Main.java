package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.TimerTask;


public class Main extends Application {

    private TextField textField1 = new TextField();
    private Label skrivenaRec ;
    private char [] charArray ;
    private char [] skrivenArray;
    private  static int  pogodjenaSlova =0;
    private  static int pokusaj =6;
    private Label infolabel;
    private BorderPane borderPane;
    private Circle glava;
    private Line telo;
    private Line levaruka;
    private Line desnaruka;
    private Line levanoga;
    private Line desnanoga;
    private int duzina;
    private GridPane donjiDeo;


    @Override
    public void start(Stage stage) throws Exception{


        // Ubacivanje  Reci u Array listu tj spisak svih dostupnih reci

        File file = new File("E:\\JAVA PROJEKTI\\VesalaFX\\src\\sample\\Bazica");
        Scanner scanner1 = new Scanner(file);
        ArrayList<String> listaReci = new ArrayList<String>();
        int lineNumber = 0;
        while (scanner1.hasNext()){
            listaReci.add(scanner1.nextLine());
            lineNumber++;
        }
        scanner1.close();

// Incijalizacija rece - UZIMA SE RANDOM REC IZ BazeReci

        Random random = new Random();
        int izvucanaRec =(random.nextInt(lineNumber)) ;
          charArray =listaReci.get(izvucanaRec).toCharArray();

//Osnovki podaci za igru broj pokusaja i duzina trazene reci
         pokusaj =6;
         duzina = charArray.length;


//SKRIVENA REC
         skrivenArray = new char[duzina];
        for(int i =0; i <duzina;i++)
            skrivenArray[i]='_';


        stage.setTitle("IGRA VESALA");

        borderPane = new BorderPane();



        //delovi za Vesala - polozena linija******************************************************
        Line line = new Line(20,250,350,250 );
        line.setStrokeWidth(5);
        line.setStroke(Color.RED);


        // Vesalo
        Polyline polyline = new Polyline(100,250,100,40,200,40,200,100 );
        polyline.setStrokeWidth(5);
        polyline.setStroke(Color.RED);


        //Covek koji visi**************************************************************************

        //glava

        glava = new Circle(20);
        glava.setStroke(Color.LIGHTBLUE);
        glava.setCenterX(200);
        glava.setCenterY(120);
        glava.setFill(Color.LIGHTBLUE);
        glava.setStroke(Color.BLACK);


        //telo

        telo = new Line(200,140, 200,205);
        telo.setStroke(Color.LIGHTBLUE);
        telo.setStrokeWidth(3);
        telo.setStroke(Color.BLACK);

        //leva ruka

         levaruka = new Line (200,160, 170,130);
        levaruka.setStroke(Color.LIGHTBLUE);
        levaruka.setStrokeWidth(3);
        levaruka.setStroke(Color.BLACK);

        //desna ruka

         desnaruka = new Line (200,160, 230,130);
        desnaruka.setStroke(Color.LIGHTBLUE);
        desnaruka.setStrokeWidth(3);
        desnaruka.setStroke(Color.BLACK);

        //leva noga

         levanoga = new Line (200,205, 170,235);
        levanoga.setStroke(Color.LIGHTBLUE);
        levanoga.setStrokeWidth(3);
        levanoga.setStroke(Color.BLACK);

        //desna noga
         desnanoga = new Line (200,205, 230,235);
        desnanoga.setStroke(Color.LIGHTBLUE);
        desnanoga.setStrokeWidth(3);
        desnanoga.setStroke(Color.BLACK);

        Group deloviTela = new Group(line,polyline);
        Group deloviVesala = new Group(glava,telo,levaruka,desnaruka,levanoga,desnanoga);
        Group finGroup = new Group(deloviTela,deloviVesala);

        //END COVEKA KOJI VISI***********************************************************************

         donjiDeo = new GridPane();
        donjiDeo.setAlignment(Pos.CENTER);

      textField1.setAlignment(Pos.CENTER);



       // Label za prikaz reci

        String string = new String (skrivenArray);

         skrivenaRec = new Label(string);
         skrivenaRec.setAlignment(Pos.CENTER);
         skrivenaRec.setFont(Font.font("Verdana", FontWeight.BOLD, 18));



        // Label sa kratkim opisom da li je slovo pogodjeno etc

         infolabel = new Label("Dobro DOsli u igru vesala");

         infolabel.setAlignment(Pos.CENTER);



       // donjiDeo.setGridLinesVisible(true); - testiranje da li su linije na grippanu ok


        // dodavanje labela u dodnji gridpane
        donjiDeo.add(skrivenaRec,0,0);
        donjiDeo.add(textField1,0,1);
        donjiDeo.add(infolabel,0,2);


        // dodavanje delova u borderpane
        borderPane.setCenter(deloviTela);
        borderPane.setBottom(donjiDeo);


        // SVA AKCIJA POZIENJE OVDE!!!!-----------------------------------------------------------------

        textField1.setOnAction(e ->textUnos() );


        //---------------------------------------------------------------------------------------------


        Scene scena = new Scene(borderPane, 365,350);
        stage.setScene(scena);
        stage.show();

    }

    public static void main(String[] args) {
       launch(args);
   }


   public  void textUnos () {

        // unos teksa sa lebela u char sequencu

        CharSequence mladen =  textField1.getCharacters();

       System.out.println(mladen);



       if(mladen.length()==1){
           char a = mladen.charAt(0);
           System.out.println(a);

           for(int i =0 ;i<charArray.length;i++){

               if(charArray[i]==a) {
                   pogodjenaSlova++;
                   skrivenArray[i] = a;
               }


               }

               if(pogodjenaSlova==0){
                   pokusaj--;
                   System.out.println("trazeno slovo ne postoji, broj pokusaja "+pokusaj);
                   infolabel.setText("trazeno slovo ne postoji, broj pokusaja "+pokusaj);
                   if (pokusaj==5)
                       borderPane.getChildren().add(glava);
                   if (pokusaj==4)
                       borderPane.getChildren().add(telo);
                   if (pokusaj==3)
                       borderPane.getChildren().add(levaruka);
                   if (pokusaj==2)
                       borderPane.getChildren().add(desnaruka);
                   if (pokusaj==1)
                       borderPane.getChildren().add(levanoga);
                   if (pokusaj<=0) {
                       borderPane.getChildren().add(desnanoga);
                       infolabel.setText("GAME  OVER");
                       textField1.setDisable(true);
                   }

               }
               else
               {
                   System.out.println("Pogodili ste slovo, broj pokusaja je "+pokusaj);
                   System.out.println("Poodjena slova" + pogodjenaSlova);
                   System.out.println("duzina reci "+ duzina);
                   infolabel.setText("Pogodili ste slovo, broj pokusaja je "+pokusaj);

               }

               if (pogodjenaSlova==duzina) {
                   infolabel.setText("YOU HAVE WON!!!");
                   textField1.setDisable(true);
               }
           }

           stampajrec(skrivenArray);
            String noviString = new String (skrivenArray);
            skrivenaRec.setText(noviString);




       textField1.clear();




   }

   public void stampajrec(char passArra []){


       for (int i = 0; i<passArra.length;i++)
           System.out.print(passArra[i]+" ");
       System.out.println();


   }


}
