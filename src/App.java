import java.util.Arrays;
import java.util.List;

import Model.Impressora3D;
import Model.MaterialImpressao;
import Model.ProjetoImpressao;
import Model.CalculadoraCusto;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    
    private Impressora3D impressoraSelecionada = null;
    private MaterialImpressao materialSelecionado = null;
    private CalculadoraCusto calculadora = new CalculadoraCusto();

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Impressora3D ender3 = new Impressora3D("Creality Ender 3", 1500.00, 350, "imagens/ender3.jpg", "A Creality Ender 3 é uma impressora 3D de entrada muito popular, famosa por ser barata, robusta e ideal para iniciantes e hobbistas.");
        Impressora3D crealityK1 = new Impressora3D("Creality K1", 3500.00, 500, "imagens/k1.jpg", "A Creality K1 é uma impressora 3D de alta performance da categoria CoreXY, famosa por ser extremamente veloz e já vir totalmente montada e fechada de fábrica.");
        Impressora3D bambuA1 = new Impressora3D("Bambu Lab", 4200.00, 400, "imagens/bambu_a1.jpg", "A Bambu Lab A1 é uma impressora 3D do tipo \"bedslinger\" (com mesa móvel) revolucionária, famosa por sua altíssima velocidade, calibração 100% automatizada e facilidade extrema de uso (estilo plug-and-play)");

        MaterialImpressao baixaDensidade = new MaterialImpressao("PLA baixa densidade", "Baixa", 0.08);
        MaterialImpressao mediaDensidade = new MaterialImpressao("PLA média densidade", "Média", 0.12);
        MaterialImpressao altaDensidade = new MaterialImpressao("PLA alta densidade", "Alta", 0.18);

        List<Impressora3D> listaImpressoras = Arrays.asList(ender3, crealityK1, bambuA1);
        List<MaterialImpressao> listaMaterial = Arrays.asList(baixaDensidade, mediaDensidade, altaDensidade);
        
        Label lblEscolherImpressora = new Label("1- Escolha da Impressora");
        lblEscolherImpressora.setStyle("-fx-font-weight: bold;");
        Label lblImpressora = new Label("Impressora:");
        Label lblPrecoAquisicao = new Label("Preço de Aquisição: R$ 0,00");
        Label lblPotencia = new Label("Potência: 0W");
        Label lblDescricao = new Label("Descrição: Selecione uma impressora.");
        lblDescricao.setWrapText(true);
        lblDescricao.setMaxWidth(300);

        ImageView imgImpressora = new ImageView();
        imgImpressora.setFitWidth(150);
        imgImpressora.setPreserveRatio(true);
        imgImpressora.setSmooth(true);

        ComboBox<String> comboImpressora = new ComboBox<>();
        comboImpressora.getItems().addAll(ender3.getModelo(), crealityK1.getModelo(), bambuA1.getModelo());
        comboImpressora.setPromptText("Selecione uma Impressora");

        Label lblDadosProjeto = new Label("2- Dados do Projeto de Impressão");
        lblDadosProjeto.setStyle("-fx-font-weight: bold;");

        Label lblNomeArquivo = new Label("Nome do arquivo: ");
        TextField txtNomeArquivo = new TextField("suporte-celular.stl");
        
        Label lblDescricaoProjeto = new Label("Descrição do Projeto: ");
        TextArea txtDescricaoProjeto = new TextArea("Suporte ajustável");
        txtDescricaoProjeto.setPrefRowCount(2);
        txtDescricaoProjeto.setWrapText(true);

        Label lblMaterial = new Label("Material: ");
        ComboBox<String> comboMaterial = new ComboBox<>();
        comboMaterial.getItems().addAll(baixaDensidade.getMaterial(), mediaDensidade.getMaterial(), altaDensidade.getMaterial());
        comboMaterial.setPromptText("Selecione o Material");

        Label lblDensidade = new Label("Densidade / Qualidade: ");
        ComboBox<String> comboDensidade = new ComboBox<>();
        comboDensidade.getItems().addAll(baixaDensidade.densidadeQualidade(), mediaDensidade.densidadeQualidade(), altaDensidade.densidadeQualidade());
        comboDensidade.setPromptText("Selecione a Qualidade");
        comboDensidade.setDisable(true);
        comboDensidade.setStyle("-fx-opacity: 1.0; " + "-fx-text-fill: #000000; " + "-fx-font-weight: bold; " + "-fx-background-color: #E0E0E0;");

        Label lblQuantidadeMaterial = new Label("Peso do Modelo (g):");
        TextField txtQuantidadeMaterial = new TextField("120");

        Label lblPesoSuporte = new Label("Peso do Suporte (g):");
        TextField txtPesoSuporte = new TextField("0");

        Label lblTempoImpressao = new Label("Tempo Impressão (h):");
        TextField txtTempoImpressao = new TextField("5");

        Label lblTempoPreparo = new Label("Tempo Preparo (h):");
        TextField txtTempoPreparo = new TextField("0");

        Label lblQuantidadePecas = new Label("Qtd Peças:");
        TextField txtQuantidadePecas = new TextField("1");

        Label lblTaxaFalha = new Label("Considerar Falha?");
        CheckBox chkAplicarFalha = new CheckBox("Sim (10%)");
        chkAplicarFalha.setSelected(false);

        Label lblTarifaEnergia = new Label("Tarifa de energia:");
        CheckBox chkDiurna = new CheckBox("Diurna (R$ 0,90)");
        CheckBox chkNoturna = new CheckBox("Noturna (R$ 0,60)");
        chkNoturna.setSelected(true);
        chkDiurna.setOnAction(e -> { if(chkDiurna.isSelected()) chkNoturna.setSelected(false); });
        chkNoturna.setOnAction(e -> { if(chkNoturna.isSelected()) chkDiurna.setSelected(false); });

        Label lblMaoObra = new Label("Valor Mão de Obra/h:");
        TextField txtMaoObra = new TextField("4");

        Label lblMargemLucro = new Label("Margem Lucro (%):");
        TextField txtMargemLucro = new TextField("30");

        Button btnCalcular = new Button("Calcular Custos");
        btnCalcular.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        btnCalcular.setMaxWidth(Double.MAX_VALUE);
        Label lblResultadosTitulo = new Label("3- Resumo de Custos");
        lblResultadosTitulo.setStyle("-fx-font-weight: bold;");
        Label lblCustoMaterial = new Label("Custo do material: R$ 0,00");
        Label lblCustoMaquina = new Label("Custo da máquina: R$ 0,00");
        Label lblCustoEnergia = new Label("Custo de energia: R$ 0,00");
        Label lblCustoMaoObra = new Label("Custo de mão de obra: R$ 0,00");
        Label lblCustoManutencao = new Label("Custo de manutenção: R$ 0,00");
        Label lblCustoTotal = new Label("Custo total: R$ 0,00");
        lblCustoTotal.setStyle("-fx-font-weight: bold;");
        Label lblValorVenda = new Label("Valor de venda sugerido: R$ 0,00");
        lblValorVenda.setStyle("-fx-font-weight: bold; -fx-text-fill: green;");

        Label lblDivisoria = new Label("-------------------------------------------------------");


        comboImpressora.setOnAction(e -> {
            String modeloSelecionado = comboImpressora.getValue();
            for (Impressora3D imp : listaImpressoras) {
                if (imp.getModelo().equals(modeloSelecionado)) {
                    impressoraSelecionada = imp;
                    lblPrecoAquisicao.setText(String.format("Preço de Aquisição: R$ %.2f", imp.getPreco()));
                    lblPotencia.setText("Potência: " + imp.getPotencia() + "W");
                    lblDescricao.setText("Descrição: " + imp.getDescricao());
                    try {
                        Image foto = new Image("file:" + imp.getImagem()); 
                        imgImpressora.setImage(foto);
                    } catch (Exception ex) {
                        System.out.println("Erro ao carregar a imagem: " + ex.getMessage());
                    }
                    break;
                }
            }
        });

        comboMaterial.setOnAction(e -> {
            String materialSelecionadoStr = comboMaterial.getValue();
            for (MaterialImpressao mat : listaMaterial) {
                if (mat.getMaterial().equals(materialSelecionadoStr)) {
                    materialSelecionado = mat;
                    comboDensidade.setValue(mat.densidadeQualidade());
                    break;
                }
            }
        });

        btnCalcular.setOnAction(e -> {
            if (impressoraSelecionada == null || materialSelecionado == null) {
                lblCustoTotal.setText("Selecione uma impressora e um material!");
                return;
            }
            try {
                double pesoModelo = Double.parseDouble(txtQuantidadeMaterial.getText());
                double pesoSuporte = Double.parseDouble(txtPesoSuporte.getText());
                double tempoImpressao = Double.parseDouble(txtTempoImpressao.getText());
                double tempoPreparo = Double.parseDouble(txtTempoPreparo.getText());
                int qtdPecas = Integer.parseInt(txtQuantidadePecas.getText());
                double valorMaoObra = Double.parseDouble(txtMaoObra.getText());
                double margemLucroPorc = Double.parseDouble(txtMargemLucro.getText());

                ProjetoImpressao projeto = new ProjetoImpressao(
                    txtNomeArquivo.getText(),
                    txtDescricaoProjeto.getText(),
                    pesoModelo,
                    pesoSuporte,
                    qtdPecas,
                    tempoImpressao,
                    tempoPreparo
                );

                if (projeto.getPesoModelo() <= 0 || projeto.getTempoImpressao() <= 0 || projeto.getQuantidadePecas() <= 0) {
                    lblCustoTotal.setText("Valores devem ser maiores que zero!");
                    return;
                }

                double valorKwh = chkDiurna.isSelected() ? 0.90 : 0.60;
                double pesoTotal =  projeto.getPesoModelo() + projeto.getPesoSuporte();
                double cMaterial = calculadora.custoMaterial( pesoTotal, materialSelecionado.getGrama());

                if (chkAplicarFalha.isSelected()) {
                    cMaterial = calculadora.taxaFalha(cMaterial);
                }

                double cMaquina = calculadora.custoHora(impressoraSelecionada.getPreco(), projeto.getTempoImpressao());
                double cEnergia = calculadora.custoEnergia(impressoraSelecionada.getPotencia(), projeto.getTempoImpressao(), valorKwh);
                double cMaoObra = calculadora.custoMaoObra(projeto.getTempoImpressao() + projeto.getTempoPreparo(), valorMaoObra);
                double cManutencao = calculadora.custoManutencao(projeto.getTempoImpressao());

                double cTotalObjeto = calculadora.custoTotal(cMaterial, cMaquina, cEnergia, cMaoObra, cManutencao);
                double cTotalLote = cTotalObjeto * projeto.getQuantidadePecas();
                double vVendaLote = calculadora.margemLucro(cTotalLote,margemLucroPorc);

                lblCustoMaterial.setText(String.format("Custo do material: R$ %.2f", cMaterial * projeto.getQuantidadePecas()));
                lblCustoMaquina.setText(String.format("Custo da máquina: R$ %.2f", cMaquina * projeto.getQuantidadePecas()));
                lblCustoEnergia.setText(String.format("Custo de energia: R$ %.2f", cEnergia * projeto.getQuantidadePecas()));
                lblCustoMaoObra.setText(String.format("Custo de mão de obra: R$ %.2f", cMaoObra * projeto.getQuantidadePecas()));
                lblCustoManutencao.setText(String.format("Custo de manutenção: R$ %.2f", cManutencao * projeto.getQuantidadePecas()));
                lblCustoTotal.setText(String.format("Custo total do lote: R$ %.2f", cTotalLote));
                lblValorVenda.setText(String.format("Valor de venda sugerido: R$ %.2f", vVendaLote));
            } catch (NumberFormatException ex) {
                lblCustoTotal.setText("Erro: valores numéricos inválidos!");
            }
        });

        HBox escolherImpressora = new HBox(10, lblImpressora, comboImpressora);
        HBox descricaoArquivo = new HBox(10, lblNomeArquivo, txtNomeArquivo);
        HBox descricaoProjeto = new HBox(10, lblDescricaoProjeto, txtDescricaoProjeto);
        HBox escolhaPeriodo = new HBox(5, chkDiurna, chkNoturna);
        VBox detalhesImpressora = new VBox(5, lblPrecoAquisicao, lblPotencia, lblDescricao);

        GridPane gridCamposProjeto = new GridPane();
        gridCamposProjeto.setHgap(10);
        gridCamposProjeto.setVgap(8);

        gridCamposProjeto.add(lblMaterial, 0, 0);
        gridCamposProjeto.add(comboMaterial, 1, 0);
        gridCamposProjeto.add(lblDensidade, 0, 1);
        gridCamposProjeto.add(comboDensidade, 1, 1);

        gridCamposProjeto.add(lblQuantidadeMaterial, 0, 2);
        gridCamposProjeto.add(txtQuantidadeMaterial, 1, 2);
        gridCamposProjeto.add(lblPesoSuporte, 0, 3);
        gridCamposProjeto.add(txtPesoSuporte, 1, 3);

        gridCamposProjeto.add(lblTempoImpressao, 0, 4);
        gridCamposProjeto.add(txtTempoImpressao, 1, 4);
        gridCamposProjeto.add(lblTempoPreparo, 0, 5);
        gridCamposProjeto.add(txtTempoPreparo, 1, 5);

        gridCamposProjeto.add(lblQuantidadePecas, 0, 6);
        gridCamposProjeto.add(txtQuantidadePecas, 1, 6);

        gridCamposProjeto.add(lblTaxaFalha, 0, 7);
        gridCamposProjeto.add(chkAplicarFalha, 1, 7);

        gridCamposProjeto.add(lblTarifaEnergia, 0, 8);
        gridCamposProjeto.add(escolhaPeriodo, 1, 8);

        gridCamposProjeto.add(lblMaoObra, 0, 9);
        gridCamposProjeto.add(txtMaoObra, 1, 9);
        gridCamposProjeto.add(lblMargemLucro, 0, 10);
        gridCamposProjeto.add(txtMargemLucro, 1, 10);

        VBox cardEsquerdoImpressora = new VBox(10);
        cardEsquerdoImpressora.setPadding(new Insets(10));
        cardEsquerdoImpressora.getChildren().addAll(
            lblEscolherImpressora,
            escolherImpressora,
            detalhesImpressora,
            lblDivisoria,
            lblDadosProjeto,
            descricaoArquivo,
            descricaoProjeto,
            gridCamposProjeto,
            btnCalcular
        );
        
        VBox cardDireitoImpressora = new VBox(12);
        cardDireitoImpressora.setPadding(new Insets(10));
        cardDireitoImpressora.setStyle("-fx-background-color: #f9f9f9; -fx-background-radius: 10;");
        cardDireitoImpressora.getChildren().addAll(
            imgImpressora,
            lblDivisoria,
            lblResultadosTitulo,
            lblCustoMaterial,
            lblCustoMaquina,
            lblCustoEnergia,
            lblCustoMaoObra,
            lblCustoManutencao,
            lblCustoTotal,
            lblValorVenda
        );

        GridPane gridImpressora = new GridPane();
        gridImpressora.setHgap(20);
        gridImpressora.setVgap(10);
        gridImpressora.setPadding(new Insets(15));
        gridImpressora.setAlignment(Pos.CENTER);
        gridImpressora.add(cardEsquerdoImpressora, 0, 0);
        gridImpressora.add(cardDireitoImpressora, 1, 0);

        primaryStage.setTitle("Calculadora de Custo - Impressão 3D");
        primaryStage.setScene(new Scene(gridImpressora, 1000, 800));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}