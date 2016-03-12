package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.edu.ladoss.entity.Aluno;
import br.edu.ladoss.entity.CronogramaRefeicao;
import br.edu.ladoss.entity.Curso;
import br.edu.ladoss.entity.Dia;
import br.edu.ladoss.entity.Refeicao;

public class DataParser {
	public static void main(String[] args) throws IOException {
		// Path da planilha
		FileInputStream inputStream = new FileInputStream(new File("C://Users//Gustavo//Downloads//planilha.xlsx"));

		// L
		XSSFSheet sheet = new XSSFWorkbook(inputStream).getSheetAt(0);

		// Linha da planilha
		XSSFRow row;

		// Coluna da planilha
		XSSFCell cell;

		// Lista de alunos que será extraída da planilha
		List<Aluno> alunos = new ArrayList<Aluno>();

		// Lista de Cronograma Refeições que será extraída da planilha
		List<CronogramaRefeicao> cronogramaRefeicoes = new ArrayList<CronogramaRefeicao>();

		// Iniciamos a partir da linha 3 para pularmos os cabeçalhos
		for (int l = 3; l < sheet.getPhysicalNumberOfRows(); l++) {
			row = sheet.getRow(l);
			Aluno aluno = new Aluno();

			boolean[] almoco = new boolean[5];
			boolean[] jantar = new boolean[5];

			for (int c = 0; c < row.getPhysicalNumberOfCells(); c++) {
				cell = (XSSFCell) row.getCell(c);
				DataFormatter formater = new DataFormatter();
				String content = formater.formatCellValue(cell);

				if (c == 0) {
					aluno.setNome(content);
				} else if (c == 1) {
					aluno.setMatricula(content);
				} else if (c == 2) {
					Curso curso = new Curso();
					curso.setNome(content);
					aluno.setCurso(curso);
				} else if (c >= 3 && c <= 7) {
					almoco[c - 3] = !content.equals("");
				} else {
					jantar[c - 8] = !content.equals("");
				}
			}

			if (aluno.getNome() != null && !aluno.getNome().equals("")) {
				alunos.add(aluno);

				for (int i = 0; i < 5; i++) {
					CronogramaRefeicao cronogramaRefeicao = new CronogramaRefeicao();

					Refeicao refeicao = new Refeicao();
					Dia dia = new Dia();

					dia.setNome(getNomeDia(i));

					if (almoco[i]) {
						refeicao.setTipo("Almoço");

						cronogramaRefeicao.setAluno(aluno);
						cronogramaRefeicao.setDia(dia);
						cronogramaRefeicao.setRefeicao(refeicao);

						cronogramaRefeicoes.add(cronogramaRefeicao);
					}
					if (jantar[i]) {
						refeicao.setTipo("Jantar");

						cronogramaRefeicao.setAluno(aluno);
						cronogramaRefeicao.setDia(dia);
						cronogramaRefeicao.setRefeicao(refeicao);

						cronogramaRefeicoes.add(cronogramaRefeicao);
					}
				}
			}
		}

		// Arranjar uma maneira de chamar o serviço para realizar as operações
		// seguintes

		for (Aluno aluno : alunos) {
			System.out.println(aluno.toString());
			// Esse seria o momento de inserir no BD o aluno
		}

		for (CronogramaRefeicao cronogramaRefeicao : cronogramaRefeicoes) {
			System.out.println(cronogramaRefeicao.toString());
			/*
			 * Antes de tudo devemos encontrar as entidade que compõem essa
			 * entidade cronograma refeicões que est]ao no BD, pois;
			 */

			// Esse seria o momento de inserir no BD o cronograma refeição
		}
	}

	// Retorna o Dia, mas aqui deve haver uma chamada ao serviço para encontrar
	// o Dia que está no BD
	@Deprecated
	private static String getNomeDia(int numeroDia) {
		switch (numeroDia) {
		case 0:
			return "Segunda";
		case 1:
			return "Terça";
		case 2:
			return "Quarta";
		case 3:
			return "Quinta";
		case 4:
			return "Sexta";
		default:
			return "Segunda";
		}
	}
}
