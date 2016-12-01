package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import static sample.Simulator.integerList;
import static sample.Simulator.loadListFromFile;
import static sample.Simulator.saveListToFile;


public class Controller {
    private ObservableList<Priority> priorities = FXCollections.observableArrayList(Priority.values());
    private ObservableList<Integer> counts = FXCollections.observableArrayList(1, 2, 4, 8, 16);
    @FXML
    private ComboBox<Priority> comboBox1 = new ComboBox<Priority>(priorities);
    @FXML
    private ComboBox<Integer> comboBox2 = new ComboBox<Integer>(counts);
    @FXML
    private Button button1, button2, button3, button4, button5;
    @FXML
    private TextField textField1, textField2;
    @FXML
    private TextArea textArea1, textArea2;

    public void setButton1(ActionEvent event) {
        comboBox1.setItems(priorities);
        comboBox1.setValue(priorities.get(4));
        button1.disableProperty().setValue(true);
        button2.disableProperty().setValue(true);
        button3.disableProperty().setValue(true);
        button4.disableProperty().setValue(true);
        comboBox2.disableProperty().setValue(true);
        textField1.setText("defaultProcess");
        textField2.setText("defaultValue");
        textField1.disableProperty().setValue(false);
        textField2.disableProperty().setValue(false);
        comboBox1.disableProperty().setValue(false);
        button5.disableProperty().setValue(false);
    }

    public void setButton5(ActionEvent event) {
        button1.disableProperty().setValue(false);
        button2.disableProperty().setValue(false);
        button3.disableProperty().setValue(false);
        button4.disableProperty().setValue(false);
        comboBox2.disableProperty().setValue(false);
        textField1.disableProperty().setValue(true);
        textField2.disableProperty().setValue(true);
        comboBox1.disableProperty().setValue(true);
        button5.disableProperty().setValue(true);
        integerList.addInTail(new ListElement<String>(textField1.getText(), comboBox1.getValue(), textField2.getText()));
        textField1.clear();
        textField2.clear();
        textArea1.setText(integerList.viewList());
        comboBox2.setItems(counts);
        comboBox2.setValue(counts.get(0));

    }

    public void setComboBox2(Event event) {
        comboBox2.setItems(counts);
        comboBox2.setValue(counts.get(0));
        button4.disableProperty().setValue(false);
        button1.disableProperty().setValue(false);
    }

    public void setButton4(ActionEvent event) {
        textArea2.clear();
        int countOfCores;
        Thread t = Thread.currentThread();
        countOfCores = comboBox2.getValue();
        Simulator[] simulators = new Simulator[countOfCores];
        for (int i = 0; i <= countOfCores - 1; i++) {
            String name = "Core " + i;
            simulators[i] = new Simulator(name, textArea2, textArea1);
            simulators[i].statr();
            try {
                Thread.sleep(1);
            } catch (Exception e) {
            }
        }
    }

    public void setButton2(ActionEvent event) {
        loadListFromFile("file1.ser");
        textArea1.clear();
        textArea1.setText(integerList.viewList());
        comboBox2.setItems(counts);
        textArea2.clear();
    }

    public void setButton3(ActionEvent event) {
        saveListToFile("file1.ser");
    }

}
