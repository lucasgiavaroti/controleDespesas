package br.com.lucasgiavaroti;

import br.com.lucasgiavaroti.dao.DespesaDAO;
import br.com.lucasgiavaroti.model.Categoria;
import br.com.lucasgiavaroti.model.Despesa;
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int option;

        DespesaDAO dao = new DespesaDAO();

        System.out.println("Sistema de controle de despesas");

        System.out.println("\nEscolha a opção desejada: ");
        System.out.println("1 - Cadastrar despesa ");
        System.out.println("2 - Alterar despesa ");
        System.out.println("3 - Excluir despesa ");
        System.out.println("4 - Listar despesas ");

        System.out.print("\nDigite a opção: ");
        option = Integer.parseInt(scan.nextLine());

        switch (option) {

            case 1:
                Despesa despesa = new Despesa();
                System.out.println("\nVocê selecionou cadastrar despesa!");
                System.out.print("Digite a descrição da despesa: ");
                despesa.setDescricao(scan.nextLine());
                System.out.print("Digite a data de despesa: ");
                despesa.setData(LocalDate.parse(scan.nextLine()));
                System.out.print("Digite o valor: ");
                despesa.setValor(Double.parseDouble(scan.nextLine()));
                System.out.print("Digite a categoria: ");
                despesa.setCategoria(Categoria.valueOf(scan.nextLine()));

                scan.close();

                dao.save(despesa);
                break;

            case 2:
                System.out.println("\nVocê selecionou alterar despesa!");
                System.out.print("Digite o ID da despesa que você deseja alterar: ");
                Optional<Despesa> despesaOptional = dao.findById(Long.valueOf(scan.nextLine()));

                if(despesaOptional.isPresent()) {

                    System.out.println("A despesa selecionada foi: " + despesaOptional.get().getDescricao());

                    System.out.print("Digite a nova descrição: ");
                    despesaOptional.get().setDescricao(scan.nextLine());
                    System.out.print("Digite a nova data: ");
                    despesaOptional.get().setData(LocalDate.parse(scan.nextLine()));
                    System.out.print("Digite o novo valor: ");
                    despesaOptional.get().setValor(Double.parseDouble(scan.nextLine()));
                    System.out.print("Digite a nova categoria: ");
                    despesaOptional.get().setCategoria(Categoria.valueOf(scan.nextLine()));

                    dao.update(despesaOptional.get());
                }else{
                    System.out.println("Não existe despesa com o ID informado.");
                }
                break;

            case 3:
                System.out.println("\nVocê selecionou excluir despesa!");
                System.out.print("Digite o ID da despesa que você deseja excluir: ");
                despesaOptional = dao.findById(Long.valueOf(scan.nextLine()));
                if(despesaOptional.isPresent()) {

                    System.out.println("A despesa selecionada foi: " + despesaOptional.get().getDescricao());

                    System.out.print("Deseja mesmo excluir (S/N)?: ");
                    String delete = scan.nextLine();

                    if(delete.equalsIgnoreCase("S")) {
                        dao.delete(despesaOptional.get().getId());
                    }else{
                        System.out.println("Operação cancelada.");
                    }

                }else{
                    System.out.println("Não existe despesa com o ID informado.");
                }
                break;

            case 4:
                System.out.println("\nVocê selecionou listar despesas!");
                System.out.println("1 - Listar todas as despesas");
                System.out.println("2 - Listar por categoria");
                System.out.println("3 - Listar por ID");
                System.out.print("Digite uma opção para listagem: ");
                int optionList = Integer.parseInt(scan.nextLine());

                if(optionList == 1){
                    System.out.println("Todas as despesas.");
                    List<Despesa> despesasList = dao.findAll();
                    for(Despesa d : despesasList){
                        System.out.println(d);
                        System.out.println("=======================");
                    }
                }else if(optionList == 2){
                    System.out.print("Digite a categoria desejada: ");
                    List<Despesa> despesasList = dao.findByCategoria(Categoria.valueOf(scan.nextLine()));
                    for(Despesa d : despesasList){
                        System.out.println(d);
                        System.out.println("=======================");
                    }
                }else if(optionList == 3){
                    System.out.print("Digite o ID desejado: ");
                    despesaOptional = dao.findById(Long.valueOf(scan.nextLine()));
                    if(despesaOptional.isPresent()) {
                        System.out.print(despesaOptional.get());
                    }else{
                        System.out.println("Não existe despesa com o ID informado.");
                    }
                }
                break;
                default:
                    System.out.println("Opção inválida, digite novamente!");
        }
    }
}
