
import java.util.Date;
import java.util.Scanner;

public class Principal {

	public static void main(String args[]) {

		try (Scanner input = new Scanner(System.in)) {
			ContatoDAO contatoDAO = new ContatoDAO();
			Contato contato1 = new Contato();

			String opcao = "";

			do {
				System.out.println("        ==== AGENDA ====");
				System.out.println("Digite uma opção: ");
				System.out.println("[1] - Cadastrar contato");
				System.out.println("[2] - Excluir contato");
				System.out.println("[3] - Pesquisar contato");
				System.out.println("[4] - Exibir todos contatos");
				System.out.println("[5] - Atualizar contato");
				System.out.println("[6] - Encerrar");
				System.out.println("--------------------------------");
				opcao = input.nextLine();

				switch (opcao) {
				case "1":
					System.out.println("==== Cadastro ====");
					System.out.print("Digite o nome: ");
					contato1.setNome(input.nextLine());
					System.out.print("Digite a idade: ");
					int idade = input.nextInt();
					contato1.setIdade(idade);
					contato1.setDataCadastro(new Date());
					contatoDAO.save(contato1);
					
					System.out.println("Contato salvo!");
					break;

				case "2":
					System.out.println("Digite o ID do contato para excluir: ");
					contatoDAO.removeById(input.nextInt());
					System.out.println("Contato removido com sucesso!");
					break;

				case "3":
					System.out.println("Digite o ID do contato para pesquisa: ");
					int id = input.nextInt();
					Contato c = contatoDAO.buscarID(id);
					
					System.out.println("Nome: " + c.getNome());
					System.out.println("Idade: " + c.getIdade());
					System.out.println("Data de cadastro: " + c.getDataCadastro());

					System.out.println("-------------------------------------------------");
					break;

				case "4":
					for (Contato c1 : contatoDAO.getContatos()) {
						System.out.println("Nome: " + c1.getNome());
						System.out.println("Idade: " + c1.getIdade());
						System.out.println("Data de cadastro: " + c1.getDataCadastro());
						System.out.println("-------------------------------------------------");
					}
					break;

				case "5":
					System.out.println("Digite o ID do contato: ");
					int codigo = input.nextInt();
					
					System.out.println("Digite o novo nome: ");
					String nome = input.next();
					contato1.setNome(nome);
					
					System.out.println("Digite a nova idade: ");
					int idade1 = input.nextInt();
					contato1.setIdade(idade1);
					contato1.setDataCadastro(new Date());
					contato1.setID(codigo);
					contatoDAO.update(contato1);
					System.out.println("Dados atualizados!");
				break;
				
				case "6":
					System.out.println("Programa encerrado!");
					break;
				}

			} while (opcao != "6");
		}
		
		
	}

}

//Cria um contato e salva no banco
// Contato contato = new Contato();
// contato.setNome("Caroline");
// contato.setID(1);
// contato.setIdade(27);
// contato.setDataCadastro(new Date());

// contatoDAO.save(contato);
// contatoDAO.removeById(4);
// contatoDAO.update(contato);