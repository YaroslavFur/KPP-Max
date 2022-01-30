package com.example.kpp_7;

import com.example.kpp_7.Logic.BlockMatrix;
import com.example.kpp_7.Logic.GaussMethod;
import com.example.kpp_7.Logic.Main;
import com.example.kpp_7.Logic.ThreadMonitor;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    private Main program = new Main();
    private GaussMethod gaussMethod = new GaussMethod();
    private ThreadMonitor threadMonitor = new ThreadMonitor();

    @FXML TextField input00;
    @FXML TextField input01;
    @FXML TextField input07;
    @FXML TextField input10;
    @FXML TextField input11;
    @FXML TextField input17;

    @FXML TextField input22;
    @FXML TextField input23;
    @FXML TextField input27;
    @FXML TextField input32;
    @FXML TextField input33;
    @FXML TextField input37;

    @FXML TextField input44;
    @FXML TextField input45;
    @FXML TextField input47;
    @FXML TextField input54;
    @FXML TextField input55;
    @FXML TextField input57;

    @FXML TextField inputThreads;

    @FXML Label resultX1;
    @FXML Label resultY1;
    @FXML Label resultX2;
    @FXML Label resultY2;
    @FXML Label resultX3;
    @FXML Label resultY3;

    @FXML Label threadName;
    @FXML Label threadState;
    @FXML Label threadPriority;
    @FXML Label threadIsOn;
    @FXML Label timeTaken;

    @FXML
    protected void onStartClick() {
        BlockMatrix block1 = new BlockMatrix(input00.getText(), input01.getText(), input07.getText(), input10.getText(),
                input11.getText(), input17.getText());
        BlockMatrix block2 = new BlockMatrix(input22.getText(), input23.getText(), input27.getText(), input32.getText(),
                input33.getText(), input37.getText());
        BlockMatrix block3 = new BlockMatrix(input44.getText(), input45.getText(), input47.getText(), input54.getText(),
                input55.getText(), input57.getText());

        if (program.setNumOfThreads(inputThreads.textProperty())) {

            resultX1.textProperty().bind(block1.resultX);
            resultY1.textProperty().bind(block1.resultY);
            resultX2.textProperty().bind(block2.resultX);
            resultY2.textProperty().bind(block2.resultY);
            resultX3.textProperty().bind(block3.resultX);
            resultY3.textProperty().bind(block3.resultY);

            program.blocks.push(block1);
            program.blocks.push(block2);
            program.blocks.push(block3);

            threadName.textProperty().bind(threadMonitor.threadName);
            threadState.textProperty().bind(threadMonitor.threadState);
            threadPriority.textProperty().bind(threadMonitor.threadPriority);
            threadIsOn.textProperty().bind(threadMonitor.threadIsOn);

            timeTaken.textProperty().bind(gaussMethod.timeTaken);


            program.run(gaussMethod, threadMonitor);
        }
    }
}