package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Aluno;
import model.Dia;

public class AlunoParser {
	public static void main(String[] args) throws IOException {
		//Path do arquivo 
		FileInputStream inputStream = new FileInputStream(new File("C://Users//Gustavo//Downloads//planilha.xlsx"));
		XSSFSheet sheet = new XSSFWorkbook(inputStream).getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		
		List<Aluno> alunos = new ArrayList<Aluno>();

		for (int l = 3; l < sheet.getPhysicalNumberOfRows(); l++) {
			row = sheet.getRow(l);
			Aluno aluno = new Aluno();
			
			for (int c = 0; c < row.getPhysicalNumberOfCells(); c++) {
				cell = (XSSFCell) row.getCell(c);
				DataFormatter formater = new DataFormatter();
				String content = formater.formatCellValue(cell);
				
				if (c == 0) {
					aluno.setNome(content);
				} else if (c == 1) {
					aluno.setMatricula(content);
				} else if (c == 2) {
					aluno.setCurso(content);
				} else if (c >= 3 && c <= 7) {
					aluno.getAlmoco().add(content.equals("X"));
				} else {
					aluno.getJantar().add(content.equals("x"));
				}
			}
			
			if (aluno.getNome() != null && !aluno.getNome().equals("")) {
				alunos.add(aluno);
			}
		}
		
		for (Aluno aluno : alunos) {
			System.out.println(aluno.toString());
		}
	}
}
