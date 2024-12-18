package controller;

import java.util.Collection;
import java.util.Stack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;


public class TelaController {

    @FXML
    private ProgressBar barraPeso;

    @FXML
    private ProgressBar barraUnidade;

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnRemover;


    @FXML
    private TextField nomeProduto;

    @FXML
    private Slider qtdAdicionar;

    @FXML
    private Pane telaAdd;

    @FXML
    private Pane telaRemover;

    @FXML
    private Pane telaLista;

    @FXML
    private Label qtdProduto;

    @FXML
    private Label qtdTotal;

    @FXML
    private Slider pesoAdicionar;

    @FXML
    private Label pesoProduto;

    @FXML
    private Label pesoTotal;

    @FXML
    private VBox listaProdutos;

    @FXML
    private TextField nomeProdutoRemover;

    @FXML
    private Label qtdProdutoRemover;

    @FXML
    private Slider qtdRemover;

    @FXML
    private Label pesoProdutoRemover;

    @FXML
    private Label pesoTotalProduto;

    @FXML
    private Label pesoUnProduto;

    @FXML
    private AnchorPane rolagem;

    
    @FXML
    void menuAdd(MouseEvent event) {
        telaRemover.setVisible(false);
        if (telaAdd.isVisible()) {
            telaAdd.setVisible(false);
            telaLista.setScaleX(1.3);
            telaLista.setScaleY(1.3);
            telaLista.layoutXProperty().set(450);
        } else {
            telaAdd.setVisible(true);
            telaLista.setScaleX(1);
            telaLista.setScaleY(1);
            telaLista.layoutXProperty().set(800);
        }
    }
    
    @FXML
    void pesoAtual(MouseEvent event) {
        
        double peso = pesoAdicionar.getValue();
        String Peso = String.format("%.1f", peso);
        pesoProduto.setText(Peso);

        double pesoLimite = 90 - Double.parseDouble(pesoTotal.getText().replace(",", "."));
        double qtdLimite = 100 - Double.parseDouble(qtdTotal.getText().replace(",", "."));
        
        double qtdMax = pesoLimite / Double.parseDouble(Peso.replace(",", "."));
        
        String QtdMax = String.format("%.0f", qtdMax);
        
        double qtd = Integer.parseInt(QtdMax);

        if (qtdLimite < 1) {
            qtdAdicionar.setMax(0.1);
            qtdAdicionar.setMajorTickUnit(0.1);
            btnAdicionar.setDisable(true);
        } else if (qtdMax>= qtdLimite) {
            qtdAdicionar.setDisable(false);
            qtdAdicionar.setMax(qtdLimite);
            qtdAdicionar.setMajorTickUnit(qtdLimite/4);
        } else {
            qtdAdicionar.setDisable(false);
            qtdAdicionar.setMax((int)qtd);
            if ((int)qtd/4 <= 1) {
                qtdAdicionar.setMajorTickUnit(1);
            } else {
                qtdAdicionar.setMajorTickUnit((int)qtd/4);
            }
            
        }
        if (qtdAdicionar.getValue() >= qtdMax) {
            qtdAdicionar.setValue(qtdMax);
            qtdProduto.setText(String.format("%.0f", qtdAdicionar.getValue()));
        } else {
            
        }
        
        if (qtdLimite < 1) {
            qtdAdicionar.setMax(1);
            qtdAdicionar.setMajorTickUnit(1);
            qtdAdicionar.setValue(0);
            btnAdicionar.setDisable(true);
            } else {
                pesoAdicionar.setDisable(false);
                qtdAdicionar.setDisable(false);
                btnAdicionar.setDisable(false);
            if (pesoLimite < 5) {
                pesoAdicionar.setMax(pesoLimite);
                pesoAdicionar.setMajorTickUnit(pesoLimite/4);
            } else {
                
                pesoAdicionar.setMax(5);
                pesoAdicionar.setMajorTickUnit(5/4);
            }
        }
        if (qtd < 1) {
            btnAdicionar.setDisable(true);
        } else {
            btnAdicionar.setDisable(false);
            
        }
    }
    
    public void atualizar(){
        
        double peso = pesoAdicionar.getValue();
        String Peso = String.format("%.1f", peso);
        pesoProduto.setText(Peso);

        double pesoLimite = 90 - Double.parseDouble(pesoTotal.getText().replace(",", "."));
        double qtdLimite = 100 - Double.parseDouble(qtdTotal.getText().replace(",", "."));


        if (pesoLimite < 0.1) {
            pesoLimite = 0.1;
            pesoAdicionar.setValue(0);
            pesoAdicionar.setDisable(true);
            qtdAdicionar.setValue(0);
            qtdAdicionar.setDisable(true);
            btnAdicionar.setDisable(true);
        }else{
            pesoAdicionar.setDisable(false);
            qtdAdicionar.setDisable(false);
            btnAdicionar.setDisable(false);
        }
        
        double qtdMax = pesoLimite / pesoAdicionar.getValue();
        
        String QtdMax = String.format("%.0f", qtdMax);
        
        double qtd = Integer.parseInt(QtdMax);

        if (qtdLimite < 1 || qtdMax < 1) {
            qtdAdicionar.setValue(0);
            qtdAdicionar.setMin(0);
            pesoAdicionar.setValue(0);
            pesoAdicionar.setDisable(true);
            qtdAdicionar.setValue(0);
            qtdAdicionar.setDisable(true);
        } else {
            if (qtdMax>= qtdLimite) {
                qtdAdicionar.setMax(qtdLimite);
                qtdAdicionar.setMajorTickUnit(qtdLimite/4);
            } else {
                
                qtdAdicionar.setMax((int)qtd);
                qtdAdicionar.setMajorTickUnit(qtd/4);
            }
        }
        
        
        if (pesoLimite < 5) {
            if (pesoLimite <= 0.1 ) {
                pesoAdicionar.setMax(0.1);
                pesoAdicionar.setMajorTickUnit(0.1/4);
                btnAdicionar.setDisable(true);
            } else {
                pesoAdicionar.setMajorTickUnit(pesoLimite/4);
            }
            pesoAdicionar.setMax(pesoLimite);
            pesoAdicionar.setMajorTickUnit(pesoLimite/4);
        } else {
            
            pesoAdicionar.setMax(5);
            pesoAdicionar.setMajorTickUnit((int)5/4);
        }

        double qtd2 = qtdAdicionar.getValue();
        String Qtd2 = String.format("%.0f", qtd2);
        qtdProduto.setText(Qtd2);


        if (Double.parseDouble(pesoTotal.getText().replace(",", ".")) >= 90 || Double.parseDouble(qtdTotal.getText().replace(",", ".")) >= 100) {
            btnAdicionar.setDisable(true);
            pesoAdicionar.setValue(0);
            pesoAdicionar.setDisable(true);
            qtdAdicionar.setValue(0);
            qtdAdicionar.setDisable(true);
        } else {
            pesoAdicionar.setDisable(false);
            qtdAdicionar.setDisable(false);
            btnAdicionar.setDisable(false);
        }
        if (qtd < 1 || nomeProduto.getText() == "") {
            btnAdicionar.setDisable(true);
        } else {
            btnAdicionar.setDisable(false);
            
        }

    }
    
    @FXML
    void qtdAtual(MouseEvent event) {
        double qtd = qtdAdicionar.getValue();
        String Qtd = String.format("%.0f", qtd);
        qtdProduto.setText(Qtd);
        if (qtd < 1) {
            btnAdicionar.setDisable(true);
        } else {
            btnAdicionar.setDisable(false);
            
        }
    }
    
    public class InfoProduto {
        private String nome;
        
        private int qtd;
        private double peso;
        private HBox informacao = new HBox();

        public InfoProduto(String nome, double peso, int qtd) {
            this.nome = nome;
            this.qtd = qtd;
            this.peso = peso;
            

            informacao.setPrefWidth(340);
            informacao.setPrefHeight(30);

            Label Nome = new Label(nome);
            Label Peso = new Label(Double.toString(peso));
            Label Unid = new Label(Integer.toString(qtd));


            informacao.getChildren().addAll(Nome, Peso, Unid);

            Nome.setPrefWidth(238);
            Peso.setPrefWidth(51);
            Unid.setPrefWidth(51);
            }
            public String getNome() {
                return nome;
            }
            public int getQtd() {
                return qtd;
            }
            public void RemoverQtd(int sub) {
                this.qtd -= sub;
            }
            public double getPeso() {
                return peso;
            }
            public void RemoverPeso(double sub) {
                this.peso -= sub;
            }

        

    }
    
    Stack<InfoProduto> pilhaProduto = new Stack<>();
    
    public void addItenLista(InfoProduto info){
        String nome = info.getNome();
        double peso = info.getPeso();
        int unidade = info.getQtd();
        InfoProduto produto = new InfoProduto(nome, peso, unidade);
        pilhaProduto.add(produto);

        listaProdutos.getChildren().add(0, pilhaProduto.peek().informacao);
        rolagem.setPrefHeight(rolagem.getPrefHeight()+17);
    }
    
    @FXML
    void add(ActionEvent event) {
        String produto = nomeProduto.getText();
        double peso = Double.parseDouble(pesoProduto.getText().replace(",", "."));
        int qtd = Integer.parseInt(qtdProduto.getText());

        addItenLista(new InfoProduto(produto, peso, qtd));

        double Peso = (peso/90)*qtd;
        barraPeso.setProgress(barraPeso.getProgress()+Peso);
        pesoTotal.setText(String.format("%.1f", barraPeso.getProgress()*90));
        
        double Qtd = (double)qtd/100;
        barraUnidade.setProgress(barraUnidade.getProgress()+Qtd);
        qtdTotal.setText(String.format("%.0f", barraUnidade.getProgress()*100));

        
        atualizar();
        pesoAdicionar.setValue(0.1);
        qtdAdicionar.setValue(1);
        if (pesoAdicionar.getValue() < 0.1 || qtdAdicionar.getValue() < 1) {
            btnAdicionar.setDisable(true);
        } else {
            btnAdicionar.setDisable(false);
        }

        if (Double.parseDouble(pesoTotal.getText().replace(",", ".")) >= 90 ||Double.parseDouble(qtdTotal.getText().replace(",", ".")) >= 100) {
            btnAdicionar.setDisable(true);
            nomeProduto.setDisable(true);
        } else {
            btnAdicionar.setDisable(false);
            nomeProduto.setDisable(false);
        }
        nomeProduto.setText("");
        pesoProduto.setText("0,1");
        qtdProduto.setText("1");
        pesoAdicionar.setValue(0.1);
        qtdAdicionar.setValue(1);
    }

    @FXML
    void menuRemover(MouseEvent event) {
        telaAdd.setVisible(false);
        if (telaRemover.isVisible()) {
            telaLista.setScaleX(1.3);
            telaLista.setScaleY(1.3);
            telaLista.layoutXProperty().set(450);
            telaRemover.setVisible(false);
        } else {
            telaRemover.setVisible(true);
            telaLista.setScaleX(1);
            telaLista.setScaleY(1);
            telaLista.layoutXProperty().set(800);

            atualizarRemover();
        }
    }


    
    public void atualizarRemover(){
        if (pilhaProduto.size() == 0) {
            nomeProdutoRemover.setText("");
            qtdRemover.setMax(0);
            qtdRemover.setDisable(true);
            btnRemover.setDisable(true);
            pesoUnProduto.setText("0 Kg");
            pesoTotalProduto.setText("0 Kg");
            pesoProdutoRemover.setText("0 Kg");

            pesoTotal.setText("0.0");
            qtdTotal.setText("0");

            barraPeso.setProgress(0);
            barraUnidade.setProgress(0);

        } else {
            qtdRemover.setDisable(false);
            btnRemover.setDisable(false);
            InfoProduto topo = pilhaProduto.peek();
            nomeProdutoRemover.setText(topo.getNome());
            qtdRemover.setMax(topo.getQtd());
            if (topo.getQtd() / 4 <= 4) {
                qtdRemover.setMajorTickUnit(1);
                
            } else {
                qtdRemover.setMajorTickUnit(topo.getQtd() / 4);
                
            }

            pesoUnProduto.setText(Double.toString(topo.getPeso()) + " Kg");
            pesoTotalProduto.setText(String.format("%.2f Kg", topo.getPeso() * topo.getQtd()));
            pesoProdutoRemover.setText(String.format("%.2f Kg", topo.getPeso()));
        }
        qtdRemover.setValue(qtdRemover.getMax());
        qtdProdutoRemover.setText(String.format("%.0f", qtdRemover.getMax()));
        btnRemover.setDisable(false);
        qtdRemover.setDisable(false);
        if (pilhaProduto.size()<1) {
            btnRemover.setDisable(true);
            qtdRemover.setDisable(true);
        }
    }

    @FXML
    void removerQtdAtual(MouseEvent event) {
        double qtd = qtdRemover.getValue();
        String Qtd = String.format("%.0f", qtd);
        qtdProdutoRemover.setText(Qtd);
        
        if (qtd < 1) {
            btnRemover.setDisable(true);
        } else {
            btnRemover.setDisable(false);
            
        }
        if (pilhaProduto.size() == 0) {
            nomeProdutoRemover.setText("Produto");
        } else {
            InfoProduto topo = pilhaProduto.peek();
            nomeProdutoRemover.setText(topo.getNome());
            qtdRemover.setMax(topo.getQtd());
            
            if ((double)topo.getQtd()/4 <= 5) {
                qtdRemover.setMajorTickUnit(2);
            } else {
                qtdRemover.setMajorTickUnit((double)topo.getQtd()/4);
            }
            

            pesoUnProduto.setText(Double.toString(topo.getPeso())+ " Kg");
            pesoTotalProduto.setText(String.format("%.2f Kg",topo.getPeso()*topo.getQtd()));
            pesoProdutoRemover.setText(String.format("%.2f Kg", topo.getPeso()*(int)qtdRemover.getValue()));
        }
    }

    @FXML
    void Remover(ActionEvent event) {
        if (pilhaProduto.size() > 0) {
                InfoProduto topo = pilhaProduto.peek();
            double pesoRemovido = topo.getPeso()*(int)qtdRemover.getValue();
            topo.RemoverQtd((int)qtdRemover.getValue());

            double peso = Double.parseDouble(String.format("%.2f", pesoRemovido/90).replace(",", "."));
            double un = qtdRemover.getValue() / 100;
            if (peso >= barraPeso.getProgress()) {
                barraPeso.setProgress(0);
            } else {
                barraPeso.setProgress(barraPeso.getProgress()-peso);
            }
            
            if (un >= barraUnidade.getProgress()) {
                barraUnidade.setProgress(0);
            } else {
                barraUnidade.setProgress(barraUnidade.getProgress() - un);
            }
            pesoTotal.setText(String.format("%.1f", barraPeso.getProgress()*90));
            qtdTotal.setText(String.format("%.0f", barraUnidade.getProgress()*100));
            
            if (topo.getQtd() <= 0) {
                pilhaProduto.pop();
                listaProdutos.getChildren().remove(0);
                rolagem.setPrefHeight(rolagem.getPrefHeight()-17);
            } else {
                Label Unid = new Label(Integer.toString(topo.getQtd()));
                
                topo.informacao.getChildren().remove(2);
                topo.informacao.getChildren().add(Unid);
            }
        } else {
            
        }
        
        atualizarRemover();
        atualizar();
    }
    
}
